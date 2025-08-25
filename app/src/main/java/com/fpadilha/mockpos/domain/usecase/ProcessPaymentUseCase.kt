package com.fpadilha.mockpos.domain.usecase

import kotlinx.coroutines.delay
import kotlin.random.Random

class ProcessPaymentUseCase {
    
    suspend operator fun invoke(amount: String, paymentMethod: String): PaymentResult {
        // Simula tempo de processamento (2-3 segundos)
        val processingTime = Random.nextLong(2000, 3000)
        delay(processingTime)
        
        // Simula resultado aleatório: 80% aprovada, 20% recusada
        val isApproved = Random.nextDouble() < 0.8
        
        return if (isApproved) {
            PaymentResult.Approved(amount, paymentMethod)
        } else {
            PaymentResult.Declined(amount, paymentMethod)
        }
    }
}

sealed class PaymentResult {
    data class Approved(val amount: String, val paymentMethod: String) : PaymentResult()
    data class Declined(val amount: String, val paymentMethod: String) : PaymentResult()
}
