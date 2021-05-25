package pl.com.pt4q.product_manager.modules.product.ui.product_part_attribute;

import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import lombok.Getter;
import pl.com.pt4q.product_manager.modules.product.data.product_part_attribute.ProductPartAttributeEntity;
import pl.com.pt4q.product_manager.modules.product.data.product_part_attribute_value_version.ProductPartAttributeValueVersionEntity;
import pl.com.pt4q.product_manager.modules.product.ui.product_part_attribute_value_version.PartAttributeValueVersionsDetailView;

import java.time.format.DateTimeFormatter;
import java.util.List;

class ProductPartAttributeValueVersionsDiv extends Div {

    @Getter
    private Grid<ProductPartAttributeValueVersionEntity> attributeValueVersionsGrid = new Grid<>();
    private Button addNewValueVersionButton = new Button(new Icon(VaadinIcon.PLUS_CIRCLE_O));

    public ProductPartAttributeValueVersionsDiv() {
        initPartAttributesGrid();
        setId("grid-wrapper");

        VerticalLayout layout = new VerticalLayout(attributeValueVersionsGrid, addNewValueVersionButton);
        layout.setAlignItems(FlexComponent.Alignment.CENTER);
        layout.setWidthFull();

        setWidthFull();
        add(layout);
    }

    private void initPartAttributesGrid() {
        String dateTimeFormat = "yyyy-MM-dd";

        this.attributeValueVersionsGrid
                .addColumn(ProductPartAttributeValueVersionEntity::getProductSeries)
                .setHeader("Attribute value version")
                .setSortable(true);
//        this.attributeValueVersionsGrid
//                .addColumn(attribute -> attribute.getActualValueVersion().getAttributeValue())
//                .setHeader("Attribute value");
//        this.attributeValueVersionsGrid
//                .addColumn(attribute -> attribute.getActualValueVersion().getProductSeries() != null ? attribute.getActualValueVersion().getProductSeries().getSeries() : "")
//                .setHeader("Product series")
//                .setSortable(true);
//        this.attributeValueVersionsGrid
//                .addColumn(attribute -> attribute.getActualValueVersion().getValidFromDate() != null ? attribute.getActualValueVersion().getValidFromDate().format(DateTimeFormatter.ofPattern(dateTimeFormat)) : "")
//                .setHeader("Valid from time")
//                .setSortable(true);

        this.attributeValueVersionsGrid.setWidthFull();
        this.attributeValueVersionsGrid.setHeightByRows(true);
//        this.refreshGrid(testCardFinder.getAll());
    }

    private String createLinkWithParam(String url, String paramName, Long id) {
        return url + "?" + paramName + "=" + id.toString();
    }

    public void refreshGrid(List<ProductPartAttributeValueVersionEntity> partAttributes) {
        this.attributeValueVersionsGrid.select(null);
        this.attributeValueVersionsGrid.setItems(partAttributes);
    }

    private void initAddNewAttributeVersionValueButton(){
        this.addNewValueVersionButton.getElement().setProperty("title", "add new value version to the attribute");
        this.attributeValueVersionsGrid.addItemClickListener(event ->{
            UI ui = UI.getCurrent();
//            ComponentUtil.setData();
            ui.navigate(PartAttributeValueVersionsDetailView.ROUTE);
        });
    }
}
