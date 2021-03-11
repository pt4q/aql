package com.example.application.views.test_card;

import com.example.application.views.main.MainView;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

@Route(value = TestCardView.route, layout = MainView.class)
//@RouteAlias(value = "", layout = MainView.class)
@PageTitle(TestCardView.pageTitle)
public class TestCardView extends VerticalLayout {

    public static final String pageTitle = "Test card";
    public static final String route = "testcard";

}
