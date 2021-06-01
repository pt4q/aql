package pl.com.pt4q.product_manager.modules.product.data.product_part_attribute;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.com.pt4q.product_manager.modules.product.data.product_part_attribute_value_version.ProductPartAttributeValueVersionEntity;
import pl.com.pt4q.product_manager.modules.product.data.product_part.ProductPartEntity;

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
