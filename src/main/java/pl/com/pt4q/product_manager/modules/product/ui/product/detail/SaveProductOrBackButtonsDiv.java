package pl.com.pt4q.product_manager.modules.product.ui.product.detail;

import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import lombok.Data;
import pl.com.pt4q.product_manager.modules.product.data.product.ProductEntity;
import pl.com.pt4q.product_manager.modules.product.services.product.AddNewOrUpdateExistingProductService;
import pl.com.pt4q.product_manager.modules.product.services.product.exceptions.ProductAlreadyExistsException;
import pl.com.pt4q.product_manager.modules.product.services.product.exceptions.ProductValidatorException;
import pl.com.pt4q.product_manager.modules.product.ui.product.general.ProductsGeneralView;

@Data
class SaveProductOrBackButtonsDiv extends Div {

    private Button saveButton = new Button("Save product");
    private Button backButton = new Button(new Icon(VaadinIcon.ARROW_BACKWARD));

    private ProductDetailFormDiv productDetailFormDiv;

    private AddNewOrUpdateExistingProductService addNewOrUpdateExistingProductService;
    private ProductEntity product;

    public SaveProductOrBackButtonsDiv(ProductDetailFormDiv productDetailFormDiv, AddNewOrUpdateExistingProductService addNewOrUpdateExistingProductService) {
        this.productDetailFormDiv = productDetailFormDiv;
        this.addNewOrUpdateExistingProductService = addNewOrUpdateExistingProductService;

        configBackButton();
        configSaveButton();

        HorizontalLayout buttonLayout = new HorizontalLayout(this.backButton, this.saveButton);
        buttonLayout.setWidthFull();
        buttonLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);

        add(buttonLayout);
    }

    private void configSaveButton() {
        this.saveButton.addClickListener(buttonClickEvent -> {
            this.product = getProductFromContext();
            if (product != null) {
                try {
                   createIfProductIsNewScenario();
                } catch (ProductValidatorException e) {
                    Notification.show(e.getMessage());
                } catch (ProductAlreadyExistsException e) {
                    updateIfProductExistsScenario(product);
                }
            } else
                Notification.show("Product is null");
        });
    }

    private void createIfProductIsNewScenario() throws ProductAlreadyExistsException, ProductValidatorException {
        this.product = addNewOrUpdateExistingProductService.add(product);
        if (product.getId() != null && product.getProductSku() != null) {
            Notification.show(String.format("Product %s has been created", product.getProductSku()));
            saveDataToContext(product);
        } else
            Notification.show("After check the product is null");
    }

    private void updateIfProductExistsScenario(ProductEntity productEntity) {
        try {
            this.product = addNewOrUpdateExistingProductService.updateExisting(productEntity);
            Notification.show(String.format("Product %s has been updated", product.getProductSku()));
            saveDataToContext(product);
        } catch (ProductValidatorException ex) {
            Notification.show(ex.getMessage());
        }
    }

    private void configBackButton() {
        this.backButton.getElement().getStyle().set("margin-right", "auto");
        this.backButton.addClickListener(buttonClickEvent -> {
            saveDataToContext(null);
            UI.getCurrent().navigate(ProductsGeneralView.ROUTE);
        });
    }

    private ProductEntity getProductFromContext() {
        return ComponentUtil.getData(UI.getCurrent(), ProductEntity.class);
    }

    private void saveDataToContext(ProductEntity product) {
        ComponentUtil.setData(UI.getCurrent(), ProductEntity.class, product);
    }
}
