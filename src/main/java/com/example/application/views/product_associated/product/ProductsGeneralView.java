package com.example.application.views.product_associated.product;

import com.example.application.views.main.MainView;
import com.example.application.views.product_associated.product_category.ProductCategoryView;
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

    public ProductsGeneralView() {
    }
}
