package pl.com.pt4q.product_manager.modules.product_parts.services.product_part;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.com.pt4q.product_manager.modules.product_parts.data.product_part.ProductPartEntity;
import pl.com.pt4q.product_manager.modules.product_parts.services.product_part.exceptions.ProductPartNotFoundException;

@Service
public class ProductPartUpdaterService {

    @Autowired
    private ProductPartCrudSaver productPartCrudSaver;

    public ProductPartEntity update (ProductPartEntity productPart) throws ProductPartNotFoundException {
        return productPartCrudSaver.update(productPart);
    }
}
