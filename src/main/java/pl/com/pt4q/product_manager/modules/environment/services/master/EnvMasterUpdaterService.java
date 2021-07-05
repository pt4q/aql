package pl.com.pt4q.product_manager.modules.environment.services.master;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.com.pt4q.product_manager.modules.environment.data.master.EnvMasterEntity;
import pl.com.pt4q.product_manager.modules.environment.services.master.exceptions.EnvMasterNotFoundException;

@Service
public class EnvMasterUpdaterService {

    @Autowired
    private EnvMasterCrudSaver envMasterCrudSaver;
    @Autowired
    private EnvMasterFinderService envMasterFinderService;

    public EnvMasterEntity update (EnvMasterEntity masterEntity) throws EnvMasterNotFoundException {
        EnvMasterEntity envMasterEntityFromDb = this.envMasterFinderService.findByIdOrThrowException(masterEntity.getId());
        return envMasterCrudSaver.update(masterEntity);
    }
}
