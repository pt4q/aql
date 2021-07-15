package pl.com.pt4q.product_manager.modules.environment.services.weee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.com.pt4q.product_manager.modules.environment.data.weee.EnvWeeeEntity;

@Repository
interface EnvWeeeCrudRepository extends JpaRepository<EnvWeeeEntity, Long> {

}
