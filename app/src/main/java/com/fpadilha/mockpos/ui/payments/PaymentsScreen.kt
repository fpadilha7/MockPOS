package com.fpadilha.mockpos.ui.payments

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.fpadilha.mockpos.R
import com.fpadilha.mockpos.domain.usecase.PaymentResult

@Composable
fun PaymentsScreen(
    viewModel: PaymentsViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    
    var transactionValue by remember { mutableStateOf("") }
    var selectedPaymentMethod by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        
        Text(
            text = viewModel.appName.uppercase(),
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.primary
        )
        
        Spacer(modifier = Modifier.height(2.dp))
        
        Text(
            text = stringResource(R.string.payment_title),
            style = MaterialTheme.typography.headlineLarge
        )
        
        OutlinedTextField(
            value = transactionValue,
            onValueChange = { transactionValue = it },
            label = { Text(stringResource(R.string.transaction_value_label)) },
            placeholder = { Text(stringResource(R.string.transaction_value_placeholder)) },
            modifier = Modifier.fillMaxWidth(),
            maxLines = 1,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            enabled = !uiState.isLoading
        )
        
        Text(
            text = stringResource(R.string.payment_method_label),
            style = MaterialTheme.typography.titleMedium
        )
        
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            val creditText = stringResource(R.string.credit_button)
            val debitText = stringResource(R.string.debit_button)
            
            Button(
                onClick = { selectedPaymentMethod = creditText },
                modifier = Modifier.weight(1f),
                enabled = !uiState.isLoading,
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (selectedPaymentMethod == creditText) 
                        MaterialTheme.colorScheme.primary 
                    else 
                        MaterialTheme.colorScheme.secondary
                )
            ) {
                Text(creditText)
            }
            
            Button(
                onClick = { selectedPaymentMethod = debitText },
                modifier = Modifier.weight(1f),
                enabled = !uiState.isLoading,
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (selectedPaymentMethod == debitText) 
                        MaterialTheme.colorScheme.primary 
                    else 
                        MaterialTheme.colorScheme.secondary
                )
            ) {
                Text(debitText)
            }
        }
        
        Button(
            onClick = { 
                viewModel.processPayment(transactionValue, selectedPaymentMethod)
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = transactionValue.isNotEmpty() && selectedPaymentMethod.isNotEmpty() && !uiState.isLoading
        ) {
            if (uiState.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(20.dp),
                    color = MaterialTheme.colorScheme.onPrimary
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(stringResource(R.string.processing_text))
            } else {
                Text(stringResource(R.string.process_payment_button))
            }
        }
    }
    
    if (uiState.showResult && uiState.paymentResult != null) {
        PaymentResultDialog(
            result = uiState.paymentResult!!,
            onStartNewSale = {
                viewModel.startNewSale()
                transactionValue = ""
                selectedPaymentMethod = ""
            }
        )
    }
    
    if (uiState.error != null) {
        AlertDialog(
            onDismissRequest = { viewModel.clearError() },
            title = { Text(stringResource(R.string.error_title)) },
            text = { Text(uiState.error!!) },
            confirmButton = {
                TextButton(onClick = { viewModel.clearError() }) {
                    Text(stringResource(R.string.ok_button))
                }
            }
        )
    }
}

@Composable
fun PaymentResultDialog(
    result: PaymentResult,
    onStartNewSale: () -> Unit
) {
    Dialog(onDismissRequest = { }) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = when (result) {
                        is PaymentResult.Approved -> stringResource(R.string.approved_text)
                        is PaymentResult.Declined -> stringResource(R.string.declined_text)
                    },
                    style = MaterialTheme.typography.headlineMedium,
                    color = when (result) {
                        is PaymentResult.Approved -> MaterialTheme.colorScheme.primary
                        is PaymentResult.Declined -> MaterialTheme.colorScheme.error
                    }
                )
                
                Text(
                    text = stringResource(R.string.amount_label, when (result) {
                        is PaymentResult.Approved -> result.amount
                        is PaymentResult.Declined -> result.amount
                    }),
                    style = MaterialTheme.typography.bodyLarge
                )
                
                Text(
                    text = stringResource(R.string.method_label, when (result) {
                        is PaymentResult.Approved -> result.paymentMethod
                        is PaymentResult.Declined -> result.paymentMethod
                    }),
                    style = MaterialTheme.typography.bodyMedium
                )
                
                Button(
                    onClick = onStartNewSale,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(stringResource(R.string.start_new_sale_button))
                }
            }
        }
    }
}
