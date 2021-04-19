package com.example.application.modules.product.data.product_part;

import com.example.application.modules.product.data.product_picture.ProductPictureEntity;
import com.example.application.modules.product.data.product.ProductEntity;
import com.example.application.modules.product.data.product_manufacturer.ProductManufacturerEntity;
import com.example.application.modules.product.data.product_series.ProductSeriesEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class ProductPartEntity {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne
    private ProductEntity product;
    @ManyToOne
    private ProductSeriesEntity productSeries;

    @ManyToOne
    private ProductManufacturerEntity productManufacturer;
    private String partModel;
    private String partDescription;
   
    private LocalDateTime validFromTime;
    private Boolean currentPart;

    private LocalDateTime modificationTime;

    @OneToOne(fetch = FetchType.LAZY)
    private ProductPictureEntity partPicture;
}
