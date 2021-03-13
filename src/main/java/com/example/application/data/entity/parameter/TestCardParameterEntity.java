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
public class TestCardParameterEntity {

    @Id
    @GeneratedValue
    private Long id;
    private String parameterName;
    private Integer parameterPoints;

    @ManyToOne
    private TestCardParameterCategoryEntity testCardParameterCategory;

    @OneToOne
    private TestCardParameterBooleanEntity testCardParameterBooleanEntity;
}
