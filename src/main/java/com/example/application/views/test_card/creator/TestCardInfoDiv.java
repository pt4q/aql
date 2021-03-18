package com.example.application.views.test_card.creator;

import com.example.application.data.entity.test_card_associated.test_card.TestCardEntity;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import lombok.Data;

@Data
class TestCardInfoDiv extends Div {

    private TextField testCardNameTextField = new TextField("Test card name");
    private TextField creationTimeText = new TextField("Creation time:");
    private TextField modificationTimeText = new TextField("Modification time:");

    private TestCardEntity testCard;

    public TestCardInfoDiv(TestCardEntity testCard) {
        this.testCard = testCard;

        initTestCardNameTextField();

        if (testCard != null){
            initCreationTimeField();
            initModificationTimeField();
        }

        VerticalLayout divLayout = new VerticalLayout();

        HorizontalLayout timeInfoInHorizontal = new HorizontalLayout(creationTimeText, modificationTimeText);

        divLayout.add(testCardNameTextField, timeInfoInHorizontal);
        divLayout.setAlignItems(FlexComponent.Alignment.CENTER);
        divLayout.setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);

        add(divLayout);
        setWidthFull();
    }

    private void initTestCardNameTextField() {
//        this.testCardNameTextField.setAutofocus(true);
//        this.testCardNameTextField.setAutoselect(true);
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

    private void initCreationTimeField () {
        if (testCard.getCreationTime() != null) {
            this.creationTimeText.setValue(String.format("Creation time: %s", testCard.getCreationTime()));
        }
        this.creationTimeText.setReadOnly(true);
    }
    private void initModificationTimeField (){
            if (testCard.getModificationTime() != null) {
                this.modificationTimeText.setValue(String.format("Modification time: %s", testCard.getCreationTime()));
            }
            this.modificationTimeText.setReadOnly(true);
    }

}
