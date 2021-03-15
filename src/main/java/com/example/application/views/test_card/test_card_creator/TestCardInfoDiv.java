package com.example.application.views.test_card.test_card_creator;

import com.example.application.data.entity.test_card_associated.test_card.TestCardEntity;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import lombok.Data;

@Data
class TestCardInfoDiv extends Div {

    private TextField testCardNameTextField = new TextField("Test card name");
    private Text creationTimeText;
    private Text modificationTimeText;

    private TestCardEntity testCard;

    public TestCardInfoDiv(TestCardEntity testCard) {
        this.testCard = testCard;
    }

    public Div create() {
        HorizontalLayout hl = new HorizontalLayout();

        testCardNameTextField.setAutofocus(true);
        testCardNameTextField.setAutoselect(true);
        testCardNameTextField.setRequired(true);
        testCardNameTextField.setAutocorrect(true);
        testCardNameTextField.setValueChangeMode(ValueChangeMode.LAZY);
        testCardNameTextField.setValueChangeTimeout(1000);
        testCardNameTextField.addValueChangeListener(e -> {
            String testCardName = testCardNameTextField.getValue();
            if (!testCardName.isEmpty())
                this.testCard.setTestCardName(testCardName);
        });
        hl.add(testCardNameTextField);

        if (testCard != null) {
            if (testCard.getCreationTime() != null) {
                this.creationTimeText = new Text(String.format("Creation time: %s", testCard.getCreationTime()));
                hl.add(creationTimeText);
            }
            if (testCard.getModificationTime() != null) {
                this.modificationTimeText = new Text(String.format("Modification time: %s", testCard.getCreationTime()));
                hl.add(modificationTimeText);
            }
        }
        add(hl);
        return this;
    }

}
