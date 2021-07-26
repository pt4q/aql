package pl.com.pt4q.product_manager.modules.environment.services.pack;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.com.pt4q.product_manager.modules.environment.data.master.EnvMasterEntity;
import pl.com.pt4q.product_manager.modules.environment.data.pack.EnvPackagingEntity;
import pl.com.pt4q.product_manager.modules.environment.data.weee.EnvWeeeEntity;

import java.util.Set;

@Repository
interface EnvPackCrudRepository extends JpaRepository<EnvPackagingEntity, Long> {

    Set<EnvPackagingEntity> findByMaster(EnvMasterEntity masterEntity);
}
