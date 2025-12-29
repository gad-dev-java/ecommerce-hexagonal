package com.gad.ecommerce.order.infrastructure.adapters.output.persistence.mapper;

import com.gad.ecommerce.order.domain.model.Order;
import com.gad.ecommerce.order.infrastructure.adapters.output.persistence.entities.OrderEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.WARN,
        uses = {OrderItemMapper.class})
public interface OrderMapper {
    OrderEntity toEntity(Order order);

    @Mapping(target = "items", source = "items")
    Order toModel(OrderEntity orderEntity);

    @AfterMapping
    default void linkItems(@MappingTarget OrderEntity entity) {
        if (entity.getItems() != null) {
            entity.getItems().forEach(item -> item.setOrder(entity));
        }
    }
}
