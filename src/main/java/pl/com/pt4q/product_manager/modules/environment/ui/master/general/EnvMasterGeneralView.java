package pl.com.pt4q.product_manager.modules.environment.ui.master.general;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import lombok.extern.log4j.Log4j2;
import pl.com.pt4q.product_manager.modules.environment.ui.master.detail.EnvMasterDetailView;
import pl.com.pt4q.product_manager.modules.product.ui.product_category.ProductCategoriesFilterDiv;
import pl.com.pt4q.product_manager.views.main.MainView;

@Log4j2
@Route(value = EnvMasterGeneralView.ROUTE, layout = MainView.class)
@PageTitle(EnvMasterGeneralView.PAGE_TITLE)
public class EnvMasterGeneralView extends Div {

    public static final String PAGE_TITLE = "Environmental conditions";
    public static final String ROUTE = "environment";

    private ProductCategoriesFilterDiv productCategoriesFilterDiv;
    private EnvMasterGeneralGridDiv masterGridDiv;

    private Button addNewProductButton = new Button("Add new env condition");


    public EnvMasterGeneralView() {

        this.productCategoriesFilterDiv = new ProductCategoriesFilterDiv();
        this.masterGridDiv = new EnvMasterGeneralGridDiv();

        initAddProductButton();
//        masterGridDiv.refreshGrid();

        HorizontalLayout toolPanel = new HorizontalLayout(productCategoriesFilterDiv, addNewProductButton);
        toolPanel.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.BASELINE);
        toolPanel.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);

        VerticalLayout layout = new VerticalLayout(toolPanel, masterGridDiv);
        layout.setWidthFull();


        add(layout);
    }

    private void initAddProductButton() {
        this.addNewProductButton.addClickListener(buttonClickEvent -> {
            UI ui = UI.getCurrent();
            ui.navigate(EnvMasterDetailView.ROUTE);
        });
    }
}
