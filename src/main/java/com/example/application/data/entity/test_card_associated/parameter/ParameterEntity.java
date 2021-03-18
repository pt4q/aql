package com.example.application.data.entity.test_card_associated.parameter;


import com.example.application.data.entity.test_card_associated.parameter.parameter_types.BooleanParameterTemplateEntity;
import com.example.application.data.entity.test_card_associated.parameter_category.ParameterCategoryEntity;
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
public class ParameterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String parameterName;
    private Integer parameterPoints;

    @ManyToOne
    private ParameterCategoryEntity parameterCategory;

    @OneToOne
    private BooleanParameterTemplateEntity booleanParameter;
}
