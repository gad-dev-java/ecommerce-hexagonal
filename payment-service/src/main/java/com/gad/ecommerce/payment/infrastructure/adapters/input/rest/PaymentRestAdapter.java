package com.gad.ecommerce.payment.infrastructure.adapters.input.rest;

import com.gad.ecommerce.common.security.CurrentUserId;
import com.gad.ecommerce.payment.application.ports.input.CreatePaymentUseCase;
import com.gad.ecommerce.payment.application.ports.input.dto.CreatePaymentCommand;
import com.gad.ecommerce.payment.application.ports.input.dto.PaymentDto;
import com.gad.ecommerce.payment.infrastructure.adapters.input.rest.mapper.PaymentRestMapper;
import com.gad.ecommerce.payment.infrastructure.adapters.input.rest.model.request.CreatePaymentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/payments")
@RequiredArgsConstructor
public class PaymentRestAdapter {
    private final CreatePaymentUseCase createPaymentUseCase;
    private final PaymentRestMapper paymentRestMapper;

    @PostMapping("/create")
    public ResponseEntity<?> createPayment(@RequestBody CreatePaymentRequest request) {
        CreatePaymentCommand command = paymentRestMapper.toCommand(request);
        PaymentDto paymentDto = createPaymentUseCase.createPaymentLink(command, request.title(), request.email());
        return ResponseEntity.ok().body(paymentDto);
    }
}
