package com.example.application.modules.test_card.ui.test_card.test_card_part_creator;

import com.example.application.modules.test_card.data.test_card.TestCardEntity;
import com.example.application.modules.test_card.data.test_card_part.TestCardPartEntity;
import com.example.application.modules.test_card.data.test_card_part_parameter_category_parameter.ParameterEntity;
import com.example.application.ui_main.MainView;
import com.example.application.modules.test_card.ui.test_card.test_card_part_parameters.TestCardParameterCreatorDiv;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.router.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Route(value = TestCardPartCreatorView.ROUTE, layout = MainView.class)
@PageTitle(TestCardPartCreatorView.PAGE_TITLE)
public class TestCardPartCreatorView extends VerticalLayout implements HasUrlParameter<String> {

    public static final String PAGE_TITLE = "Test card part creator";
    public static final String ROUTE = "testcard-part-creator";
    public static final String QUERY_PARAM_ID_NAME = "testCardPartId";

    private SaveTestCardPartOrBackButtonsDiv saveTestCardPartOrBackButtonsDiv;
    private TestCardPartInfoDiv testCardPartInfoDiv;
    private TestCardPartParamsGridDiv testCardPartParamsGridDiv;
    private TestCardParameterCreatorDiv testCardParameterCreatorDiv;

    private TestCardEntity testCardEntity;
    private TestCardPartEntity testCardPartEntity;
    private Set<ParameterEntity> testCardPartParameters;

    public TestCardPartCreatorView() {
        this.saveTestCardPartOrBackButtonsDiv = new SaveTestCardPartOrBackButtonsDiv();
        this.testCardPartInfoDiv = new TestCardPartInfoDiv(this.testCardPartEntity);
        this.testCardPartParamsGridDiv = new TestCardPartParamsGridDiv(this.testCardPartEntity);
        this.testCardParameterCreatorDiv = new TestCardParameterCreatorDiv(this.testCardPartEntity);

        SplitLayout splitLayout = new SplitLayout();
        splitLayout.setSizeFull();
        splitLayout.addToPrimary(testCardPartParamsGridDiv);
        splitLayout.addToSecondary(testCardParameterCreatorDiv);

        setWidthFull();
        setAlignItems(Alignment.CENTER);
        add(saveTestCardPartOrBackButtonsDiv, testCardPartInfoDiv, splitLayout);
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, @OptionalParameter String urlQuery) {
        Location location = beforeEvent.getLocation();
        QueryParameters queryParameters = location.getQueryParameters();
        Map<String, List<String>> parametersMap = queryParameters.getParameters();

        if (parametersMap.containsKey(QUERY_PARAM_ID_NAME)) {
            String paramValueString = parametersMap.get(QUERY_PARAM_ID_NAME).get(0);
//            Long id = Long.valueOf(paramValueString);

        }
    }
}
