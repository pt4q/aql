package pl.com.pt4q.product_manager.modules.product.ui.product_part;


import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import lombok.Data;
import pl.com.pt4q.product_manager.modules.product.data.product_part.ProductPartAttributeEntity;
import pl.com.pt4q.product_manager.modules.product.data.product_part.ProductPartEntity;
import pl.com.pt4q.product_manager.modules.product.ui.product.detail.ProductDetailView;

import java.time.format.DateTimeFormatter;

@Data
class PartAttributesGridDiv extends Div {

    private Grid<ProductPartAttributeEntity> partAttributesGrid = new Grid<>();

    public PartAttributesGridDiv() {
        initGrid();

        VerticalLayout gridLayout = new VerticalLayout(this.partAttributesGrid);
        gridLayout.setAlignItems(FlexComponent.Alignment.CENTER);

        setSizeFull();
        add(gridLayout);
    }

    private void initGrid() {
        String dateTimeFormat = "yyyy-MM-dd HH:mm:ss";

        this.partAttributesGrid
                .addColumn(ProductPartAttributeEntity::getAttributeName)
                .setHeader("Attribute name")
                .setSortable(true);

        this.partAttributesGrid.setWidthFull();
        this.partAttributesGrid.setHeightByRows(true);
//        this.refreshGrid(testCardFinder.getAll());
    }

    private String createLinkWithParam(String url, String paramName, Long id) {
        return url + "?" + paramName + "=" + id.toString();
    }

//    public void refreshGrid(List<TestCardEntity> testCards) {
//        this.productEntityGrid.select(null);
//        this.productEntityGrid.setItems(testCards);
//    }
}
