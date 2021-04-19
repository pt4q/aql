package com.example.application.modules.test_card.data.test_card_part_parameter_category_parameter.parameter_types;

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
public class NumericPossibleRequiredValue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private NumericParameterTemplateEntity numericParameterTemplateEntity;

    private Double requiredValue;

    private String unit;

    private Integer points;
}
