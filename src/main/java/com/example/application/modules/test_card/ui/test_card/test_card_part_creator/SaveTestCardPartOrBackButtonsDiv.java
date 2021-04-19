package com.example.application.modules.test_card.ui.test_card.test_card_part_creator;

import com.example.application.modules.test_card.ui.test_card.test_card_creator.TestCardCreatorView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import lombok.Data;

@Data
class SaveTestCardPartOrBackButtonsDiv extends Div {

    private Button saveButton;
    private Button backButton;

    public SaveTestCardPartOrBackButtonsDiv() {
        initBackButton();
        initSaveButton();

        HorizontalLayout buttonLayout = new HorizontalLayout(this.backButton, this.saveButton);
        buttonLayout.setWidthFull();
        buttonLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);

        add(buttonLayout);
    }

    private void initSaveButton(){
        this.saveButton = new Button("Save test card part");
        this.saveButton.addClickListener(buttonClickEvent -> {

        });
    }

    private void initBackButton(){
        this.backButton = new Button(new Icon(VaadinIcon.ARROW_BACKWARD));
        this.backButton.getElement().getStyle().set("margin-right", "auto");
        this.backButton.addClickListener(buttonClickEvent -> {
            UI.getCurrent().navigate(TestCardCreatorView.ROUTE);
        });
    }
}
