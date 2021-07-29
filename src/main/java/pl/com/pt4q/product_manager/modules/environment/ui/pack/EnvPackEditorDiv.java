package pl.com.pt4q.product_manager.modules.environment.ui.pack;

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
import pl.com.pt4q.product_manager.modules.environment.data.pack.EnvPackagingEntity;
import pl.com.pt4q.product_manager.modules.environment.data.pack.EnvPackagingTypeWrapper;
import pl.com.pt4q.product_manager.modules.environment.data.weee.EnvSourceTypeEnumWrapper;
import pl.com.pt4q.product_manager.modules.product.data.unit.UnitEntity;
import pl.com.pt4q.product_manager.modules.product.services.unit.UnitCrudService;
import pl.com.pt4q.product_manager.view_utils.SaveClearAndDeleteButtonsDiv;

import java.util.List;
import java.util.stream.Collectors;

class EnvPackEditorDiv extends Div {

    private TextField productTextField = new TextField("Product");
    private TextArea productDescriptionTextArea = new TextArea("Product description");
    private DatePicker validFromDatePicker = new DatePicker("Valid from");
    private DatePicker validToDatePicker = new DatePicker("Valid to");

    private NumberField netWeightNumberField = new NumberField("Net weight");
    private ComboBox<String> netWeightUnitComboBox = new ComboBox<>("Net weight unit");

    private ComboBox<String> materialGeneralComboBox = new ComboBox<>("Material - general");
    private ComboBox<String> materialDetailComboBox = new ComboBox<>("Material - detail");
    private ComboBox<String> typeOfPackaging = new ComboBox<>("Type of packaging");

    @Getter
    private SaveClearAndDeleteButtonsDiv buttonsDiv = new SaveClearAndDeleteButtonsDiv();

    @Getter
    private Binder<EnvPackagingEntity> packEntityBinder = new Binder<>(EnvPackagingEntity.class);

    private UnitCrudService unitCrudService;

    public EnvPackEditorDiv(UnitCrudService unitCrudService, EnvMasterEntity envMasterEntity) {
        this.unitCrudService = unitCrudService;

        setUpComboBoxItems();

        initOtherFields();
        initWeightLayout();
        initBinder(envMasterEntity);

        setId("editor");
        setMinWidth("20%");
        setMaxWidth("40%");
        add(initFormLayoutDiv());
    }

    private Div initFormLayoutDiv() {
        VerticalLayout formLayout = new VerticalLayout();
        formLayout.setId("editor-layout");
        formLayout.add(
                this.productTextField,
                this.productDescriptionTextArea,
                this.validFromDatePicker,
                this.validToDatePicker,
                initWeightLayout(),
                initMaterialGeneralAndDetailLayout(),
                typeOfPackaging,
                this.buttonsDiv
        );
        formLayout.setSizeFull();
        formLayout.setAlignItems(FlexComponent.Alignment.CENTER);

        Div layoutDiv = new Div();
        layoutDiv.add(formLayout);
        layoutDiv.setSizeFull();
        return layoutDiv;
    }

    private HorizontalLayout initWeightLayout() {
        this.netWeightNumberField.setSizeFull();
        this.netWeightNumberField.setMinWidth("10%");
        this.netWeightUnitComboBox.setSizeFull();
        this.netWeightUnitComboBox.setMinWidth("10%");

        HorizontalLayout hl = new HorizontalLayout();
        hl.add(this.netWeightNumberField, this.netWeightUnitComboBox);
        hl.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        hl.setAlignItems(FlexComponent.Alignment.BASELINE);
        hl.setSizeFull();
        return hl;
    }

    private HorizontalLayout initMaterialGeneralAndDetailLayout() {
        this.materialGeneralComboBox.setSizeFull();
        this.materialGeneralComboBox.setMinWidth("10%");
        this.materialDetailComboBox.setSizeFull();
        this.materialDetailComboBox.setMinWidth("10%");

        HorizontalLayout hl = new HorizontalLayout();
        hl.add(this.materialGeneralComboBox, this.materialDetailComboBox);
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
        this.netWeightUnitComboBox.setItems(units);

        this.typeOfPackaging.setItems(new EnvPackagingTypeWrapper().getTypesStringsForComboBox());
    }

    private void initOtherFields() {
        this.productTextField.setSizeFull();
        this.productTextField.setReadOnly(true);
        this.productDescriptionTextArea.setSizeFull();
        this.productDescriptionTextArea.setReadOnly(true);

        this.validFromDatePicker.setSizeFull();
        this.validFromDatePicker.setReadOnly(true);
        this.validToDatePicker.setSizeFull();
        this.validToDatePicker.setReadOnly(true);

        this.typeOfPackaging.setSizeFull();
    }

    private void initBinder(EnvMasterEntity envMasterEntity) {
        this.packEntityBinder.forField(productTextField)
                .bind(envWeeeEntity -> envMasterEntity != null ? envMasterEntity.getProduct().getSku() : "",
                        null);
        this.packEntityBinder.forField(productDescriptionTextArea)
                .bind(envWeeeEntity -> envMasterEntity != null ? envMasterEntity.getProduct().getDescriptionPL() : "",
                        null);
        this.packEntityBinder.forField(validFromDatePicker)
                .bind(envWeeeEntity -> envMasterEntity != null ? envMasterEntity.getValidFrom() : null,
                        null);
        this.packEntityBinder.forField(validToDatePicker)
                .bind(envWeeeEntity -> envMasterEntity != null ? envMasterEntity.getValidTo() : null,
                        null);



        this.packEntityBinder.forField(netWeightNumberField)
                .asRequired("Item net weight cannot be empty")
                .bind(EnvPackagingEntity::getNetWeight, EnvPackagingEntity::setNetWeight);
        this.packEntityBinder.forField(netWeightUnitComboBox)
                .asRequired("Select a unit of weight")
                .bind(envPackagingEntity -> envPackagingEntity.getNetWeightUnit() != null ? envPackagingEntity.getNetWeightUnit().getUnits() : "",
                        (envPackagingEntity, s) -> unitCrudService.findByUnits(s).ifPresent(envPackagingEntity::setNetWeightUnit));
        this.packEntityBinder.forField(typeOfPackaging)
                .asRequired("Type of packaging cannot be empty")
                .bind(envPackagingEntity -> envPackagingEntity.getTypeOfPackaging() != null ? envPackagingEntity.getTypeOfPackaging().name() : "",
                        (envPackagingEntity, s) -> envPackagingEntity.setTypeOfPackaging(new EnvPackagingTypeWrapper().getTypeOfPackagingFromString(s)));

        this.packEntityBinder.setBean(EnvPackagingEntity.builder().master(envMasterEntity).build());
    }

    private void cleanForm() {
        populateForm(null);
    }

    public void populateForm(EnvPackagingEntity packEntity) {
        this.packEntityBinder.setBean(packEntity);
    }
}
