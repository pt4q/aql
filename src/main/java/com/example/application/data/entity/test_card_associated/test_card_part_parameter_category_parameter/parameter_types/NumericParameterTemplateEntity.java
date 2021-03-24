package com.example.application.data.entity.test_card_associated.test_card_part_parameter_category_parameter.parameter_types;

import com.example.application.data.entity.test_card_associated.test_card_part_parameter_category_parameter.ParameterEntity;
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
public class NumericParameterTemplateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "numericParameter")
    private ParameterEntity parameter;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<NumericPossibleRequiredValue> numericPossibleRequiredValues;
}
