package pl.com.pt4q.product_manager.modules.environment.ui.master.detail;

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
import pl.com.pt4q.product_manager.modules.environment.services.master.EnvMasterFinderService;
import pl.com.pt4q.product_manager.modules.environment.services.master.EnvMasterSaverService;
import pl.com.pt4q.product_manager.modules.environment.services.master.exceptions.EnvMasterAlreadyExistsException;
import pl.com.pt4q.product_manager.modules.environment.services.master.exceptions.EnvMasterNotFoundException;
import pl.com.pt4q.product_manager.modules.environment.services.pack.EnvPackFinderService;
import pl.com.pt4q.product_manager.modules.environment.services.weee.EnvWeeeFinderService;
import pl.com.pt4q.product_manager.modules.environment.ui.bat.EnvBatView;
import pl.com.pt4q.product_manager.modules.environment.ui.light_source.EnvLightSourceView;
import pl.com.pt4q.product_manager.modules.environment.ui.master.general.EnvMasterGeneralView;
import pl.com.pt4q.product_manager.modules.environment.ui.pack.EnvPacksView;
import pl.com.pt4q.product_manager.modules.environment.ui.weee.EnvWeeeView;
import pl.com.pt4q.product_manager.modules.product.services.product.ProductFinderService;
import pl.com.pt4q.product_manager.modules.product.services.unit.UnitCrudService;
import pl.com.pt4q.product_manager.view_utils.SaveAndBackButtonsDiv;
import pl.com.pt4q.product_manager.views.main.MainView;

import java.util.List;
import java.util.Map;

@Log4j2
@Route(value = EnvMasterDetailView.ROUTE, layout = MainView.class)
@PageTitle(EnvMasterDetailView.PAGE_TITLE)
public class EnvMasterDetailView extends Div implements HasUrlParameter<String> {

    public static final String PAGE_TITLE = EnvMasterGeneralView.PAGE_TITLE + " of product";
    public static final String ROUTE = EnvMasterGeneralView.ROUTE + "/detail";
    public static final String QUERY_PARAM_ID_NAME = "masterId";

    private SaveAndBackButtonsDiv saveAndBackButtonsDiv;
    private EnvMasterDetailEditorDiv masterDetailEditorDiv;
    private AddAdditionalEnvCardsButtonsDiv buttonsDiv;

    private ProductFinderService productFinderService;
    private UnitCrudService unitCrudService;

    private EnvWeeeFinderService weeeFinderService;
    private EnvPackFinderService packFinderService;

    private EnvMasterFinderService masterFinderService;
    private EnvMasterSaverService masterSaverService;

    private EnvMasterEntity envMasterEntity;

    @Autowired
    public EnvMasterDetailView(ProductFinderService productFinderService,
                               UnitCrudService unitCrudService,
                               EnvWeeeFinderService weeeFinderService,
                               EnvPackFinderService packFinderService,
                               EnvMasterFinderService masterFinderService,
                               EnvMasterSaverService masterSaverService) {

        this.productFinderService = productFinderService;
        this.unitCrudService = unitCrudService;
        this.weeeFinderService = weeeFinderService;
        this.packFinderService = packFinderService;
        this.masterFinderService = masterFinderService;
        this.masterSaverService = masterSaverService;

        this.envMasterEntity = getMasterFromContextOrCreateNew();

        this.saveAndBackButtonsDiv = new SaveAndBackButtonsDiv("Save master card");
        this.masterDetailEditorDiv = new EnvMasterDetailEditorDiv(this.productFinderService, this.unitCrudService);
        this.buttonsDiv = new AddAdditionalEnvCardsButtonsDiv();

        initMasterAdditionalCardsButtons();

        initSaveButton();
        initBackButton();

        populateForm(this.envMasterEntity);

        VerticalLayout layout = new VerticalLayout();
        layout.add(this.saveAndBackButtonsDiv, this.masterDetailEditorDiv, this.buttonsDiv);
        layout.setAlignItems(FlexComponent.Alignment.CENTER);
        layout.setSizeFull();

        setSizeFull();
        add(layout);
    }

    private EnvMasterEntity getMasterFromContextOrCreateNew() {
        EnvMasterEntity masterEntity = ComponentUtil.getData(UI.getCurrent(), EnvMasterEntity.class);
        return masterEntity != null ? masterEntity : new EnvMasterEntity();
    }

    private void saveMasterToContext(UI ui, EnvMasterEntity masterEntity) {
        ComponentUtil.setData(ui, EnvMasterEntity.class, masterEntity);
    }

    private void populateForm(EnvMasterEntity masterEntity) {
        this.masterDetailEditorDiv.populateForm(masterEntity);
    }

