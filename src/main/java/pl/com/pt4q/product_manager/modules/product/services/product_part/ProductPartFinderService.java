package pl.com.pt4q.product_manager.modules.product.services.product_part;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.com.pt4q.product_manager.modules.product.data.product.ProductEntity;
import pl.com.pt4q.product_manager.modules.product.data.product_part.ProductPartEntity;
import pl.com.pt4q.product_manager.modules.product.services.product.exceptions.ProductNotFoundException;

import java.util.List;

@Service
public class ProductPartFinderService {

    private ProductPartCrudRepository productPartRepository;

    @Autowired
    public ProductPartFinderService(ProductPartCrudRepository productPartRepository) {
        this.productPartRepository = productPartRepository;
    }

    public List<ProductPartEntity> findAllProductParts(ProductEntity product) throws ProductNotFoundException {
        if (product != null)
            if (product.getId() != null) {
                return productPartRepository.findAllByProduct(product);
            }
        throw new ProductNotFoundException("Product is null or have null id");
    }

}
