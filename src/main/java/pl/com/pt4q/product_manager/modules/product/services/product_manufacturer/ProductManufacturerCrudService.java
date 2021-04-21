package pl.com.pt4q.product_manager.modules.product.services.product_manufacturer;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.vaadin.artur.helpers.CrudService;
import pl.com.pt4q.product_manager.modules.product.data.product_category.ProductCategoryEntity;
import pl.com.pt4q.product_manager.modules.product.data.product_manufacturer.ProductManufacturerEntity;
import pl.com.pt4q.product_manager.modules.product.services.product_category.exceptions.ProductCategoryAlreadyExistsException;
import pl.com.pt4q.product_manager.modules.product.services.product_category.exceptions.ProductCategoryNotFoundException;
import pl.com.pt4q.product_manager.modules.product.services.product_manufacturer.exceptions.ProductManufacturerAlreadyExistsException;
import pl.com.pt4q.product_manager.modules.product.services.product_manufacturer.exceptions.ProductManufacturerNotFoundException;
import pl.com.pt4q.product_manager.service_utils.CustomCrudServiceInterface;

import java.util.List;
import java.util.Optional;

@Service
public class ProductManufacturerCrudService extends CrudService<ProductManufacturerEntity, Long> implements CustomCrudServiceInterface<ProductManufacturerEntity, Long, ProductManufacturerNotFoundException, ProductManufacturerAlreadyExistsException> {

    @Autowired
    private ProductManufacturerCrudRepository productManufacturerCrudRepository;

    @Override
    protected JpaRepository<ProductManufacturerEntity, Long> getRepository() {
        return productManufacturerCrudRepository;
    }

    @SneakyThrows
    public ProductManufacturerEntity create(ProductManufacturerEntity product) {
        try {
            getByIdOrThrow(product.getId());
        } catch (Exception ex) {
            return getRepository().save(product);
        }
        throw new ProductCategoryAlreadyExistsException(String.format("Product manufacturer %s already exists on id:%d", product.getManufacturerName(), product.getId()));
    }

    @Override
    public ProductManufacturerEntity updateOrThrow(ProductManufacturerEntity product) throws ProductManufacturerNotFoundException {
        getByIdOrThrow(product.getId());
        return productManufacturerCrudRepository.save(product);
    }

    @Override
    public ProductManufacturerEntity getByIdOrThrow(Long id) throws ProductManufacturerNotFoundException {
        if (id != null) {
            Optional<ProductManufacturerEntity> productEntity = super.get(id);
            if (productEntity.isPresent())
                return productEntity.get();
            else
                throw new ProductManufacturerNotFoundException(String.format("Product manufacturer with id:%d not exists", id));
        }
        throw new ProductManufacturerNotFoundException(String.format("Product manufacturer id:%d", id));
    }

    public List<ProductManufacturerEntity> getAll(){
        return productManufacturerCrudRepository.findAll();
    }
}
