package pl.com.pt4q.product_manager.modules.environment.data.pack;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.com.pt4q.product_manager.modules.environment.data.master.EnvMasterEntity;
import pl.com.pt4q.product_manager.modules.environment.data.material_associated.group_of_material.EnvMaterialGroupEntity;
import pl.com.pt4q.product_manager.modules.environment.data.material_associated.material.EnvMaterialEntity;
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

    @ManyToOne
    private EnvMasterEntity master;

    private Double netWeight;
    @ManyToOne
    private UnitEntity netWeightUnit;

    @ManyToOne
    private EnvMaterialGroupEntity materialGeneral;

    @ManyToOne
    private EnvMaterialEntity materialDetail;

    @Enumerated(EnumType.STRING)
    private EnvPackagingTypeEnum typeOfPackaging;
}
