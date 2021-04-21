package pl.com.pt4q.product_manager.modules.test_card.ui.test_card_part_parameters;

import pl.com.pt4q.product_manager.modules.test_card.data.test_card_part.TestCardPartEntity;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;

class TestCardParameterInfoDiv extends Div {

    private TextField testCardPartNameTextField = new TextField("Parameter name");
    private NumberField testCardPartPointsNumberField = new NumberField("Points");

    private TestCardPartEntity testCardPartEntity;

    public TestCardParameterInfoDiv(TestCardPartEntity testCardPartEntity) {
        this.testCardPartEntity = testCardPartEntity;

        initTestCardPartNameTextField();
        initTestCardPartPoints();

        VerticalLayout infoLayout = new VerticalLayout(testCardPartNameTextField, testCardPartPointsNumberField);
        infoLayout.setAlignItems(FlexComponent.Alignment.START);
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
