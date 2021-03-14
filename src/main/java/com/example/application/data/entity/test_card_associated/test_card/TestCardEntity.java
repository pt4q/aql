package com.example.application.data.entity.test_card_associated.test_card;

import com.example.application.data.entity.test_card_associated.parameter_category.ParameterCategoryEntity;
import com.example.application.data.entity.product.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class TestCardEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String testCardName;

    private LocalDateTime creationTime;
    private LocalDateTime modificationTime;

    @ManyToOne
    private ProductEntity product;

    @OneToMany(mappedBy = "testCard")
    private Set<ParameterCategoryEntity> testCardParameterCategories;

}
