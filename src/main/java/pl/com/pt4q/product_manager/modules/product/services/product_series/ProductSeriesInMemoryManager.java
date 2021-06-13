package pl.com.pt4q.product_manager.modules.product.services.product_series;

import lombok.AllArgsConstructor;
import lombok.Data;
import pl.com.pt4q.product_manager.modules.product.data.product_series.ProductSeriesEntity;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class ProductSeriesInMemoryManager {

    private List<ProductSeriesEntity> parts;

    public Optional<ProductSeriesEntity> getByName(String name) {
        return parts.stream()
                .filter(series -> series.getSeries().equals(name))
                .findFirst();
    }

    public List<ProductSeriesEntity> addToList(ProductSeriesEntity part) {
        parts.add(part);
        return parts;
    }

    public List<ProductSeriesEntity> getElementsToSaveInDatabase() {
        return parts.stream()
                .filter(category -> category.getId() == null)
                .collect(Collectors.toList());
    }

    public List<String> getPartNames() {
        return getParts().stream()
                .map(ProductSeriesEntity::getSeries)
                .collect(Collectors.toList());
    }
}
