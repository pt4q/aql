package pl.com.pt4q.product_manager.modules.environment.services.pack;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.com.pt4q.product_manager.modules.environment.data.master.EnvMasterEntity;
import pl.com.pt4q.product_manager.modules.environment.data.pack.EnvPackagingEntity;
import pl.com.pt4q.product_manager.modules.environment.services.master.EnvMasterFinderService;
import pl.com.pt4q.product_manager.modules.environment.services.master.EnvMasterSaverService;
import pl.com.pt4q.product_manager.modules.environment.services.master.exceptions.EnvMasterAlreadyExistsException;
import pl.com.pt4q.product_manager.modules.environment.services.master.exceptions.EnvMasterNotFoundException;
import pl.com.pt4q.product_manager.modules.environment.services.pack.exceptions.EnvPackAlreadyExistsException;
import pl.com.pt4q.product_manager.modules.environment.services.pack.exceptions.EnvPackNotFoundException;

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

    public EnvPackagingEntity createPack(EnvPackagingEntity packagingEntity) throws EnvPackAlreadyExistsException {
        try {
            packagingEntity = envPackFinderService.findByIdOrThrowException(packagingEntity.getId());
        } catch (EnvPackNotFoundException e) {
            EnvMasterEntity masterEntity = null;
            try {
                masterEntity = createMasterIfNotExists(packagingEntity.getMaster());
                packagingEntity.setMaster(masterEntity);
                packagingEntity = envPackCrudSaver.save(packagingEntity);

                log.info(String.format("PACK (id:%d) has been connected with master (id:%d)",
                        packagingEntity.getId(),
                        masterEntity.getId()));

                return packagingEntity;

            } catch (EnvMasterNotFoundException e2) {
                log.error(String.format("Error when try to create PACK: %s", e2.getMessage()));
            }
        }
        throw new EnvPackAlreadyExistsException(String.format("Pack card (id:%d) already exists for master (id:%d)",
                packagingEntity.getId(),
                packagingEntity.getMaster().getId()
        ));
    }

    private EnvMasterEntity createMasterIfNotExists(EnvMasterEntity masterEntity) throws EnvMasterNotFoundException {
        try {
            masterEntity = envMasterSaverService.create(masterEntity);
        } catch (EnvMasterAlreadyExistsException e2) {
            masterEntity = envMasterSaverService.update(masterEntity);
        }
        return masterEntity;
    }

    public EnvPackagingEntity update(EnvPackagingEntity packagingEntity) throws EnvPackNotFoundException {
        EnvPackagingEntity packInDb = this.envPackFinderService.findByIdOrThrowException(packagingEntity.getId());
        packagingEntity.setMaster(packInDb.getMaster());

        return envPackCrudSaver.update(packagingEntity);
    }
}
