package com.example.application.modules.product.data.product_part;

import com.example.application.modules.product.data.product.ProductEntity;
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
    private LocalDateTime modificationTime;
    private Boolean actualPart;
    private String partDescription;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] partPicture;
}
