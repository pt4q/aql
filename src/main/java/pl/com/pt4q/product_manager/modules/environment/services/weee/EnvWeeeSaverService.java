package pl.com.pt4q.product_manager.modules.environment.services.weee;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.com.pt4q.product_manager.modules.environment.data.master.EnvMasterEntity;
import pl.com.pt4q.product_manager.modules.environment.data.weee.EnvWeeeEntity;
import pl.com.pt4q.product_manager.modules.environment.services.master.EnvMasterFinderService;
import pl.com.pt4q.product_manager.modules.environment.services.master.EnvMasterSaverService;
import pl.com.pt4q.product_manager.modules.environment.services.master.exceptions.EnvMasterAlreadyExistsException;
import pl.com.pt4q.product_manager.modules.environment.services.master.exceptions.EnvMasterNotFoundException;
import pl.com.pt4q.product_manager.modules.environment.services.weee.exceptions.EnvWeeeAlreadyExistsException;
import pl.com.pt4q.product_manager.modules.environment.services.weee.exceptions.EnvWeeeNotFoundException;

@Slf4j
@Service
public class EnvWeeeSaverService {

    @Autowired
    private EnvWeeeCrudSaver envWeeeCrudSaver;
    @Autowired
    private EnvWeeeFinderService envWeeeFinderService;

    @Autowired
    private EnvMasterSaverService envMasterSaverService;
    @Autowired
    private EnvMasterFinderService envMasterFinderService;

    public EnvWeeeEntity create(EnvWeeeEntity weeeEntity) throws EnvWeeeAlreadyExistsException {
        try {
            weeeEntity = envWeeeFinderService.findByIdOrThrowException(weeeEntity.getId());
        } catch (EnvWeeeNotFoundException e) {
            EnvMasterEntity masterEntity = null;
            try {
                masterEntity = createMasterIfNotExists(weeeEntity.getMaster());
                weeeEntity.setMaster(masterEntity);
                weeeEntity = envWeeeCrudSaver.save(weeeEntity);

                log.info(String.format("WEEE (id:%d) has been connected with master (id:%d)",
                        weeeEntity.getId(),
                        masterEntity.getId()));

                return weeeEntity;

            } catch (EnvMasterNotFoundException e2) {
                log.error(String.format("Error when try to create WEEE: %s", e2.getMessage()));
            }
        }
        throw new EnvWeeeAlreadyExistsException(String.format("Weee card (id:%d) already exists for master (id:%d)",
                weeeEntity.getId(),
                weeeEntity.getMaster().getId()
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

    public EnvWeeeEntity update(EnvWeeeEntity weeeEntity) throws EnvWeeeNotFoundException {
        EnvWeeeEntity weeeInDb = this.envWeeeFinderService.findByIdOrThrowException(weeeEntity.getId());
        weeeEntity.setMaster(weeeInDb.getMaster());

        return envWeeeCrudSaver.update(weeeEntity);
    }
}
