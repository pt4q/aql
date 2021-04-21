package pl.com.pt4q.product_manager.modules.product.ui.product_part;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import lombok.Data;
import pl.com.pt4q.product_manager.modules.product.data.product_part.ProductPartEntity;
import pl.com.pt4q.product_manager.modules.product.ui.product.detail.ProductDetailView;

@Data
class SaveProductPartOrBackButtonsDiv extends Div {

    private Button saveButton = new Button("Save part");
    private Button backButton = new Button(new Icon(VaadinIcon.ARROW_BACKWARD));

    private ProductPartEntity productPart;

    public SaveProductPartOrBackButtonsDiv(ProductPartEntity productPart) {
        this.productPart = productPart;
        backButtonListenerConfig();
        saveButtonListenerConfig();

        HorizontalLayout buttonLayout = new HorizontalLayout(this.backButton, this.saveButton);
        buttonLayout.setWidthFull();
        buttonLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);

        add(buttonLayout);
    }

    private void saveButtonListenerConfig() {
        saveButton.addClickListener(buttonClickEvent -> {

        });
    }

    private void backButtonListenerConfig() {
        backButton.getElement().getStyle().set("margin-right", "auto");
        backButton.addClickListener(buttonClickEvent -> {
            UI.getCurrent().navigate(ProductDetailView.ROUTE);
        });
    }
}
