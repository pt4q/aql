package pl.com.pt4q.product_manager.modules.test_card.ui.main_view;

import pl.com.pt4q.product_manager.modules.test_card.data.test_card.TestCardEntity;
import pl.com.pt4q.product_manager.modules.product.services.product_category.ProductCategoryCrudService;
import pl.com.pt4q.product_manager.modules.test_card.services.test_card.TestCardFinder;
import pl.com.pt4q.product_manager.views.main.MainView;
import pl.com.pt4q.product_manager.view_utils.FilterTextFieldDiv;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Route(value = TestCardsMainView.ROUTE, layout = MainView.class)
//@RouteAlias(value = "", layout = MainView.class)
@PageTitle(TestCardsMainView.PAGE_TITLE)
public class TestCardsMainView extends VerticalLayout {

    public static final String PAGE_TITLE = "Test cards";
    public static final String ROUTE = "test-cards";

    private FilterTextFieldDiv productCategoryFiler;
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

        this.productCategoryFiler = new FilterTextFieldDiv("Filter by product category");
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
