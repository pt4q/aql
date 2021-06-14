package pl.com.pt4q.product_manager.modules.product.services.product;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.com.pt4q.product_manager.modules.product.data.product.ProductEntity;

@Log4j2
@Service
class ProductCrudSaver {

    @Autowired
    private ProductCrudRepository productCrudRepository;

    public ProductEntity save(ProductEntity product) {
        product = productCrudRepository.save(product);
        log.info(String.format("Product %s (id:%d) with category: %s has been created",
                product.getSku(),
                product.getId(),
                product.getProductCategory().getCategoryName()
        ));
        return product;
    }

    public ProductEntity update(ProductEntity product) {
        product = productCrudRepository.save(product);
        log.info(String.format("Product %s (id:%d) with category: %s has been updated",
                product.getSku(),
                product.getId(),
                product.getProductCategory().getCategoryName()
                )
        );
        return product;
    }
}
