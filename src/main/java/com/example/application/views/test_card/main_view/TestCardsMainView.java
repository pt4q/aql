package com.example.application.views.test_card.main_view;

import com.example.application.data.entity.test_card_associated.test_card.TestCardEntity;
import com.example.application.data.service.product_category.ProductCategoryCrudService;
import com.example.application.data.service.test_card_associated.test_card.TestCardFinder;
import com.example.application.views.main.MainView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Route(value = TestCardsMainView.ROUTE, layout = MainView.class)
@RouteAlias(value = "", layout = MainView.class)
@PageTitle(TestCardsMainView.PAGE_TITLE)
public class TestCardsMainView extends VerticalLayout {

    public static final String PAGE_TITLE = "Test cards";
    public static final String ROUTE = "test-cards";

    private ProductCategoryFilterDiv productCategoryFiler;
    private AddNewTestCardToGridDiv addNewTestCardToGridDiv;
    private TestCardsGridDiv testCardsGrid;

    private Button createTestCardButton = new Button("Create new test card for product category");

    private ProductCategoryCrudService productCategoryCrudService;
    private TestCardFinder testCardFinder;
    private List<TestCardEntity> testCards;

    @Autowired
    public TestCardsMainView(ProductCategoryCrudService productCategoryCrudService,
                             TestCardFinder testCardFinder) {
        this.productCategoryCrudService = productCategoryCrudService;
        this.testCardFinder = testCardFinder;

        this.productCategoryFiler = new ProductCategoryFilterDiv();
        this.addNewTestCardToGridDiv = new AddNewTestCardToGridDiv();
        this.testCardsGrid = new TestCardsGridDiv(this.testCardFinder);

        HorizontalLayout topPanel = new HorizontalLayout(productCategoryFiler, addNewTestCardToGridDiv);
        topPanel.setDefaultVerticalComponentAlignment(Alignment.BASELINE);
        topPanel.setJustifyContentMode(JustifyContentMode.BETWEEN);

        add(
                topPanel,
                testCardsGrid
        );
    }
}
