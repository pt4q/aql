package pl.com.pt4q.product_manager.modules.product.ui.product_part;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import pl.com.pt4q.product_manager.modules.product.data.product_part.ProductPartEntity;

class ProductPartFormDiv extends Div {


    private Binder<ProductPartEntity> partEntityBinder = new Binder<>(ProductPartEntity.class);

    //    private ComboBox<String> partManufacturerComboBox = new ComboBox<>("Part Manufacturer");
    private TextField partModelTextField = new TextField("Part model");
    private TextField partDescriptionTextField = new TextField("Part description");

    private ProductPartEntity partEntity;

    public ProductPartFormDiv(ProductPartEntity partEntity) {
        this.partEntity = partEntity;

        initPartBinder();
        initFormComponents();

        VerticalLayout formLayout = new VerticalLayout(partModelTextField, partDescriptionTextField);
        formLayout.setWidthFull();
        formLayout.setAlignItems(FlexComponent.Alignment.CENTER);

        setWidthFull();
        add(formLayout);
    }

    private void initFormComponents() {
        String minWidth = "20%";
        String maxWidth = "60%";

        partModelTextField.setMinWidth(minWidth);
        partModelTextField.setMaxWidth(maxWidth);
        if (partModelTextField.getValue().isEmpty())
            partModelTextField.setAutofocus(true);

        partDescriptionTextField.setMinWidth(minWidth);
        partDescriptionTextField.setMaxWidth(maxWidth);

    }

    private void initPartBinder() {
        partEntityBinder
                .forField(partModelTextField)
                .asRequired("Part model can't be empty")
                .bind(ProductPartEntity::getPartModelOrPartName, ProductPartEntity::setPartModelOrPartName);
        partEntityBinder
                .forField(partDescriptionTextField)
                .bind(ProductPartEntity::getPartDescription, ProductPartEntity::setPartDescription);
        partEntityBinder.setBean(this.partEntity);
    }

    public void populatePartForm(ProductPartEntity productPart) {
        partEntityBinder.readBean(productPart);
    }

    public ProductPartEntity getPartFromForm() {
        return partEntityBinder.getBean();
    }
}
