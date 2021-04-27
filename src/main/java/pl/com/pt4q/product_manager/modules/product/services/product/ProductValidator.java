package pl.com.pt4q.product_manager.modules.product.services.product;

import lombok.AllArgsConstructor;
import pl.com.pt4q.product_manager.modules.product.data.product.ProductEntity;
import pl.com.pt4q.product_manager.modules.product.services.product.exceptions.ProductValidatorException;

@AllArgsConstructor
class ProductValidator {

    private ProductEntity product;

    public void validate() throws ProductValidatorException {
        checkManufacturer(product);
        checkProductCategory(product);
    }

    private void checkManufacturer(ProductEntity product) throws ProductValidatorException {
        if (product.getManufacturer() == null)
            throwValidatorException("Manufacturer can't be empty");
    }

    private void checkProductCategory(ProductEntity product) throws ProductValidatorException {
        if(product.getProductCategory() == null)
            throwValidatorException("Product category can't be empty");
    }

    private void throwValidatorException(String message) throws ProductValidatorException {
        throw new ProductValidatorException(message);
    }
}
