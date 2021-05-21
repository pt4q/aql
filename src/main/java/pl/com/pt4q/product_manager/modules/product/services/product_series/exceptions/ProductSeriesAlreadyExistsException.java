package pl.com.pt4q.product_manager.modules.product.services.product_series.exceptions;

public class ProductSeriesAlreadyExistsException extends Exception {

    public ProductSeriesAlreadyExistsException() {
    }

    public ProductSeriesAlreadyExistsException(String message) {
        super(message);
    }
}
