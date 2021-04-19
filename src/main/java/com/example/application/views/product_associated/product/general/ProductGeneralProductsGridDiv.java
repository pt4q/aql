package com.example.application.views.product_associated.product.general;

import com.example.application.data.entity.product_associated.product.ProductEntity;
import com.example.application.views.product_associated.product.detail.ProductDetailView;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import lombok.Data;

import java.time.format.DateTimeFormatter;

@Data
class ProductGeneralProductsGridDiv extends Div {

    private Grid<ProductEntity> productEntityGrid = new Grid<>();

    public ProductGeneralProductsGridDiv() {
        initGrid();

        VerticalLayout gridLayout = new VerticalLayout(this.productEntityGrid);
        gridLayout.setAlignItems(FlexComponent.Alignment.CENTER);

        setSizeFull();
        add(gridLayout);
    }

    private void initGrid() {
        String dateTimeFormat = "yyyy-MM-dd HH:mm:ss";

        this.productEntityGrid
                .addColumn(productEntity -> productEntity.getProductCategory().getProductCategoryName())
                .setHeader("Product category")
                .setSortable(true);
        this.productEntityGrid
                .addColumn(new ComponentRenderer<>(pe ->
                        new Anchor(createLinkWithParam(ProductDetailView.ROUTE, ProductDetailView.QUERY_PARAM_ID_NAME, pe.getId()), pe.getProductSku())))
                .setHeader("Test card name")
                .setSortable(true);
//        this.productEntityGrid
//                .addColumn(testCardEntity -> testCardEntity.getTestCardParts().size())
//                .setHeader("Number of param categories")
//                .setSortable(true);
        this.productEntityGrid
                .addColumn(productEntity ->
                        productEntity.getCreationTime() != null ? productEntity.getCreationTime().format(DateTimeFormatter.ofPattern(dateTimeFormat)) : null)
                .setHeader("Creation time")
                .setSortable(true);
        this.productEntityGrid
                .addColumn(productEntity ->
                        productEntity.getModificationTime() != null ? productEntity.getModificationTime().format(DateTimeFormatter.ofPattern(dateTimeFormat)) : null)
                .setHeader("Modification time")
                .setSortable(true);

        this.productEntityGrid.setWidthFull();
        this.productEntityGrid.setHeightByRows(true);
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
