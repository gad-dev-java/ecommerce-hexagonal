package com.gad.ecommerce.order.application.ports.output;

import com.gad.ecommerce.order.domain.model.Order;

public interface OrderPersistencePort {
    Order saveOrder(Order order);
}
