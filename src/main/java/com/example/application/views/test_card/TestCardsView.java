package com.example.application.views.test_card;

import com.example.application.data.entity.test_card_associated.test_card.TestCardEntity;
import com.example.application.data.service.product_category.ProductCategoryCrudService;
import com.example.application.data.service.test_card_associated.test_card.TestCardFinder;
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

import java.util.List;

@Route(value = TestCardsView.route, layout = MainView.class)
//@RouteAlias(value = "", layout = MainView.class)
@PageTitle(TestCardsView.pageTitle)
public class TestCardsView extends VerticalLayout {

    public static final String pageTitle = "Test cards";
    public static final String route = "test-cards";

    private ProductCategoryCrudService productCategoryCrudService;
    private TestCardFinder testCardFinder;
    private List<TestCardEntity> testCards;

    private Button createTestCardButton = new Button("Create new test card for product category");
    private ProductCategoryFilterDiv productCategoryFiler;
    private TestCardsGridDiv testCardsDiv;

    @Autowired
    public TestCardsView(ProductCategoryCrudService productCategoryCrudService,
                         TestCardFinder testCardFinder) {
        this.productCategoryCrudService = productCategoryCrudService;
        this.testCardFinder = testCardFinder;

        this.productCategoryFiler = new ProductCategoryFilterDiv();
        this.testCardsDiv = initTestCardGrid();

        add(
                productCategoryFiler,
                testCardsDiv
        );
    }

    private void refreshTestCardList(){
        this.testCards = testCardFinder.getAll();

    }

    private TestCardsGridDiv initTestCardGrid() {
        return new TestCardsGridDiv();
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
