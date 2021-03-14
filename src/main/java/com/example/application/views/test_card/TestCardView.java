package com.example.application.views.test_card;

import com.example.application.views.main.MainView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = TestCardView.route, layout = MainView.class)
//@RouteAlias(value = "", layout = MainView.class)
@PageTitle(TestCardView.pageTitle)
public class TestCardView extends VerticalLayout {

    public static final String pageTitle = "Test card";
    public static final String route = "testcard";

    private Button createTestCardButton = new Button("Create new test card for product");

    public TestCardView() {
        add(buttonsLayout());
    }

    private Div buttonsLayout() {
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.add(initCreateTestCardButton());
        horizontalLayout.setAlignItems(Alignment.START);
        return new Div(horizontalLayout);
    }

    private Button initCreateTestCardButton() {
        this.createTestCardButton.addClickListener(e -> {
            getUI().get().navigate(TestCardCreatorView.route);
        });
        return this.createTestCardButton;
    }
}
