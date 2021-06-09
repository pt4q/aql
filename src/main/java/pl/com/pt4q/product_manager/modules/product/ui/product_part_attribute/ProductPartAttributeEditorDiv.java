package pl.com.pt4q.product_manager.modules.product.ui.product_part_attribute;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import lombok.Getter;
import pl.com.pt4q.product_manager.modules.product.data.product_part_attribute.ProductPartAttributeEntity;
import pl.com.pt4q.product_manager.modules.product.data.unit.UnitEntity;
import pl.com.pt4q.product_manager.modules.product.services.unit.UnitsComboBoxManager;

import java.util.List;

class ProductPartAttributeEditorDiv extends Div {

    private TextField attributeNameTextField = new TextField("Attribute name");
    @Getter
    private ComboBox<String> unitComboBox = new ComboBox<>("Units");

    @Getter
    private Binder<ProductPartAttributeEntity> productPartAttributeEntityBinder = new Binder<>();

    private UnitsComboBoxManager unitsComboBoxManager;
    private ProductPartAttributeEntity productPartAttribute;

    public ProductPartAttributeEditorDiv(ProductPartAttributeEntity productPartAttribute) {
        this.productPartAttribute = productPartAttribute;

        initProductPartAttributeEntityBinder();
        initFormFieldSizes();

        HorizontalLayout layout = new HorizontalLayout(attributeNameTextField, unitComboBox);
        layout.setAlignItems(FlexComponent.Alignment.BASELINE);
        layout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        layout.setWidthFull();

        setSizeFull();
        add(layout);
    }

    private void initFormFieldSizes() {
        if (productPartAttribute == null)
            this.attributeNameTextField.setAutofocus(true);
        this.attributeNameTextField.setMinWidth("25%");
        this.attributeNameTextField.setMaxWidth("70%");

        this.unitComboBox.setMinWidth("10%");
        this.unitComboBox.setMaxWidth("15%");
    }

    private void initProductPartAttributeEntityBinder() {
        this.productPartAttributeEntityBinder
                .forField(attributeNameTextField)
                .asRequired("Part attribute name can't be empty")
                .bind(ProductPartAttributeEntity::getAttributeName, ProductPartAttributeEntity::setAttributeName);
        this.productPartAttributeEntityBinder
                .forField(unitComboBox)
                .asRequired("The part attribute mush have defined units")
                .bind(
                        attributeEntity -> attributeEntity.getUnits().getUnits(),
                        (attributeEntity, s) -> attributeEntity.setUnits(unitsComboBoxManager.getByUnitsString(s))
                );
//        this.productPartAttributeEntityBinder.setBean(productPartAttribute);
    }

    public void setUnitComboBoxOptions(List<UnitEntity> units) {
        this.unitsComboBoxManager = new UnitsComboBoxManager(units);
        this.unitComboBox.setItems(this.unitsComboBoxManager.getAllUnitFormattedStrings());

        this.productPartAttributeEntityBinder.setBean(productPartAttribute);
    }

    public void cleanForm() {
        populateForm(null);
    }

    public void populateForm(ProductPartAttributeEntity productPartAttribute) {
        this.productPartAttribute = productPartAttribute;
        this.productPartAttributeEntityBinder.readBean(this.productPartAttribute);
    }
}
