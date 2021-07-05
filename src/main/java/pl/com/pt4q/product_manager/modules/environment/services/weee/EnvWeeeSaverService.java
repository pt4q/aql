package pl.com.pt4q.product_manager.modules.environment.services.weee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.com.pt4q.product_manager.modules.environment.data.master.EnvMasterEntity;
import pl.com.pt4q.product_manager.modules.environment.data.weee.EnvWeeeEntity;
import pl.com.pt4q.product_manager.modules.environment.services.master.exceptions.EnvMasterAlreadyExistsException;
import pl.com.pt4q.product_manager.modules.environment.services.weee.exceptions.EnvWeeeNotFoundException;
import pl.com.pt4q.product_manager.modules.product.data.product.ProductEntity;

@Service
public class EnvWeeeSaverService {

    @Autowired
    private EnvWeeeCrudSaver envWeeeCrudSaver;
    @Autowired
    private EnvWeeeFinderService envWeeeFinderService;

    public EnvWeeeEntity create(EnvWeeeEntity weeeEntity) throws EnvMasterAlreadyExistsException {
        EnvMasterEntity envMasterEntity;
        ProductEntity productEntity;
        try {
            weeeEntity = envWeeeFinderService.findByIdOrThrowException(weeeEntity.getId());
            envMasterEntity = weeeEntity.getMaster();
            productEntity = envMasterEntity.getProduct();
        } catch (EnvWeeeNotFoundException e) {
            return envWeeeCrudSaver.save(weeeEntity);
        }
        throw new EnvMasterAlreadyExistsException(String.format("Weee card (id:%d) for Master (id:%d) to product %s (id: %d) already exists",
                weeeEntity.getId(),
                envMasterEntity.getId(),
                productEntity != null ? productEntity.getSku() : null,
                productEntity != null ? productEntity.getId() : null
        ));
    }

    public EnvWeeeEntity update(EnvWeeeEntity weeeEntity) throws EnvWeeeNotFoundException {
        this.envWeeeFinderService.findByIdOrThrowException(weeeEntity.getId());
        return envWeeeCrudSaver.update(weeeEntity);
    }
}
