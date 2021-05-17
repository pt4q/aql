package pl.com.pt4q.product_manager.modules.product.services.product_part;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.com.pt4q.product_manager.modules.product.data.product.ProductEntity;
import pl.com.pt4q.product_manager.modules.product.data.product_part.ProductPartAttributeEntity;
import pl.com.pt4q.product_manager.modules.product.data.product_part.ProductPartEntity;
import pl.com.pt4q.product_manager.modules.product.services.product.exceptions.ProductNotFoundException;
import pl.com.pt4q.product_manager.modules.product.services.product_part.exceptions.ProductPartNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.Set;

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

    public List<ProductPartEntity> findAllProductParts(ProductEntity product) throws ProductNotFoundException {
        if (product != null)
            if (product.getId() != null) {
                return productPartRepository.findAllByProduct(product);
            }
        throw new ProductNotFoundException("Product is null or have null id");
    }

//    public List<ProductPartAttributeEntity> findAllProductPartAttributes(ProductPartEntity productPart){
//        return productPartAttributeCrudRepository.findAllByPart(productPart);
//    }
}
