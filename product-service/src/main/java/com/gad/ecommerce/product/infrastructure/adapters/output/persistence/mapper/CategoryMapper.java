package com.gad.ecommerce.product.infrastructure.adapters.output.persistence.mapper;

import com.gad.ecommerce.product.domain.model.Category;
import com.gad.ecommerce.product.infrastructure.adapters.output.persistence.entities.CategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.WARN)
public interface CategoryMapper {
    Category toDomain(CategoryEntity entity);
}
