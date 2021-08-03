package pl.com.pt4q.product_manager.modules.environment.services.material_associated.material;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.com.pt4q.product_manager.modules.environment.data.material_associated.group_of_material.EnvMaterialGroupEntity;
import pl.com.pt4q.product_manager.modules.environment.data.material_associated.material.EnvMaterialEntity;
import pl.com.pt4q.product_manager.modules.environment.services.material_associated.material.exception.EnvMaterialAlreadyExistsException;
import pl.com.pt4q.product_manager.modules.environment.services.material_associated.material.exception.EnvMaterialNotFoundException;
import pl.com.pt4q.product_manager.service_utils.CustomCrudServiceInterface;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class EnvMaterialCrudService implements CustomCrudServiceInterface<EnvMaterialEntity, Long, EnvMaterialNotFoundException, EnvMaterialAlreadyExistsException> {

    @Autowired
    private EnvMaterialCrudRepository materialRepository;

    public EnvMaterialEntity create(EnvMaterialEntity material) throws EnvMaterialAlreadyExistsException {
        try {
            getByIdOrThrow(material.getId());
        } catch (EnvMaterialNotFoundException ex) {
            material = materialRepository.save(material);
            log.info(String.format("Material '%s' has been created on id: %d", material.getNamePL(), material.getId()));
            return material;
        }
        throw new EnvMaterialAlreadyExistsException(String.format("Material '%s' already exists on id:%d", material.getNamePL(), material.getId()));
    }

    @Override
    public EnvMaterialEntity updateOrThrow(EnvMaterialEntity material) throws EnvMaterialNotFoundException {
        getByIdOrThrow(material.getId());
        material = materialRepository.save(material);
        log.info(String.format("Material '%s' has been updated on id: %d", material.getNamePL(), material.getId()));
        return material;
    }

    @Override
    public EnvMaterialEntity getByIdOrThrow(Long id) throws EnvMaterialNotFoundException {
        if (id != null) {
            Optional<EnvMaterialEntity> productEntity = materialRepository.findById(id);
            if (productEntity.isPresent())
                return productEntity.get();
            else
                throw new EnvMaterialNotFoundException(String.format("Material with id:%d not exists", id));
        }
        throw new EnvMaterialNotFoundException(String.format("Material id:%d", id));
    }

    @Override
    public List<EnvMaterialEntity> getAll(){
        return materialRepository.findAll();
    }

    public Set<EnvMaterialEntity> findAllByMaterialGroup(EnvMaterialGroupEntity group){
        return materialRepository.findAllByGroup(group);
    }
}
