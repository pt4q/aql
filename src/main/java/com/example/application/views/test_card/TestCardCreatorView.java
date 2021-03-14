package com.example.application.views.test_card;

import com.example.application.views.main.MainView;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = TestCardCreatorView.route, layout = MainView.class)
@PageTitle(TestCardCreatorView.pageTitle)
class TestCardCreatorView extends VerticalLayout {

    public static final String pageTitle = "Test card creator";
    public static final String route = "testcard-creator";

    public TestCardCreatorView() {

    }
}
