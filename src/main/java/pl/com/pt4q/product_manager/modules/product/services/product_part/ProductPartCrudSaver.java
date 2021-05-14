package pl.com.pt4q.product_manager.modules.product.services.product_part;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.com.pt4q.product_manager.modules.product.data.product_part.ProductPartEntity;

@Log4j2
@Service
class ProductPartCrudSaver {

    @Autowired
    private ProductPartCrudRepository productCrudRepository;

    public ProductPartEntity save(ProductPartEntity part) {
        return saveInDatabase(
                part,
                String.format("Created new part %s (id:%d) with series no: %s for %s product (id:%d)",
                        part.getPartModelOrPartName(),
                        part.getId(),

                        part.getProduct().getProductSku(),
                        part.getProduct().getId()
                )
        );
    }

    public ProductPartEntity update(ProductPartEntity part) {
        return saveInDatabase(
                part,
                String.format("Updated part %s (id:%d) with series no: %s for %s product (id:%d)",
                        part.getPartModelOrPartName(),
                        part.getId(),
                        part.getProduct().getProductSku(),
                        part.getProduct().getId()
                )
        );
    }

    private ProductPartEntity saveInDatabase(ProductPartEntity part, String message) {
        part = productCrudRepository.save(part);
        log.info(message);
        return part;
    }
}
