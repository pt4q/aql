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
        return saveInDatabase(
                product,
                String.format("Product %s (id:%d) with category: %s has been created",
                        product.getProductSku(),
                        product.getId(),
                        product.getProductCategory().getCategoryName()
                )
        );
    }

    public ProductEntity update(ProductEntity product) {
        return saveInDatabase(
                product,
                String.format("Product %s (id:%d) with category: %s has been updated",
                        product.getProductSku(),
                        product.getId(),
                        product.getProductCategory().getCategoryName()
                )
        );
    }

    private ProductEntity saveInDatabase(ProductEntity product, String message) {
        product = productCrudRepository.save(product);
        log.info(message);
        return product;
    }
}
