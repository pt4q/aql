package com.example.application.data.entity.test_card;

import com.example.application.data.entity.parameter.TestCardParameterCategoryTemplateEntity;
import com.example.application.data.entity.product.ProductEntity;
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
public class TestCardEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String testCardName;

    private Integer version;

    @ManyToOne
    private ProductEntity product;

    @OneToMany(mappedBy = "testCard")
    private Set<TestCardParameterCategoryTemplateEntity> testCardParameterCategories;

}
