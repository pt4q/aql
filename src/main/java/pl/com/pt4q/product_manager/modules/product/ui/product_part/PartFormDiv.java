package pl.com.pt4q.product_manager.modules.product.ui.product_part;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import pl.com.pt4q.product_manager.modules.product.data.product_part.ProductPartEntity;
import pl.com.pt4q.product_manager.modules.product.services.product_series.ProductSeriesCrudService;
import pl.com.pt4q.product_manager.modules.product.services.product_series.ProductSeriesInMemoryManager;

class PartFormDiv extends Div {

    private VerticalLayout formLayout = new VerticalLayout();
    private Binder<ProductPartEntity> partEntityBinder = new Binder<>(ProductPartEntity.class);

    //    private ComboBox<String> partManufacturerComboBox = new ComboBox<>("Part Manufacturer");
    private TextField partModelTextField = new TextField("Part model");
    private TextField partDescriptionTextField = new TextField("Part description");

    private ProductPartEntity productPart;

    public PartFormDiv(ProductPartEntity productPart) {
        this.productPart = productPart;

        initPartBinder();
        initFormComponentsSize();
        initFormLayout();

        setWidthFull();
        add(formLayout);
    }

    private void initFormLayout() {
//        formLayout.add(partManufacturerComboBox);
        formLayout.add(partModelTextField);
        formLayout.add(partDescriptionTextField);
//        formLayout.add(productSeriesComboBox);
//        formLayout.add(validFromDateDatePicker);

        formLayout.setWidthFull();
        formLayout.setAlignItems(FlexComponent.Alignment.CENTER);
    }

    private void initFormComponentsSize() {
        String minWidth = "20%";
        String maxWidth = "60%";

//        partManufacturerComboBox.setMinWidth(minWidth);
//        partManufacturerComboBox.setMaxWidth(maxWidth);
        partModelTextField.setMinWidth(minWidth);
        partModelTextField.setMaxWidth(maxWidth);
        partDescriptionTextField.setMinWidth(minWidth);
        partDescriptionTextField.setMaxWidth(maxWidth);

    }

    private void initPartBinder() {
//
//        partEntityBinder
//                .forField(partManufacturerComboBox)
//                .asRequired("Part manufacturer can't be empty");
        partEntityBinder
                .forField(partModelTextField)
                .asRequired("Part model can't be empty")
                .bind(ProductPartEntity::getPartModelOrPartName, ProductPartEntity::setPartModelOrPartName);
        partEntityBinder
                .forField(partDescriptionTextField)
                .bind(ProductPartEntity::getPartDescription, ProductPartEntity::setPartDescription);

    }

    public void populateForm(ProductPartEntity productPart){
        this.productPart = productPart;
        partEntityBinder.readBean(productPart);
    }
}
