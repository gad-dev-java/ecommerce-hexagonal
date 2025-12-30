package com.gad.ecommerce.payment.infrastructure.adapters.input.rest.mapper;

import com.gad.ecommerce.payment.application.ports.input.dto.CreatePaymentCommand;
import com.gad.ecommerce.payment.infrastructure.adapters.input.rest.model.request.CreatePaymentRequest;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.WARN)
public interface PaymentRestMapper {
    CreatePaymentCommand toCommand(CreatePaymentRequest request);
}
