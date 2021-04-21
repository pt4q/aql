package pl.com.pt4q.product_manager.modules.product.ui.product.detail;

import pl.com.pt4q.product_manager.modules.product.ui.product.general.ProductsGeneralView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import lombok.Data;

@Data
class SaveProductOrBackButtonsDiv extends Div {

    private Button saveButton;
    private Button backButton;

    public SaveProductOrBackButtonsDiv() {
        initBackButton();
        initSaveButton();

        HorizontalLayout buttonLayout = new HorizontalLayout(this.backButton, this.saveButton);
        buttonLayout.setWidthFull();
        buttonLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);

        add(buttonLayout);
    }

    private void initSaveButton(){
        this.saveButton = new Button("Save product");
        this.saveButton.addClickListener(buttonClickEvent -> {

        });
    }

    private void initBackButton(){
        this.backButton = new Button(new Icon(VaadinIcon.ARROW_BACKWARD));
        this.backButton.getElement().getStyle().set("margin-right", "auto");
        this.backButton.addClickListener(buttonClickEvent -> {
            UI.getCurrent().navigate(ProductsGeneralView.ROUTE);
        });
    }
}
