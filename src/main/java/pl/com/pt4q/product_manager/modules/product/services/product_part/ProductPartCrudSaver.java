package pl.com.pt4q.product_manager.modules.product.services.product_part;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.com.pt4q.product_manager.modules.product.data.product_part.ProductPartEntity;
import pl.com.pt4q.product_manager.modules.product.services.product_part.exceptions.ProductPartAlreadyExistsException;
import pl.com.pt4q.product_manager.modules.product.services.product_part.exceptions.ProductPartNotFoundException;

@Log4j2
@Service
public class ProductPartCrudSaver {

    @Autowired
    private ProductPartCrudRepository productCrudRepository;
    @Autowired
    private ProductPartFinderService productPartFinderService;


    public ProductPartEntity save(ProductPartEntity part) throws ProductPartAlreadyExistsException {
        try {
            productPartFinderService.findByIdOrThrowException(part.getId());
        } catch (ProductPartNotFoundException e) {
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
        throw new ProductPartAlreadyExistsException("Product part already exists");
    }

    public ProductPartEntity update(ProductPartEntity part) throws ProductPartNotFoundException {
        productPartFinderService.findByIdOrThrowException(part.getId());
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
