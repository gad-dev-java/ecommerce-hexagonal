package com.gad.ecommerce.payment.application.service;

import com.gad.ecommerce.payment.application.ports.input.CreatePaymentUseCase;
import com.gad.ecommerce.payment.application.ports.input.dto.CreatePaymentCommand;
import com.gad.ecommerce.payment.application.ports.input.dto.PaymentDto;
import com.gad.ecommerce.payment.application.ports.output.PaymentGatewayPort;
import com.gad.ecommerce.payment.application.ports.output.PaymentRepositoryPort;
import com.gad.ecommerce.payment.domain.enums.PaymentStatus;
import com.gad.ecommerce.payment.domain.model.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentService implements CreatePaymentUseCase {
    private final PaymentRepositoryPort paymentRepositoryPort;
    private final PaymentGatewayPort paymentGatewayPort;

    @Override
    @Transactional
    public PaymentDto createPaymentLink(CreatePaymentCommand command, String title, String email) {
        Payment payment = new Payment();
        payment.setStatus(PaymentStatus.PENDING);
        payment.setOrderId(command.orderId());
        payment.setAmount(command.amount());
        payment.setUserId(UUID.randomUUID());
        payment.setPaymentMethod(command.paymentMethod());
        payment.setStatus(PaymentStatus.PENDING);

        Payment savedPayment = paymentRepositoryPort.savePayment(payment);

        Payment paymentWithUrl = paymentGatewayPort.generatePaymentPreference(savedPayment, title, email);
        Payment paymentFinal = paymentRepositoryPort.savePayment(paymentWithUrl);

        return toDto(paymentFinal);
    }

    private PaymentDto toDto(Payment payment) {
        return PaymentDto.builder()
                .id(payment.getId())
                .orderId(payment.getOrderId())
                .userId(payment.getUserId())
                .amount(payment.getAmount())
                .paymentMethod(payment.getPaymentMethod())
                .status(payment.getStatus())
                .transactionId(payment.getTransactionId())
                .paymentUrl(payment.getPaymentUrl())
                .createdAt(payment.getCreatedAt())
                .build();
    }
}
