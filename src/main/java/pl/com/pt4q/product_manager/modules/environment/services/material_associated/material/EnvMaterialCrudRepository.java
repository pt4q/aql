package pl.com.pt4q.product_manager.modules.environment.services.material_associated.material;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.com.pt4q.product_manager.modules.environment.data.material_associated.group_of_material.EnvMaterialGroupEntity;
import pl.com.pt4q.product_manager.modules.environment.data.material_associated.material.EnvMaterialEntity;

import java.util.Optional;
import java.util.Set;

@Repository
interface EnvMaterialCrudRepository extends JpaRepository<EnvMaterialEntity, Long> {

    Set<EnvMaterialEntity> findAllByGroup(EnvMaterialGroupEntity group);
    Optional<EnvMaterialEntity> findByNameENG(String nameENG);
}
