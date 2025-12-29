package com.gad.ecommerce.order.infrastructure.adapters.input.rest.mapper;

import com.gad.ecommerce.order.application.ports.input.dto.CreateOrderCommand;
import com.gad.ecommerce.order.application.ports.input.dto.OrderDto;
import com.gad.ecommerce.order.infrastructure.adapters.input.rest.model.request.CreateOrderRequest;
import com.gad.ecommerce.order.infrastructure.adapters.input.rest.model.response.OrderSummaryDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.WARN)
public interface OrderRestMapper {
    CreateOrderCommand toCommand(CreateOrderRequest request);
    OrderSummaryDto toSummary(OrderDto orderDto);
}
