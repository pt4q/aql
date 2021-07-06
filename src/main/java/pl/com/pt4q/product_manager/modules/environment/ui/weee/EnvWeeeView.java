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
import pl.com.pt4q.product_manager.modules.environment.services.master.exceptions.EnvMasterAlreadyExistsException;
import pl.com.pt4q.product_manager.modules.environment.services.weee.EnvWeeeFinderService;
import pl.com.pt4q.product_manager.modules.environment.services.weee.EnvWeeeSaverService;
import pl.com.pt4q.product_manager.modules.environment.services.weee.exceptions.EnvWeeeAlreadyExistsException;
import pl.com.pt4q.product_manager.modules.environment.services.weee.exceptions.EnvWeeeNotFoundException;
import pl.com.pt4q.product_manager.modules.environment.ui.master.detail.EnvMasterDetailView;
import pl.com.pt4q.product_manager.modules.product.services.unit.UnitCrudService;
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
    private EnvWeeeEditorDiv weeeEditorDiv;

    private EnvWeeeFinderService envWeeeFinderService;
    private EnvWeeeSaverService envWeeeSaverService;
    private UnitCrudService unitCrudService;

    private EnvWeeeEntity envWeeeEntity;
    private EnvMasterEntity envMasterEntity;

    @Autowired
    public EnvWeeeView(EnvWeeeFinderService envWeeeFinderService,
                       EnvWeeeSaverService envWeeeSaverService,
                       UnitCrudService unitCrudService) {

        this.envWeeeFinderService = envWeeeFinderService;
        this.envWeeeSaverService = envWeeeSaverService;
        this.unitCrudService = unitCrudService;

        this.envMasterEntity = getMasterEntityFromContext();
        this.envWeeeEntity = initWeeeEntity();

        this.weeeEditorDiv = new EnvWeeeEditorDiv(unitCrudService);

        initSaveButton();
        initBackButton();

        populateWeeeForm(this.envWeeeEntity);

        VerticalLayout layout = new VerticalLayout();
        layout.add(this.saveObjectAndBackButtonsDiv, this.weeeEditorDiv);
        layout.setAlignItems(FlexComponent.Alignment.CENTER);
        layout.setSizeFull();

        setSizeFull();
        add(layout);
    }

    private EnvWeeeEntity initWeeeEntity() {
        if (this.envMasterEntity != null) {
            if (this.envMasterEntity.getWeee() != null)
                return envMasterEntity.getWeee();
            else
                return EnvWeeeEntity.builder().master(envMasterEntity).build();
        } else
            return null;
    }

    private EnvMasterEntity getMasterEntityFromContext() {
        return ComponentUtil.getData(UI.getCurrent(), EnvMasterEntity.class);
    }

    private void saveMasterToContext(UI ui, EnvMasterEntity masterEntity) {
        ComponentUtil.setData(ui, EnvMasterEntity.class, masterEntity);
    }

    private void populateWeeeForm(EnvWeeeEntity weeeEntity) {
        this.weeeEditorDiv.populateForm(weeeEntity);
    }

    private void initSaveButton() {
        this.saveObjectAndBackButtonsDiv.getSaveButton().addClickListener(buttonClickEvent -> {
            Binder<EnvWeeeEntity> formBinder = this.weeeEditorDiv.getWeeeEntityBinder();
            formBinder.validate().getBeanValidationErrors();

            if (formBinder.isValid()){
                try {
                    this.envWeeeEntity = envWeeeSaverService.create(formBinder.getBean());
                    this.envMasterEntity.setWeee(envWeeeEntity);
                    saveMasterToContext(UI.getCurrent(), this.envMasterEntity);
                    Notification.show(String.format("%s: WEEE card has been created for %s", PAGE_TITLE, envWeeeEntity.getMaster().getProduct().getSku()));
                } catch (EnvWeeeAlreadyExistsException | EnvMasterAlreadyExistsException e) {
                    try {
                        this.envWeeeEntity = envWeeeSaverService.update(formBinder.getBean());
                        this.envMasterEntity.setWeee(envWeeeEntity);
                        saveMasterToContext(UI.getCurrent(), this.envMasterEntity);
                        Notification.show(String.format("%s: WEEE card has been updated for %s", PAGE_TITLE, envWeeeEntity.getMaster().getProduct().getSku()));
                    } catch (EnvWeeeNotFoundException ex) {
                        String errMsg = ex.getMessage();
                        EnvWeeeEntity fromBinder = formBinder.getBean();
                        log.error(String.format("%s: %s for object %s", PAGE_TITLE, errMsg, fromBinder.toString()));
                        Notification.show(String.format("%s: Cannot update WEEE card for %s", PAGE_TITLE, fromBinder.getMaster().getProduct()));
                    }
                }
            }
        });
    }

    private void initBackButton() {
        this.saveObjectAndBackButtonsDiv.getBackButton().addClickListener(buttonClickEvent -> {
            if(this.weeeEditorDiv.getWeeeEntityBinder().getBean().getId() == null)
                Notification.show(String.format("WEEE card for %s product has not been saved", envMasterEntity.getProduct().getSku()));
            UI ui = UI.getCurrent();

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
            try {
                this.envWeeeEntity = this.envWeeeFinderService.findByIdOrThrowException(id);
                saveMasterToContext(UI.getCurrent(), this.envWeeeEntity.getMaster());
                populateWeeeForm(this.envWeeeEntity);
            } catch (EnvWeeeNotFoundException e) {
                log.warn(showNotification(e.getMessage()));
            }
        }
    }

    private String showNotification(String message) {
        String theWholeMessage = String.format("%s: %s", PAGE_TITLE, message);
        Notification.show(theWholeMessage);
        return theWholeMessage;
    }
}
