package com.gad.ecommerce.order.application.ports.input;

import com.gad.ecommerce.order.application.ports.input.dto.CreateOrderCommand;
import com.gad.ecommerce.order.application.ports.input.dto.OrderDto;

public interface CreateOrderUseCase {
    OrderDto createOrder(CreateOrderCommand command);
}
