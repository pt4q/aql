package pl.com.pt4q.product_manager.view_utils;


import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import lombok.Getter;

@Getter
public class BackButtonDiv extends Div {

    private Button backButton;

    public BackButtonDiv() {
        this.backButton = new Button(new Icon(VaadinIcon.ARROW_BACKWARD));
        add(backButton);
    }
}
