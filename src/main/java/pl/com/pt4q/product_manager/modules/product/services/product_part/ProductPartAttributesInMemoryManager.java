package pl.com.pt4q.product_manager.modules.product.services.product_part;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import pl.com.pt4q.product_manager.modules.product.data.manufacturer.ManufacturerEntity;
import pl.com.pt4q.product_manager.modules.product.data.product_part.ProductPartAttributeEntity;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class ProductPartAttributesInMemoryManager {

    private List<ProductPartAttributeEntity> attributes;


    public Optional<ProductPartAttributeEntity> getByName(String name) {
        return attributes.stream()
                .filter(attribute -> attribute.getAttributeName().equals(name))
                .findFirst();
    }

    public List<ProductPartAttributeEntity> addToList(ProductPartAttributeEntity attribute) {
        attributes.add(attribute);
        return attributes;
    }

    public List<ProductPartAttributeEntity> getElementsToSaveInDatabase() {
        return attributes.stream()
                .filter(category -> category.getId() == null)
                .collect(Collectors.toList());
    }

    public List<String> getManufacturersNames() {
        return getAttributes().stream()
                .map(ProductPartAttributeEntity::getAttributeName)
                .collect(Collectors.toList());
    }
}
