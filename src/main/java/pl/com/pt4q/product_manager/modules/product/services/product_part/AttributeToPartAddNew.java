package pl.com.pt4q.product_manager.modules.product.services.product_part;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.com.pt4q.product_manager.modules.product.data.product_part.ProductPartAttributeEntity;
import pl.com.pt4q.product_manager.modules.product.data.product_part.ProductPartEntity;

import java.util.List;

@Getter
@AllArgsConstructor
public class AttributeToPartAddNew {

    private ProductPartEntity productPart;

    public void addNewAttributeVersion(ProductPartAttributeEntity newAttribute){

    }

    private ProductPartAttributeEntity findPreviousAttribute(List<ProductPartAttributeEntity> attributes){
        return null;
    }

    private List<ProductPartAttributeEntity> setPreviousAttributeNotActual(){
        return null;
    }
}
