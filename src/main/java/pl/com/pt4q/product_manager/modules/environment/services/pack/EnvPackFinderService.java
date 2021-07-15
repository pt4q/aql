package pl.com.pt4q.product_manager.modules.environment.services.pack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.com.pt4q.product_manager.modules.environment.data.pack.EnvPackagingEntity;
import pl.com.pt4q.product_manager.modules.environment.services.pack.exceptions.EnvPackNotFoundException;

import java.util.Optional;

@Service
public class EnvPackFinderService {

    @Autowired
    private EnvPackCrudRepository repository;

    public EnvPackagingEntity findByIdOrThrowException(Long id) throws EnvPackNotFoundException {
        String errorMessage = "Pack card id:%d";
        if (id != null) {
            Optional<EnvPackagingEntity> packagingEntity = repository.findById(id);
            if (packagingEntity.isPresent())
                return packagingEntity.get();
            else
                throw new EnvPackNotFoundException(String.format(errorMessage, id));
        } else
            throw new EnvPackNotFoundException(String.format(errorMessage, id));
    }
}
