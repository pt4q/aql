package pl.com.pt4q.product_manager.modules.environment.services.pack;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.com.pt4q.product_manager.modules.environment.data.master.EnvMasterEntity;
import pl.com.pt4q.product_manager.modules.environment.data.pack.EnvPackagingEntity;
import pl.com.pt4q.product_manager.modules.environment.data.weee.EnvWeeeEntity;
import pl.com.pt4q.product_manager.modules.environment.services.master.EnvMasterFinderService;
import pl.com.pt4q.product_manager.modules.environment.services.master.EnvMasterSaverService;
import pl.com.pt4q.product_manager.modules.environment.services.master.exceptions.EnvMasterAlreadyExistsException;
import pl.com.pt4q.product_manager.modules.environment.services.master.exceptions.EnvMasterNotFoundException;
import pl.com.pt4q.product_manager.modules.environment.services.pack.exceptions.EnvPackNotFoundException;
import pl.com.pt4q.product_manager.modules.environment.services.weee.exceptions.EnvWeeeAlreadyExistsException;
import pl.com.pt4q.product_manager.modules.environment.services.weee.exceptions.EnvWeeeNotFoundException;

@Slf4j
@Service
public class EnvPackSaverService {

    @Autowired
    private EnvPackCrudSaver envPackCrudSaver;
    @Autowired
    private EnvPackFinderService envPackFinderService;

    @Autowired
    private EnvMasterSaverService envMasterSaverService;
    @Autowired
    private EnvMasterFinderService envMasterFinderService;

    public EnvMasterEntity createWeeeAndAddItToMaster(EnvMasterEntity masterEntity, EnvPackagingEntity packagingEntity) throws EnvWeeeAlreadyExistsException {
        try {
            packagingEntity = envPackFinderService.findByIdOrThrowException(packagingEntity.getId());
        } catch (EnvPackNotFoundException e) {
            packagingEntity = envPackCrudSaver.save(packagingEntity);

            masterEntity.setPackaging(packagingEntity);
            return connectWeeeWithMaster(masterEntity);
        }
        throw new EnvWeeeAlreadyExistsException(String.format("Pack card (id:%d) already exists",
                packagingEntity.getId()
        ));
    }

    private EnvMasterEntity connectWeeeWithMaster(EnvMasterEntity masterEntity) {
        try {
            masterEntity = envMasterSaverService.create(masterEntity);
            log.info(String.format("Master (id=%d) has been created for pack (id:%d)",
                    masterEntity.getId(),
                    masterEntity.getWeee().getId()));
        } catch (EnvMasterAlreadyExistsException e2) {
            try {
                masterEntity = envMasterSaverService.update(masterEntity);
            } catch (EnvMasterNotFoundException e3) {
                envPackCrudSaver.delete(masterEntity.getPackaging());
                log.error(String.format("Error when try to update existing master (id:%d) with pack (id:%d): %s",
                        masterEntity.getId(),
                        masterEntity.getWeee().getId(),
                        e3.getMessage()));
            }
        }
        return masterEntity;
    }

    public EnvPackagingEntity update(EnvPackagingEntity packagingEntity) throws EnvPackNotFoundException {
        this.envPackFinderService.findByIdOrThrowException(packagingEntity.getId());
        return envPackCrudSaver.update(packagingEntity);
    }
}
