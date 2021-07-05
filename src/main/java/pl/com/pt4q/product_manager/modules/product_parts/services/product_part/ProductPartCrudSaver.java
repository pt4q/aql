package pl.com.pt4q.product_manager.modules.product_parts.services.product_part;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.com.pt4q.product_manager.modules.product_parts.data.product_part.ProductPartEntity;
import pl.com.pt4q.product_manager.modules.product_parts.services.product_part.exceptions.ProductPartAlreadyExistsException;
import pl.com.pt4q.product_manager.modules.product_parts.services.product_part.exceptions.ProductPartNotFoundException;

@Log4j2
@Service
class ProductPartCrudSaver {

    @Autowired
    private ProductPartCrudRepository productCrudRepository;
    @Autowired
    private ProductPartFinderService productPartFinderService;


    public ProductPartEntity save(ProductPartEntity part) throws ProductPartAlreadyExistsException {
        try {
            productPartFinderService.findByIdOrThrowException(part.getId());
        } catch (ProductPartNotFoundException e) {
            part = productCrudRepository.save(part);
            log.info(String.format("Created new part %s (id:%d) for %s product (id:%d)",
                    part.getPartModelOrPartName(),
                    part.getId(),
                    part.getProduct().getSku(),
                    part.getProduct().getId()
            ));
            return part;
        }
        throw new ProductPartAlreadyExistsException("Product part already exists");
    }

    public ProductPartEntity update(ProductPartEntity part) throws ProductPartNotFoundException {
        productPartFinderService.findByIdOrThrowException(part.getId());
        part = productCrudRepository.save(part);
        log.info(String.format("Updated part %s (id:%d) for %s product (id:%d)",
                part.getPartModelOrPartName(),
                part.getId(),
                part.getProduct().getSku(),
                part.getProduct().getId()
        ));
        return part;
    }
}
