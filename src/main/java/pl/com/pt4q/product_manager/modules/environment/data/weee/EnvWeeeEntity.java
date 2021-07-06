package pl.com.pt4q.product_manager.modules.environment.data.weee;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.com.pt4q.product_manager.modules.environment.data.data_utils.EnvSourceTypeEnum;
import pl.com.pt4q.product_manager.modules.environment.data.master.EnvMasterEntity;
import pl.com.pt4q.product_manager.modules.product.data.unit.UnitEntity;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class EnvWeeeEntity {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

//    @OneToOne
//    private EnvMasterEntity master;

//    private LocalDate validFrom;
//    private LocalDate validTo;

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

    private LocalDateTime creationDate;
    private LocalDateTime modificationDate;
}
