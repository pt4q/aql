package pl.com.pt4q.product_manager.modules.product.services.manufacturer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.com.pt4q.product_manager.modules.product.data.manufacturer.ManufacturerEntity;
import pl.com.pt4q.product_manager.modules.product.services.product_category.exceptions.ProductCategoryAlreadyExistsException;
import pl.com.pt4q.product_manager.modules.product.services.manufacturer.exceptions.ManufacturerAlreadyExistsException;
import pl.com.pt4q.product_manager.modules.product.services.manufacturer.exceptions.ManufacturerNotFoundException;
import pl.com.pt4q.product_manager.service_utils.CustomCrudServiceInterface;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ManufacturerCrudService implements CustomCrudServiceInterface<ManufacturerEntity, Long, ManufacturerNotFoundException, ManufacturerAlreadyExistsException> {

    @Autowired
    private ManufacturerCrudRepository manufacturerCrudRepository;

    public ManufacturerEntity create(ManufacturerEntity manufacturer) throws ProductCategoryAlreadyExistsException {
        try {
            getByIdOrThrow(manufacturer.getId());
        } catch (ManufacturerNotFoundException ex) {
            manufacturer = manufacturerCrudRepository.save(manufacturer);
            log.info(String.format("Manufacturer '%s' has been created on id: %d", manufacturer.getManufacturerName(), manufacturer.getId()));
            return manufacturer;
        }
        throw new ProductCategoryAlreadyExistsException(String.format("Manufacturer '%s' already exists on id:%d", manufacturer.getManufacturerName(), manufacturer.getId()));
    }

    @Override
    public ManufacturerEntity updateOrThrow(ManufacturerEntity manufacturer) throws ManufacturerNotFoundException {
        getByIdOrThrow(manufacturer.getId());
        manufacturer = manufacturerCrudRepository.save(manufacturer);
        log.info(String.format("Manufacturer '%s' has been updated on id: %d", manufacturer.getManufacturerName(), manufacturer.getId()));
        return manufacturer;
    }

    @Override
    public ManufacturerEntity getByIdOrThrow(Long id) throws ManufacturerNotFoundException {
        if (id != null) {
            Optional<ManufacturerEntity> productEntity = manufacturerCrudRepository.findById(id);
            if (productEntity.isPresent())
                return productEntity.get();
            else
                throw new ManufacturerNotFoundException(String.format("Manufacturer with id:%d not exists", id));
        }
        throw new ManufacturerNotFoundException(String.format("Manufacturer id:%d", id));
    }

    @Override
    public List<ManufacturerEntity> getAll(){
        return manufacturerCrudRepository.findAll();
    }
}
