package pl.com.pt4q.product_manager.modules.product.services.product_part;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.com.pt4q.product_manager.modules.product.data.product_part.ProductPartAttributeEntity;
import pl.com.pt4q.product_manager.modules.product.data.product_part.ProductPartEntity;
import pl.com.pt4q.product_manager.modules.product.services.product_part.exceptions.ProductPartNotFoundException;

import java.util.Collections;
import java.util.List;

@Service
public class ProductPartAttributeFinderService {

    @Autowired
    private ProductPartAttributeCrudRepository productPartAttributeCrudRepository;

    public List<ProductPartAttributeEntity> findAllProductPartsAttributesByProductPart(ProductPartEntity productPart) throws ProductPartNotFoundException {
        if (productPart != null)
            if (productPart.getId() != null) {
                return productPartAttributeCrudRepository.findAllByPart(productPart);
            }
        throw new ProductPartNotFoundException("Product part is null or have null id");
    }
}
