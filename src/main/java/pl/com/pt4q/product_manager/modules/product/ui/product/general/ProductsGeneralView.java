package pl.com.pt4q.product_manager.modules.product.ui.product.general;

import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import org.springframework.beans.factory.annotation.Autowired;
import pl.com.pt4q.product_manager.modules.product.data.product.ProductEntity;
import pl.com.pt4q.product_manager.modules.product.services.product.ProductFinderService;
import pl.com.pt4q.product_manager.views.main.MainView;
import pl.com.pt4q.product_manager.modules.product.ui.product.detail.ProductDetailView;
import pl.com.pt4q.product_manager.modules.product.ui.product_category.ProductCategoryFilterDiv;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

//@CssImport(ProductCategoryView.css)
@Route(value = ProductsGeneralView.ROUTE, layout = MainView.class)
//@RouteAlias(value = "", layout = MainView.class)
@PageTitle(ProductsGeneralView.PAGE_TITLE)
public class ProductsGeneralView extends Div {

    public static final String PAGE_TITLE = "Products";
    public static final String ROUTE = "products";
//    public static final String css = "./views/product_category/product-category-view.css";

    private ProductCategoryFilterDiv productCategoryFilterDiv;
    private ProductGeneralProductsGridDiv productsGridDiv;

    private Button addNewProductButton = new Button("Add new product");

    private ProductFinderService productFinderService;

    private ProductEntity productEntity;

    @Autowired
    public ProductsGeneralView(ProductFinderService productFinderService) {
        this.productFinderService = productFinderService;

        this.productCategoryFilterDiv = new ProductCategoryFilterDiv();
        this.productsGridDiv = new ProductGeneralProductsGridDiv(productFinderService);

        initAddProductButton();

        HorizontalLayout toolPanel = new HorizontalLayout(productCategoryFilterDiv, addNewProductButton);
        toolPanel.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.BASELINE);
        toolPanel.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);

        add(toolPanel,
                productsGridDiv);
    }

    private void initAddProductButton(){
        this.addNewProductButton.addClickListener(buttonClickEvent -> {
            UI ui = UI.getCurrent();
            ComponentUtil.setData(ui, ProductEntity.class, new ProductEntity());
            ui.navigate(ProductDetailView.ROUTE);
        });
    }
}
