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

    public EnvMasterEntity createWeeeAndAddItToMaster(EnvMasterEntity masterEntity, EnvWeeeEntity weeeEntity) throws EnvWeeeAlreadyExistsException {
        try {
            weeeEntity = envWeeeFinderService.findByIdOrThrowException(weeeEntity.getId());
        } catch (EnvWeeeNotFoundException e) {
            weeeEntity = envWeeeCrudSaver.save(weeeEntity);

            masterEntity.setWeee(weeeEntity);
            return connectWeeeWithMaster(masterEntity);
        }
        throw new EnvWeeeAlreadyExistsException(String.format("Weee card (id:%d) already exists",
                weeeEntity.getId()
//                envMasterEntity.getId(),
//                productEntity != null ? productEntity.getSku() : null,
//                productEntity != null ? productEntity.getId() : null
        ));
    }

    private EnvMasterEntity connectWeeeWithMaster(EnvMasterEntity masterEntity) {
        try {
            masterEntity = envMasterSaverService.create(masterEntity);
            log.info(String.format("Master (id=%d) has been created for weee (id:%d)",
                    masterEntity.getId(),
                    masterEntity.getWeee().getId()));
        } catch (EnvMasterAlreadyExistsException e2) {
            try {
                masterEntity = envMasterSaverService.update(masterEntity);
            } catch (EnvMasterNotFoundException e3) {
                envWeeeCrudSaver.delete(masterEntity.getWeee());
                log.error(String.format("Error when try to update existing master (id:%d) with weee (id:%d): %s",
                        masterEntity.getId(),
                        masterEntity.getWeee().getId(),
                        e3.getMessage()));
            }
        }
        return masterEntity;
    }

    public EnvWeeeEntity update(EnvWeeeEntity weeeEntity) throws EnvWeeeNotFoundException {
        this.envWeeeFinderService.findByIdOrThrowException(weeeEntity.getId());
        return envWeeeCrudSaver.update(weeeEntity);
    }
}
