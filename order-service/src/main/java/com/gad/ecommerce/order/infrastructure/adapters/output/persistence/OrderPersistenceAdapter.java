package com.gad.ecommerce.order.infrastructure.adapters.output.persistence;

import com.gad.ecommerce.order.application.ports.output.OrderPersistencePort;
import com.gad.ecommerce.order.domain.model.Order;
import com.gad.ecommerce.order.infrastructure.adapters.output.persistence.entities.OrderEntity;
import com.gad.ecommerce.order.infrastructure.adapters.output.persistence.mapper.OrderMapper;
import com.gad.ecommerce.order.infrastructure.adapters.output.persistence.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderPersistenceAdapter implements OrderPersistencePort {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Override
    public Order saveOrder(Order order) {
        OrderEntity entity = orderMapper.toEntity(order);
        OrderEntity orderSaved = orderRepository.save(entity);
        return orderMapper.toModel(orderSaved);
    }
}
