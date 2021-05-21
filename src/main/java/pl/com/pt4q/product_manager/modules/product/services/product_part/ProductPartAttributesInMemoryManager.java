package pl.com.pt4q.product_manager.modules.product.services.product_part;

import lombok.Getter;
import pl.com.pt4q.product_manager.modules.product.data.product_part.ProductPartAttributeEntity;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ProductPartAttributesInMemoryManager {

    private List<ProductPartAttributeEntity> attributes;
    private List<ProductPartAttributeEntity> activeAttributesToRemove;

    public ProductPartAttributesInMemoryManager(List<ProductPartAttributeEntity> attributes) {
        this.attributes = attributes;
    }

    public List<ProductPartAttributeEntity> getAttributesByName(String name) {
        return attributes.stream()
                .filter(attribute -> attribute.getAttributeName().equals(name))
                .collect(Collectors.toList());
    }

    public List<ProductPartAttributeEntity> addToList(ProductPartAttributeEntity attribute) {
        attributes.add(attribute);
        return attributes;
    }

    public List<ProductPartAttributeEntity> removeFromList(ProductPartAttributeEntity attribute){


        return attributes;
    }

    public List<ProductPartAttributeEntity> getElementsToSaveInDatabase() {
        return attributes.stream()
                .filter(attribute -> attribute.getId() == null)
                .collect(Collectors.toList());
    }

    public List<ProductPartAttributeEntity> getElementsToRemoveFromDatabase(){
        return activeAttributesToRemove.stream()
                .filter(attribute -> attribute.getId() != null)
                .collect(Collectors.toList());
    }

    public List<String> getManufacturersNames() {
        return getAttributes().stream()
                .map(ProductPartAttributeEntity::getAttributeName)
                .collect(Collectors.toList());
    }
}
