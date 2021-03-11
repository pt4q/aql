package com.example.application.data.entity.parameter;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class TestCardParameterEntity {

    @Id
    private Long id;
    private String parameterName;
    private Integer parameterPoints;

    @ManyToOne
    private TestCardParameterCategoryEntity testCardParameterCategory;

    @OneToOne
    private TestCardParameterBooleanEntity testCardParameterBooleanEntity;
}
