package pl.com.pt4q.product_manager.modules.product.data.product_category;

import pl.com.pt4q.product_manager.modules.test_card.data.test_card.TestCardEntity;
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
public class ProductCategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String productCategoryName;

//    @OneToMany(fetch = FetchType.LAZY)
//    private Set<ProductEntity> products;

    @OneToOne
    private TestCardEntity testCard;
}
