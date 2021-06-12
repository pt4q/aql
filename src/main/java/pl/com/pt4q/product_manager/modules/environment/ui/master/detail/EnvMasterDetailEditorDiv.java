package pl.com.pt4q.product_manager.modules.environment.ui.master.detail;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import lombok.Getter;
import pl.com.pt4q.product_manager.modules.environment.data.master.EnvMasterEntity;

@Getter
class EnvMasterDetailEditorDiv extends Div {

    private TextField productComboBox = new TextField("Product");
    private TextArea productDescriptionTextArea = new TextArea("Product description");
    private DatePicker validFromDatePicker = new DatePicker("Valid from");
    private DatePicker validToDatePicker = new DatePicker("Valid to");
    private NumberField grossWeightNumberField = new NumberField("Gross weight");
    private ComboBox<String> grossWeightUnitComboBox = new ComboBox<>("Gross weight unit");

    private Binder<EnvMasterEntity> masterBinder = new Binder<>();

    public EnvMasterDetailEditorDiv() {
        initFields();
        initBinder();

        VerticalLayout formLayout = new VerticalLayout();
        formLayout.add(
                productComboBox,
                productDescriptionTextArea,
                validFromDatePicker, validToDatePicker,
                initWeightLayout()
        );

        formLayout.setWidthFull();
        formLayout.setAlignItems(FlexComponent.Alignment.CENTER);

        setMinWidth("20%");
        setMaxWidth("40%");
        add(formLayout);
    }

    private HorizontalLayout initWeightLayout() {
        HorizontalLayout hl = new HorizontalLayout(grossWeightNumberField, grossWeightUnitComboBox);
        hl.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        hl.setAlignItems(FlexComponent.Alignment.CENTER);
        hl.setWidthFull();

        return hl;
    }

    private void initFields() {
        this.productComboBox.setSizeFull();
        this.productComboBox.setReadOnly(true);
        this.productDescriptionTextArea.setSizeFull();
        this.productDescriptionTextArea.setReadOnly(true);

        this.validFromDatePicker.setWidthFull();
        this.validToDatePicker.setWidthFull();
        this.grossWeightNumberField.setWidthFull();
        this.grossWeightNumberField.setMinWidth("10%");
        this.grossWeightUnitComboBox.setWidthFull();
        this.grossWeightUnitComboBox.setMinWidth("10%");
    }

    private void initBinder(){

    }
}
