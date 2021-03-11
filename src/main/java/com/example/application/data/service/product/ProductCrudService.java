package com.example.application.data.service.product;

import com.example.application.data.entity.product.ProductCrudRepository;
import com.example.application.data.entity.product.ProductEntity;
import com.example.application.data.entity.sample.SamplePerson;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.vaadin.artur.helpers.CrudService;

import java.util.List;

@Service
public class ProductCrudService {

    @Autowired
    private ProductCrudRepository productCrudRepository;

    public ProductEntity createProduct(ProductEntity product){
        return productCrudRepository.save(product);
    }

    public ProductEntity getProduct(Long id) throws ProductNotFound {
        return productCrudRepository
                .findById(id)
                .orElseThrow(ProductNotFound::new);
    }

    public List<ProductEntity> getAllProducts() {
        return productCrudRepository
                .findAll();
    }

    public ProductEntity update(ProductEntity product) throws ProductNotFound {
        if (product.getId() != null) {
            getProduct(product.getId());
            return productCrudRepository.save(product);
        }
        else
            throw new ProductNotFound("Product id can't be null");
    }
}
