package pl.com.pt4q.product_manager.modules.product.services.product_category;

import pl.com.pt4q.product_manager.modules.product.data.product_category.ProductCategoryEntity;
import pl.com.pt4q.product_manager.modules.test_card.services.CustomCrudServiceInterface;
import pl.com.pt4q.product_manager.modules.product.services.product_category.exceptions.ProductCategoryAlreadyExistsException;
import pl.com.pt4q.product_manager.modules.product.services.product_category.exceptions.ProductCategoryNotFoundException;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.vaadin.artur.helpers.CrudService;

import java.util.List;
import java.util.Optional;

@Service
public class ProductCategoryCrudService extends CrudService<ProductCategoryEntity, Long> implements CustomCrudServiceInterface<ProductCategoryEntity, Long, ProductCategoryNotFoundException, ProductCategoryAlreadyExistsException> {

    @Autowired
    private ProductCategoryCrudRepository productCategoryCrudRepository;

    @Override
    protected JpaRepository<ProductCategoryEntity, Long> getRepository() {
        return productCategoryCrudRepository;
    }

    @SneakyThrows
    public ProductCategoryEntity create(ProductCategoryEntity product) {
        try {
            getByIdOrThrow(product.getId());
        } catch (Exception ex) {
            return getRepository().save(product);
        }
        throw new ProductCategoryAlreadyExistsException(String.format("Product category %s already exists on id:%d", product.getProductCategoryName(), product.getId()));
    }

    @Override
    public ProductCategoryEntity updateOrThrow(ProductCategoryEntity product) throws ProductCategoryNotFoundException {
        getByIdOrThrow(product.getId());
        return productCategoryCrudRepository.save(product);
    }

    @Override
    public ProductCategoryEntity getByIdOrThrow(Long id) throws ProductCategoryNotFoundException {
        if (id != null) {
            Optional<ProductCategoryEntity> productEntity = super.get(id);
            if (productEntity.isPresent())
                return productEntity.get();
            else
                throw new ProductCategoryNotFoundException(String.format("Product category with id:%d not exists", id));
        }
        throw new ProductCategoryNotFoundException(String.format("Product category id:%d", id));
    }

    public List<ProductCategoryEntity> getAll(){
        return productCategoryCrudRepository.findAll();
    }
}
