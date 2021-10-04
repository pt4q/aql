package pl.com.pt4q.product_manager.view_utils;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import lombok.Getter;

@Getter
public class SaveClearAndDeleteButtonsDiv extends Div {

    private Button saveButton;
    private Button clearButton;
    private Button deleteButton;

    public SaveClearAndDeleteButtonsDiv() {
        this.saveButton = new Button("Save");
        this.clearButton = new Button("Clear");
        this.deleteButton = new Button("Delete");

        initButtonsThemes();

        add(initLayout());
    }

    private HorizontalLayout initLayout() {
        HorizontalLayout layout = new HorizontalLayout();
        layout.add(this.saveButton, this.clearButton, this.deleteButton);
        layout.setId("button-layout");
        layout.setWidthFull();
        layout.setSpacing(true);
        layout.setAlignItems(FlexComponent.Alignment.START);

        return layout;
    }

    private void initButtonsThemes() {
        this.saveButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        this.clearButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        this.deleteButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        this.deleteButton.getStyle().set("color","red");
    }
}
