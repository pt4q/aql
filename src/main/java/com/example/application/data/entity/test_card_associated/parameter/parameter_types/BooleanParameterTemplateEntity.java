package com.example.application.data.entity.test_card_associated.parameter.parameter_types;

import com.example.application.data.entity.test_card_associated.parameter.ParameterEntity;
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
public class BooleanParameterTemplateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "booleanParameter")
    private ParameterEntity parameter;

    private Boolean requiredValue;
}
