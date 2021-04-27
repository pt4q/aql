package pl.com.pt4q.product_manager.modules.product.data.product;

import pl.com.pt4q.product_manager.modules.product.data.product_picture.ProductPictureEntity;
import pl.com.pt4q.product_manager.modules.product.data.product_category.ProductCategoryEntity;
import pl.com.pt4q.product_manager.modules.product.data.manufacturer.ManufacturerEntity;
import pl.com.pt4q.product_manager.modules.product.data.product_part.ProductPartEntity;
import pl.com.pt4q.product_manager.modules.product.data.product_series.ProductSeriesEntity;
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
    private ManufacturerEntity manufacturer;
    private String productSku;
//    @ManyToMany
//    private Set<ProductSeriesEntity> productSeries;
//    @OneToMany (fetch = FetchType.LAZY, mappedBy = "product")
//    private List<ProductPartEntity> productParts;

    private LocalDateTime creationTime;
    private LocalDateTime modificationTime;

    @OneToOne(fetch = FetchType.LAZY)
    private ProductPictureEntity productPicture;
}