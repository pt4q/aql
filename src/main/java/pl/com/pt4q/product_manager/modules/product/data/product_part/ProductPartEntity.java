package pl.com.pt4q.product_manager.modules.product.data.product_part;

import pl.com.pt4q.product_manager.modules.product.data.product_picture.ProductPictureEntity;
import pl.com.pt4q.product_manager.modules.product.data.product.ProductEntity;
import pl.com.pt4q.product_manager.modules.product.data.manufacturer.ManufacturerEntity;
import pl.com.pt4q.product_manager.modules.product.data.product_series.ProductSeriesEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

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
    @ManyToOne(fetch = FetchType.EAGER)
    private ProductSeriesEntity productSeries;
//    @ManyToOne
//    private ManufacturerEntity partManufacturer;

    private String partModelOrPartName;
    private String partDescription;
    private LocalDate validFromDate;

    private Boolean activePart;

    @ManyToMany
    private Set<ProductPartAttributeEntity> partAttributes;

    @OneToOne(fetch = FetchType.LAZY)
    private ProductPictureEntity partPicture;

    private LocalDateTime modificationTime;
}