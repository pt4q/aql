package pl.com.pt4q.product_manager.modules.product.services.product_part_attribute;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.com.pt4q.product_manager.modules.product.data.product_part_attribute.ProductPartAttributeEntity;
import pl.com.pt4q.product_manager.modules.product.data.product_part.ProductPartEntity;
import pl.com.pt4q.product_manager.modules.product.services.product_part.exceptions.ProductPartAttributeNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class ProductPartAttributeFinderService {

    @Autowired
    private ProductPartAttributeCrudRepository productPartAttributeCrudRepository;

    public ProductPartAttributeEntity findByIdOrThrowException(Long id) throws ProductPartAttributeNotFoundException {
        String errorMessage = "Part Attribute id:%d";
        if (id != null){
            Optional<ProductPartAttributeEntity> attribute = productPartAttributeCrudRepository.findById(id);
            if (attribute.isPresent())
                return attribute.get();
            else
                throw new ProductPartAttributeNotFoundException(String.format(errorMessage, id));
        } else throw new ProductPartAttributeNotFoundException(String.format(errorMessage, id));
    }

    public List<ProductPartAttributeEntity> findAllProductPartsAttributesByProductPart(ProductPartEntity productPart) throws ProductPartAttributeNotFoundException {
        if (productPart != null)
            if (productPart.getId() != null) {
                return productPartAttributeCrudRepository.findAllByPart(productPart);
            }
        throw new ProductPartAttributeNotFoundException("Product part is null or have null id");
    }
}
