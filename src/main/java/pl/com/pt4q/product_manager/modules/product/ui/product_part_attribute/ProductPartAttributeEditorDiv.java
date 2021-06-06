package pl.com.pt4q.product_manager.modules.product.ui.product_part_attribute;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import lombok.Getter;
import pl.com.pt4q.product_manager.modules.product.data.product_part_attribute.ProductPartAttributeEntity;
import pl.com.pt4q.product_manager.modules.product.services.product_series.ProductSeriesCrudService;

import java.util.List;

class ProductPartAttributeEditorDiv extends Div {

    private TextField attributeNameTextField = new TextField("Attribute name");

    @Getter
    private Binder<ProductPartAttributeEntity> productPartAttributeEntityBinder = new Binder<>();

    @Getter
    private ComboBox<String> unitComboBox = new ComboBox<>("Unit");

    private ProductPartAttributeEntity productPartAttribute;

    public ProductPartAttributeEditorDiv(ProductPartAttributeEntity productPartAttribute) {
        this.productPartAttribute = productPartAttribute;

        initProductPartAttributeEntityBinder();
        initFormFieldSizes();

        VerticalLayout layout = new VerticalLayout(attributeNameTextField);
        layout.setAlignItems(FlexComponent.Alignment.CENTER);
        layout.setWidthFull();

        setSizeFull();
        add(layout);
    }

    private void initFormFieldSizes() {
        String minWidth = "20%";
        String maxWidth = "60%";

        this.attributeNameTextField.setAutofocus(true);
        this.attributeNameTextField.setMinWidth(minWidth);
        this.attributeNameTextField.setMaxWidth(maxWidth);
    }

    private void initProductPartAttributeEntityBinder() {
        this.productPartAttributeEntityBinder
                .forField(attributeNameTextField)
                .asRequired("Part attribute can't be empty")
                .bind(ProductPartAttributeEntity::getAttributeName, ProductPartAttributeEntity::setAttributeName);
        this.productPartAttributeEntityBinder.setBean(this.productPartAttribute);
    }

    public void cleanForm() {
        populateForm(null);
    }

    public void populateForm(ProductPartAttributeEntity productPartAttribute) {
        this.productPartAttribute = productPartAttribute;
        this.productPartAttributeEntityBinder.readBean(this.productPartAttribute);
    }
}
