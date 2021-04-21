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
    private ManufacturerEntity manufacturer;
    private String partModel;
    private String partDescription;
    private LocalDate validFromDate;

    private Boolean currentPart;

    private LocalDateTime modificationTime;

    @OneToOne(fetch = FetchType.LAZY)
    private ProductPictureEntity partPicture;
}