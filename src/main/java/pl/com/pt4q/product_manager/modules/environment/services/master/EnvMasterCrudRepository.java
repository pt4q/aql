package pl.com.pt4q.product_manager.modules.environment.services.master;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.com.pt4q.product_manager.modules.environment.data.master.EnvMasterEntity;

@Repository
interface EnvMasterCrudRepository extends JpaRepository<EnvMasterEntity, Long> {

}
