package com.gad.ecommerce.product.infrastructure.adapters.output.persistence.mapper;

import com.gad.ecommerce.product.domain.model.Product;
import com.gad.ecommerce.product.infrastructure.adapters.output.persistence.entities.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.WARN,
        uses = {CategoryMapper.class})
public interface ProductMapper {

    Product toDomain(ProductEntity entity);
}
