package com.example.application.data.entity.test_card_associated.test_card_part;

import com.example.application.data.entity.test_card_associated.test_card_part_parameter_category.ParameterCategoryEntity;
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
public class TestCardPartEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String testCardPartName;

    @ManyToOne
    private TestCardEntity testCard;

    @OneToMany(mappedBy = "testCardPart", fetch = FetchType.EAGER)
    private Set<ParameterCategoryEntity> testCardParameterCategories;
}
