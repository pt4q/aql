package com.example.application.views.test_card;

import com.example.application.views.main.MainView;
import com.example.application.views.test_card.test_card_creator.TestCardCreatorView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = TestCardsView.route, layout = MainView.class)
//@RouteAlias(value = "", layout = MainView.class)
@PageTitle(TestCardsView.pageTitle)
public class TestCardsView extends VerticalLayout {

    public static final String pageTitle = "Product detail";
    public static final String route = "product-detail";

    private Button createTestCardButton = new Button("Create new test card for product");

    public TestCardsView() {
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
