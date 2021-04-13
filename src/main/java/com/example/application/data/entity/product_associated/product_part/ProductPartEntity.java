package com.example.application.data.entity.product_associated.product_part;

import com.example.application.data.entity.product_associated.product.ProductEntity;
import com.example.application.data.entity.product_associated.product_series.ProductSeriesEntity;
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

    private ProductEntity product;
    private ProductSeriesEntity productSeries;
    private LocalDateTime modificationTime;
    private Boolean actualPart;
    private String partDescription;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] partPicture;
}
