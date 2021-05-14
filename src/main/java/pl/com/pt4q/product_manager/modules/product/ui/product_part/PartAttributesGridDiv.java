package pl.com.pt4q.product_manager.modules.product.ui.product_part;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import pl.com.pt4q.product_manager.modules.product.data.product_part.ProductPartAttributeEntity;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;

class PartAttributesGridDiv extends Div {

    private Grid<ProductPartAttributeEntity> partAttributesGrid = new Grid<>();

    private Set<ProductPartAttributeEntity> partAttributes;

    public PartAttributesGridDiv() {
        initPartAttributesGrid();
        setId("grid-wrapper");
        setWidthFull();
        add(partAttributesGrid);
    }

    private void initPartAttributesGrid() {
        String dateTimeFormat = "yyyy-MM-dd";

        this.partAttributesGrid
                .addColumn(ProductPartAttributeEntity::getAttributeName)
                .setHeader("Attribute name")
                .setSortable(true);
        this.partAttributesGrid
                .addColumn(ProductPartAttributeEntity::getAttributeValue)
                .setHeader("Attribute value");
        this.partAttributesGrid
                .addColumn(attribute -> attribute.getProductSeries() != null ? attribute.getProductSeries().getSeries() : "")
                .setHeader("Product series")
                .setSortable(true);
        this.partAttributesGrid
                .addColumn(attribute -> attribute.getValidFromDate() != null ? attribute.getValidFromDate().format(DateTimeFormatter.ofPattern(dateTimeFormat)) : "")
                .setHeader("Valid from time")
                .setSortable(true);

        this.partAttributesGrid.setWidthFull();
        this.partAttributesGrid.setHeightByRows(true);
//        this.refreshGrid(testCardFinder.getAll());
    }

    private String createLinkWithParam(String url, String paramName, Long id) {
        return url + "?" + paramName + "=" + id.toString();
    }

    public void refreshGrid(List<ProductPartAttributeEntity> partAttributes) {
        this.partAttributesGrid.select(null);
        this.partAttributesGrid.setItems(partAttributes);
    }
}
