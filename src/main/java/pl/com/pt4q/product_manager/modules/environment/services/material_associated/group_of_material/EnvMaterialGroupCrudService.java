package pl.com.pt4q.product_manager.modules.environment.services.material_associated.group_of_material;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.com.pt4q.product_manager.modules.environment.data.material_associated.group_of_material.EnvMaterialGroupEntity;
import pl.com.pt4q.product_manager.modules.environment.services.material_associated.group_of_material.exceptions.EnvMaterialGroupAlreadyExistsException;
import pl.com.pt4q.product_manager.modules.environment.services.material_associated.group_of_material.exceptions.EnvMaterialGroupNotFoundException;
import pl.com.pt4q.product_manager.modules.product.data.manufacturer.ManufacturerEntity;
import pl.com.pt4q.product_manager.modules.product.services.manufacturer.exceptions.ManufacturerNotFoundException;
import pl.com.pt4q.product_manager.modules.product.services.product_category.exceptions.ProductCategoryAlreadyExistsException;
import pl.com.pt4q.product_manager.service_utils.CustomCrudServiceInterface;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class EnvMaterialGroupCrudService implements CustomCrudServiceInterface<EnvMaterialGroupEntity, Long, EnvMaterialGroupNotFoundException, EnvMaterialGroupAlreadyExistsException> {

    @Autowired
    private EnvMaterialGroupCrudRepository groupRepository;

    public EnvMaterialGroupEntity create(EnvMaterialGroupEntity group) throws EnvMaterialGroupAlreadyExistsException {
        try {
            getByIdOrThrow(group.getId());
        } catch (EnvMaterialGroupNotFoundException ex) {
            group = groupRepository.save(group);
            log.info(String.format("Group of materials '%s' has been created on id: %d", group.getName(), group.getId()));
            return group;
        }
        throw new EnvMaterialGroupAlreadyExistsException(String.format("Group of materials '%s' already exists on id:%d", group.getName(), group.getId()));
    }

    @Override
    public EnvMaterialGroupEntity updateOrThrow(EnvMaterialGroupEntity group) throws EnvMaterialGroupNotFoundException {
        getByIdOrThrow(group.getId());
        group = groupRepository.save(group);
        log.info(String.format("Group of materials '%s' has been updated on id: %d", group.getName(), group.getId()));
        return group;
    }

    @Override
    public EnvMaterialGroupEntity getByIdOrThrow(Long id) throws EnvMaterialGroupNotFoundException {
        if (id != null) {
            Optional<EnvMaterialGroupEntity> productEntity = groupRepository.findById(id);
            if (productEntity.isPresent())
                return productEntity.get();
            else
                throw new EnvMaterialGroupNotFoundException(String.format("Group of materials with id:%d not exists", id));
        }
        throw new EnvMaterialGroupNotFoundException(String.format("Group of materials id:%d", id));
    }

    @Override
    public List<EnvMaterialGroupEntity> getAll(){
        return groupRepository.findAll();
    }
}
