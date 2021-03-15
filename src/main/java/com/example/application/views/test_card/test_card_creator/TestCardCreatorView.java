package com.example.application.views.test_card.test_card_creator;

import com.example.application.data.entity.product.ProductEntity;
import com.example.application.data.entity.test_card_associated.parameter_category.ParameterCategoryEntity;
import com.example.application.data.entity.test_card_associated.test_card.TestCardEntity;
import com.example.application.data.service.test_card_associated.test_card.TestCardFinder;
import com.example.application.data.service.test_card_associated.test_card.TestCardForProductCreator;
import com.example.application.views.main.MainView;
import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedHashSet;

@Route(value = TestCardCreatorView.route, layout = MainView.class)
@PageTitle(TestCardCreatorView.pageTitle)
public class TestCardCreatorView extends VerticalLayout {

    public static final String pageTitle = "Test card creator";
    public static final String route = "testcard-creator";

    private TestCardForProductCreator testCardForProductCreator;
    private TestCardFinder testCardFinder;

    private ProductEntity productEntity;
    private TestCardEntity testCard;
    private LinkedHashSet<ParameterCategoryEntity> testCardCategories;

    private Div testCardInfoDiv;
    private Div testCardCategoriesDiv;

    public TestCardCreatorView(TestCardFinder testCardFinder,
                               TestCardForProductCreator testCardForProductCreator) {
        this.testCardFinder = testCardFinder;
        this.testCardForProductCreator = testCardForProductCreator;
        this.productEntity = loadProductFromContext();
        this.testCard = initEmptyTestCardForProduct();
        this.testCardInfoDiv = initTestCardInfoDiv();
        this.testCardCategoriesDiv = initTestCardCategoriesDiv();

        add(testCardInfoDiv, testCardCategoriesDiv);
    }

    private TestCardEntity initEmptyTestCardForProduct() {
        return TestCardEntity.builder()
                .product(productEntity != null ? productEntity : null)
                .build();
    }

    private ProductEntity loadProductFromContext() {
        return ComponentUtil.getData(UI.getCurrent(), ProductEntity.class);
    }

    private Div initTestCardInfoDiv() {
        return new TestCardInfoDiv(this.testCard)
                .create();
    }

    private Div initTestCardCategoriesDiv() {
        return new TestCardParamCategoriesDiv(this.testCardCategories)
                .create();
    }
}
