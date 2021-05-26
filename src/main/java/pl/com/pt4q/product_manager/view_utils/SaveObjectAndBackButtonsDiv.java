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
import pl.com.pt4q.product_manager.modules.product.data.product_part.ProductPartEntity;
import pl.com.pt4q.product_manager.modules.product.services.product_part.ProductPartCrudSaver;

public class SaveObjectAndBackButtonsDiv <T> extends Div {

    @Getter
    private Button saveButton ;
    @Getter
    private Button backButton = new Button(new Icon(VaadinIcon.ARROW_BACKWARD));

    private ProductPartCrudSaver productPartCrudSaver;

    public SaveObjectAndBackButtonsDiv(String saveButtonLabel) {
        initSaveButton(saveButtonLabel);
        initBackButton();

        HorizontalLayout buttonLayout = new HorizontalLayout(this.backButton, this.saveButton);
        buttonLayout.setWidthFull();
        buttonLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);

        add(buttonLayout);
    }

    private void initSaveButton(String saveButtonLabel){
        this.saveButton = new Button(saveButtonLabel);
    }

    private void initBackButton() {
        this.backButton.getElement().getStyle().set("margin-right", "auto");
    }

    public void createBackButtonClickListenerWithRemoveObjectFromContext(Class<T> objClass, String backButtonNavigationPath){
        this.backButton.addClickListener(buttonClickEvent -> {
            UI ui = UI.getCurrent();
            removeObjectFromContext(ui, objClass);
            ui.navigate(backButtonNavigationPath);
        });
    }

    private void removeObjectFromContext(UI ui, Class<T> objClass){
        ComponentUtil.setData(ui, objClass, null);
    }
}
