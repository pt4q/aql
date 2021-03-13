package com.example.application.data.entity.parameter;


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
public class TestCardParameterTemplateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String parameterName;
    private Integer parameterPoints;

    @ManyToOne
    private TestCardParameterCategoryTemplateEntity parameterCategory;

    @OneToOne
    private TestCardParameterBooleanTemplateEntity booleanParameter;
}
