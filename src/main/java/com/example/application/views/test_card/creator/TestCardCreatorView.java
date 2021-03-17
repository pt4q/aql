package com.example.application.views.test_card.creator;

import com.example.application.data.entity.product_category.ProductCategoryEntity;
import com.example.application.data.entity.test_card_associated.parameter_category.ParameterCategoryEntity;
import com.example.application.data.entity.test_card_associated.test_card.TestCardEntity;
import com.example.application.data.service.test_card_associated.test_card.TestCardFinder;
import com.example.application.data.service.test_card_associated.test_card.TestCardForProductCategoryCreator;
import com.example.application.views.main.MainView;
import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.LinkedHashSet;

@Route(value = TestCardCreatorView.route, layout = MainView.class)
@PageTitle(TestCardCreatorView.pageTitle)
public class TestCardCreatorView extends VerticalLayout {

    public static final String pageTitle = "Test card creator";
    public static final String route = "testcard-creator";

    private TestCardForProductCategoryCreator testCardForProductCategoryCreator;
    private TestCardFinder testCardFinder;

    private ProductCategoryEntity productCategoryEntity;
    private TestCardEntity testCard;
    private LinkedHashSet<ParameterCategoryEntity> testCardParamCategories;

    private Div testCardInfoDiv;
    private Div testCardCategoriesDiv;

    public TestCardCreatorView(TestCardFinder testCardFinder,
                               TestCardForProductCategoryCreator testCardForProductCategoryCreator) {
        this.testCardFinder = testCardFinder;
        this.testCardForProductCategoryCreator = testCardForProductCategoryCreator;
        this.productCategoryEntity = loadProductFromContext();
        this.testCard = initEmptyTestCardForProduct();
        this.testCardInfoDiv = initTestCardInfoDiv();
        this.testCardCategoriesDiv = initTestCardCategoriesDiv();

        add(testCardInfoDiv, testCardCategoriesDiv);
    }

    private TestCardEntity initEmptyTestCardForProduct() {
        return TestCardEntity.builder()
                .productCategory(productCategoryEntity != null ? productCategoryEntity : null)
                .build();
    }

    private ProductCategoryEntity loadProductFromContext() {
        return ComponentUtil.getData(UI.getCurrent(), ProductCategoryEntity.class);
    }

    private Div initTestCardInfoDiv() {
        return new TestCardInfoDiv(this.testCard)
                .create();
    }

    private Div initTestCardCategoriesDiv() {
        return new TestCardParamCategoriesFactoryDiv(this.testCardParamCategories)
                .create();
    }
}
