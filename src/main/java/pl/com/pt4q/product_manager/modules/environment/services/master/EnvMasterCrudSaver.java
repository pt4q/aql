package pl.com.pt4q.product_manager.modules.environment.services.master;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.com.pt4q.product_manager.modules.environment.data.master.EnvMasterEntity;
import pl.com.pt4q.product_manager.modules.product.data.product.ProductEntity;

@Slf4j
@Service
class EnvMasterCrudSaver {

    @Autowired
    private EnvMasterCrudRepository repository;

    public EnvMasterEntity save(EnvMasterEntity masterEntity){
        masterEntity = repository.save(masterEntity);
        ProductEntity productEntity = masterEntity.getProduct();
        log.info(String.format("Master card (id:%d) has been created for product %s (id:%d)",
                masterEntity.getId(),
                productEntity.getSku(),
                productEntity.getId())
        );
        return masterEntity;
    }

    public EnvMasterEntity update(EnvMasterEntity masterEntity){
        masterEntity = repository.save(masterEntity);
        ProductEntity productEntity = masterEntity.getProduct();
        log.info(String.format("Master card (id:%d) has been updated for product %s (id:%d)",
                masterEntity.getId(),
                productEntity.getSku(),
                productEntity.getId())
        );
        return masterEntity;
    }
}
