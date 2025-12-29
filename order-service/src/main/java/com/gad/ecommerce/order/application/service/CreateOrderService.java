package com.gad.ecommerce.order.application.service;

import com.gad.ecommerce.order.application.ports.input.CreateOrderUseCase;
import com.gad.ecommerce.order.application.ports.input.dto.CreateOrderCommand;
import com.gad.ecommerce.order.application.ports.input.dto.OrderDto;
import com.gad.ecommerce.order.application.ports.input.dto.OrderItemCommand;
import com.gad.ecommerce.order.application.ports.output.OrderPersistencePort;
import com.gad.ecommerce.order.application.ports.output.ProductExternalPort;
import com.gad.ecommerce.order.domain.exception.InvalidOrderException;
import com.gad.ecommerce.order.domain.model.Order;
import com.gad.ecommerce.order.domain.model.OrderItem;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Slf4j
public class CreateOrderService implements CreateOrderUseCase {
    private final OrderPersistencePort orderPersistencePort;
    private final ProductExternalPort productExternalPort;

    @Override
    @Transactional
    public OrderDto createOrder(CreateOrderCommand command) {
        log.info("Command received: {}", command);
        Order order = new Order(command.buyerId(), command.sellerId(), command.shippingAddress());
        log.info("Order to create: {}", order);

        for (OrderItemCommand itemCommand : command.items()) {
            var productSummary = productExternalPort.getProductSummary(itemCommand.productId());

            if (productSummary.stock() < itemCommand.quantity()) {
                throw new InvalidOrderException("Invalid quantity");
            }

            productExternalPort.reserveStock(itemCommand.productId(), itemCommand.quantity());

            OrderItem orderItem = new OrderItem(itemCommand.productId(), productSummary.title(), itemCommand.quantity(), productSummary.price());
            log.info("Adding order item: {}", orderItem);
            order.addItem(orderItem);
        }

        Order orderSaved = orderPersistencePort.saveOrder(order);

        return toDto(orderSaved);
    }

    private OrderDto toDto(Order order) {
        return OrderDto.builder()
                .id(order.getId())
                .orderNumber(order.getOrderNumber())
                .buyerId(order.getBuyerId())
                .sellerId(order.getSellerId())
                .totalAmount(order.getTotalAmount())
                .shippingCost(order.getShippingCost())
                .status(order.getStatus())
                .shippingAddress(order.getShippingAddress())
                .items(order.getItems())
                .build();
    }
}
