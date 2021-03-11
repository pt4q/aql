package com.example.application.data.entity.test_card;

import com.example.application.data.entity.parameter.TestCardParameterCategoryEntity;
import com.example.application.data.entity.product.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class TestCardEntity {

    @Id
    private Long id;

    private String testCardName;

    private Integer version;

    @ManyToOne
    private ProductEntity product;

    @OneToMany(mappedBy = "testCard")
    private Set<TestCardParameterCategoryEntity> testCardParameterCategories;

}
