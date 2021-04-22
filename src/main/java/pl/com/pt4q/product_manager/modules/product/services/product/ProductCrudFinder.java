package pl.com.pt4q.product_manager.modules.product.services.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.com.pt4q.product_manager.modules.product.data.product.ProductEntity;
import pl.com.pt4q.product_manager.modules.product.services.product.exceptions.ProductNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class ProductCrudFinder {

    private ProductCrudRepository productCrudRepository;

    @Autowired
    public ProductCrudFinder(ProductCrudRepository productCrudRepository) {
        this.productCrudRepository = productCrudRepository;
    }

    public ProductEntity findByIdOrThrowException(Long id) throws ProductNotFoundException {
        String errorMessage = "Product id:%d";
        if (id != null) {
            Optional<ProductEntity> product = productCrudRepository.findById(id);
            if (product.isPresent())
                return product.get();
            else
                throw new ProductNotFoundException(String.format(errorMessage, id));
        } else {
            throw new ProductNotFoundException(String.format(errorMessage, id));
        }
    }

    public List<ProductEntity> findAll(){
        return productCrudRepository.findAll();
    }
}
