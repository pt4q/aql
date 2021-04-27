package pl.com.pt4q.product_manager.modules.product.data.product_part;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.com.pt4q.product_manager.modules.product.data.product_category.ProductCategoryEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
class ProductPartAttributeEntity {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne
    private ProductCategoryEntity productCategory;



    private String attributeName;
    private String attributeValue;
}
