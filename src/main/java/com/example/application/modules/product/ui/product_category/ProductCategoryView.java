package com.example.application.modules.product.ui.product_category;

import com.example.application.modules.product.data.product_category.ProductCategoryEntity;
import com.example.application.modules.product.services.product_category.ProductCategoryCrudService;
import com.example.application.modules.product.services.product_category.exceptions.ProductCategoryNotFoundException;
import com.example.application.views.main.MainView;
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

@CssImport(ProductCategoryView.CSS)
@Route(value = ProductCategoryView.ROUTE, layout = MainView.class)
//@RouteAlias(value = "", layout = MainView.class)
@PageTitle(ProductCategoryView.PAGE_TITLE)
public class ProductCategoryView extends VerticalLayout {

    public static final String PAGE_TITLE = "Product category";
    public static final String ROUTE = "product-category";
    public static final String CSS = "./views/product_category/product-category-view.css";

    private ProductCategoryCrudService productCategoryCrudService;

    private Grid<ProductCategoryEntity> grid = new Grid<>(ProductCategoryEntity.class, false);

    private TextField productName;

    private Button cancel = new Button("Cancel");
    private Button save = new Button("Save");

    private BeanValidationBinder<ProductCategoryEntity> binder;

    private ProductCategoryEntity product;

    @Autowired
    public ProductCategoryView(ProductCategoryCrudService productCategoryCrudService) {
        this.productCategoryCrudService = productCategoryCrudService;
        addClassName(ROUTE + "-view");
        // Create UI
        SplitLayout splitLayout = new SplitLayout();
        splitLayout.setSizeFull();

        createGridLayout(splitLayout);
        createEditorLayout(splitLayout);

        add(splitLayout);

        // Configure Grid
        grid.addColumn(ProductCategoryEntity::getId).setHeader("Id").setAutoWidth(true);
        grid.addColumn(ProductCategoryEntity::getProductCategoryName).setHeader("Product category").setAutoWidth(true);
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
                ProductCategoryEntity productFromBackend = null;
                // when a row is selected but the data is no longer available, refresh grid
                try {
                    productFromBackend = productCategoryCrudService.getByIdOrThrow(event.getValue().getId());
                    populateForm(productFromBackend);
                } catch (ProductCategoryNotFoundException ex) {
                    refreshGrid();
                }

            } else {
                clearForm();
            }
        });
        refreshGrid();

        // Configure Form
        binder = new BeanValidationBinder<>(ProductCategoryEntity.class);

        // Bind fields. This where you'd define e.g. validation rules

        binder.forField(productName).bind(ProductCategoryEntity::getProductCategoryName, ProductCategoryEntity::setProductCategoryName);

        cancel.addClickListener(e -> {
            clearForm();
            refreshGrid();
        });

        save.addClickListener(e -> {
            if (productName.getValue() != null) {
                try {
                    if (this.product == null) {
                        this.product = ProductCategoryEntity
                                .builder()
                                .productCategoryName(productName.getValue())
                                .build();
                    }
                    binder.writeBean(this.product);

                    try {
                        productCategoryCrudService.updateOrThrow(this.product);
                    } catch (ProductCategoryNotFoundException productCategoryNotFoundException) {
                        productCategoryCrudService.create(this.product);
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

        productName = new TextField("Product number");

        Component[] fields = new Component[]{productName};

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
        grid.setItems(productCategoryCrudService.getAll());
    }

    private void clearForm() {
        populateForm(null);
    }

    private void populateForm(ProductCategoryEntity value) {
        this.product = value;
        binder.readBean(this.product);

    }
}
