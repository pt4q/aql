package pl.com.pt4q.product_manager.modules.environment.ui.material_associated.group_of_material;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import lombok.Getter;
import pl.com.pt4q.product_manager.modules.environment.data.material_associated.group_of_material.EnvMaterialGroupEntity;
import pl.com.pt4q.product_manager.view_utils.SaveClearAndDeleteButtonsDiv;

class EnvMaterialGroupEditorDiv extends Div {

    private TextField groupNamePL = new TextField("Group of materials name in PL");
    private TextField groupNameENG = new TextField("Group of materials name in ENG");

    @Getter
    private SaveClearAndDeleteButtonsDiv buttonsDiv = new SaveClearAndDeleteButtonsDiv();
    @Getter
    private Binder<EnvMaterialGroupEntity> binder = new Binder<>();

    public EnvMaterialGroupEditorDiv() {
        initFields();
        initBinder();

        setId("editor");
        setMinWidth("20%");
        setMaxWidth("40%");
        add(initFormLayoutDiv());
    }

    private Div initFormLayoutDiv() {
        VerticalLayout formLayout = new VerticalLayout();
        formLayout.setId("editor-layout");
        formLayout.add(
                this.groupNamePL,
                this.groupNameENG,
                this.buttonsDiv
        );
        formLayout.setSizeFull();
        formLayout.setAlignItems(FlexComponent.Alignment.CENTER);

        Div layoutDiv = new Div();
        layoutDiv.add(formLayout);
        layoutDiv.setSizeFull();
        return layoutDiv;
    }

    private void initFields() {
        this.groupNamePL.setSizeFull();
        this.groupNameENG.setSizeFull();
    }

    private void initBinder(){
        this.binder.forField(groupNamePL)
                .asRequired("Group name cannot be empty")
                .bind(EnvMaterialGroupEntity::getNamePL, EnvMaterialGroupEntity::setNamePL);
        this.binder.forField(groupNameENG)
                .asRequired("Group name cannot be empty")
                .bind(EnvMaterialGroupEntity::getNameENG, EnvMaterialGroupEntity::setNameENG);

        this.binder.setBean(new EnvMaterialGroupEntity());
    }

    public void populateForm(EnvMaterialGroupEntity group){
        this.binder.setBean(group);
    }
}
