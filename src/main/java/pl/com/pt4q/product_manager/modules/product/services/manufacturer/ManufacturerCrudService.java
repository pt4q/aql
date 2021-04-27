package pl.com.pt4q.product_manager.modules.product.services.manufacturer;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.vaadin.artur.helpers.CrudService;
import pl.com.pt4q.product_manager.modules.product.data.manufacturer.ManufacturerEntity;
import pl.com.pt4q.product_manager.modules.product.services.product_category.exceptions.ProductCategoryAlreadyExistsException;
import pl.com.pt4q.product_manager.modules.product.services.manufacturer.exceptions.ManufacturerAlreadyExistsException;
import pl.com.pt4q.product_manager.modules.product.services.manufacturer.exceptions.ManufacturerNotFoundException;
import pl.com.pt4q.product_manager.service_utils.CustomCrudServiceInterface;

import java.util.List;
import java.util.Optional;

@Service
public class ManufacturerCrudService extends CrudService<ManufacturerEntity, Long> implements CustomCrudServiceInterface<ManufacturerEntity, Long, ManufacturerNotFoundException, ManufacturerAlreadyExistsException> {

    @Autowired
    private ManufacturerCrudRepository manufacturerCrudRepository;

    @Override
    protected JpaRepository<ManufacturerEntity, Long> getRepository() {
        return manufacturerCrudRepository;
    }

    @SneakyThrows
    public ManufacturerEntity create(ManufacturerEntity product) {
        try {
            getByIdOrThrow(product.getId());
        } catch (Exception ex) {
            return getRepository().save(product);
        }
        throw new ProductCategoryAlreadyExistsException(String.format("Product manufacturer %s already exists on id:%d", product.getManufacturerName(), product.getId()));
    }

    @Override
    public ManufacturerEntity updateOrThrow(ManufacturerEntity product) throws ManufacturerNotFoundException {
        getByIdOrThrow(product.getId());
        return manufacturerCrudRepository.save(product);
    }

    @Override
    public ManufacturerEntity getByIdOrThrow(Long id) throws ManufacturerNotFoundException {
        if (id != null) {
            Optional<ManufacturerEntity> productEntity = super.get(id);
            if (productEntity.isPresent())
                return productEntity.get();
            else
                throw new ManufacturerNotFoundException(String.format("Product manufacturer with id:%d not exists", id));
        }
        throw new ManufacturerNotFoundException(String.format("Product manufacturer id:%d", id));
    }

    public List<ManufacturerEntity> getAll(){
        return manufacturerCrudRepository.findAll();
    }
}
