package com.example.application.views.product_associated.product.general;

import com.example.application.data.entity.product_associated.product.ProductEntity;
import com.example.application.data.entity.test_card_associated.test_card.TestCardEntity;
import com.example.application.views.main.MainView;
import com.example.application.views.product_associated.product.detail.ProductDetailView;
import com.example.application.views.product_associated.product_category.ProductCategoryFilterDiv;
import com.example.application.views.test_card.test_card_creator.TestCardCreatorView;
import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

//@CssImport(ProductCategoryView.css)
@Route(value = ProductsGeneralView.ROUTE, layout = MainView.class)
//@RouteAlias(value = "", layout = MainView.class)
@PageTitle(ProductsGeneralView.PAGE_TITLE)
public class ProductsGeneralView extends VerticalLayout {

    public static final String PAGE_TITLE = "Products";
    public static final String ROUTE = "products";
//    public static final String css = "./views/product_category/product-category-view.css";

    private ProductCategoryFilterDiv productCategoryFilterDiv;
    private ProductGeneralProductsGridDiv productsGridDiv;

    private Button addNewProductButton = new Button("Add new product");

    private ProductEntity productEntity;

    public ProductsGeneralView() {
        this.productCategoryFilterDiv = new ProductCategoryFilterDiv();
        this.productsGridDiv = new ProductGeneralProductsGridDiv();

        initAddProductButton();

        HorizontalLayout toolPanel = new HorizontalLayout(productCategoryFilterDiv, addNewProductButton);
        toolPanel.setDefaultVerticalComponentAlignment(Alignment.BASELINE);
        toolPanel.setJustifyContentMode(JustifyContentMode.BETWEEN);

        add(toolPanel,
                productsGridDiv);
    }

    private void initAddProductButton(){
        this.addNewProductButton.addClickListener(buttonClickEvent -> {
            this.productEntity = ProductEntity.builder()
                    .build();

            ComponentUtil.setData(UI.getCurrent(), ProductEntity.class, productEntity);
            UI.getCurrent().navigate(ProductDetailView.ROUTE);
        });
    }
}
