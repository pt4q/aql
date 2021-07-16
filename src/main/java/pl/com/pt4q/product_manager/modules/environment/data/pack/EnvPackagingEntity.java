package pl.com.pt4q.product_manager.modules.environment.data.pack;

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
public class EnvPackagingEntity {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private Double netWeight;
    @ManyToOne
    private UnitEntity netWeightUnit;

    @Enumerated(EnumType.STRING)
    private EnvPackagingMaterialGeneralEnum materialGeneral;

//    private materialDetail;

    @Enumerated(EnumType.STRING)
    private EnvPackagingTypeEnum typeOfPackaging;
}
