package pl.com.pt4q.product_manager.modules.environment.services.material_associated.group_of_material;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.com.pt4q.product_manager.modules.environment.data.material_associated.group_of_material.EnvMaterialGroupEntity;

import java.util.Optional;

@Repository
interface EnvMaterialGroupCrudRepository extends JpaRepository<EnvMaterialGroupEntity, Long> {

    Optional<EnvMaterialGroupEntity> findByNameENG(String nameENG);
}
