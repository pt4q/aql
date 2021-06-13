package pl.com.pt4q.product_manager.modules.environment.ui.master.detail;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import lombok.Getter;

@Getter
class AddAdditionalEnvCardsButtonsDiv extends Div {

    private Button addWeeButton = new Button("Add WEEE");
    private Button addLightSourceButton = new Button("Add LS");
    private Button addBatButton = new Button("Add BAT");
    private Button addPackButton = new Button("Add PACK");

    public AddAdditionalEnvCardsButtonsDiv() {
        VerticalLayout layout = new VerticalLayout();
        layout.add(initButtonsLayoutDiv());
        layout.setAlignItems(FlexComponent.Alignment.CENTER);

        setWidthFull();
        add(layout);
    }

    private Div initButtonsLayoutDiv(){
        Div buttonsDiv = new Div();
        HorizontalLayout buttonsLayout = new HorizontalLayout();
        buttonsLayout.add(this.addWeeButton, this.addLightSourceButton, this.addBatButton, this.addPackButton);
        buttonsLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);
        buttonsLayout.setWidthFull();

        buttonsDiv.add(buttonsLayout);
        return buttonsDiv;
    }
}
