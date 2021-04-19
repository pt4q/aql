package com.example.application.modules.test_card.ui.test_card_creator;

import com.example.application.modules.product.data.product_category.ProductCategoryEntity;
import com.example.application.modules.test_card.data.test_card.TestCardEntity;
import com.example.application.modules.product.services.product_category.ProductCategoryCrudService;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import lombok.Data;

import java.util.stream.Collectors;

@Data
class TestCardInfoDiv extends Div {

    private ComboBox<String> productCategoryComboBox = new ComboBox<>("Product category");
    private TextField testCardNameTextField = new TextField("Test card name");
    private TextField creationTimeText = new TextField("Creation time:");
    private TextField modificationTimeText = new TextField("Modification time:");

    private ProductCategoryCrudService productCategoryCrudService;
    private TestCardEntity testCard;

    public TestCardInfoDiv(TestCardEntity testCard, ProductCategoryCrudService productCategoryCrudService) {
        this.testCard = testCard;
        this.productCategoryCrudService = productCategoryCrudService;

        initProductCategoryComboBox();
        initTestCardNameTextField();

        if (testCard != null) {
            initCreationTimeField();
            initModificationTimeField();
        }

        VerticalLayout divLayout = new VerticalLayout();
        HorizontalLayout productCategoryAndTestCardNameLayout = new HorizontalLayout(productCategoryComboBox, testCardNameTextField);
        productCategoryAndTestCardNameLayout.setSizeFull();
        HorizontalLayout timeInfoInHorizontal = new HorizontalLayout(creationTimeText, modificationTimeText);

        divLayout.add(productCategoryAndTestCardNameLayout, timeInfoInHorizontal);
        divLayout.setAlignItems(FlexComponent.Alignment.CENTER);
        divLayout.setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);

        add(divLayout);
        setWidthFull();
    }

    private void initProductCategoryComboBox() {
        this.productCategoryComboBox
                .setItems(productCategoryCrudService.getAll()
                        .stream()
                        .map(ProductCategoryEntity::getProductCategoryName)
                        .collect(Collectors.toList()));
        this.productCategoryComboBox.setRequired(true);
        this.productCategoryComboBox.setWidth("20%");
        this.productCategoryComboBox.setMinWidth("20%");
    }

    private void initTestCardNameTextField() {
        this.testCardNameTextField.setAutofocus(true);
        this.testCardNameTextField.setAutoselect(true);
        this.testCardNameTextField.setRequired(true);
        this.testCardNameTextField.setAutocorrect(true);
        this.testCardNameTextField.setValueChangeMode(ValueChangeMode.LAZY);
        this.testCardNameTextField.setValueChangeTimeout(1000);
        this.testCardNameTextField.setWidthFull();

        this.testCardNameTextField.addValueChangeListener(e -> {
            String testCardName = testCardNameTextField.getValue();
            if (!testCardName.isEmpty())
                this.testCard.setTestCardName(testCardName);
        });
    }

    private void initCreationTimeField() {
        if (testCard.getCreationTime() != null) {
            this.creationTimeText.setValue(String.format("Creation time: %s", testCard.getCreationTime()));
        }
        this.creationTimeText.setReadOnly(true);
    }

    private void initModificationTimeField() {
        if (testCard.getModificationTime() != null) {
            this.modificationTimeText.setValue(String.format("Modification time: %s", testCard.getCreationTime()));
        }
        this.modificationTimeText.setReadOnly(true);
    }

}
