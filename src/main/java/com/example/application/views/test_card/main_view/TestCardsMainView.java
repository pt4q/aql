package com.example.application.views.test_card.main_view;

import com.example.application.data.entity.test_card_associated.test_card.TestCardEntity;
import com.example.application.data.service.product_category.ProductCategoryCrudService;
import com.example.application.data.service.test_card_associated.test_card.TestCardFinder;
import com.example.application.views.main.MainView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Route(value = TestCardsMainView.route, layout = MainView.class)
@RouteAlias(value = "", layout = MainView.class)
@PageTitle(TestCardsMainView.pageTitle)
public class TestCardsMainView extends VerticalLayout {

    public static final String pageTitle = "Test cards";
    public static final String route = "test-cards";

    private ProductCategoryFilterDiv productCategoryFiler;
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
        this.testCardsGrid = new TestCardsGridDiv(this.testCardFinder);

        add(
                productCategoryFiler,
                testCardsGrid
        );
    }


//    private Div buttonsLayout() {
//        HorizontalLayout horizontalLayout = new HorizontalLayout();
//        horizontalLayout.add(initCreateTestCardButton());
//        horizontalLayout.setAlignItems(Alignment.START);
//        return new Div(horizontalLayout);
//    }
//
//    private Button initCreateTestCardButton() {
//        this.createTestCardButton.addClickListener(e -> {
//            getUI().get().navigate(TestCardCreatorView.route);
//        });
//        return createTestCardButton;
//    }
}
