package com.fpadilha.mockpos.ui.payments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fpadilha.mockpos.BuildConfig
import com.fpadilha.mockpos.domain.usecase.ProcessPaymentUseCase
import com.fpadilha.mockpos.domain.usecase.PaymentResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PaymentsViewModel : ViewModel() {
    
    private val processPaymentUseCase = ProcessPaymentUseCase()
    
    private val _uiState = MutableStateFlow(PaymentsUiState())
    val uiState: StateFlow<PaymentsUiState> = _uiState.asStateFlow()
    
    val appName: String
        get() = BuildConfig.FLAVOR + " BANK"
    
    fun processPayment(amount: String, paymentMethod: String) {
        if (amount.isEmpty() || paymentMethod.isEmpty()) return
        
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                isLoading = true,
                showResult = false
            )
            
            try {
                val result = processPaymentUseCase(amount, paymentMethod)
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    showResult = true,
                    paymentResult = result
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = e.message ?: "Erro desconhecido"
                )
            }
        }
    }
    
    fun startNewSale() {
        _uiState.value = PaymentsUiState()
    }
    
    fun clearError() {
        _uiState.value = _uiState.value.copy(error = null)
    }
}

data class PaymentsUiState(
    val isLoading: Boolean = false,
    val showResult: Boolean = false,
    val paymentResult: PaymentResult? = null,
    val error: String? = null
)
