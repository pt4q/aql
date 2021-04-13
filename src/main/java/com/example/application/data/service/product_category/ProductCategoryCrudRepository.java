package com.example.application.data.service.product_category;

import com.example.application.data.entity.product_associated.product_category.ProductCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCategoryCrudRepository extends JpaRepository<ProductCategoryEntity, Long> {

}
