package com.example.application.data.service.product;

import com.example.application.data.entity.product.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCrudRepository extends JpaRepository<ProductEntity, Long> {



}
