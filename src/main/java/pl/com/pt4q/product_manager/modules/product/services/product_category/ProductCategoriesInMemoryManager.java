package pl.com.pt4q.product_manager.modules.product.services.product_category;

import lombok.AllArgsConstructor;
import lombok.Data;
import pl.com.pt4q.product_manager.modules.product.data.product_category.ProductCategoryEntity;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class ProductCategoriesInMemoryManager {

    private List<ProductCategoryEntity> categories;

    public Optional<ProductCategoryEntity> findByName(String name) {
        return categories.stream()
                .filter(category -> category.getCategoryName().equals(name))
                .findFirst();
    }

    public List<ProductCategoryEntity> addToList(ProductCategoryEntity category) {
        categories.add(category);
        return categories;
    }

    public List<ProductCategoryEntity> getElementsToSaveInDatabase() {
        return categories.stream()
                .filter(category -> category.getId() == null)
                .collect(Collectors.toList());
    }

    public List<String> getCategoriesNames() {
        return getCategories().stream()
                .map(ProductCategoryEntity::getCategoryName)
                .collect(Collectors.toList());
    }
}
