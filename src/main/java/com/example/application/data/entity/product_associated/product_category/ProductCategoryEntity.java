package com.example.application.data.entity.product_associated.product_category;

import com.example.application.data.entity.product_associated.product.ProductEntity;
import com.example.application.data.entity.test_card_associated.test_card.TestCardEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

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
