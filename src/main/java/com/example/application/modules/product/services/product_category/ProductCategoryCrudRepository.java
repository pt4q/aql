package com.example.application.modules.product.services.product_category;

import com.example.application.modules.product.data.product_category.ProductCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCategoryCrudRepository extends JpaRepository<ProductCategoryEntity, Long> {

}
