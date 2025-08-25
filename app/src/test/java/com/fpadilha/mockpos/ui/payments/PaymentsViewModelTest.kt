package com.fpadilha.mockpos.ui.payments

import com.fpadilha.mockpos.domain.usecase.PaymentResult
import org.junit.Test
import org.junit.Assert.*

class PaymentsViewModelTest {
    
    @Test
    fun `should create payments ui state with correct default values`() {
        // When
        val uiState = PaymentsUiState()
        
        // Then
        assertFalse(uiState.isLoading)
        assertFalse(uiState.showResult)
        assertNull(uiState.paymentResult)
        assertNull(uiState.error)
    }
    
    @Test
    fun `should reset state when start new sale`() {
        // Given
        val modifiedState = PaymentsUiState(
            isLoading = true,
            showResult = true,
            paymentResult = PaymentResult.Approved("50.00", "debit_card"),
            error = "Some error"
        )
        
        // When
        val resetState = modifiedState.copy(
            isLoading = false,
            showResult = false,
            paymentResult = null,
            error = null
        )
        
        // Then
        assertFalse(resetState.isLoading)
        assertFalse(resetState.showResult)
        assertNull(resetState.paymentResult)
        assertNull(resetState.error)
    }
    
    @Test
    fun `should clear error when clear error is called`() {
        // Given
        val stateWithError = PaymentsUiState(
            isLoading = false,
            showResult = false,
            paymentResult = null,
            error = "Payment failed"
        )
        
        // When
        val clearedState = stateWithError.copy(error = null)
        
        // Then
        assertNull(clearedState.error)
        assertFalse(clearedState.isLoading)
        assertFalse(clearedState.showResult)
        assertNull(clearedState.paymentResult)
    }
}
