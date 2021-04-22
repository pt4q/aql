package pl.com.pt4q.product_manager.modules.product.services.manufacturer;

import lombok.AllArgsConstructor;
import lombok.Data;
import pl.com.pt4q.product_manager.modules.product.data.manufacturer.ManufacturerEntity;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class ManufacturersInMemoryManager {

    private List<ManufacturerEntity> categories;

    public Optional<ManufacturerEntity> findByName(String name) {
        return categories.stream()
                .filter(category -> category.getManufacturerName().equals(name))
                .findFirst();
    }

    public List<ManufacturerEntity> addToList(ManufacturerEntity category) {
        categories.add(category);
        return categories;
    }

    public List<ManufacturerEntity> getElementsToSaveInDatabase() {
        return categories.stream()
                .filter(category -> category.getId() == null)
                .collect(Collectors.toList());
    }

    public List<String> getManufacturersNames() {
        return getCategories().stream()
                .map(ManufacturerEntity::getManufacturerName)
                .collect(Collectors.toList());
    }
}
