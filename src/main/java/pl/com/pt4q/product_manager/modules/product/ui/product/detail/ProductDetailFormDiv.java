package pl.com.pt4q.product_manager.modules.product.ui.product.detail;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.textfield.TextField;

class ProductDetailFormDiv extends Div {

//    private H2 formLabel = new H2("Product info");
    private ComboBox productCategoryComboBox = new ComboBox("Product category");
    private ComboBox productManufacturerComboBox = new ComboBox("Product manufacturer");
    private TextField productSkuTextField = new TextField("Product SKU");

    public ProductDetailFormDiv() {
        FormLayout formLayout = new FormLayout(
//                formLabel,
                productCategoryComboBox,
                productManufacturerComboBox,
                productSkuTextField);

        add(formLayout);
    }
}
