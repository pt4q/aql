package com.example.application.data.entity.parameter;

import com.example.application.data.entity.test_card.TestCardEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class TestCardParameterCategoryEntity {

    @Id
    private Long id;

    private String parameterCategoryName;

    private Integer parameterCategoryPoints;

    @ManyToOne
    private TestCardEntity testCard;

    @OneToMany(mappedBy = "testCardParameterCategory")
    private Set<TestCardParameterEntity> parameters;
}
