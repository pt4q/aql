package com.example.application.data.service.product;

import com.example.application.data.entity.product.ProductCrudRepository;
import com.example.application.data.entity.product.ProductEntity;
import com.example.application.data.service.CustomCrudServiceInterface;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.vaadin.artur.helpers.CrudService;

import java.util.List;
import java.util.Optional;

@Service
public class ProductCrudService extends CrudService<ProductEntity, Long> implements CustomCrudServiceInterface<ProductEntity, Long, ProductNotFoundException, ProductAlreadyExistsException> {

    @Autowired
    private ProductCrudRepository productCrudRepository;

    @Override
    protected JpaRepository<ProductEntity, Long> getRepository() {
        return productCrudRepository;
    }

    @SneakyThrows
    public ProductEntity create(ProductEntity product) {
        try {
            getByIdOrThrow(product.getId());
        } catch (Exception ex) {
            return getRepository().save(product);
        }
        throw new ProductAlreadyExistsException(String.format("Product %s already exists on id:$d", product.getProductNumber(), product.getId()));
    }

    @Override
    public ProductEntity updateOrThrow(ProductEntity product) throws ProductNotFoundException {
        getByIdOrThrow(product.getId());
        return productCrudRepository.save(product);
    }

    @Override
    public ProductEntity getByIdOrThrow(Long id) throws ProductNotFoundException {
        if (id != null) {
            Optional<ProductEntity> productEntity = super.get(id);
            if (productEntity.isPresent())
                return productEntity.get();
            else
                throw new ProductNotFoundException(String.format("Product id:%d not exists", id));
        }
        throw new ProductNotFoundException(String.format("Product id:%d", id));
    }

    public List<ProductEntity> getAll(){
        return productCrudRepository.findAll();
    }
}
