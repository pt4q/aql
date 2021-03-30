package com.example.application.views.test_card.test_card_part_creator;

import com.example.application.data.entity.test_card_associated.test_card_part.TestCardPartEntity;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;

class TestCardPartInfoDiv extends Div {

    private TextField testCardPartNameTextField = new TextField("Test card part name");
    private NumberField testCardPartPointsNumberField = new NumberField("Points");

    private TestCardPartEntity testCardPartEntity;

    public TestCardPartInfoDiv(TestCardPartEntity testCardPartEntity) {
        this.testCardPartEntity = testCardPartEntity;

        initTestCardPartNameTextField();
        initTestCardPartPoints();

        HorizontalLayout infoLayout = new HorizontalLayout(testCardPartNameTextField, testCardPartPointsNumberField);
        infoLayout.setAlignItems(FlexComponent.Alignment.CENTER);
        infoLayout.setWidthFull();

        setWidthFull();
        add(infoLayout);
    }

    private void initTestCardPartNameTextField(){
        this.testCardPartNameTextField.setAutofocus(true);
        this.testCardPartNameTextField.setAutoselect(true);
        this.testCardPartNameTextField.setRequired(true);
        this.testCardPartNameTextField.setAutocorrect(true);
        this.testCardPartNameTextField.setValueChangeMode(ValueChangeMode.LAZY);
        this.testCardPartNameTextField.setValueChangeTimeout(1000);
        this.testCardPartNameTextField.setWidthFull();

        this.testCardPartNameTextField.addValueChangeListener(e -> {
            String testCardName = testCardPartNameTextField.getValue();
            if (!testCardName.isEmpty())
                this.testCardPartEntity.setTestCardPartName(testCardName);
        });
    }

    private void initTestCardPartPoints(){
        this.testCardPartPointsNumberField.setValue(10d);
        this.testCardPartPointsNumberField.setHasControls(true);
        this.testCardPartPointsNumberField.setMin(10);
        this.testCardPartPointsNumberField.setMax(100);
        this.testCardPartPointsNumberField.setStep(10d);
        this.testCardPartPointsNumberField.setMinWidth("10%");
    }
}
