package pl.com.pt4q.product_manager.modules.environment.data.material_associated.material;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.com.pt4q.product_manager.modules.environment.data.material_associated.group_of_material.EnvMaterialGroupEntity;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class EnvMaterialEntity {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne
    private EnvMaterialGroupEntity group;

    @Column(name = "name_pl")
    private String namePL;

    @Column(name = "name_eng")
    private String nameENG;
}
