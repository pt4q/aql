package pl.com.pt4q.product_manager.modules.environment.data.weee;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.com.pt4q.product_manager.modules.product.data.unit.UnitEntity;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class EnvWeeeEntity {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private Double netWeight;
    @ManyToOne
    private UnitEntity netWeightUnit;

    private Double itemHeight;
    private Double itemLength;
    private Double itemDepth;
    @ManyToOne
    private UnitEntity itemLengthUnit;

    @Enumerated(EnumType.STRING)
    private EnvSourceTypeEnum sourceType;
}
