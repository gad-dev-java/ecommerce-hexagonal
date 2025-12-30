package com.gad.ecommerce.order.infrastructure.adapters.input.rest.mapper;

import com.gad.ecommerce.order.application.ports.input.dto.CreateOrderCommand;
import com.gad.ecommerce.order.application.ports.input.dto.OrderDto;
import com.gad.ecommerce.order.infrastructure.adapters.input.rest.model.request.CreateOrderRequest;
import com.gad.ecommerce.order.infrastructure.adapters.input.rest.model.response.OrderSummaryDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.UUID;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.WARN)
public interface OrderRestMapper {
    @Mapping(target = "buyerId", source = "buyerId")
    CreateOrderCommand toCommand(CreateOrderRequest request, UUID buyerId);
    OrderSummaryDto toSummary(OrderDto orderDto);
}
