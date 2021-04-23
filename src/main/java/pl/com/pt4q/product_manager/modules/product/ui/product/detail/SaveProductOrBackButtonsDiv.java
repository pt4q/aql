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
import pl.com.pt4q.product_manager.modules.product.services.product.AddNewProductService;
import pl.com.pt4q.product_manager.modules.product.services.product.exceptions.ProductAlreadyExistsException;
import pl.com.pt4q.product_manager.modules.product.services.product.exceptions.ProductValidatorException;
import pl.com.pt4q.product_manager.modules.product.ui.product.general.ProductsGeneralView;

@Data
class SaveProductOrBackButtonsDiv extends Div {

    private Button saveButton;
    private Button backButton;

    private AddNewProductService addNewProductService;
    private ProductEntity productEntity;

    public SaveProductOrBackButtonsDiv(ProductEntity productEntity,
                                       AddNewProductService addNewProductService) {
        this.productEntity = productEntity;
        this.addNewProductService = addNewProductService;

        initBackButton();
        initSaveButton();

        HorizontalLayout buttonLayout = new HorizontalLayout(this.backButton, this.saveButton);
        buttonLayout.setWidthFull();
        buttonLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);

        add(buttonLayout);
    }

    private void initSaveButton() {
        this.saveButton = new Button("Save product");
        this.saveButton.addClickListener(buttonClickEvent -> {
            if (productEntity != null) {
                try {
                    productEntity = addNewProductService.add(productEntity);
                    if (productEntity.getId() != null && productEntity.getProductSku() != null) {
                        ComponentUtil.setData(UI.getCurrent(), ProductEntity.class, null);
                        UI.getCurrent().navigate(ProductsGeneralView.ROUTE);
                    } else
                        Notification.show("After check the product is null");
                } catch (ProductAlreadyExistsException | ProductValidatorException e) {
                    Notification.show(e.getMessage());
                }
            } else
                Notification.show("Product is null");
        });
    }

    private void initBackButton() {
        this.backButton = new Button(new Icon(VaadinIcon.ARROW_BACKWARD));
        this.backButton.getElement().getStyle().set("margin-right", "auto");
        this.backButton.addClickListener(buttonClickEvent -> {
            UI.getCurrent().navigate(ProductsGeneralView.ROUTE);
        });
    }
}
