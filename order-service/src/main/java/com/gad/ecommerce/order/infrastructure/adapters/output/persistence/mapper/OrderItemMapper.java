package com.gad.ecommerce.order.infrastructure.adapters.output.persistence.mapper;

import com.gad.ecommerce.order.domain.model.OrderItem;
import com.gad.ecommerce.order.infrastructure.adapters.output.persistence.entities.OrderItemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.WARN)
public interface OrderItemMapper {
    @Mapping(target = "order", ignore = true)
    @Mapping(target = "id", ignore = true)
    OrderItemEntity toEntity(OrderItem orderItem);

    OrderItem toDomain(OrderItemEntity orderItemEntity);
}
