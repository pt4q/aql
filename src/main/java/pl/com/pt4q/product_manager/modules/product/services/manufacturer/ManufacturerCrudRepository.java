package pl.com.pt4q.product_manager.modules.product.services.manufacturer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.com.pt4q.product_manager.modules.product.data.manufacturer.ManufacturerEntity;

@Repository
public interface ManufacturerCrudRepository extends JpaRepository<ManufacturerEntity, Long> {

}
