package pl.com.pt4q.product_manager.modules.environment.ui.bat;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;
import lombok.extern.log4j.Log4j2;
import pl.com.pt4q.product_manager.modules.environment.ui.master.detail.EnvMasterDetailView;
import pl.com.pt4q.product_manager.view_utils.SaveAndBackButtonsDiv;
import pl.com.pt4q.product_manager.views.main.MainView;

@Log4j2
@Route(value = EnvBatView.ROUTE, layout = MainView.class)
@PageTitle(EnvBatView.PAGE_TITLE)
public class EnvBatView extends Div implements HasUrlParameter<String> {

    public static final String PAGE_TITLE = "Battery type product card";
    public static final String ROUTE = EnvMasterDetailView.ROUTE + "-bat";
    public static final String QUERY_PARAM_ID_NAME = "productId";

    private SaveAndBackButtonsDiv saveAndBackButtonsDiv = new SaveAndBackButtonsDiv("Save BAT card");
    private EnvBatEditorDiv weeeEditorDiv = new EnvBatEditorDiv();

    public EnvBatView() {

        initSaveButton();
        initBackButton();

        VerticalLayout layout = new VerticalLayout();
        layout.add(this.saveAndBackButtonsDiv, this.weeeEditorDiv);
        layout.setAlignItems(FlexComponent.Alignment.CENTER);
        layout.setSizeFull();

        setSizeFull();
        add(layout);
    }

    private void initSaveButton(){

    }

    private void initBackButton(){
        this.saveAndBackButtonsDiv.getBackButton().addClickListener(buttonClickEvent -> {
            UI ui = UI.getCurrent();
            ui.navigate(EnvMasterDetailView.ROUTE);
        });
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, @OptionalParameter String s) {

    }
}
