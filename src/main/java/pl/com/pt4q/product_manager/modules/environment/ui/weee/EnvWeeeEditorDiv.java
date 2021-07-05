package pl.com.pt4q.product_manager.modules.environment.ui.weee;

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

class EnvWeeeEditorDiv extends Div {

    private TextField productComboBox = new TextField("Product");
    private TextArea productDescriptionTextArea = new TextArea("Product description");
    private DatePicker validFromDatePicker = new DatePicker("Valid from");
    private DatePicker validToDatePicker = new DatePicker("Valid to");

    private NumberField netWeightNumberField = new NumberField("Net weight");
    private ComboBox<String> netWeightUnitComboBox = new ComboBox<>("Net weight unit");

    private NumberField itemHeightNumberField = new NumberField("Height");
    private NumberField itemLengthNumberField = new NumberField("Length");
    private NumberField itemDepthNumberField = new NumberField("Depth");
    private ComboBox<String> lengthUnitComboBox = new ComboBox<>("Length unit");

    private ComboBox<String> sourceTypeComboBox = new ComboBox<>("Source type");

    private Binder<EnvWeeeEntity> weeeEntityBinder = new Binder<>();

    public EnvWeeeEditorDiv() {
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
                initLengthLayout(),
                initWeightLayout(),
                this.sourceTypeComboBox
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
//        hl.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.BASELINE);
        hl.setAlignItems(FlexComponent.Alignment.BASELINE);
        hl.setSizeFull();
        return hl;
//        Div weightLayoutDiv = new Div();
//        weightLayoutDiv.add(hl);
//        weightLayoutDiv.setWidthFull();
//        return weightLayoutDiv;
    }

    private HorizontalLayout initLengthLayout() {
        HorizontalLayout hl = new HorizontalLayout();
        hl.add(this.itemHeightNumberField, this.itemLengthNumberField, this.itemDepthNumberField, this.lengthUnitComboBox);
        hl.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
//        hl.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.BASELINE);
        hl.setAlignItems(FlexComponent.Alignment.BASELINE);
        hl.setSizeFull();
        return hl;
//        Div lengthLayoutDiv = new Div();
//        lengthLayoutDiv.add(hl);
////        lengthLayoutDiv.setWidthFull();
//        lengthLayoutDiv.setMinWidth("20%");
//        lengthLayoutDiv.setMaxWidth("40%");
//        return lengthLayoutDiv;
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

        this.itemHeightNumberField.setSizeFull();
        this.itemHeightNumberField.setMinWidth("10%");
        this.itemLengthNumberField.setSizeFull();
        this.itemLengthNumberField.setMinWidth("10%");
        this.itemDepthNumberField.setSizeFull();
        this.itemDepthNumberField.setMinWidth("10%");
        this.lengthUnitComboBox.setSizeFull();
        this.lengthUnitComboBox.setMinWidth("15%");

        this.sourceTypeComboBox.setSizeFull();
    }

    private void initBinder() {


        this.weeeEntityBinder.setBean(new EnvWeeeEntity());
    }

    private void cleanForm(){
        populateForm(null);
    }

    public void populateForm(EnvWeeeEntity weeeEntity){
        this.weeeEntityBinder.readBean(weeeEntity);
    }
}
