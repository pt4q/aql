package com.example.application.views.test_card.test_card_part_creator;

import com.example.application.data.entity.test_card_associated.test_card.TestCardEntity;
import com.example.application.data.entity.test_card_associated.test_card_part.TestCardPartEntity;
import com.example.application.data.entity.test_card_associated.test_card_part_parameter_category_parameter.ParameterEntity;
import com.example.application.views.main.MainView;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;

import java.awt.*;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Route(value = TestCardPartCreatorView.ROUTE, layout = MainView.class)
@PageTitle(TestCardPartCreatorView.PAGE_TITLE)
public class TestCardPartCreatorView extends VerticalLayout implements HasUrlParameter<String> {

    public static final String PAGE_TITLE = "Test card part creator";
    public static final String ROUTE = "testcard-part-creator";
    public static final String QUERY_PARAM_ID_NAME = "testCardPartId";

    private TextField testCardPartName;

    private TestCardEntity testCardEntity;
    private TestCardPartEntity testCardPartEntity;
    private Set<ParameterEntity> testCardPartParameters;




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
