package pl.com.pt4q.product_manager.modules.test_card.data.test_card_part_parameter_category_parameter.parameter_types;

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
public class AlphanumericPossibleRequiredValue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private AlphanumericParameterTemplateEntity alphanumericParameterTemplateEntity;

    private String requiredValue;

    private Integer points;
}
