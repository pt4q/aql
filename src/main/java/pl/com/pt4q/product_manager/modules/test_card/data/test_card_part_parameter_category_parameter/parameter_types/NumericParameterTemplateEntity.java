package pl.com.pt4q.product_manager.modules.test_card.data.test_card_part_parameter_category_parameter.parameter_types;

import pl.com.pt4q.product_manager.modules.test_card.data.test_card_part_parameter_category_parameter.ParameterEntity;
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
