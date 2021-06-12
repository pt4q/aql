package pl.com.pt4q.product_manager.modules.environment.ui.bat;

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

class EnvBatEditorDiv extends Div {

    private TextField productComboBox = new TextField("Product");
    private TextArea productDescriptionTextArea = new TextArea("Product description");
    private DatePicker validFromDatePicker = new DatePicker("Valid from");
    private DatePicker validToDatePicker = new DatePicker("Valid to");

    //initItemsPerFractionAndItemsPerFractionUnitLayout()
    private NumberField itemsPerFractionNumberField = new NumberField("Items per fraction");
    private ComboBox<String> itemsPerFractionUnitComboBox = new ComboBox<>("Items per fraction unit");

    //initWeightLayout()
    private NumberField netWeightNumberField = new NumberField("Net weight");
    private ComboBox<String> netWeightUnitComboBox = new ComboBox<>("Net weight unit");

    //initBatteryTypeAndChemicalSystemLayout()
    private ComboBox<String> batteryTypeComboBox = new ComboBox<>("Battery type");
    private ComboBox<String> chemicalSystemComboBox = new ComboBox<>("Chemical system");

    private ComboBox<String> primarySecondaryComboBox = new ComboBox<>("Primary / Secondary");

    private ComboBox<String> manufacturerComboBox = new ComboBox<>("Fraction brand name");

    //initNumberOfCellsAndNumberOfCellsUnitLayout()
    private NumberField numberOfCellsInPackNumberField = new NumberField("Number of cells per Akku pack");
    private ComboBox<String> numberOfCellsInPackUnitField = new ComboBox<>("Number of cells unit");

    //initCapacityAndCapacityUnitLayout()
    private NumberField capacityNumberField = new NumberField("Capacity");
    private ComboBox<String> capacityUnitComboBox = new ComboBox<>("Capacity unit");

    private ComboBox<String> buttonTypeBattery = new ComboBox<>("Button cell type");

    //initMercuryContentAndMercuryContentUnitLayout()
    private NumberField mercuryContentNumberField = new NumberField("Mercury content");
    private ComboBox<String> mercuryContentUnitComboBox = new ComboBox<>("Mercury content unit");

    private ComboBox<String> sourceTypeComboBox = new ComboBox<>("Source type");


    private Binder<EnvWeeeEntity> batEntityBinder = new Binder<>();

    public EnvBatEditorDiv() {
        initOtherFields();
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
                initItemsPerFractionAndItemsPerFractionUnitLayout(),
                initWeightLayout(),
                this.manufacturerComboBox,
                initNumberOfCellsAndNumberOfCellsUnitLayout(),
                initCapacityAndCapacityUnitLayout(),
                this.buttonTypeBattery,
                initBatteryTypeAndChemicalSystemLayout(),
                this.primarySecondaryComboBox,
                initMercuryContentAndMercuryContentUnitLayout(),
                this.sourceTypeComboBox
        );
        formLayout.setSizeFull();
        formLayout.setAlignItems(FlexComponent.Alignment.CENTER);

