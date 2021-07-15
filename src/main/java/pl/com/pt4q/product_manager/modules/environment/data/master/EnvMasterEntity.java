package pl.com.pt4q.product_manager.modules.environment.data.master;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.com.pt4q.product_manager.modules.environment.data.bat.EnvBatteryEntity;
import pl.com.pt4q.product_manager.modules.environment.data.light_source.EnvLightSourceEntity;
import pl.com.pt4q.product_manager.modules.environment.data.pack.EnvPackagingEntity;
import pl.com.pt4q.product_manager.modules.environment.data.weee.EnvWeeeEntity;
import pl.com.pt4q.product_manager.modules.product.data.product.ProductEntity;
import pl.com.pt4q.product_manager.modules.product.data.unit.UnitEntity;

import javax.persistence.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class EnvMasterEntity {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @OneToOne
    private ProductEntity product;

    private LocalDate validFrom;

    private LocalDate validTo;

    private Double grossWeight;

    @ManyToOne
    private UnitEntity grossWeightUnit;

    @OneToOne
    private EnvWeeeEntity weee;

    @OneToOne(mappedBy = "master")
    private EnvLightSourceEntity lightSource;

    @OneToOne(mappedBy = "master")
    private EnvBatteryEntity battery;

    @OneToOne
    private EnvPackagingEntity packaging;

//    private boolean copyright;


}
