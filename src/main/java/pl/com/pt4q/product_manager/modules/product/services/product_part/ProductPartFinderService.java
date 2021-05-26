package pl.com.pt4q.product_manager.modules.product.services.product_part;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.com.pt4q.product_manager.modules.product.data.product.ProductEntity;
import pl.com.pt4q.product_manager.modules.product.data.product_part.ProductPartEntity;
import pl.com.pt4q.product_manager.modules.product.services.product_part.exceptions.ProductPartNotFoundException;
import pl.com.pt4q.product_manager.modules.product.services.product_part_attribute.ProductPartAttributeFinderService;

import java.util.List;
import java.util.Optional;

@Service
public class ProductPartFinderService {

    @Autowired
    private ProductPartCrudRepository productPartRepository;

    @Autowired
    @Getter
    private ProductPartAttributeFinderService productPartAttributeFinderService;

    public ProductPartEntity findByIdOrThrowException(Long id) throws ProductPartNotFoundException {
        String errorMessage = "Part id:%d";
        if (id != null) {
            Optional<ProductPartEntity> partEntity = productPartRepository.findById(id);
            if (partEntity.isPresent())
                return partEntity.get();
            else
                throw new ProductPartNotFoundException(String.format(errorMessage, id));
        } else
            throw new ProductPartNotFoundException(String.format(errorMessage, id));
    }

    public List<ProductPartEntity> findAllProductPartsByProduct(ProductEntity product) throws ProductPartNotFoundException {
        if (product != null)
            if (product.getId() != null)
                return productPartRepository.findAllByProduct(product);

        throw new ProductPartNotFoundException("Product is null or have null id");
    }
}
