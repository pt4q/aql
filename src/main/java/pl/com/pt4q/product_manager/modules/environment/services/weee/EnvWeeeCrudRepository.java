package pl.com.pt4q.product_manager.modules.environment.services.weee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.com.pt4q.product_manager.modules.environment.data.master.EnvMasterEntity;
import pl.com.pt4q.product_manager.modules.environment.data.weee.EnvWeeeEntity;

import java.util.Optional;

@Repository
interface EnvWeeeCrudRepository extends JpaRepository<EnvWeeeEntity, Long> {
    Optional<EnvWeeeEntity> findByMaster(EnvMasterEntity master);
}
