package pl.com.pt4q.product_manager.modules.product.services.product_series.exceptions;

public class ProductSeriesNotFoundException extends Exception{
    public ProductSeriesNotFoundException() {
    }

    public ProductSeriesNotFoundException(String message) {
        super(message);
    }
}
