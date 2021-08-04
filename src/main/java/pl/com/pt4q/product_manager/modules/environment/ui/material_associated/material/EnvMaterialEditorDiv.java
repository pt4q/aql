package pl.com.pt4q.product_manager.modules.environment.ui.material_associated.material;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import lombok.Getter;
import pl.com.pt4q.product_manager.modules.environment.data.material_associated.group_of_material.EnvMaterialGroupEntity;
import pl.com.pt4q.product_manager.modules.environment.data.material_associated.material.EnvMaterialEntity;
import pl.com.pt4q.product_manager.view_utils.SaveClearAndDeleteButtonsDiv;

class EnvMaterialEditorDiv extends Div {

    private TextField groupNamePL = new TextField("Material name in PL");
    private TextField groupNameENG = new TextField("Material name in ENG");

    @Getter
    private SaveClearAndDeleteButtonsDiv buttonsDiv = new SaveClearAndDeleteButtonsDiv();
    @Getter
    private Binder<EnvMaterialEntity> binder = new Binder<>();

    private EnvMaterialGroupEntity materialGroupEntity;

    public EnvMaterialEditorDiv(EnvMaterialGroupEntity materialGroupEntity) {

        this.materialGroupEntity = materialGroupEntity;

        initFields();
        initBinder(materialGroupEntity);

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

    private void initBinder(EnvMaterialGroupEntity materialGroupEntity){
        this.binder.forField(groupNamePL)
                .asRequired("Material name cannot be empty")
                .bind(EnvMaterialEntity::getNamePL, EnvMaterialEntity::setNamePL);
        this.binder.forField(groupNameENG)
                .asRequired("Material name cannot be empty")
                .bind(EnvMaterialEntity::getNameENG, EnvMaterialEntity::setNameENG);

        this.binder.setBean(EnvMaterialEntity.builder().group(materialGroupEntity).build());
    }

    public void populateForm(EnvMaterialEntity material){
        material.setGroup(this.materialGroupEntity);
        this.binder.setBean(material);
    }
}
