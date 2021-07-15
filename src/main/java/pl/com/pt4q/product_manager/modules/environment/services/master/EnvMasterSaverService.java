package pl.com.pt4q.product_manager.modules.environment.services.master;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.com.pt4q.product_manager.modules.environment.data.master.EnvMasterEntity;
import pl.com.pt4q.product_manager.modules.environment.services.master.exceptions.EnvMasterAlreadyExistsException;
import pl.com.pt4q.product_manager.modules.environment.services.master.exceptions.EnvMasterNotFoundException;
import pl.com.pt4q.product_manager.modules.product.data.product.ProductEntity;

@Service
public class EnvMasterSaverService {

    @Autowired
    public EnvMasterCrudSaver envMasterCrudSaver;
    @Autowired
    public EnvMasterFinderService envMasterFinderService;

    public EnvMasterEntity create(EnvMasterEntity masterEntity) throws EnvMasterAlreadyExistsException {
        EnvMasterEntity envMasterEntity;
        ProductEntity productEntity;
        try {
            envMasterEntity = envMasterFinderService.findByIdOrThrowException(masterEntity.getId());
            productEntity = envMasterEntity.getProduct();
        } catch (EnvMasterNotFoundException e) {
            return envMasterCrudSaver.save(masterEntity);
        }
        throw new EnvMasterAlreadyExistsException(String.format("Master card (id: %d) for product %s (id: %d) already exists",
                envMasterEntity.getId(),
                productEntity != null ? productEntity.getSku() : null,
                productEntity != null ? productEntity.getId() : null
        ));
    }

    public EnvMasterEntity update(EnvMasterEntity masterEntity) throws EnvMasterNotFoundException {
        this.envMasterFinderService.findByIdOrThrowException(masterEntity.getId());
        return envMasterCrudSaver.update(masterEntity);
    }

    public void delete(EnvMasterEntity masterEntity){
        this.envMasterCrudSaver.delete(masterEntity);
    }
}