    private void initMasterAdditionalCardsButtons() {
        if (this.weeeFinderService.findByMaster(this.envMasterEntity).isPresent())
            this.buttonsDiv.getAddWeeButton().setText("Open WEEE");

        if (this.envMasterEntity.getLightSource() != null)
            this.buttonsDiv.getAddLightSourceButton().setText("Open LS");
        if (this.envMasterEntity.getBattery() != null)
            this.buttonsDiv.getAddBatButton().setText("Open BAT");
        if (!this.packFinderService.findByMaster(this.envMasterEntity).isEmpty())
            this.buttonsDiv.getAddPackButton().setText("Open PACK");

        if (this.envMasterEntity != null) {
            this.buttonsDiv.getAddWeeButton().addClickListener(buttonClickEvent -> saveMasterToContextIfBinderIsValidAndRouteToEndpoint(EnvWeeeView.ROUTE));
            this.buttonsDiv.getAddLightSourceButton().addClickListener(buttonClickEvent -> saveMasterToContextIfBinderIsValidAndRouteToEndpoint(EnvLightSourceView.ROUTE));
            this.buttonsDiv.getAddBatButton().addClickListener(buttonClickEvent -> saveMasterToContextIfBinderIsValidAndRouteToEndpoint(EnvBatView.ROUTE));
            this.buttonsDiv.getAddPackButton().addClickListener(buttonClickEvent -> saveMasterToContextIfBinderIsValidAndRouteToEndpoint(EnvPacksView.ROUTE));
        }
    }

    private void saveMasterToContextIfBinderIsValidAndRouteToEndpoint(String route) {
        this.masterDetailEditorDiv.getMasterBinder().validate();
        if (this.masterDetailEditorDiv.getMasterBinder().isValid()) {
            EnvMasterEntity masterEntityFromForm = this.masterDetailEditorDiv.getMasterBinder().getBean();
            UI ui = UI.getCurrent();
            ComponentUtil.setData(ui, EnvMasterEntity.class, masterEntityFromForm);
            ui.navigate(route);
        }
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, @OptionalParameter String s) {
        Location location = beforeEvent.getLocation();
        QueryParameters queryParameters = location.getQueryParameters();
        Map<String, List<String>> parametersMap = queryParameters.getParameters();

        if (parametersMap.containsKey(QUERY_PARAM_ID_NAME)) {
            Long id = Long.valueOf(parametersMap.get(QUERY_PARAM_ID_NAME).get(0));
            try {
                this.envMasterEntity = masterFinderService.findByIdOrThrowException(id);
                populateForm(this.envMasterEntity);
                initMasterAdditionalCardsButtons();
                saveMasterToContext(UI.getCurrent(), this.envMasterEntity);
            } catch (EnvMasterNotFoundException e) {
                log.warn(showNotification(e.getMessage()));
            }
        }
    }

    private void initBackButton() {
        this.saveAndBackButtonsDiv.getBackButton().addClickListener(buttonClickEvent -> {
            UI ui = UI.getCurrent();
            saveMasterToContext(ui, null);
            ui.navigate(EnvMasterGeneralView.ROUTE);
        });
    }

    private void initSaveButton() {
        this.saveAndBackButtonsDiv.getSaveButton().addClickListener(buttonClickEvent -> {
            Binder<EnvMasterEntity> formBinder = this.masterDetailEditorDiv.getMasterBinder();
            formBinder.validate().getBeanValidationErrors();

            if (formBinder.isValid()) {
                try {
                    this.envMasterEntity = masterSaverService.create(formBinder.getBean());
                    Notification.show(String.format("%s: Master card has been created for %s", PAGE_TITLE, envMasterEntity.getProduct().getSku()));
                } catch (EnvMasterAlreadyExistsException e) {
                    try {
                        this.envMasterEntity = masterSaverService.update(formBinder.getBean());
                        Notification.show(String.format("%s: Master card has been updated for %s", PAGE_TITLE, envMasterEntity.getProduct().getSku()));
                    } catch (EnvMasterNotFoundException ex) {
                        String errMsg = ex.getMessage();
                        EnvMasterEntity fromBinder = formBinder.getBean();
                        log.error(String.format("%s: %s for object %s", PAGE_TITLE, errMsg, fromBinder.toString()));
                        Notification.show(String.format("%s: Cannot update master card for %s", PAGE_TITLE, fromBinder.getProduct()));
                    }
                }
            }
        });
    }

    private String showNotification(String message) {
        String theWholeMessage = String.format("%s: %s", PAGE_TITLE, message);
        Notification.show(theWholeMessage);
        return theWholeMessage;
    }
}
