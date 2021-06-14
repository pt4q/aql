package pl.com.pt4q.product_manager.modules.environment.ui.master.detail;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.data.binder.Binder;
import lombok.Getter;
import pl.com.pt4q.product_manager.modules.environment.data.master.EnvMasterEntity;
import pl.com.pt4q.product_manager.modules.product.data.product.ProductEntity;
import pl.com.pt4q.product_manager.modules.product.data.unit.UnitEntity;
import pl.com.pt4q.product_manager.modules.product.services.product.ProductFinderService;
import pl.com.pt4q.product_manager.modules.product.services.unit.UnitCrudService;

@Getter
class EnvMasterDetailEditorDiv extends Div {

    private ComboBox<String> productComboBox = new ComboBox<>("Product");
    private TextArea productDescriptionTextArea = new TextArea("Product description");
    private DatePicker validFromDatePicker = new DatePicker("Valid from");
    private DatePicker validToDatePicker = new DatePicker("Valid to");
    private NumberField grossWeightNumberField = new NumberField("Gross weight");
    private ComboBox<String> grossWeightUnitComboBox = new ComboBox<>("Gross weight unit");

    private Binder<EnvMasterEntity> masterBinder = new Binder<>();

    private ProductFinderService productFinderService;
    private UnitCrudService unitCrudService;

    public EnvMasterDetailEditorDiv(ProductFinderService productFinderService,
                                    UnitCrudService unitCrudService) {

        this.productFinderService = productFinderService;
        this.unitCrudService = unitCrudService;

        setUpComboBoxItems();

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

    private void setUpComboBoxItems(){
        this.productComboBox.setItems(productFinderService.findAll()
                .stream()
                .map(ProductEntity::getSku)
        );
        this.grossWeightUnitComboBox.setItems(unitCrudService.getAll()
                .stream()
                .map(UnitEntity::getUnits)
        );
    }

    private void initFields() {
        this.productComboBox.setSizeFull();
        if (this.productComboBox.getValue() != null)
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

    private void initBinder() {
        this.masterBinder.forField(productComboBox)
                .bind(
                        masterEntity -> masterEntity.getProduct() != null ? masterEntity.getProduct().getSku() : "",
                        (masterEntity, s) -> productFinderService.findBySku(s).ifPresent(masterEntity::setProduct)
                );
        this.masterBinder.forField(productDescriptionTextArea)
                .bind(
                        masterEntity -> masterEntity.getProduct() != null ? masterEntity.getProduct().getDescriptionENG() : "",
                        (masterEntity, s) -> masterEntity.getProduct().setDescriptionENG(s)
                );
        this.masterBinder.forField(validFromDatePicker)
                .asRequired("Valid from date can't be empty")
                .bind(EnvMasterEntity::getValidFrom, EnvMasterEntity::setValidFrom);
        this.masterBinder.forField(validToDatePicker)
                .bind(EnvMasterEntity::getValidTo, EnvMasterEntity::setValidTo);
        this.masterBinder.forField(grossWeightNumberField)
                .asRequired("Gross weight can't be empty")
                .bind(EnvMasterEntity::getGrossWeight, EnvMasterEntity::setGrossWeight);
        this.masterBinder.forField(grossWeightUnitComboBox)
                .bind(
                        masterEntity -> masterEntity.getGrossWeightUnit() != null ? masterEntity.getGrossWeightUnit().getUnits() : "",
                        (masterEntity, s) -> unitCrudService.findByUnits(s).ifPresent(masterEntity::setGrossWeightUnit)
                );
        this.masterBinder.setBean(new EnvMasterEntity());
    }

    private void cleanForm() {
        populateForm(null);
    }

    public void populateForm(EnvMasterEntity masterEntity) {
        this.masterBinder.readBean(masterEntity);
    }
}
