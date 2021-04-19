package pl.com.pt4q.product_manager.modules.test_card.data.test_card_part_parameter_category_parameter;


import pl.com.pt4q.product_manager.modules.test_card.data.test_card_part_parameter_category.ParameterCategoryEntity;
import pl.com.pt4q.product_manager.modules.test_card.data.test_card_part_parameter_category_parameter.parameter_types.AlphanumericParameterTemplateEntity;
import pl.com.pt4q.product_manager.modules.test_card.data.test_card_part_parameter_category_parameter.parameter_types.BooleanParameterTemplateEntity;
import pl.com.pt4q.product_manager.modules.test_card.data.test_card_part_parameter_category_parameter.parameter_types.NumericParameterTemplateEntity;
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
    @OneToOne
    private NumericParameterTemplateEntity numericParameter;
    @OneToOne
    private AlphanumericParameterTemplateEntity alphanumericParameter;
}
