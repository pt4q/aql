package pl.com.pt4q.product_manager.modules.product.ui.product_part;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import lombok.Getter;
import lombok.Setter;
import pl.com.pt4q.product_manager.modules.product.data.product_part.ProductPartAttributeEntity;


class AttributeEditorDiv extends Div {

    private TextField newAttributeNameTextField = new TextField("New attribute name");
    private TextField newAttributeValueTextField = new TextField("New attribute value");

    private Button saveButton = new Button(new Icon(VaadinIcon.PLUS_CIRCLE_O));
    private Button clearButton = new Button(new Icon(VaadinIcon.CLOSE_CIRCLE_O));
    private Button deleteButton = new Button(new Icon(VaadinIcon.MINUS_CIRCLE));

    private Binder<ProductPartAttributeEntity> partAttributeEntityBinder = new Binder<>(ProductPartAttributeEntity.class);

    @Setter
    @Getter
    private ProductPartAttributeEntity attributeEntity;

    public AttributeEditorDiv() {
        setId("editor-layout");

        initAttributeBinder();
        initEditorButtons();

        HorizontalLayout editorLayout = initNewAttributeForm();
        editorLayout.add(saveButton, clearButton, deleteButton);

        editorLayout.setWidthFull();
        editorLayout.setAlignItems(FlexComponent.Alignment.BASELINE);
        editorLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);

        add(editorLayout);
    }

    private void initAttributeBinder() {
        partAttributeEntityBinder
                .forField(newAttributeNameTextField)
                .asRequired("Type attribute name")
                .bind(ProductPartAttributeEntity::getAttributeName, ProductPartAttributeEntity::setAttributeName);
        partAttributeEntityBinder
                .forField(newAttributeValueTextField)
                .asRequired("Type attribute value")
                .bind(ProductPartAttributeEntity::getAttributeValue, ProductPartAttributeEntity::setAttributeValue);
    }

    private HorizontalLayout initNewAttributeForm() {
        newAttributeNameTextField.setMaxWidth("50%");
        newAttributeNameTextField.setMinWidth("30%");

        newAttributeValueTextField.setMaxWidth("20%");
        newAttributeValueTextField.setMinWidth("10%");

        Component[] fields = new Component[]{newAttributeNameTextField, newAttributeValueTextField};
        for (Component field : fields) {
            ((HasStyle) field).addClassName("full-width");
        }

        return new HorizontalLayout(fields);
    }

    private void initEditorButtons() {
        saveButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        saveButton.getElement().setProperty("title", "save attribute");
        clearButton.getElement().setProperty("title", "clear form fields");
        deleteButton.getElement().setProperty("title", "delete attribute");

        saveButton.addClickListener(buttonClickEvent -> {

        });
        clearButton.addClickListener(buttonClickEvent -> clearForm());
        deleteButton.addClickListener(buttonClickEvent -> {

        });
    }

    private void clearForm() {
        populateForm(null);
    }

    public void populateForm(ProductPartAttributeEntity value) {
        this.attributeEntity = value;
        partAttributeEntityBinder.readBean(this.attributeEntity);
    }
}
