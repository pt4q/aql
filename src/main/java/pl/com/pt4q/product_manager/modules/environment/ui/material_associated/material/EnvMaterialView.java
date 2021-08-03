package pl.com.pt4q.product_manager.modules.environment.ui.material_associated.material;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.router.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import pl.com.pt4q.product_manager.modules.environment.data.material_associated.group_of_material.EnvMaterialGroupEntity;
import pl.com.pt4q.product_manager.modules.environment.services.material_associated.group_of_material.EnvMaterialGroupCrudService;
import pl.com.pt4q.product_manager.modules.environment.services.material_associated.group_of_material.exceptions.EnvMaterialGroupNotFoundException;
import pl.com.pt4q.product_manager.modules.environment.services.material_associated.material.EnvMaterialCrudService;
import pl.com.pt4q.product_manager.modules.environment.ui.material_associated.group_of_material.EnvMaterialGroupView;
import pl.com.pt4q.product_manager.views.main.MainView;

import java.util.List;
import java.util.Map;

@Log4j2
@Route(value = EnvMaterialView.ROUTE, layout = MainView.class)
@PageTitle(EnvMaterialView.PAGE_TITLE)
public class EnvMaterialView extends Div implements HasUrlParameter<String> {

    public static final String PAGE_TITLE = "Materials";
    public static final String ROUTE = EnvMaterialGroupView.ROUTE + "/materials";
    public static final String QUERY_PARAM_ID_NAME = "groupId";

    private EnvMaterialCrudService materialCrudService;
    private EnvMaterialGroupCrudService groupCrudService;

    private EnvMaterialGroupEntity materialGroupEntity;

    @Autowired
    public EnvMaterialView(EnvMaterialCrudService materialCrudService,
                           EnvMaterialGroupCrudService groupCrudService) {

        this.materialCrudService = materialCrudService;
        this.groupCrudService = groupCrudService;

        initGridSelectAction();
        initSaveButtonAction();
        initClearButtonAction();
        initDeleteButtonAction();

        SplitLayout layout = new SplitLayout();
//        layout.addToPrimary(gridDiv);
//        layout.addToSecondary(editorDiv);
        layout.setSizeFull();

        add(layout);
    }

    private void initGridSelectAction() {
//        this.gridDiv.getMaterialGroupGrid().addItemClickListener(event -> {
//            this.gridDiv.getMaterialGroupGrid().getSelectedItems()
//                    .stream()
//                    .findFirst()
//                    .ifPresent(this::populateForm);
//        });
    }

    private void initSaveButtonAction() {
//        this.editorDiv.getButtonsDiv().getSaveButton().addClickListener(buttonClickEvent -> {
//            Binder<EnvMaterialGroupEntity> formBinder = this.editorDiv.getBinder();
//            formBinder.validate().getBeanValidationErrors();
//
//            if (formBinder.isValid()){
//                EnvMaterialGroupEntity group = formBinder.getBean();
//                try {
//                    this.materialGroupCrudService.create(group);
//                    populateForm(null);
//                    reloadGrid();
//                    Notification.show(String.format("%s: Group of materials '%s' card has been created", PAGE_TITLE, group.getNamePL()));
//
//                } catch (EnvMaterialGroupAlreadyExistsException e) {
//                    try {
//                        this.materialGroupCrudService.updateOrThrow(group);
//                    } catch (EnvMaterialGroupNotFoundException ex) {
//                        String errMsg = ex.getMessage();
//                        EnvMaterialGroupEntity fromForm = formBinder.getBean();
//                        log.error(String.format("%s: %s for object %s", PAGE_TITLE, errMsg, fromForm.toString()));
//                        Notification.show(String.format("%s: Cannot update group of materials '%s'", PAGE_TITLE, fromForm.getNamePL()));
//                    }
//                }
//            }
//        });
    }

    private void initClearButtonAction() {
//        this.editorDiv.getButtonsDiv().getClearButton().addClickListener(buttonClickEvent -> {
//            populateForm(new EnvMaterialGroupEntity());
//        });
    }

    private void initDeleteButtonAction() {
//        this.editorDiv.getButtonsDiv().getDeleteButton().addClickListener(buttonClickEvent -> {
//            Notification.show("Not implemented yet");
//        });
    }

    private void reloadGrid() {
//        this.gridDiv.refreshGrid(this.materialGroupCrudService.getAll());
    }

    private void populateForm(EnvMaterialGroupEntity materialEntity) {
//        this.editorDiv.populateForm(materialEntity);
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, @OptionalParameter String s) {
        Location location = beforeEvent.getLocation();
        QueryParameters queryParameters = location.getQueryParameters();
        Map<String, List<String>> parametersMap = queryParameters.getParameters();

        if (parametersMap.containsKey(QUERY_PARAM_ID_NAME)) {
            Long id = Long.valueOf(parametersMap.get(QUERY_PARAM_ID_NAME).get(0));
            try {
                this.materialGroupEntity = this.groupCrudService.getByIdOrThrow(id);
            } catch (EnvMaterialGroupNotFoundException e) {
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
