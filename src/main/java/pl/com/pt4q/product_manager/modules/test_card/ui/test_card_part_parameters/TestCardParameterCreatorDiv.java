package pl.com.pt4q.product_manager.modules.test_card.ui.test_card_part_parameters;

import pl.com.pt4q.product_manager.modules.test_card.data.test_card_part.TestCardPartEntity;
import pl.com.pt4q.product_manager.modules.test_card.data.test_card_part_parameter_category_parameter.ParameterEntity;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class TestCardParameterCreatorDiv extends Div {

    private TestCardParameterInfoDiv testCardParameterInfoDiv;
    private TestCardParameterTypeSelectorDiv testCardParameterTypeSelectorDiv;

    private TestCardPartEntity testCardPartEntity;
    private ParameterEntity parameterEntity;

    public TestCardParameterCreatorDiv(TestCardPartEntity testCardPartEntity) {
        this.testCardPartEntity = testCardPartEntity;
        this.testCardParameterInfoDiv = new TestCardParameterInfoDiv(testCardPartEntity);
        this.testCardParameterTypeSelectorDiv = new TestCardParameterTypeSelectorDiv();

        VerticalLayout verticalLayout = new VerticalLayout(testCardParameterInfoDiv, testCardParameterTypeSelectorDiv);


        add(verticalLayout);
    }
}
