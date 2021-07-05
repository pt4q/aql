package pl.com.pt4q.product_manager.modules.product_parts.services.product_part_attribute;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.com.pt4q.product_manager.modules.product_parts.data.product_part_attribute.ProductPartAttributeEntity;
import pl.com.pt4q.product_manager.modules.product_parts.services.product_part_attribute.exceptions.ProductPartAttributeAlreadyExistsException;

@Getter
@AllArgsConstructor
@Service
public class ProductPartAttributeCreatorService {

    @Autowired
    private ProductPartAttributeCrudSaver productPartAttributeCrudSaver;

    public ProductPartAttributeEntity create(ProductPartAttributeEntity attribute) throws ProductPartAttributeAlreadyExistsException {
        return productPartAttributeCrudSaver.save(attribute);
    }

}
