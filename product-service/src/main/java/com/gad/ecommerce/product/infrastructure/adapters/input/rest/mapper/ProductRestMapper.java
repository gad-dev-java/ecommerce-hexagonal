package com.gad.ecommerce.product.infrastructure.adapters.input.rest.mapper;

import com.gad.ecommerce.product.application.ports.input.dto.ProductDto;
import com.gad.ecommerce.product.infrastructure.adapters.input.rest.model.dto.ProductSummaryDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.WARN,
        uses = {CategoryRestMapper.class})
public interface ProductRestMapper {

    ProductSummaryDto toSummary(ProductDto productDto);
}
