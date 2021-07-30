package pl.com.pt4q.product_manager.modules.environment.ui.material_associated.group_of_material;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import pl.com.pt4q.product_manager.modules.environment.data.material_associated.group_of_material.EnvMaterialGroupEntity;
import pl.com.pt4q.product_manager.modules.environment.services.material_associated.group_of_material.EnvMaterialGroupCrudService;
import pl.com.pt4q.product_manager.modules.environment.services.material_associated.group_of_material.exceptions.EnvMaterialGroupAlreadyExistsException;
import pl.com.pt4q.product_manager.modules.environment.services.material_associated.group_of_material.exceptions.EnvMaterialGroupNotFoundException;
import pl.com.pt4q.product_manager.views.main.MainView;

@Log4j2
@Route(value = EnvMaterialGroupView.ROUTE, layout = MainView.class)
@PageTitle(EnvMaterialGroupView.PAGE_TITLE)
public class EnvMaterialGroupView extends Div {

    public static final String PAGE_TITLE = "Materials";
    public static final String ROUTE = "material";

    private EnvMaterialGroupGridDiv gridDiv = new EnvMaterialGroupGridDiv();
    private EnvMaterialGroupEditorDiv editorDiv = new EnvMaterialGroupEditorDiv();

    private EnvMaterialGroupCrudService materialGroupCrudService;

    @Autowired
    public EnvMaterialGroupView(EnvMaterialGroupCrudService materialGroupCrudService) {
        this.materialGroupCrudService = materialGroupCrudService;

        initGridSelectAction();
        initSaveButtonAction();
        initClearButtonAction();
        initDeleteButtonAction();

        SplitLayout layout = new SplitLayout();
        layout.addToPrimary(gridDiv);
        layout.addToSecondary(editorDiv);
        layout.setSizeFull();

        add(layout);
    }

    private void initGridSelectAction(){
        this.gridDiv.getMaterialGroupGrid().addItemClickListener(event -> {
            this.gridDiv.getMaterialGroupGrid().getSelectedItems()
                    .stream()
                    .findFirst()
                    .ifPresent(this::populateForm);
        });
    }

    private void initSaveButtonAction(){
        this.editorDiv.getButtonsDiv().getSaveButton().addClickListener(buttonClickEvent -> {
            Binder<EnvMaterialGroupEntity> formBinder = this.editorDiv.getBinder();
            formBinder.validate().getBeanValidationErrors();

            if (formBinder.isValid()){
                EnvMaterialGroupEntity group = formBinder.getBean();
                try {
                    this.materialGroupCrudService.create(group);
                    populateForm(null);
                    reloadGrid();
                    Notification.show(String.format("%s: Group of materials '%s' card has been created", PAGE_TITLE, group.getName()));

                } catch (EnvMaterialGroupAlreadyExistsException e) {
                    try {
                        this.materialGroupCrudService.updateOrThrow(group);
                    } catch (EnvMaterialGroupNotFoundException ex) {
                        String errMsg = ex.getMessage();
                        EnvMaterialGroupEntity fromForm = formBinder.getBean();
                        log.error(String.format("%s: %s for object %s", PAGE_TITLE, errMsg, fromForm.toString()));
                        Notification.show(String.format("%s: Cannot update group of materials '%s'", PAGE_TITLE, fromForm.getName()));
                    }
                }
            }
        });
    }

    private void initClearButtonAction(){
        this.editorDiv.getButtonsDiv().getClearButton().addClickListener(buttonClickEvent -> {
            populateForm(new EnvMaterialGroupEntity());
        });
    }

    private void initDeleteButtonAction(){
        this.editorDiv.getButtonsDiv().getDeleteButton().addClickListener(buttonClickEvent -> {
            Notification.show("Not implemented yet");
        });
    }

    private void reloadGrid(){
        this.gridDiv.refreshGrid(this.materialGroupCrudService.getAll());
    }

    private void populateForm(EnvMaterialGroupEntity materialEntity){
        this.editorDiv.populateForm(materialEntity);
    }
}
