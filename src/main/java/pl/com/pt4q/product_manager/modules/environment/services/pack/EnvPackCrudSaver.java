package pl.com.pt4q.product_manager.modules.environment.services.pack;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.com.pt4q.product_manager.modules.environment.data.pack.EnvPackagingEntity;

@Slf4j
@Service
class EnvPackCrudSaver {

    @Autowired
    private EnvPackCrudRepository repository;

    public EnvPackagingEntity save(EnvPackagingEntity packagingEntity) {
        packagingEntity = repository.save(packagingEntity);
        log.info(String.format("Pack card (id:%d) has been created",
                packagingEntity.getId()
                )
        );
        return packagingEntity;
    }

    public EnvPackagingEntity update(EnvPackagingEntity packagingEntity) {
        packagingEntity = repository.save(packagingEntity);
        log.info(String.format("Pack card (id:%d) has been updated",
                packagingEntity.getId()
                )
        );
        return packagingEntity;
    }

    public void delete(EnvPackagingEntity packagingEntity){
        repository.delete(packagingEntity);
        log.info(String.format("Packaging card (id:%d) has been deleted", packagingEntity.getId()));
    }
}
