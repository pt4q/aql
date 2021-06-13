package pl.com.pt4q.product_manager.modules.product_parts.services.product_part_attribute;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.com.pt4q.product_manager.modules.product.data.product.ProductEntity;
import pl.com.pt4q.product_manager.modules.product_parts.data.product_part.ProductPartEntity;
import pl.com.pt4q.product_manager.modules.product_parts.data.product_part_attribute.ProductPartAttributeEntity;
import pl.com.pt4q.product_manager.modules.product_parts.services.product_part.exceptions.ProductPartAttributeNotFoundException;
import pl.com.pt4q.product_manager.modules.product_parts.services.product_part_attribute.exceptions.ProductPartAttributeAlreadyExistsException;

@Slf4j
@Service
class ProductPartAttributeCrudSaver {

    @Autowired
    private ProductPartAttributeCrudRepository attributeCrudRepository;
    @Autowired
    private ProductPartAttributeFinderService attributeFinderService;

    public ProductPartAttributeEntity save(ProductPartAttributeEntity partAttribute) throws ProductPartAttributeAlreadyExistsException {
        try {
            attributeFinderService.findByIdOrThrowException(partAttribute.getId());
        } catch (ProductPartAttributeNotFoundException e) {
            partAttribute = attributeCrudRepository.save(partAttribute);

            ProductPartEntity partEntity = partAttribute.getPart();
            ProductEntity productEntity = partEntity.getProduct();
            log.info(String.format("Created new attribute %s (id:%d) for part %s (id:%d) for product %s (id:%d)",
                    partAttribute.getAttributeName(),
                    partAttribute.getId(),
                    partEntity.getPartModelOrPartName(),
                    partEntity.getId(),
                    productEntity.getProductSku(),
                    productEntity.getId()
            ));
            return partAttribute;
        }
        throw new ProductPartAttributeAlreadyExistsException(String.format("Attribute %s already exists", partAttribute.getAttributeName()));
    }

    public ProductPartAttributeEntity update(ProductPartAttributeEntity partAttribute) throws ProductPartAttributeNotFoundException {
        attributeFinderService.findByIdOrThrowException(partAttribute.getId());
        partAttribute = attributeCrudRepository.save(partAttribute);

        ProductPartEntity partEntity = partAttribute.getPart();
        ProductEntity productEntity = partEntity.getProduct();
        log.info(String.format("Updated attribute %s (id:%d) for part %s (id:%d) for product %s (id:%d)",
                partAttribute.getAttributeName(),
                partAttribute.getId(),
                partEntity.getPartModelOrPartName(),
                partEntity.getId(),
                productEntity.getProductSku(),
                productEntity.getId()
        ));
        return partAttribute;
    }
}
