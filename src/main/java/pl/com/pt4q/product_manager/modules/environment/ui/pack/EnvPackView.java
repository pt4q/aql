package pl.com.pt4q.product_manager.modules.environment.ui.pack;

import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.*;
import lombok.extern.log4j.Log4j2;
import pl.com.pt4q.product_manager.modules.environment.data.master.EnvMasterEntity;
import pl.com.pt4q.product_manager.modules.environment.data.pack.EnvPackagingEntity;
import pl.com.pt4q.product_manager.modules.environment.ui.master.detail.EnvMasterDetailView;
import pl.com.pt4q.product_manager.modules.product.services.unit.UnitCrudService;
import pl.com.pt4q.product_manager.view_utils.SaveObjectAndBackButtonsDiv;
import pl.com.pt4q.product_manager.views.main.MainView;

@Log4j2
@Route(value = EnvPackView.ROUTE, layout = MainView.class)
@PageTitle(EnvPackView.PAGE_TITLE)
public class EnvPackView extends Div implements HasUrlParameter<String> {

    public static final String PAGE_TITLE = "Product pack card";
    public static final String ROUTE = EnvMasterDetailView.ROUTE + "-pack";
    public static final String QUERY_PARAM_ID_NAME = "productId";

    private SaveObjectAndBackButtonsDiv saveObjectAndBackButtonsDiv = new SaveObjectAndBackButtonsDiv("Save PACK card");
    private EnvPackEditorDiv packEditorDiv;


    private UnitCrudService unitCrudService;

    private EnvMasterEntity envMasterEntity;

    public EnvPackView(UnitCrudService unitCrudService) {

        this.unitCrudService = unitCrudService;

        this.envMasterEntity = getMasterEntityFromContext();
        if (this.envMasterEntity != null)
            this.envMasterEntity.setPackaging(getPackFromMasterOrInitNew(this.envMasterEntity));

        this.packEditorDiv = new EnvPackEditorDiv(unitCrudService, this.envMasterEntity);

        initSaveButton();
        initBackButton();

        if (this.envMasterEntity != null)
            populatePackForm(this.envMasterEntity.getPackaging());

        VerticalLayout layout = new VerticalLayout();
        layout.add(this.saveObjectAndBackButtonsDiv, this.packEditorDiv);
        layout.setAlignItems(FlexComponent.Alignment.CENTER);
        layout.setSizeFull();

        setSizeFull();
        add(layout);
    }

    private EnvPackagingEntity getPackFromMasterOrInitNew(EnvMasterEntity masterEntity) {
        if (masterEntity != null)
            return envMasterEntity.getPackaging() != null ? envMasterEntity.getPackaging() : new EnvPackagingEntity();
        else
            return null;
    }

    private void populatePackForm(EnvPackagingEntity packagingEntity) {
        this.packEditorDiv.populateForm(packagingEntity);
    }

    private void initSaveButton(){

    }

    private void initBackButton(){
        this.saveObjectAndBackButtonsDiv.getBackButton().addClickListener(buttonClickEvent -> {
            Binder<EnvPackagingEntity> packagingEntityBinder = this.packEditorDiv.getPackEntityBinder();

            if (packagingEntityBinder.getBean() != null && packagingEntityBinder.getBean().getId() != null)
                this.envMasterEntity.setPackaging(packagingEntityBinder.getBean());
            else {
                this.envMasterEntity.setPackaging(null);
                showNotification(String.format("PACK card for %s product has not been saved", envMasterEntity.getProduct().getSku()));
            }
            saveMasterToContext(this.envMasterEntity);
            UI.getCurrent().navigate(EnvMasterDetailView.ROUTE);
        });
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, @OptionalParameter String s) {

    }

    private String showNotification(String message) {
        String theWholeMessage = String.format("%s: %s", PAGE_TITLE, message);
        Notification.show(theWholeMessage);
        return theWholeMessage;
    }

    private void saveMasterToContext(EnvMasterEntity master) {
        ComponentUtil.setData(UI.getCurrent(), EnvMasterEntity.class, master);
    }

    private EnvMasterEntity getMasterEntityFromContext() {
        return ComponentUtil.getData(UI.getCurrent(), EnvMasterEntity.class);
    }
}
