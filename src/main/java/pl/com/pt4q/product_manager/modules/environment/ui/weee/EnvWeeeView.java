package pl.com.pt4q.product_manager.modules.environment.ui.weee;

import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;
import lombok.extern.log4j.Log4j2;
import pl.com.pt4q.product_manager.modules.environment.data.master.EnvMasterEntity;
import pl.com.pt4q.product_manager.modules.environment.data.weee.EnvWeeeEntity;
import pl.com.pt4q.product_manager.modules.environment.ui.master.detail.EnvMasterDetailView;
import pl.com.pt4q.product_manager.view_utils.SaveObjectAndBackButtonsDiv;
import pl.com.pt4q.product_manager.views.main.MainView;

import java.util.List;
import java.util.Map;

@Log4j2
@Route(value = EnvWeeeView.ROUTE, layout = MainView.class)
@PageTitle(EnvWeeeView.PAGE_TITLE)
public class EnvWeeeView extends Div implements HasUrlParameter<String> {

    public static final String PAGE_TITLE = "WEEE type product card";
    public static final String ROUTE = EnvMasterDetailView.ROUTE + "-weee";
    public static final String QUERY_PARAM_ID_NAME = "productId";

    private SaveObjectAndBackButtonsDiv saveObjectAndBackButtonsDiv = new SaveObjectAndBackButtonsDiv("Save WEEE card");
    private EnvWeeeEditorDiv weeeEditorDiv = new EnvWeeeEditorDiv();

    private EnvMasterEntity envMasterEntity;

    public EnvWeeeView() {

        this.envMasterEntity = getMasterEntityFromContext();

        initSaveButton();
        initBackButton();

        VerticalLayout layout = new VerticalLayout();
        layout.add(this.saveObjectAndBackButtonsDiv, this.weeeEditorDiv);
        layout.setAlignItems(FlexComponent.Alignment.CENTER);
        layout.setSizeFull();

        setSizeFull();
        add(layout);
    }

    private EnvMasterEntity getMasterEntityFromContext() {
        return ComponentUtil.getData(UI.getCurrent(), EnvMasterEntity.class);
    }

    private void saveMasterToContext(UI ui, EnvMasterEntity masterEntity) {
        ComponentUtil.setData(ui, EnvMasterEntity.class, masterEntity);
    }

    private void populateWeeeForm(EnvWeeeEntity weeeEntity) {
//        this.weeeEditorDiv;
    }

    private void initSaveButton() {
        this.saveObjectAndBackButtonsDiv.getSaveButton().addClickListener(buttonClickEvent -> {

        });
    }

    private void initBackButton() {
        this.saveObjectAndBackButtonsDiv.getBackButton().addClickListener(buttonClickEvent -> {
            UI ui = UI.getCurrent();
            Notification.show(String.format("WEEE card for %s product has not been saved", envMasterEntity.getProduct().getSku()));
            ui.navigate(EnvMasterDetailView.ROUTE);
        });
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, @OptionalParameter String s) {
        Location location = beforeEvent.getLocation();
        QueryParameters queryParameters = location.getQueryParameters();
        Map<String, List<String>> parametersMap = queryParameters.getParameters();

        if (parametersMap.containsKey(QUERY_PARAM_ID_NAME)) {
            Long id = Long.valueOf(parametersMap.get(QUERY_PARAM_ID_NAME).get(0));

        }
    }
}
