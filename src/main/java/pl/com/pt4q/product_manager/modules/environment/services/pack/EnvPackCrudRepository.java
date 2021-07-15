package pl.com.pt4q.product_manager.modules.environment.services.pack;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.com.pt4q.product_manager.modules.environment.data.pack.EnvPackagingEntity;
import pl.com.pt4q.product_manager.modules.environment.data.weee.EnvWeeeEntity;

@Repository
interface EnvPackCrudRepository extends JpaRepository<EnvPackagingEntity, Long> {

}
