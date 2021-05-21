package pl.com.pt4q.product_manager.modules.product.services.product_part;

import lombok.Builder;
import lombok.Getter;
import pl.com.pt4q.product_manager.modules.product.data.product_part.ProductPartAttributeEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Getter
public class ProductPartAttributesInMemoryManager {

    private List<ProductPartAttributeEntity> activeAttributesToAdd;
    private List<ProductPartAttributeEntity> activeAttributesToRemove;

    public ProductPartAttributesInMemoryManager(List<ProductPartAttributeEntity> activeAttributesToAdd) {
        this.activeAttributesToAdd = activeAttributesToAdd;
    }

    public List<ProductPartAttributeEntity> getAttributesByName(String name) {
        return activeAttributesToAdd.stream()
                .filter(attribute -> attribute.getAttributeName().equals(name))
                .collect(Collectors.toList());
    }

    public List<ProductPartAttributeEntity> addToList(ProductPartAttributeEntity attribute) {
        activeAttributesToAdd.add(attribute);
        return activeAttributesToAdd;
    }

    public List<ProductPartAttributeEntity> removeFromList(ProductPartAttributeEntity attribute){


        return activeAttributesToAdd;
    }

    public List<ProductPartAttributeEntity> getElementsToSaveInDatabase() {
        return activeAttributesToAdd.stream()
                .filter(attribute -> attribute.getId() == null)
                .collect(Collectors.toList());
    }

    public List<ProductPartAttributeEntity> getElementsToRemoveFromDatabase(){
        return activeAttributesToRemove.stream()
                .filter(attribute -> attribute.getId() != null)
                .collect(Collectors.toList());
    }

    public List<String> getManufacturersNames() {
        return getActiveAttributesToAdd().stream()
                .map(ProductPartAttributeEntity::getAttributeName)
                .collect(Collectors.toList());
    }
}
