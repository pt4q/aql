package pl.com.pt4q.product_manager.modules.product.ui.product_part;


import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import pl.com.pt4q.product_manager.modules.product.data.product_part.ProductPartAttributeEntity;
import pl.com.pt4q.product_manager.modules.product.data.product_part.ProductPartEntity;

import java.util.Set;

class PartAttributesDiv extends Div {

    private PartAttributesGridDiv attributesGridDiv;
    private PartAttributesEditorDiv attributeEditorDiv;

    private ProductPartEntity partEntity;

    public PartAttributesDiv(ProductPartEntity partEntity) {
        attributesGridDiv = new PartAttributesGridDiv();
        attributeEditorDiv = new PartAttributesEditorDiv();

        SplitLayout splitLayout = new SplitLayout();
        splitLayout.setOrientation(SplitLayout.Orientation.VERTICAL);
        splitLayout.setHeightFull();

        splitLayout.addToPrimary(attributesGridDiv);
        splitLayout.addToSecondary(attributeEditorDiv);

        setSizeFull();
        add(splitLayout);
    }






}
