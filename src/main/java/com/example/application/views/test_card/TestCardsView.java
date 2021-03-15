package com.example.application.views.test_card;

import com.example.application.data.service.product_category.ProductCategoryCrudService;
import com.example.application.views.main.MainView;
import com.example.application.views.test_card.test_card_creator.TestCardCreatorView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = TestCardsView.route, layout = MainView.class)
//@RouteAlias(value = "", layout = MainView.class)
@PageTitle(TestCardsView.pageTitle)
public class TestCardsView extends VerticalLayout {

    public static final String pageTitle = "Test cards";
    public static final String route = "test-cards";

    private ProductCategoryCrudService productCategoryCrudService;

    private Button createTestCardButton = new Button("Create new test card for product category");
    private TextField productCategoryFiler = new TextField("Product category filter");
    private Div testCardsDiv;

    @Autowired
    public TestCardsView(ProductCategoryCrudService productCategoryCrudService) {
        this.productCategoryCrudService = productCategoryCrudService;
        this.productCategoryFiler = initTestCardsFilter();
        this.testCardsDiv = initTestCardGrid();

        add(
                productCategoryFiler,
                buttonsLayout()
        );
    }

    private TextField initTestCardsFilter() {
        productCategoryFiler.setAutofocus(true);
        productCategoryFiler.setAutoselect(true);
        productCategoryFiler.setRequired(true);
        productCategoryFiler.setAutocorrect(true);
        productCategoryFiler.setValueChangeMode(ValueChangeMode.LAZY);
        productCategoryFiler.setValueChangeTimeout(1000);
        productCategoryFiler.addValueChangeListener(e -> {

        });
        return productCategoryFiler;
    }

    private Div initTestCardGrid() {
        return new TestCardsGridDiv()
                .create();
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
        return createTestCardButton;
    }
}
