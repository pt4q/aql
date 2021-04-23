package pl.com.pt4q.product_manager.modules.product.ui.product.general;

import org.springframework.beans.factory.annotation.Autowired;
import pl.com.pt4q.product_manager.modules.product.data.product.ProductEntity;
import pl.com.pt4q.product_manager.modules.product.services.product.ProductCrudFinder;
import pl.com.pt4q.product_manager.views.main.MainView;
import pl.com.pt4q.product_manager.modules.product.ui.product.detail.ProductDetailView;
import pl.com.pt4q.product_manager.modules.product.ui.product_category.ProductCategoryFilterDiv;
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

    private ProductCrudFinder productCrudFinder;

    private ProductEntity productEntity;

    @Autowired
    public ProductsGeneralView(ProductCrudFinder productCrudFinder) {
        this.productCrudFinder = productCrudFinder;

        this.productCategoryFilterDiv = new ProductCategoryFilterDiv();
        this.productsGridDiv = new ProductGeneralProductsGridDiv(productCrudFinder);

        initAddProductButton();

        HorizontalLayout toolPanel = new HorizontalLayout(productCategoryFilterDiv, addNewProductButton);
        toolPanel.setDefaultVerticalComponentAlignment(Alignment.BASELINE);
        toolPanel.setJustifyContentMode(JustifyContentMode.BETWEEN);

        add(toolPanel,
                productsGridDiv);
    }

    private void initAddProductButton(){
        this.addNewProductButton.addClickListener(buttonClickEvent -> {
            UI.getCurrent().navigate(ProductDetailView.ROUTE);
        });
    }
}
