package com.example.application.views.product;

import com.example.application.data.entity.product.ProductEntity;
import com.example.application.data.service.product.ProductCrudService;
import com.example.application.data.service.product.ProductNotFound;
import com.example.application.views.main.MainView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
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

@Route(value = ProductView.route, layout = MainView.class)
//@RouteAlias(value = "", layout = MainView.class)
@PageTitle(ProductView.pageTitle)
public class ProductView extends VerticalLayout {

    public static final String pageTitle = "Product";
    public static final String route = "product";

    private Grid<ProductEntity> grid = new Grid<>(ProductEntity.class, false);

    private TextField productName;

    private Button cancel = new Button("Cancel");
    private Button save = new Button("Save");

    private BeanValidationBinder<ProductEntity> binder;

    private ProductEntity product;

    public ProductView(@Autowired ProductCrudService productCrudService) {
        addClassName(route + "-view");
        // Create UI
        SplitLayout splitLayout = new SplitLayout();
        splitLayout.setSizeFull();

        createGridLayout(splitLayout);
        createEditorLayout(splitLayout);

        add(splitLayout);

        // Configure Grid
        grid.addColumn("productNumber").setAutoWidth(true);
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
                ProductEntity productFromBackend = null;
                // when a row is selected but the data is no longer available, refresh grid
                try {
                    productFromBackend = productCrudService.getProduct(event.getValue().getId());
                    populateForm(productFromBackend);
                } catch (ProductNotFound ex) {
                    refreshGrid();
                }

            } else {
                clearForm();
            }
        });

        // Configure Form
        binder = new BeanValidationBinder<>(ProductEntity.class);

        // Bind fields. This where you'd define e.g. validation rules

        binder.forField(productName).bind(ProductEntity::getProductNumber, ProductEntity::setProductNumber);

        cancel.addClickListener(e -> {
            clearForm();
            refreshGrid();
        });

        save.addClickListener(e -> {
            try {
                if (this.product == null) {
                    this.product = new ProductEntity();
                }
                binder.writeBean(this.product);

                productCrudService.update(this.product);
                clearForm();
                refreshGrid();
                Notification.show("SamplePerson details stored.");
            } catch (ValidationException ex) {
                Notification.show("An exception happened while trying to store the samplePerson details.");
            } catch (ProductNotFound productNotFound) {
                productCrudService.createProduct(this.product);
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
        productName = new TextField("First Name");

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
        grid.getDataProvider().refreshAll();
    }

    private void clearForm() {
        populateForm(null);
    }

    private void populateForm(ProductEntity value) {
        this.product = value;
        binder.readBean(this.product);

    }
}
