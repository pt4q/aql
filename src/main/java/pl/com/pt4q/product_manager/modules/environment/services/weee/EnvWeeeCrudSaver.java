package pl.com.pt4q.product_manager.modules.environment.services.weee;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.com.pt4q.product_manager.modules.environment.data.master.EnvMasterEntity;
import pl.com.pt4q.product_manager.modules.environment.data.weee.EnvWeeeEntity;
import pl.com.pt4q.product_manager.modules.product.data.product.ProductEntity;

@Slf4j
@Service
class EnvWeeeCrudSaver {

    @Autowired
    private EnvWeeeCrudRepository repository;

    public EnvWeeeEntity save(EnvWeeeEntity weeeEntity) {
        weeeEntity = repository.save(weeeEntity);
//        EnvMasterEntity envMasterEntity = weeeEntity.getMaster();
//        ProductEntity productEntity = envMasterEntity.getProduct();
        log.info(String.format("Weee card (id:%d) has been created",
                weeeEntity.getId()
//                envMasterEntity.getId(),
//                productEntity.getSku(),
//                productEntity.getId()
                )
        );
        return weeeEntity;
    }

    public EnvWeeeEntity update(EnvWeeeEntity weeeEntity) {
        weeeEntity = repository.save(weeeEntity);
//        EnvMasterEntity envMasterEntity = weeeEntity.getMaster();
//        ProductEntity productEntity = envMasterEntity.getProduct();
        log.info(String.format("Weee card (id:%d) has been updated",
                weeeEntity.getId()
//                envMasterEntity.getId(),
//                productEntity.getSku(),
//                productEntity.getId()
                )
        );
        return weeeEntity;
    }

    public void delete(EnvWeeeEntity weeeEntity){
        repository.delete(weeeEntity);
        log.info(String.format("Weee card (id:%d) has been deleted", weeeEntity.getId()));
    }
}
