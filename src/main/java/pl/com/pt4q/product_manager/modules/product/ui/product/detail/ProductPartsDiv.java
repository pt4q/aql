package pl.com.pt4q.product_manager.modules.product.ui.product.detail;


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

@Data
class ProductPartsDiv extends Div {

    private Grid<ProductPartEntity> productPartsGrid = new Grid<>();
    private Button addNewTestCardPartButton = new Button(new Icon(VaadinIcon.PLUS_CIRCLE_O));

    private ProductEntity productEntity;

    public ProductPartsDiv() {
        initGrid();
        initAddProductPartButton();

        VerticalLayout gridLayout = new VerticalLayout(this.productPartsGrid, this.addNewTestCardPartButton);
        gridLayout.setAlignItems(FlexComponent.Alignment.CENTER);

        setSizeFull();
        add(gridLayout);
    }

    private void initGrid() {
        String dateTimeFormat = "yyyy-MM-dd HH:mm:ss";
        this.productPartsGrid
                .addColumn(new ComponentRenderer<>(ppe ->
                        new Anchor(createLinkWithParam(ProductDetailView.ROUTE, ProductDetailView.QUERY_PARAM_ID_NAME, ppe.getId()), ppe.getPartModelOrPartName())))
                .setHeader("Part model")
                .setSortable(true);
        this.productPartsGrid
                .addColumn(ProductPartEntity::getPartDescription)
                .setHeader("Part description")
                .setSortable(true);
//        this.productPartsGrid
//                .addColumn(productPartEntity ->
//                        productPartEntity.getPartManufacturer() != null ?
//                                productPartEntity.getPartManufacturer()
//                                : "")
//                .setHeader("Part manufacturer")
//                .setSortable(true);
        this.productPartsGrid
                .addColumn(productPartEntity ->
                        productPartEntity.getProductSeries() != null ?
                                productPartEntity.getProductSeries().getSeries()
                                : "")
                .setHeader("Version")
                .setSortable(true);
        this.productPartsGrid
                .addColumn(productPartEntity ->
                        productPartEntity.getValidFromDate() != null ?
                        productPartEntity.getValidFromDate()
                        : "")
                .setHeader("Valid from time")
                .setSortable(true);
        this.productPartsGrid
                .addColumn(productEntity ->
                        productEntity.getModificationTime() != null ? productEntity.getModificationTime().format(DateTimeFormatter.ofPattern(dateTimeFormat)) : null)
                .setHeader("Modification time")
                .setSortable(true);

        this.productPartsGrid.setWidthFull();
        this.productPartsGrid.setHeightByRows(true);
//        this.refreshGrid(testCardFinder.getAll());
    }

    private String createLinkWithParam(String url, String paramName, Long id) {
        return url + "?" + paramName + "=" + id.toString();
    }

//    public void refreshGrid(List<TestCardEntity> testCards) {
//        this.productEntityGrid.select(null);
//        this.productEntityGrid.setItems(testCards);
//    }

    private void initAddProductPartButton() {
        this.addNewTestCardPartButton.getElement().setProperty("title", "add new part to the product");
        this.addNewTestCardPartButton.addClickListener(e -> {
            UI.getCurrent().navigate(ProductPartDetailView.ROUTE);
        });
    }
}
