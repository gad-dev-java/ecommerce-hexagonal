package com.gad.ecommerce.payment.application.ports.output;

import com.gad.ecommerce.payment.domain.model.Payment;

public interface PaymentRepositoryPort {
    Payment savePayment(Payment payment);
}
