package pl.com.pt4q.product_manager.modules.product.ui.product_part_attribute;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import pl.com.pt4q.product_manager.modules.product.data.product.ProductEntity;
import pl.com.pt4q.product_manager.modules.product.data.product_part_attribute.ProductPartAttributeEntity;

class ProductPartAttributeEditorDiv extends Div {

    private TextField attributeNameTextField = new TextField("Attribute name");
    private ComboBox<String> attributeValueVersionComboBox = new ComboBox<>("Attribute value version");

    private Binder<ProductPartAttributeEntity> productPartAttributeEntityBinder = new Binder<>();

    private ProductPartAttributeEntity productPartAttribute;

    public ProductPartAttributeEditorDiv(ProductPartAttributeEntity productPartAttribute) {
        this.productPartAttribute = productPartAttribute;

        initAttributeValueVersionComboBox();
        initProductPartAttributeEntityBinder();

        initFormFieldSizes();

        VerticalLayout layout = new VerticalLayout(attributeNameTextField, attributeValueVersionComboBox);
        layout.setAlignItems(FlexComponent.Alignment.CENTER);
        layout.setWidthFull();

        setSizeFull();
        add(layout);
    }

    private void initFormFieldSizes() {
        String minWidth = "20%";
        String maxWidth = "60%";

        this.attributeNameTextField.setMinWidth(minWidth);
        this.attributeNameTextField.setMaxWidth(maxWidth);
        this.attributeValueVersionComboBox.setMinWidth(minWidth);
        this.attributeValueVersionComboBox.setMaxWidth(maxWidth);
    }

    private void initProductPartAttributeEntityBinder() {
        this.productPartAttributeEntityBinder
                .forField(attributeNameTextField)
                .bind(ProductPartAttributeEntity::getAttributeName, ProductPartAttributeEntity::setAttributeName);
        this.productPartAttributeEntityBinder
                .forField(attributeValueVersionComboBox)
                .bind(
                        productPartAttributeEntity -> createAttributeValueVersionComboBoxString(productPartAttributeEntity),
                        (productPartAttributeEntity, s) -> {}
                );
        this.productPartAttributeEntityBinder.setBean(this.productPartAttribute);
    }

    private String createAttributeValueVersionComboBoxString(ProductPartAttributeEntity productPartAttribute){
        return String.format("%s - %s: %s",
                productPartAttribute.getActualValueVersion().getProductSeries().getSeries(),
                productPartAttribute.getAttributeName(),
                productPartAttribute.getActualValueVersion().getAttributeValue()
        );
    }

    private void initAttributeValueVersionComboBox(){

    }

    private void cleanForm() {
        populateForm(null);
    }

    public void populateForm(ProductPartAttributeEntity productPartAttribute) {
        this.productPartAttribute = productPartAttribute;
        this.productPartAttributeEntityBinder.readBean(this.productPartAttribute);
    }
}
