package pl.com.pt4q.product_manager.modules.product.ui.manufacturer;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import pl.com.pt4q.product_manager.modules.product.data.manufacturer.ManufacturerEntity;
import pl.com.pt4q.product_manager.modules.product.services.manufacturer.ManufacturerCrudService;
import pl.com.pt4q.product_manager.modules.product.services.manufacturer.exceptions.ManufacturerNotFoundException;
import pl.com.pt4q.product_manager.views.main.MainView;

@CssImport(ManufacturerView.CSS)
@Route(value = ManufacturerView.ROUTE, layout = MainView.class)
//@RouteAlias(value = "", layout = MainView.class)
@PageTitle(ManufacturerView.PAGE_TITLE)
public class ManufacturerView extends VerticalLayout {

    public static final String PAGE_TITLE = "Manufacturer";
    public static final String ROUTE = "manufacturer";
    public static final String CSS = "./views/product_module/manufacturer/manufacturer-view.css";

    private ManufacturerCrudService manufacturerCrudService;

    private Grid<ManufacturerEntity> grid = new Grid<>(ManufacturerEntity.class, false);

    private TextField manufacturerNameTextField = new TextField("Manufacturer name");
    private TextField manufacturerDescriptionTextField = new TextField("Description");

    private Button cancel = new Button("Cancel");
    private Button save = new Button("Save");

    private BeanValidationBinder<ManufacturerEntity> binder;

    private ManufacturerEntity manufacturerEntity;

    @Autowired
    public ManufacturerView(ManufacturerCrudService manufacturerCrudService) {
        this.manufacturerCrudService = manufacturerCrudService;
        addClassName(ROUTE + "-view");
        // Create UI
        SplitLayout splitLayout = new SplitLayout();
        splitLayout.setSizeFull();

        createGridLayout(splitLayout);
        createEditorLayout(splitLayout);

        add(splitLayout);

        // Configure Grid
        grid.addColumn(ManufacturerEntity::getId).setHeader("Id").setAutoWidth(true);
        grid.addColumn(ManufacturerEntity::getManufacturerName).setHeader("Manufacturer name").setAutoWidth(true);
        grid.addColumn(ManufacturerEntity::getDescription).setHeader("Description").setAutoWidth(true);
//        grid.addColumn("lastName").setAutoWidth(true);
//        grid.addColumn("email").setAutoWidth(true);
//        grid.addColumn("phone").setAutoWidth(true);
//        grid.addColumn("dateOfBirth").setAutoWidth(true);
//        grid.addColumn("occupation").setAutoWidth(true);
//        TemplateRenderer<ProductEntity> importantRenderer = TemplateRenderer.<SamplePerson>of(
//                "<iron-icon hidden='[[!item.important]]' icon='vaadin:check' style='width: var(--lumo-icon-size-s); height: var(--lumo-icon-size-s); color: var(--lumo-primary-text-color);'></iron-icon><iron-icon hidden='[[item.important]]' icon='vaadin:minus' style='width: var(--lumo-icon-size-s); height: var(--lumo-icon-size-s); color: var(--lumo-disabled-text-color);'></iron-icon>")
//                .withProperty("important", SamplePerson::isImportant);
//        grid.addColumn(importantRenderer).setHeader("Important").setAutoWidth(true);

//        grid.setDataProvider(new CrudServiceDataProvider<>(productCrudService));
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);
        grid.setHeightFull();

        // when a row is selected or deselected, populate form
        grid.asSingleSelect().addValueChangeListener(event -> {
            if (event.getValue() != null) {
                ManufacturerEntity manufacturerFromBackend = null;
                // when a row is selected but the data is no longer available, refresh grid
                try {
                    manufacturerFromBackend = manufacturerCrudService.getByIdOrThrow(event.getValue().getId());
                    populateForm(manufacturerFromBackend);
                } catch (ManufacturerNotFoundException ex) {
                    refreshGrid();
                }

            } else {
                clearForm();
            }
        });
        refreshGrid();

        // Configure Form
        binder = new BeanValidationBinder<>(ManufacturerEntity.class);

        // Bind fields. This where you'd define e.g. validation rules

        binder.forField(manufacturerNameTextField).bind(ManufacturerEntity::getManufacturerName, ManufacturerEntity::setManufacturerName);
        binder.forField(manufacturerDescriptionTextField).bind(ManufacturerEntity::getDescription, ManufacturerEntity::setDescription);

        cancel.addClickListener(e -> {
            clearForm();
            refreshGrid();
        });

        save.addClickListener(e -> {
            if (manufacturerNameTextField.getValue() != null) {
                try {
                    if (this.manufacturerEntity == null) {
                        this.manufacturerEntity = ManufacturerEntity
                                .builder()
                                .manufacturerName(manufacturerNameTextField.getValue())
                                .build();
                    }
                    binder.writeBean(this.manufacturerEntity);

                    try {
                        manufacturerCrudService.updateOrThrow(this.manufacturerEntity);
                    } catch (ManufacturerNotFoundException ex) {
                        manufacturerCrudService.create(this.manufacturerEntity);
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

        Component[] fields = new Component[]{manufacturerNameTextField, manufacturerDescriptionTextField};

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
        grid.setItems(manufacturerCrudService.getAll());
    }

    private void clearForm() {
        populateForm(null);
    }

    private void populateForm(ManufacturerEntity value) {
        this.manufacturerEntity = value;
        binder.readBean(this.manufacturerEntity);

    }
}
