package pl.com.pt4q.product_manager.modules.product.ui.product_part_attribute;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import pl.com.pt4q.product_manager.modules.product.data.product_part.ProductPartEntity;
import pl.com.pt4q.product_manager.modules.product.data.product_part_attribute.ProductPartAttributeEntity;

class ProductPartAttributeEditorDiv extends Div {

    private TextField attributeNameTextField = new TextField("Attribute name");
    private ComboBox<String> attributeValueVersionComboBox = new ComboBox<>("Attribute value version");

    private Binder<ProductPartAttributeEntity> productPartAttributeEntityBinder = new Binder<>();

    private ProductPartEntity productPart;

    public ProductPartAttributeEditorDiv() {
        VerticalLayout layout = new VerticalLayout(attributeNameTextField, attributeValueVersionComboBox);
        layout.setAlignItems(FlexComponent.Alignment.CENTER);
        layout.setWidthFull();

        setSizeFull();
        add(layout);
    }

    private void initFormFieldSizes(){
        String minWidth = "20%";
        String maxWidth = "60%";

        this.attributeNameTextField.setMinWidth(minWidth);
        this.attributeNameTextField.setMaxWidth(maxWidth);
        this.attributeValueVersionComboBox.setMinWidth(minWidth);
        this.attributeValueVersionComboBox.setMaxWidth(maxWidth);
    }
}
