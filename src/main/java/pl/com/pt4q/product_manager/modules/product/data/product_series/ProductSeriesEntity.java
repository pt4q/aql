package pl.com.pt4q.product_manager.modules.product.data.product_series;

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
public class ProductSeriesEntity {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String series;
    private LocalDateTime modificationTime;

//    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "productSeries")
//    private Set<ProductEntity> products;
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "productSeries")
//    private List<ProductPartEntity> parts;
}
