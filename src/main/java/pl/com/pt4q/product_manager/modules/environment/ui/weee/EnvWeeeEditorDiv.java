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
import lombok.Getter;
import pl.com.pt4q.product_manager.modules.environment.data.weee.EnvSourceTypeEnumWrapper;
import pl.com.pt4q.product_manager.modules.environment.data.master.EnvMasterEntity;
import pl.com.pt4q.product_manager.modules.environment.data.weee.EnvWeeeEntity;
import pl.com.pt4q.product_manager.modules.product.data.unit.UnitEntity;
import pl.com.pt4q.product_manager.modules.product.services.unit.UnitCrudService;

import java.util.List;
import java.util.stream.Collectors;

class EnvWeeeEditorDiv extends Div {

    private TextField productTextField = new TextField("Product");
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

    @Getter
    private Binder<EnvWeeeEntity> weeeEntityBinder = new Binder<>();

    private UnitCrudService unitCrudService;

    public EnvWeeeEditorDiv(UnitCrudService unitCrudService, EnvMasterEntity envMasterEntity) {
        this.unitCrudService = unitCrudService;

        setUpComboBoxItems();

        initFields();
        initBinder(envMasterEntity);

        setMinWidth("20%");
        setMaxWidth("40%");
        add(initFormLayoutDiv());
    }

    private Div initFormLayoutDiv() {
        VerticalLayout formLayout = new VerticalLayout();
        formLayout.add(
                this.productTextField,
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
        hl.setAlignItems(FlexComponent.Alignment.BASELINE);
        hl.setSizeFull();
        return hl;
    }

    private HorizontalLayout initLengthLayout() {
        HorizontalLayout hl = new HorizontalLayout();
        hl.add(this.itemHeightNumberField, this.itemLengthNumberField, this.itemDepthNumberField, this.lengthUnitComboBox);
        hl.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        hl.setAlignItems(FlexComponent.Alignment.BASELINE);
        hl.setSizeFull();
        return hl;
    }

    private void setUpComboBoxItems() {
        List<String> units = unitCrudService.getAll()
                .stream()
                .map(UnitEntity::getUnits)
                .collect(Collectors.toList());
        this.lengthUnitComboBox.setItems(units);
        this.netWeightUnitComboBox.setItems(units);

//        List<String> sourceTypes = Arrays.stream(EnvSourceTypeEnum.values())
//                .map(Enum::name)
//                .collect(Collectors.toList());
        this.sourceTypeComboBox.setItems(new EnvSourceTypeEnumWrapper().getUnitsStringsForComboBox());
    }

    private void initFields() {
        this.productTextField.setSizeFull();
        this.productTextField.setReadOnly(true);
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

    private void initBinder(EnvMasterEntity envMasterEntity) {
        this.weeeEntityBinder.forField(productTextField)
                .bind(envWeeeEntity -> envMasterEntity != null ? envMasterEntity.getProduct().getSku() : "",
                        null);
        this.weeeEntityBinder.forField(productDescriptionTextArea)
                .bind(envWeeeEntity -> envMasterEntity != null ? envMasterEntity.getProduct().getDescriptionPL() : "",
                        null);
        this.weeeEntityBinder.forField(validFromDatePicker)
                .bind(envWeeeEntity -> envMasterEntity != null ? envMasterEntity.getValidFrom() : null,
                        null);
        this.weeeEntityBinder.forField(validToDatePicker)
                .bind(envWeeeEntity -> envMasterEntity != null ? envMasterEntity.getValidTo() : null,
                        null);

        this.weeeEntityBinder.forField(itemHeightNumberField)
                .asRequired("Item height cannot be empty")
                .bind(EnvWeeeEntity::getItemHeight, EnvWeeeEntity::setItemHeight);
        this.weeeEntityBinder.forField(itemLengthNumberField)
                .asRequired("Item length cannot be empty")
                .bind(EnvWeeeEntity::getItemLength, EnvWeeeEntity::setItemLength);
        this.weeeEntityBinder.forField(itemDepthNumberField)
                .asRequired("Item depth cannot be empty")
                .bind(EnvWeeeEntity::getItemDepth, EnvWeeeEntity::setItemDepth);
        this.weeeEntityBinder.forField(lengthUnitComboBox)
                .asRequired("Select a unit of length")
                .bind(envWeeeEntity -> envWeeeEntity.getItemLengthUnit() != null ? envWeeeEntity.getItemLengthUnit().getUnits() : "",
                        (envWeeeEntity, s) -> unitCrudService.findByUnits(s).ifPresent(envWeeeEntity::setItemLengthUnit));

        this.weeeEntityBinder.forField(netWeightNumberField)
                .asRequired("Item net weight cannot be empty")
                .bind(EnvWeeeEntity::getNetWeight, EnvWeeeEntity::setNetWeight);
        this.weeeEntityBinder.forField(netWeightUnitComboBox)
                .asRequired("Select a unit of weight")
                .bind(envWeeeEntity -> envWeeeEntity.getNetWeightUnit() != null ? envWeeeEntity.getNetWeightUnit().getUnits() : "",
                        (envWeeeEntity, s) -> unitCrudService.findByUnits(s).ifPresent(envWeeeEntity::setNetWeightUnit));

        this.weeeEntityBinder.forField(sourceTypeComboBox)
                .asRequired("Select a type of source")
                .bind(envWeeeEntity -> envWeeeEntity.getSourceType() != null ? new EnvSourceTypeEnumWrapper().getUnitTypeStringFromEnum(envWeeeEntity.getSourceType()) : "",
                        (envWeeeEntity, s) -> envWeeeEntity.setSourceType(new EnvSourceTypeEnumWrapper().getUnitTypeFromString(s)));

        this.weeeEntityBinder.setBean(EnvWeeeEntity.builder().master(envMasterEntity).build());
    }

    private void cleanForm() {
        populateForm(null);
    }

    public void populateForm(EnvWeeeEntity weeeEntity) {
        this.weeeEntityBinder.setBean(weeeEntity);
    }
}
