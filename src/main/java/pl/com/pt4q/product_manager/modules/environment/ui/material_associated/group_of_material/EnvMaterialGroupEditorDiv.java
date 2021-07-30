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

    private TextField groupName = new TextField("Group name");

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
                this.groupName,
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
        this.groupName.setSizeFull();
    }

    private void initBinder(){
        this.binder.forField(groupName)
                .asRequired("Group name cannot be empty")
                .bind(EnvMaterialGroupEntity::getName, EnvMaterialGroupEntity::setName);

        this.binder.setBean(new EnvMaterialGroupEntity());
    }

    public void populateForm(EnvMaterialGroupEntity group){
        this.binder.setBean(group);
    }
}
