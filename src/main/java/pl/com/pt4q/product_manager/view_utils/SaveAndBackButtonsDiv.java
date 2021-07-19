package pl.com.pt4q.product_manager.view_utils;

import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import lombok.Getter;

public class SaveAndBackButtonsDiv extends Div {

    @Getter
    private Button saveButton;
    @Getter
    private Button backButton;

    public SaveAndBackButtonsDiv(String saveButtonLabel) {
        initSaveButton(saveButtonLabel);
        initBackButton();

        HorizontalLayout buttonLayout = new HorizontalLayout(this.backButton, this.saveButton);
        buttonLayout.setWidthFull();
        buttonLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);

        add(buttonLayout);
    }

    private void initSaveButton(String saveButtonLabel) {
        this.saveButton = new Button(saveButtonLabel);
    }

    private void initBackButton() {
        this.backButton = new BackButtonDiv().getBackButton();
        this.backButton.getElement().getStyle().set("margin-right", "auto");
    }
}