        Div layoutDiv = new Div();
        layoutDiv.add(formLayout);
        layoutDiv.setSizeFull();
        return layoutDiv;
    }
    private HorizontalLayout initItemsPerFractionAndItemsPerFractionUnitLayout() {
        this.itemsPerFractionNumberField.setSizeFull();
        this.itemsPerFractionNumberField.setMinWidth("20%");
        this.itemsPerFractionNumberField.setSizeFull();
        this.itemsPerFractionNumberField.setMinWidth("10%");

        HorizontalLayout hl = new HorizontalLayout();
        hl.add(this.itemsPerFractionNumberField, this.itemsPerFractionUnitComboBox);
        hl.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        hl.setAlignItems(FlexComponent.Alignment.BASELINE);
        hl.setSizeFull();
        return hl;
    }

    private HorizontalLayout initWeightLayout() {
        this.netWeightNumberField.setSizeFull();
        this.netWeightNumberField.setMinWidth("20%");
        this.netWeightUnitComboBox.setSizeFull();
        this.netWeightUnitComboBox.setMinWidth("10%");
        this.netWeightUnitComboBox.setReadOnly(true);

        HorizontalLayout hl = new HorizontalLayout();
        hl.add(this.netWeightNumberField, this.netWeightUnitComboBox);
        hl.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        hl.setAlignItems(FlexComponent.Alignment.BASELINE);
        hl.setSizeFull();
        return hl;
    }

    private HorizontalLayout initBatteryTypeAndChemicalSystemLayout() {
        this.batteryTypeComboBox.setSizeFull();
        this.batteryTypeComboBox.setMinWidth("20%");
        this.chemicalSystemComboBox.setSizeFull();
        this.chemicalSystemComboBox.setMinWidth("10%");

        HorizontalLayout hl = new HorizontalLayout();
        hl.add(this.batteryTypeComboBox, this.chemicalSystemComboBox);
        hl.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        hl.setAlignItems(FlexComponent.Alignment.BASELINE);
        hl.setSizeFull();
        return hl;
    }

    private HorizontalLayout initNumberOfCellsAndNumberOfCellsUnitLayout() {
        this.numberOfCellsInPackNumberField.setSizeFull();
        this.numberOfCellsInPackNumberField.setMinWidth("20%");
        this.numberOfCellsInPackUnitField.setSizeFull();
        this.numberOfCellsInPackUnitField.setMinWidth("10%");
        this.numberOfCellsInPackUnitField.setReadOnly(true);

        HorizontalLayout hl = new HorizontalLayout();
        hl.add(this.numberOfCellsInPackNumberField, this.numberOfCellsInPackUnitField);
        hl.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        hl.setAlignItems(FlexComponent.Alignment.BASELINE);
        hl.setSizeFull();
        return hl;
    }

    private HorizontalLayout initCapacityAndCapacityUnitLayout() {
        this.capacityNumberField.setSizeFull();
        this.capacityNumberField.setMinWidth("20%");
        this.capacityUnitComboBox.setSizeFull();
        this.capacityUnitComboBox.setMinWidth("10%");
        this.capacityUnitComboBox.setReadOnly(true);

        HorizontalLayout hl = new HorizontalLayout();
        hl.add(this.capacityNumberField, this.capacityUnitComboBox);
        hl.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        hl.setAlignItems(FlexComponent.Alignment.BASELINE);
        hl.setSizeFull();
        return hl;
    }

    private HorizontalLayout initMercuryContentAndMercuryContentUnitLayout() {
        this.mercuryContentNumberField.setSizeFull();
        this.mercuryContentNumberField.setMinWidth("20%");
        this.mercuryContentUnitComboBox.setSizeFull();
        this.mercuryContentUnitComboBox.setMinWidth("10%");
        this.mercuryContentUnitComboBox.setReadOnly(true);

        HorizontalLayout hl = new HorizontalLayout();
        hl.add(this.mercuryContentNumberField, this.mercuryContentUnitComboBox);
        hl.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        hl.setAlignItems(FlexComponent.Alignment.BASELINE);
        hl.setSizeFull();
        return hl;
    }

    private void initOtherFields() {
        this.productComboBox.setSizeFull();
        this.productComboBox.setReadOnly(true);
        this.productDescriptionTextArea.setSizeFull();
        this.productDescriptionTextArea.setReadOnly(true);

        this.validFromDatePicker.setSizeFull();
        this.validFromDatePicker.setReadOnly(true);
        this.validToDatePicker.setSizeFull();
        this.validToDatePicker.setReadOnly(true);

        this.primarySecondaryComboBox.setSizeFull();
        this.manufacturerComboBox.setSizeFull();
        this.buttonTypeBattery.setSizeFull();
        this.sourceTypeComboBox.setSizeFull();
    }

    private void initBinder() {

    }
}
