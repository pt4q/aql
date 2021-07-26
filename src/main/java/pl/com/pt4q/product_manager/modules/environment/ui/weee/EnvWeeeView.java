package pl.com.pt4q.product_manager.modules.environment.ui.weee;

import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import pl.com.pt4q.product_manager.modules.environment.data.master.EnvMasterEntity;
import pl.com.pt4q.product_manager.modules.environment.data.weee.EnvWeeeEntity;
import pl.com.pt4q.product_manager.modules.environment.services.master.EnvMasterFinderService;
import pl.com.pt4q.product_manager.modules.environment.services.master.EnvMasterSaverService;
import pl.com.pt4q.product_manager.modules.environment.services.master.exceptions.EnvMasterNotFoundException;
import pl.com.pt4q.product_manager.modules.environment.services.weee.EnvWeeeSaverService;
import pl.com.pt4q.product_manager.modules.environment.services.weee.exceptions.EnvWeeeAlreadyExistsException;
import pl.com.pt4q.product_manager.modules.environment.ui.master.detail.EnvMasterDetailView;
import pl.com.pt4q.product_manager.modules.product.services.unit.UnitCrudService;
import pl.com.pt4q.product_manager.view_utils.SaveAndBackButtonsDiv;
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

    private SaveAndBackButtonsDiv saveAndBackButtonsDiv = new SaveAndBackButtonsDiv("Save WEEE card");
    private EnvWeeeEditorDiv weeeEditorDiv;

    private EnvWeeeSaverService envWeeeSaverService;
    private EnvMasterFinderService envMasterFinderService;
    private EnvMasterSaverService envMasterSaverService;
    private UnitCrudService unitCrudService;

    private EnvMasterEntity envMasterEntity;

    @Autowired
    public EnvWeeeView(EnvWeeeSaverService envWeeeSaverService,
                       EnvMasterFinderService envMasterFinderService,
                       EnvMasterSaverService envMasterSaverService,
                       UnitCrudService unitCrudService) {

        this.envWeeeSaverService = envWeeeSaverService;
        this.envMasterFinderService = envMasterFinderService;
        this.envMasterSaverService = envMasterSaverService;
        this.unitCrudService = unitCrudService;

        this.envMasterEntity = getMasterEntityFromContext();
//        if (this.envMasterEntity != null)
//            this.envMasterEntity.setWeee(getWeeeFromMasterOrInitNew(this.envMasterEntity));

        this.weeeEditorDiv = new EnvWeeeEditorDiv(this.unitCrudService, this.envMasterEntity);

        initSaveButton();
        initBackButton();

        if (this.envMasterEntity != null)
            populateWeeeForm(this.envMasterEntity.getWeee());

        VerticalLayout layout = new VerticalLayout();
        layout.add(this.saveAndBackButtonsDiv, this.weeeEditorDiv);
        layout.setAlignItems(FlexComponent.Alignment.CENTER);
        layout.setSizeFull();

        setSizeFull();
        add(layout);
    }

    private EnvWeeeEntity getWeeeFromMasterOrInitNew(EnvMasterEntity masterEntity) {
        if (masterEntity != null)
            return envMasterEntity.getWeee() != null ? envMasterEntity.getWeee() : new EnvWeeeEntity();
        else
            return null;
    }

    private void populateWeeeForm(EnvWeeeEntity weeeEntity) {
        this.weeeEditorDiv.populateForm(weeeEntity);
    }

    private void initSaveButton() {
        this.saveAndBackButtonsDiv.getSaveButton().addClickListener(buttonClickEvent -> {
            Binder<EnvWeeeEntity> formBinder = this.weeeEditorDiv.getWeeeEntityBinder();
            formBinder.validate().getBeanValidationErrors();

            if (formBinder.isValid()) {
                try {
                    envWeeeSaverService.createWeee(formBinder.getBean());
                    saveMasterToContext(this.envMasterEntity);
                    Notification.show(String.format("%s: WEEE card has been created for %s", PAGE_TITLE, this.envMasterEntity.getProduct().getSku()));

                } catch (EnvWeeeAlreadyExistsException e) {
                    try {
//                        this.envMasterEntity.setWeee(formBinder.getBean());
                        this.envMasterEntity = envMasterSaverService.update(this.envMasterEntity);
                        saveMasterToContext(this.envMasterEntity);
                        Notification.show(String.format("%s: WEEE card has been updated for %s", PAGE_TITLE, this.envMasterEntity.getProduct().getSku()));

                    } catch (EnvMasterNotFoundException ex) {
                        String errMsg = ex.getMessage();
                        EnvWeeeEntity fromBinder = formBinder.getBean();
                        log.error(String.format("%s: %s for object %s", PAGE_TITLE, errMsg, fromBinder.toString()));
                        Notification.show(String.format("%s: Cannot update WEEE card for %s", PAGE_TITLE, this.envMasterEntity.getProduct()));
                    }
                }
            }
        });
    }

    private void initBackButton() {
        this.saveAndBackButtonsDiv.getBackButton().addClickListener(buttonClickEvent -> {
            Binder<EnvWeeeEntity> weeeEntityBinder = this.weeeEditorDiv.getWeeeEntityBinder();

            if (weeeEntityBinder.getBean() != null && weeeEntityBinder.getBean().getId() != null)
                this.envMasterEntity.setWeee(weeeEntityBinder.getBean());
            else {
                this.envMasterEntity.setWeee(null);
                showNotification(String.format("WEEE card for %s product has not been saved", envMasterEntity.getProduct().getSku()));
            }

            saveMasterToContext(this.envMasterEntity);
            UI.getCurrent().navigate(EnvMasterDetailView.ROUTE);
        });
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, @OptionalParameter String s) {
        Location location = beforeEvent.getLocation();
        QueryParameters queryParameters = location.getQueryParameters();
        Map<String, List<String>> parametersMap = queryParameters.getParameters();

        if (parametersMap.containsKey(QUERY_PARAM_ID_NAME)) {
            Long id = Long.valueOf(parametersMap.get(QUERY_PARAM_ID_NAME).get(0));
            try {
                this.envMasterEntity = envMasterFinderService.findByIdOrThrowException(id);
                saveMasterToContext(this.envMasterEntity);
                populateWeeeForm(this.envMasterEntity.getWeee());
            } catch (EnvMasterNotFoundException e) {
                log.warn(showNotification(e.getMessage()));
            }
        }
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
