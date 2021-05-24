package pl.com.pt4q.product_manager.modules.product.data.product_part;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class ProductPartAttributeEntity {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne
    private ProductPartEntity part;

    private String attributeName;
    @OneToOne
    private ProductPartAttributeValueVersionEntity actualValueVersion;
}
