package com.example.application.modules.test_card.data.test_card_part_parameter_category_parameter.parameter_types;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class NumericUnitEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // moc

    private String unit; // [kW] = n*1000[W]

    private Integer multiplier; //1=1*10^0, 10=1*10^1, 100=1*10^2 etc
}
