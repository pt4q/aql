package pl.com.pt4q.product_manager.modules.product.ui.product.general;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import lombok.Data;
import pl.com.pt4q.product_manager.modules.product.data.product.ProductEntity;
import pl.com.pt4q.product_manager.modules.product.services.product.ProductFinderService;
import pl.com.pt4q.product_manager.modules.product.ui.product.detail.ProductDetailView;

import java.util.List;

@Data
class ProductGeneralProductsGridDiv extends Div {

    private Grid<ProductEntity> productEntityGrid = new Grid<>();

    private ProductFinderService productFinderService;

    public ProductGeneralProductsGridDiv(ProductFinderService productFinderService) {
        this.productFinderService = productFinderService;

        initGrid();

        VerticalLayout gridLayout = new VerticalLayout(this.productEntityGrid);
        gridLayout.setAlignItems(FlexComponent.Alignment.CENTER);

        setSizeFull();
        add(gridLayout);
    }

    private void initGrid() {
        String dateTimeFormat = "yyyy-MM-dd HH:mm:ss";

        this.productEntityGrid
                .addColumn(new ComponentRenderer<>(pe ->
                        new Anchor(createLinkWithParam(ProductDetailView.ROUTE, ProductDetailView.QUERY_PARAM_ID_NAME, pe.getId()), pe.getProductSku())))
                .setHeader("Product SKU")
                .setSortable(true)
                .setAutoWidth(true);
//        this.productEntityGrid
//                .addColumn(productEntity -> {
//                    Set<ProductSeriesEntity> seriesSet = productEntity.getProductSeries();
//                    OptionalInt optionalInt = new LastProductSeries(seriesSet).getLatest();
//                    return optionalInt.isPresent() ? optionalInt.getAsInt() : "";
//                })
//                .setHeader("Product version")
//                .setAutoWidth(true);
        this.productEntityGrid
                .addColumn(productEntity -> productEntity.getProductCategory().getCategoryName())
                .setHeader("Product category")
                .setSortable(true)
                .setAutoWidth(true);
        this.productEntityGrid
                .addColumn(productEntity -> productEntity.getManufacturer().getManufacturerName())
                .setHeader("Manufacturer")
                .setSortable(true)
                .setAutoWidth(true);
//        this.productEntityGrid
//                .addColumn(productEntity ->
//                        productEntity.getCreationTime() != null ? productEntity.getCreationTime().format(DateTimeFormatter.ofPattern(dateTimeFormat)) : null)
//                .setHeader("Creation time")
//                .setSortable(true);
//        this.productEntityGrid
//                .addColumn(productEntity ->
//                        productEntity.getModificationTime() != null ? productEntity.getModificationTime().format(DateTimeFormatter.ofPattern(dateTimeFormat)) : null)
//                .setHeader("Modification time")
//                .setSortable(true);

        this.productEntityGrid.setWidthFull();
        this.productEntityGrid.setHeightByRows(true);
        this.refreshGrid(productFinderService.findAll());
    }

    private String createLinkWithParam(String url, String paramName, Long id) {
        return url + "?" + paramName + "=" + id.toString();
    }

    public void refreshGrid(List<ProductEntity> products) {
        this.productEntityGrid.select(null);
        this.productEntityGrid.setItems(products);
    }
}
