package com.example.application.data.entity.product_associated.product;

import com.example.application.data.entity.product_associated.product_category.ProductCategoryEntity;
import com.example.application.data.entity.product_associated.product_series.ProductSeriesEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class ProductEntity {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private ProductCategoryEntity productCategory;
    private String productName;
    private Set<ProductSeriesEntity> productSeries;

    private LocalDateTime creationTime;
    private LocalDateTime modificationTime;
}
