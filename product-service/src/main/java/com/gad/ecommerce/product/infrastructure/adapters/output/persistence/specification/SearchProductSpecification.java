package com.gad.ecommerce.product.infrastructure.adapters.output.persistence.specification;

import com.gad.ecommerce.product.infrastructure.adapters.output.persistence.entities.CategoryEntity;
import com.gad.ecommerce.product.infrastructure.adapters.output.persistence.entities.ProductEntity;
import jakarta.persistence.criteria.*;
import lombok.AllArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class SearchProductSpecification implements Specification<ProductEntity> {
    private String title;
    private String description;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private String currency;
    private String category;
    private String conditionType;
    private String status;

    @Override
    public @Nullable Predicate toPredicate(Root<ProductEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        if (StringUtils.hasText(title)) {
            Expression<String> titleToLowerCase = criteriaBuilder.lower(root.get("title"));
            Predicate titleLikePredicate = criteriaBuilder.like(titleToLowerCase, "%".concat(title.toLowerCase().concat("%")));
            predicates.add(titleLikePredicate);
        }

        if (StringUtils.hasText(description)) {
            Expression<String> descriptionToLowerCase = criteriaBuilder.lower(root.get("description"));
            Predicate descriptionLikePredicate = criteriaBuilder.like(descriptionToLowerCase, "%".concat(description.toLowerCase().concat("%")));
            predicates.add(descriptionLikePredicate);
        }

        if (minPrice != null && !minPrice.equals(BigDecimal.ZERO)) {
            Predicate priceGreaterThanEqualPredicate = criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice);
            predicates.add(priceGreaterThanEqualPredicate);
        }

        if (maxPrice != null && !maxPrice.equals(BigDecimal.ZERO)) {
            Predicate priceLessThanEqualPredicate = criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice);
            predicates.add(priceLessThanEqualPredicate);
        }
        if (StringUtils.hasText(currency)) {
            Predicate currencyEqualPredicate = criteriaBuilder.equal(root.get("currency").as(String.class), currency.toUpperCase());
            predicates.add(currencyEqualPredicate);
        }

        if (StringUtils.hasText(category)) {
            Join<ProductEntity, CategoryEntity> productCategoryJoin = root.join("category");
            Expression<String> categoryNameToLowerCase = criteriaBuilder.lower(productCategoryJoin.get("name"));
            Predicate categoryNameLikePredicate = criteriaBuilder.like(categoryNameToLowerCase, "%".concat(category.toLowerCase().concat("%")));
            predicates.add(categoryNameLikePredicate);
        }

        if (StringUtils.hasText(conditionType)) {
            Predicate conditionEqualPredicate = criteriaBuilder.equal(root.get("conditionType").as(String.class), conditionType.toUpperCase());
            predicates.add(conditionEqualPredicate);
        }

        if (StringUtils.hasText(status)) {
            Predicate statusEqualPredicate = criteriaBuilder.equal(root.get("status").as(String.class), status.toUpperCase());
            predicates.add(statusEqualPredicate);
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
    }
}
