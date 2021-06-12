package pl.com.pt4q.product_manager.modules.environment.ui.master.detail;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;
import lombok.extern.log4j.Log4j2;
import pl.com.pt4q.product_manager.modules.environment.ui.bat.EnvBatView;
import pl.com.pt4q.product_manager.modules.environment.ui.light_source.EnvLightSourceView;
import pl.com.pt4q.product_manager.modules.environment.ui.master.general.EnvMasterGeneralView;
import pl.com.pt4q.product_manager.modules.environment.ui.weee.EnvWeeeView;
import pl.com.pt4q.product_manager.view_utils.SaveObjectAndBackButtonsDiv;
import pl.com.pt4q.product_manager.views.main.MainView;

@Log4j2
@Route(value = EnvMasterDetailView.ROUTE, layout = MainView.class)
@PageTitle(EnvMasterDetailView.PAGE_TITLE)
public class EnvMasterDetailView extends Div implements HasUrlParameter<String> {

    public static final String PAGE_TITLE = EnvMasterGeneralView.ROUTE + " of product";
    public static final String ROUTE = EnvMasterGeneralView.ROUTE + "-detail";
    public static final String QUERY_PARAM_ID_NAME = "masterId";

    private SaveObjectAndBackButtonsDiv saveObjectAndBackButtonsDiv = new SaveObjectAndBackButtonsDiv("Save master card");
    private EnvMasterDetailEditorDiv masterDetailEditorDiv = new EnvMasterDetailEditorDiv();
    private AddEnvCardsButtonsDiv buttonsDiv = new AddEnvCardsButtonsDiv();

    public EnvMasterDetailView() {
        initAddWeeButton();
        initAddLightSourceButton();
        initAddBatButton();
        initAddPackButton();

        initSaveButton();
        initBackButton();

        VerticalLayout layout = new VerticalLayout();
        layout.add(this.saveObjectAndBackButtonsDiv, this.masterDetailEditorDiv, this.buttonsDiv);
        layout.setAlignItems(FlexComponent.Alignment.CENTER);
        layout.setSizeFull();

        setSizeFull();
        add(layout);
    }


    private void initAddWeeButton(){
        this.buttonsDiv.getAddWeeButton().addClickListener(buttonClickEvent -> {
            UI ui = UI.getCurrent();
            ui.navigate(EnvWeeeView.ROUTE);
        });
    }

    private void initAddLightSourceButton(){
        this.buttonsDiv.getAddLightSourceButton().addClickListener(buttonClickEvent -> {
            UI ui = UI.getCurrent();
            ui.navigate(EnvLightSourceView.ROUTE);
        });
    }

    private void initAddBatButton(){
        this.buttonsDiv.getAddBatButton().addClickListener(buttonClickEvent -> {
            UI ui = UI.getCurrent();
            ui.navigate(EnvBatView.ROUTE);
        });
    }

    private void initAddPackButton(){
        this.buttonsDiv.getAddPackButton().addClickListener(buttonClickEvent -> {

        });
    }

    private void initSaveButton(){

    }

    private void initBackButton(){
        this.saveObjectAndBackButtonsDiv.getBackButton().addClickListener(buttonClickEvent -> {
            UI ui= UI.getCurrent();
            ui.navigate(EnvMasterGeneralView.ROUTE);
        });
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, @OptionalParameter String s) {

    }
}
