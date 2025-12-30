package com.gad.ecommerce.payment.application.ports.input;

import com.gad.ecommerce.payment.application.ports.input.dto.CreatePaymentCommand;
import com.gad.ecommerce.payment.application.ports.input.dto.PaymentDto;

public interface CreatePaymentUseCase {
    PaymentDto createPaymentLink(CreatePaymentCommand command, String title, String email);
}
