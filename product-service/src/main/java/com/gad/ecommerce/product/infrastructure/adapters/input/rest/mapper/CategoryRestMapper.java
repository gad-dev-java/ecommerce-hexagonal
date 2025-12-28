package com.gad.ecommerce.product.infrastructure.adapters.input.rest.mapper;

import com.gad.ecommerce.product.domain.model.Category;
import com.gad.ecommerce.product.infrastructure.adapters.input.rest.model.dto.CategorySummaryDto;
import com.gad.ecommerce.product.infrastructure.adapters.input.rest.model.dto.ParentSummaryDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.WARN)
public interface CategoryRestMapper {
    CategorySummaryDto toSummary(Category category);

    ParentSummaryDto toParentSummary(Category category);
}
