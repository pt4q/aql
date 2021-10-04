package pl.com.pt4q.product_manager.modules.environment.ui.light_source;

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
@Route(value = EnvLightSourceView.ROUTE, layout = MainView.class)
@PageTitle(EnvLightSourceView.PAGE_TITLE)
public class EnvLightSourceView extends Div implements HasUrlParameter<String> {

    public static final String PAGE_TITLE = "Light Source type product card";
    public static final String ROUTE = EnvMasterDetailView.ROUTE + "-ls";
    public static final String QUERY_PARAM_ID_NAME = "productId";

    private SaveAndBackButtonsDiv saveAndBackButtonsDiv = new SaveAndBackButtonsDiv("Save LS card");
    private EnvLightSourceEditorDiv weeeEditorDiv = new EnvLightSourceEditorDiv();

    public EnvLightSourceView() {

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
