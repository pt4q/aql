package com.example.application.views.test_card.test_card_part_creator;

import com.example.application.views.main.MainView;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;

import java.util.List;
import java.util.Map;

@Route(value = TestCardPartCreatorView.ROUTE, layout = MainView.class)
@PageTitle(TestCardPartCreatorView.PAGE_TITLE)
public class TestCardPartCreatorView extends VerticalLayout implements HasUrlParameter<String> {

    public static final String PAGE_TITLE = "Test card part creator";
    public static final String ROUTE = "testcard-part-creator";
    public static final String QUERY_PARAM_ID_NAME = "testCardPartId";

    @Override
    public void setParameter(BeforeEvent beforeEvent, @OptionalParameter String urlQuery) {
        Location location = beforeEvent.getLocation();
        QueryParameters queryParameters = location.getQueryParameters();
        Map<String, List<String>> parametersMap = queryParameters.getParameters();

        if (parametersMap.containsKey(QUERY_PARAM_ID_NAME)) {
            Long id = Long.valueOf(parametersMap.get(QUERY_PARAM_ID_NAME).get(0));

        }
    }
}
