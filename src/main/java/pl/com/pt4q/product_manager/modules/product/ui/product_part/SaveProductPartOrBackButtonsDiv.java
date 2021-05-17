package pl.com.pt4q.product_manager.modules.product.ui.product_part;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import lombok.Data;
import lombok.Getter;
import pl.com.pt4q.product_manager.modules.product.data.product_part.ProductPartEntity;
import pl.com.pt4q.product_manager.modules.product.services.product_part.ProductPartCrudSaver;
import pl.com.pt4q.product_manager.modules.product.services.product_part.exceptions.ProductPartAlreadyExistsException;
import pl.com.pt4q.product_manager.modules.product.services.product_part.exceptions.ProductPartNotFoundException;
import pl.com.pt4q.product_manager.modules.product.ui.product.detail.ProductDetailView;

class SaveProductPartOrBackButtonsDiv extends Div {

    @Getter
    private Button saveButton = new Button("Save part");
    @Getter
    private Button backButton = new Button(new Icon(VaadinIcon.ARROW_BACKWARD));

    private ProductPartCrudSaver productPartCrudSaver;
    private ProductPartEntity productPart;

    public SaveProductPartOrBackButtonsDiv(ProductPartEntity productPart, ProductPartCrudSaver productPartCrudSaver) {
        this.productPart = productPart;
        this.productPartCrudSaver = productPartCrudSaver;

        backButtonListenerConfig();
        saveButtonListenerConfig();

        HorizontalLayout buttonLayout = new HorizontalLayout(this.backButton, this.saveButton);
        buttonLayout.setWidthFull();
        buttonLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);

        add(buttonLayout);
    }

    private void saveButtonListenerConfig() {
        saveButton.addClickListener(buttonClickEvent -> {
            try {
                productPart = productPartCrudSaver.save(productPart);
            } catch (ProductPartAlreadyExistsException e) {
                try {
                    productPart = productPartCrudSaver.update(productPart);
                } catch (ProductPartNotFoundException ex) {
                    Notification.show(String.format("Can't save part because: %s", ex.getMessage()));
                }
            }
        });
    }

    private void backButtonListenerConfig() {
        backButton.getElement().getStyle().set("margin-right", "auto");
        backButton.addClickListener(buttonClickEvent -> {
            UI.getCurrent().navigate(ProductDetailView.ROUTE);
        });
    }
}
