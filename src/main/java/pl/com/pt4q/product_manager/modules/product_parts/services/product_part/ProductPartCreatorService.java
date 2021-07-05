package pl.com.pt4q.product_manager.modules.product_parts.services.product_part;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.com.pt4q.product_manager.modules.product_parts.data.product_part.ProductPartEntity;
import pl.com.pt4q.product_manager.modules.product_parts.services.product_part.exceptions.ProductPartAlreadyExistsException;

@Service
public class ProductPartCreatorService {

    @Autowired
    private ProductPartCrudSaver productPartCrudSaver;

    public ProductPartEntity create(ProductPartEntity productPart) throws ProductPartAlreadyExistsException {
        return productPartCrudSaver.save(productPart);
    }
}
