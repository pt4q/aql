package pl.com.pt4q.product_manager.modules.product.ui.units;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import pl.com.pt4q.product_manager.modules.product.data.unit.UnitEntity;
import pl.com.pt4q.product_manager.modules.product.data.unit.UnitTypeEnumWrapper;
import pl.com.pt4q.product_manager.modules.product.services.unit.UnitCrudService;
import pl.com.pt4q.product_manager.modules.product.services.unit.exceptions.UnitAlreadyExistsException;
import pl.com.pt4q.product_manager.modules.product.services.unit.exceptions.UnitNotFoundException;
import pl.com.pt4q.product_manager.views.main.MainView;

@CssImport(UnitsView.CSS)
@Route(value = UnitsView.ROUTE, layout = MainView.class)
@PageTitle(UnitsView.PAGE_TITLE)
public class UnitsView extends VerticalLayout {

    public static final String PAGE_TITLE = "Units";
    public static final String ROUTE = "units";
    public static final String CSS = "./views/product_module/unit/unit-view.css";

    private Grid<UnitEntity> grid = new Grid<>(UnitEntity.class, false);

    private TextField unitNameTextField = new TextField("Unit name");
    private TextField unitsTextField = new TextField("Units");
    private NumberField multiplicityNumberField = new NumberField("Decimal places");
    private ComboBox<String> valuesTypeComboBox = new ComboBox<>("Values type");

    private Button cancel = new Button("Cancel");
    private Button save = new Button("Save");

    private BeanValidationBinder<UnitEntity> binder;

    private UnitCrudService unitCrudService;

    private UnitEntity unitEntity;

    @Autowired
    public UnitsView(UnitCrudService unitCrudService) {
        this.unitCrudService = unitCrudService;
        addClassName(ROUTE + "-view");
        // Create UI
        SplitLayout splitLayout = new SplitLayout();
        splitLayout.setSizeFull();

        createGridLayout(splitLayout);
        createEditorLayout(splitLayout);

        add(splitLayout);

        // Configure Grid
        grid.addColumn(UnitEntity::getId).setHeader("Id").setAutoWidth(true).setSortable(true);
        grid.addColumn(UnitEntity::getName).setHeader("Unit name").setAutoWidth(true).setSortable(true);
        grid.addColumn(UnitEntity::getUnits).setHeader("Units").setAutoWidth(true).setSortable(true);
        grid.addColumn(UnitEntity::getDecimalPlaces).setHeader("Decimal places (x10^n)").setAutoWidth(true).setSortable(true);
        grid.addColumn(UnitEntity::getValuesType).setHeader("Values type").setAutoWidth(true).setSortable(true);

        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);
        grid.setHeightFull();

        // when a row is selected or deselected, populate form
        grid.asSingleSelect().addValueChangeListener(event -> {
            if (event.getValue() != null) {
                UnitEntity manufacturerFromBackend = null;
                // when a row is selected but the data is no longer available, refresh grid
                try {
                    manufacturerFromBackend = unitCrudService.getByIdOrThrow(event.getValue().getId());
                    populateForm(manufacturerFromBackend);
                } catch (UnitNotFoundException ex) {
                    refreshGrid();
                }

            } else {
                clearForm();
            }
        });
        refreshGrid();

        // Configure Form
        initUnitValueTypeComboBox();
        initDecimalPlacesNumberField();
        binder = new BeanValidationBinder<>(UnitEntity.class);

        // Bind fields. This where you'd define e.g. validation rules

        binder.forField(unitNameTextField)
                .asRequired("Unit name can't be empty")
                .bind(UnitEntity::getName, UnitEntity::setName);
        binder.forField(unitsTextField)
                .asRequired("Units can't be empty")
                .bind(UnitEntity::getUnits, UnitEntity::setUnits);
        binder.forField(multiplicityNumberField)
                .asRequired("Unit multiplication can't be empty")
                .bind(
                        unitEntity -> unitEntity.getDecimalPlaces().doubleValue(),
                        (unitEntity, aDouble) -> unitEntity.setDecimalPlaces(aDouble.intValue())
                );
        binder.forField(valuesTypeComboBox)
                .asRequired("Unit value type can't be empty")
                .bind(
                        unitEntity -> unitEntity.getValuesType().toString(),
                        (unitEntity, s) -> unitEntity.setValuesType(new UnitTypeEnumWrapper().getUnitTypeFromString(s))
                        );

