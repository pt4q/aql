package pl.com.pt4q.product_manager.modules.environment.services.master;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.com.pt4q.product_manager.modules.environment.data.master.EnvMasterEntity;
import pl.com.pt4q.product_manager.modules.environment.services.master.exceptions.EnvMasterNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class EnvMasterFinderService {

    @Autowired
    private EnvMasterCrudRepository repository;

    public EnvMasterEntity findByIdOrThrowException(Long id) throws EnvMasterNotFoundException {
        String errorMessage = "Master card id:%d";
        if (id != null) {
            Optional<EnvMasterEntity> masterEntity = repository.findById(id);
            if (masterEntity.isPresent())
                return masterEntity.get();
            else
                throw new EnvMasterNotFoundException(String.format(errorMessage, id));
        } else
            throw new EnvMasterNotFoundException(String.format(errorMessage, id));
    }

    public List<EnvMasterEntity> findAll() {
        return repository.findAll();
    }
}
