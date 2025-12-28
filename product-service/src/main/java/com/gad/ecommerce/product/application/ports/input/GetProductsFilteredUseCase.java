package com.gad.ecommerce.product.application.ports.input;

import com.gad.ecommerce.product.application.ports.input.dto.PaginatedResponse;
import com.gad.ecommerce.product.application.ports.input.dto.PaginationFilter;
import com.gad.ecommerce.product.application.ports.input.dto.ProductDto;
import com.gad.ecommerce.product.application.ports.input.dto.ProductFilterParams;

public interface GetProductsFilteredUseCase {
    PaginatedResponse<ProductDto> getProductListFilteredAndPaginated(PaginationFilter paginationFilter, ProductFilterParams filterParams);
}
