package pl.com.pt4q.product_manager.modules.product.services.unit;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.com.pt4q.product_manager.modules.product.data.unit.UnitEntity;

import java.util.Optional;

@Repository
interface UnitCrudRepository extends JpaRepository<UnitEntity, Long> {

    Optional<UnitEntity> findByUnits(String units);
}
