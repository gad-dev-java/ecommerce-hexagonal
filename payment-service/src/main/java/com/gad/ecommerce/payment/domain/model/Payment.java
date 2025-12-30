package com.gad.ecommerce.payment.domain.model;

import com.gad.ecommerce.payment.domain.enums.PaymentMethod;
import com.gad.ecommerce.payment.domain.enums.PaymentStatus;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
    private UUID id;
    private UUID orderId;
    private UUID userId;
    private BigDecimal amount;
    private PaymentMethod paymentMethod;
    private PaymentStatus status;
    private String transactionId;
    private String paymentUrl;
    private LocalDateTime createdAt;
}
