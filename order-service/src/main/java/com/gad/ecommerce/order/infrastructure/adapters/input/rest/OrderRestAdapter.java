package com.gad.ecommerce.order.infrastructure.adapters.input.rest;

import com.gad.ecommerce.order.application.ports.input.CreateOrderUseCase;
import com.gad.ecommerce.order.application.ports.input.dto.OrderDto;
import com.gad.ecommerce.order.infrastructure.adapters.input.rest.mapper.OrderRestMapper;
import com.gad.ecommerce.order.infrastructure.adapters.input.rest.model.request.CreateOrderRequest;
import com.gad.ecommerce.order.infrastructure.adapters.input.rest.model.response.DataResponse;
import com.gad.ecommerce.order.infrastructure.adapters.input.rest.model.response.OrderSummaryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderRestAdapter {
    private final CreateOrderUseCase createOrderUseCase;
    private final OrderRestMapper orderRestMapper;

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody CreateOrderRequest request) {
        OrderDto orderDto = createOrderUseCase.createOrder(orderRestMapper.toCommand(request));
        OrderSummaryDto orderSummary = orderRestMapper.toSummary(orderDto);

        URI location = URI.create("/orders/" + orderSummary.id());

        DataResponse<OrderSummaryDto> dataResponse = DataResponse.<OrderSummaryDto>builder()
                .status(HttpStatus.OK.value())
                .message("Order created successfully")
                .data(orderSummary)
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.created(location).body(dataResponse);
    }
}
