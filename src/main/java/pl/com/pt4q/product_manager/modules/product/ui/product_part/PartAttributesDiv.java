package pl.com.pt4q.product_manager.modules.product.ui.product_part;


import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import pl.com.pt4q.product_manager.modules.product.data.product_part.ProductPartAttributeEntity;
import pl.com.pt4q.product_manager.modules.product.data.product_part.ProductPartEntity;

import java.util.Set;

class PartAttributesDiv extends Div {

    private Grid<ProductPartAttributeEntity> partAttributesGrid = new Grid<>();
    private AttributeEditorDiv attributeEditorDiv;

    private Set<ProductPartAttributeEntity> partAttributes;
    private ProductPartEntity partEntity;

    public PartAttributesDiv(ProductPartEntity partEntity) {
        SplitLayout splitLayout = new SplitLayout();
        splitLayout.setOrientation(SplitLayout.Orientation.HORIZONTAL);
        splitLayout.setHeightFull();
        attributeEditorDiv = new AttributeEditorDiv();
        Div gridLayout = createGridLayout();

        splitLayout.addToPrimary(gridLayout);
        splitLayout.addToSecondary(attributeEditorDiv);

        setSizeFull();
        add(partAttributesGrid, attributeEditorDiv);
    }


    private Div createGridLayout() {
        initPartAttributesGrid();
        Div wrapper = new Div();
        wrapper.setId("grid-wrapper");
        wrapper.setWidthFull();
//        splitLayout.addToPrimary(wrapper);
        wrapper.add(partAttributesGrid);
        return wrapper;
    }

    private void initPartAttributesGrid() {
        String dateTimeFormat = "yyyy-MM-dd HH:mm:ss";

        this.partAttributesGrid
                .addColumn(ProductPartAttributeEntity::getAttributeName)
                .setHeader("Attribute name")
                .setSortable(true);
        this.partAttributesGrid
                .addColumn(ProductPartAttributeEntity::getAttributeValue)
                .setHeader("Attribute value");

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
