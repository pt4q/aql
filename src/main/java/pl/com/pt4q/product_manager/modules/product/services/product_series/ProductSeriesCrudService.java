package pl.com.pt4q.product_manager.modules.product.services.product_series;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.com.pt4q.product_manager.modules.product.data.product_series.ProductSeriesEntity;
import pl.com.pt4q.product_manager.modules.product.services.product_series.exceptions.ProductSeriesAlreadyExistsException;
import pl.com.pt4q.product_manager.modules.product.services.product_series.exceptions.ProductSeriesNotFoundException;
import pl.com.pt4q.product_manager.service_utils.CustomCrudServiceInterface;

import java.util.List;
import java.util.Optional;

@Service
public class ProductSeriesCrudService implements CustomCrudServiceInterface<ProductSeriesEntity, Long, ProductSeriesNotFoundException, ProductSeriesAlreadyExistsException> {

    @Autowired
    private ProductSeriesCrudRepository productSeriesCrudRepository;

    @Override
    public ProductSeriesEntity create(ProductSeriesEntity entity) throws ProductSeriesAlreadyExistsException {
        try {
            getByIdOrThrow(entity.getId());
        } catch (ProductSeriesNotFoundException e) {
            return productSeriesCrudRepository.save(entity);
        }
        throw new ProductSeriesAlreadyExistsException(String.format("Series %s already exists on id:%d", entity.getSeries(), entity.getId()));
    }

    @Override
    public ProductSeriesEntity getByIdOrThrow(Long id) throws ProductSeriesNotFoundException {
        if (id != null) {
            Optional<ProductSeriesEntity> seriesEntity = productSeriesCrudRepository.findById(id);
            if (seriesEntity.isPresent())
                return seriesEntity.get();
            else
                throw new ProductSeriesNotFoundException(String.format("Series with id:%d not exists", id));
        }
        throw new ProductSeriesNotFoundException(String.format("Series id:%d", id));
    }

    @Override
    public ProductSeriesEntity updateOrThrow(ProductSeriesEntity entity) throws ProductSeriesNotFoundException {
        getByIdOrThrow(entity.getId());
        return productSeriesCrudRepository.save(entity);
    }

    @Override
    public List<ProductSeriesEntity> getAll() {
        return productSeriesCrudRepository.findAll();
    }
}
