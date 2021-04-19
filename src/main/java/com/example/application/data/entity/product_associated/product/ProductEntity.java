package com.example.application.data.entity.product_associated.product;

import com.example.application.data.entity.product_associated.product_picture.ProductPictureEntity;
import com.example.application.data.entity.product_associated.product_category.ProductCategoryEntity;
import com.example.application.data.entity.product_associated.product_manufacturer.ProductManufacturerEntity;
import com.example.application.data.entity.product_associated.product_part.ProductPartEntity;
import com.example.application.data.entity.product_associated.product_series.ProductSeriesEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @ManyToOne
    private ProductCategoryEntity productCategory;
    @ManyToOne
    private ProductManufacturerEntity productManufacturer;
    private String productName;
    @OneToMany
    private Set<ProductSeriesEntity> productSeries;
    @OneToMany
    private List<ProductPartEntity> productParts;

    private LocalDateTime creationTime;
    private LocalDateTime modificationTime;

    @OneToOne(fetch = FetchType.LAZY)
    private ProductPictureEntity productPicture;
}
