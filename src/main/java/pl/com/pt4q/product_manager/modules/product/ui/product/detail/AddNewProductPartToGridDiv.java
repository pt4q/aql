package pl.com.pt4q.product_manager.modules.product.ui.product.detail;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import pl.com.pt4q.product_manager.modules.product.data.product.ProductEntity;
import pl.com.pt4q.product_manager.modules.product.data.product_part.ProductPartEntity;
import pl.com.pt4q.product_manager.modules.product.ui.product_part.ProductPartDetailView;

import java.util.LinkedList;
import java.util.List;

class AddNewProductPartToGridDiv extends Div {

    private Button addNewTestCardPartButton = new Button(new Icon(VaadinIcon.PLUS_CIRCLE_O));

    private Grid<ProductPartEntity> testCardPartEntityGrid;

    private ProductEntity productEntity;
    private List<ProductPartEntity> productPartList;

    public AddNewProductPartToGridDiv(ProductEntity productEntity, Grid<ProductPartEntity> testCardPartEntityGrid) {
        this.productEntity = productEntity;
        this.testCardPartEntityGrid = testCardPartEntityGrid;

        this.productPartList = initProductPartList();
        initAddNewTestCardPartButton();

        HorizontalLayout layout = new HorizontalLayout(addNewTestCardPartButton);
        layout.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);

        add(layout);
    }

    private List<ProductPartEntity> initProductPartList() {
        List<ProductPartEntity> testCardParts = this.productEntity.getProductParts();
        return testCardParts != null ? testCardParts : new LinkedList<>();
    }

    private void initAddNewTestCardPartButton() {
        this.addNewTestCardPartButton.addClickListener(e -> {
            UI.getCurrent().navigate(ProductPartDetailView.ROUTE);
        });
    }
}
