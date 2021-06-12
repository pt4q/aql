package pl.com.pt4q.product_manager.modules.product.ui.product.detail;


import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import pl.com.pt4q.product_manager.modules.product.data.product.ProductEntity;
import pl.com.pt4q.product_manager.modules.product.data.product_part.ProductPartEntity;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import lombok.Data;
import pl.com.pt4q.product_manager.modules.product.ui.product_part.ProductPartDetailView;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Data
class ProductPartsGridDiv extends Div {

    private Grid<ProductPartEntity> productPartsGrid = new Grid<>();
    private Button addNewPartButton = new Button(new Icon(VaadinIcon.PLUS_CIRCLE_O));

    public ProductPartsGridDiv() {
        initGrid();
        initAddNewPartButton();

        VerticalLayout gridLayout = new VerticalLayout(this.productPartsGrid, this.addNewPartButton);
        gridLayout.setAlignItems(FlexComponent.Alignment.CENTER);

        setSizeFull();
        add(gridLayout);
    }

    private void initGrid() {
        String dateTimeFormat = "yyyy-MM-dd HH:mm:ss";
        this.productPartsGrid
                .addColumn(new ComponentRenderer<>(partEntity ->
                        new Anchor(createLinkWithParam(ProductPartDetailView.ROUTE, ProductPartDetailView.QUERY_PARAM_ID_NAME, partEntity.getId()), partEntity.getPartModelOrPartName())))
                .setHeader("Part model")
                .setSortable(true);
        this.productPartsGrid
                .addColumn(ProductPartEntity::getPartDescription)
                .setHeader("Part description")
                .setSortable(true);
        this.productPartsGrid
                .addColumn(productEntity ->
                        productEntity.getModificationTime() != null ? productEntity.getModificationTime().format(DateTimeFormatter.ofPattern(dateTimeFormat)) : null)
                .setHeader("Modification time")
                .setSortable(true);

        this.productPartsGrid.setWidthFull();
        this.productPartsGrid.setHeightByRows(true);

//        refreshGrid();
    }

    private String createLinkWithParam(String url, String paramName, Long id) {
        return url + "?" + paramName + "=" + id.toString();
    }

    public void refreshGrid(List<ProductPartEntity> parts) {
        this.productPartsGrid.select(null);
        this.productPartsGrid.setItems(parts);
    }

    private void initAddNewPartButton() {
        this.addNewPartButton.getElement().setProperty("title", "add new part to the product");
    }
}
