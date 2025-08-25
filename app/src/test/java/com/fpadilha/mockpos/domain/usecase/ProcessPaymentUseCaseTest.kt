package com.fpadilha.mockpos.domain.usecase

import org.junit.Test
import org.junit.Assert.*

class ProcessPaymentUseCaseTest {
    
    @Test
    fun `should create use case instance`() {
        // When
        val useCase = ProcessPaymentUseCase()
        
        // Then
        assertNotNull(useCase)
        assertTrue(useCase is ProcessPaymentUseCase)
    }
    
    @Test
    fun `should have correct payment result structure`() {
        // Given
        val amount = "100.00"
        val paymentMethod = "credit_card"
        
        // When
        val approvedResult = PaymentResult.Approved(amount, paymentMethod)
        val declinedResult = PaymentResult.Declined(amount, paymentMethod)
        
        // Then
        assertTrue(approvedResult is PaymentResult.Approved)
        assertTrue(declinedResult is PaymentResult.Declined)
        assertEquals(amount, approvedResult.amount)
        assertEquals(paymentMethod, approvedResult.paymentMethod)
        assertEquals(amount, declinedResult.amount)
        assertEquals(paymentMethod, declinedResult.paymentMethod)
    }
}
