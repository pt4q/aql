package pl.com.pt4q.product_manager.modules.product_parts.data.product_part_attribute;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.com.pt4q.product_manager.modules.product_parts.data.product_part_attribute_version.ProductPartAttributeVersionEntity;
import pl.com.pt4q.product_manager.modules.product_parts.data.product_part.ProductPartEntity;
import pl.com.pt4q.product_manager.modules.product.data.unit.UnitEntity;

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

    @ManyToOne
    private UnitEntity units;

    @OneToOne
    private ProductPartAttributeVersionEntity actualVersion;
}
