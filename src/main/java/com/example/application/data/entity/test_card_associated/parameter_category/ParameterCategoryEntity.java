package com.example.application.data.entity.test_card_associated.parameter_category;

import com.example.application.data.entity.test_card_associated.parameter.ParameterEntity;
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
public class ParameterCategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String parameterCategoryName;

    private Integer parameterCategoryPoints;

    @ManyToOne
    private TestCardEntity testCard;

    @OneToMany(mappedBy = "parameterCategory", fetch = FetchType.EAGER)
    private Set<ParameterEntity> parameters;
}
