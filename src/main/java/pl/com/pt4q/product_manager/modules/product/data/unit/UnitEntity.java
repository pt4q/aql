package pl.com.pt4q.product_manager.modules.product.data.unit;

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
public class UnitEntity {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String name;

    private String unit;

    private Integer multiplicity;

    @Enumerated(EnumType.STRING)
    private UnitTypeEnum type;
}
