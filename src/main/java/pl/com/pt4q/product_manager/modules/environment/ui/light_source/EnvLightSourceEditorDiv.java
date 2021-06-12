package pl.com.pt4q.product_manager.modules.environment.ui.light_source;

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
import pl.com.pt4q.product_manager.modules.environment.data.weee.EnvWeeeEntity;

class EnvLightSourceEditorDiv extends Div {

    private TextField productComboBox = new TextField("Product");
    private TextArea productDescriptionTextArea = new TextArea("Product description");
    private DatePicker validFromDatePicker = new DatePicker("Valid from");
    private DatePicker validToDatePicker = new DatePicker("Valid to");

    private NumberField netWeightNumberField = new NumberField("Net weight");
    private ComboBox<String> netWeightUnitComboBox = new ComboBox<>("Net weight unit");
    private ComboBox<String> lightSourceTypeComboBox = new ComboBox<>("Light source type");
    private ComboBox<String> lightSourceReplaceableComboBox = new ComboBox<>("Light source replaceable");

    private Binder<EnvWeeeEntity> lsEntityBinder = new Binder<>();

    public EnvLightSourceEditorDiv() {
        initFields();
        initWeightLayout();
        initBinder();

        setMinWidth("20%");
        setMaxWidth("40%");
        add(initFormLayoutDiv());
    }

    private Div initFormLayoutDiv() {
        VerticalLayout formLayout = new VerticalLayout();
        formLayout.add(
                this.productComboBox,
                this.productDescriptionTextArea,
                this.validFromDatePicker,
                this.validToDatePicker,
                initWeightLayout(),
                this.lightSourceTypeComboBox,
                this.lightSourceReplaceableComboBox
        );
        formLayout.setSizeFull();
        formLayout.setAlignItems(FlexComponent.Alignment.CENTER);

        Div layoutDiv = new Div();
        layoutDiv.add(formLayout);
        layoutDiv.setSizeFull();
        return layoutDiv;
    }

    private HorizontalLayout initWeightLayout() {
        HorizontalLayout hl = new HorizontalLayout();
        hl.add(this.netWeightNumberField, this.netWeightUnitComboBox);
        hl.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        hl.setAlignItems(FlexComponent.Alignment.BASELINE);
        hl.setSizeFull();
        return hl;
    }

    private void initFields() {
        this.productComboBox.setSizeFull();
        this.productComboBox.setReadOnly(true);
        this.productDescriptionTextArea.setSizeFull();
        this.productDescriptionTextArea.setReadOnly(true);

        this.validFromDatePicker.setSizeFull();
        this.validFromDatePicker.setReadOnly(true);
        this.validToDatePicker.setSizeFull();
        this.validToDatePicker.setReadOnly(true);

        this.netWeightNumberField.setSizeFull();
        this.netWeightNumberField.setMinWidth("10%");
        this.netWeightUnitComboBox.setSizeFull();
        this.netWeightUnitComboBox.setMinWidth("10%");

        this.lightSourceTypeComboBox.setSizeFull();
        this.lightSourceReplaceableComboBox.setSizeFull();
    }

    private void initBinder() {

    }
}
