package com.gad.ecommerce.payment.application.ports.output;

import com.gad.ecommerce.payment.domain.model.Payment;

public interface PaymentGatewayPort {
    Payment generatePaymentPreference(Payment payment, String title, String email);
}
