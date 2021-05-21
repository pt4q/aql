package pl.com.pt4q.product_manager.modules.product.ui.product_part;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import lombok.Getter;
import pl.com.pt4q.product_manager.modules.product.data.product_part.ProductPartAttributeEntity;
import pl.com.pt4q.product_manager.modules.product.services.product_part.ProductPartAttributeFinderService;
import pl.com.pt4q.product_manager.modules.product.services.product_part.ProductPartFinderService;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;

class PartAttributesGridDiv extends Div {

    @Getter
    private Grid<ProductPartAttributeEntity> partAttributesGrid = new Grid<>();

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
                .addColumn(attribute -> attribute.getActualValueVersion().getAttributeValue())
                .setHeader("Attribute value");
        this.partAttributesGrid
                .addColumn(attribute -> attribute.getActualValueVersion().getProductSeries() != null ? attribute.getActualValueVersion().getProductSeries().getSeries() : "")
                .setHeader("Product series")
                .setSortable(true);
        this.partAttributesGrid
                .addColumn(attribute -> attribute.getActualValueVersion().getValidFromDate() != null ? attribute.getActualValueVersion().getValidFromDate().format(DateTimeFormatter.ofPattern(dateTimeFormat)) : "")
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
