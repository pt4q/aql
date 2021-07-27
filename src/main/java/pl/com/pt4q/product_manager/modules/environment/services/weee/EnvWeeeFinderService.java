package pl.com.pt4q.product_manager.modules.environment.services.weee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.com.pt4q.product_manager.modules.environment.data.master.EnvMasterEntity;
import pl.com.pt4q.product_manager.modules.environment.data.weee.EnvWeeeEntity;
import pl.com.pt4q.product_manager.modules.environment.services.weee.exceptions.EnvWeeeNotFoundException;

import java.util.Optional;

@Service
public class EnvWeeeFinderService {

    @Autowired
    private EnvWeeeCrudRepository repository;

    public EnvWeeeEntity findByIdOrThrowException(Long id) throws EnvWeeeNotFoundException {
        String errorMessage = "Weee card id:%d";
        if (id != null) {
            Optional<EnvWeeeEntity> weeeEntity = repository.findById(id);
            if (weeeEntity.isPresent())
                return weeeEntity.get();
            else
                throw new EnvWeeeNotFoundException(String.format(errorMessage, id));
        } else
            throw new EnvWeeeNotFoundException(String.format(errorMessage, id));
    }

    public Optional<EnvWeeeEntity> findByMaster(EnvMasterEntity masterEntity) {
        if (masterEntity != null && masterEntity.getId() != null)
            return repository.findByMaster(masterEntity);
        else
            return Optional.empty();
    }
}
