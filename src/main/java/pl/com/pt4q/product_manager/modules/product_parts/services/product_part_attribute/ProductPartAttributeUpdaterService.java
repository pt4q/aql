package pl.com.pt4q.product_manager.modules.product_parts.services.product_part_attribute;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.com.pt4q.product_manager.modules.product_parts.data.product_part_attribute.ProductPartAttributeEntity;
import pl.com.pt4q.product_manager.modules.product_parts.services.product_part.exceptions.ProductPartAttributeNotFoundException;

@Service
public class ProductPartAttributeUpdaterService {

    @Autowired
    private ProductPartAttributeCrudSaver crudSaver;

    public ProductPartAttributeEntity update(ProductPartAttributeEntity partAttribute) throws ProductPartAttributeNotFoundException {
        return crudSaver.update(partAttribute);
    }

}