        cancel.addClickListener(e -> {
            clearForm();
            refreshGrid();
        });

        save.addClickListener(e -> {
            if (unitNameTextField.getValue() != null) {
                try {
                    if (this.unitEntity == null) {
                        this.unitEntity = UnitEntity
                                .builder()
                                .name(unitNameTextField.getValue())
                                .units(unitsTextField.getValue())
                                .decimalPlaces(multiplicityNumberField.getValue() != null ? multiplicityNumberField.getValue().intValue() : 0)
                                .valuesType(new UnitTypeEnumWrapper().getUnitTypeFromString(valuesTypeComboBox.getValue()))
                                .build();
                    }
                    binder.writeBean(this.unitEntity);

                    try {
                        unitCrudService.updateOrThrow(this.unitEntity);
                    } catch (UnitNotFoundException ex) {
                        try {
                            unitCrudService.create(this.unitEntity);
                        } catch (UnitAlreadyExistsException exx) {
                        }
                    }

                    clearForm();
                    refreshGrid();
                    Notification.show("SamplePerson details stored.");
                } catch (ValidationException ex) {
                    Notification.show("An exception happened while trying to store the samplePerson details.");
                }
            }
        });


    }

    private void createEditorLayout(SplitLayout splitLayout) {
        Div editorLayoutDiv = new Div();
        editorLayoutDiv.setId("editor-layout");

        Div editorDiv = new Div();
        editorDiv.setId("editor");
        editorLayoutDiv.add(editorDiv);

        FormLayout formLayout = new FormLayout();

        Component[] fields = new Component[]{unitNameTextField, unitsTextField, multiplicityNumberField, valuesTypeComboBox};

        for (Component field : fields) {
            ((HasStyle) field).addClassName("full-width");
        }
        formLayout.add(fields);
        editorDiv.add(formLayout);
        createButtonLayout(editorLayoutDiv);

        splitLayout.addToSecondary(editorLayoutDiv);
    }

    private void createButtonLayout(Div editorLayoutDiv) {
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.setId("button-layout");
        buttonLayout.setWidthFull();
        buttonLayout.setSpacing(true);
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonLayout.add(save, cancel);
        editorLayoutDiv.add(buttonLayout);
    }

    private void createGridLayout(SplitLayout splitLayout) {
        Div wrapper = new Div();
        wrapper.setId("grid-wrapper");
        wrapper.setWidthFull();
        splitLayout.addToPrimary(wrapper);
        wrapper.add(grid);
    }

    private void refreshGrid() {
        grid.select(null);
//        grid.getDataProvider().refreshAll();
        grid.setItems(unitCrudService.getAll());
    }

    private void initUnitValueTypeComboBox() {
        String helpText = new StringBuilder("Większość parametrów powinna być wybrana jako float, float ponieważ mogą zawierać ułamki.")
                .append("\nTyp decimal wybiera się dla jednostek takich jak np. szt, opak itd")
                .append("\nTekst nie ma jednostki (\"[-]\")")
                .toString();
        this.valuesTypeComboBox.setItems(new UnitTypeEnumWrapper().getUnitsStringsForComboBox());
        this.valuesTypeComboBox.setHelperText(helpText);
    }

    private void initDecimalPlacesNumberField(){
        this.multiplicityNumberField.setHasControls(true);
        this.multiplicityNumberField.setValue(0d);
        this.multiplicityNumberField.setStep(3d);
        this.multiplicityNumberField.setMin(-9d);
        this.multiplicityNumberField.setMax(9d);
    }

    private void clearForm() {
        populateForm(null);
    }

    private void populateForm(UnitEntity value) {
        this.unitEntity = value;
        binder.readBean(this.unitEntity);
    }
}
