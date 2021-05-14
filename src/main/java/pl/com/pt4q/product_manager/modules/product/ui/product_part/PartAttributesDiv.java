package pl.com.pt4q.product_manager.modules.product.ui.product_part;


import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import lombok.Getter;
import pl.com.pt4q.product_manager.modules.product.data.product_part.ProductPartEntity;
import pl.com.pt4q.product_manager.modules.product.services.product_series.ProductSeriesCrudService;

class PartAttributesDiv extends Div {

    @Getter
    private PartAttributesGridDiv attributesGridDiv;
    @Getter
    private PartAttributesEditorDiv attributeEditorDiv;

    private ProductSeriesCrudService productSeriesCrudService;
    private ProductPartEntity partEntity;

    public PartAttributesDiv(ProductPartEntity partEntity, ProductSeriesCrudService productSeriesCrudService) {
        this.partEntity = partEntity;
        this.productSeriesCrudService = productSeriesCrudService;

        attributesGridDiv = new PartAttributesGridDiv();
        attributeEditorDiv = new PartAttributesEditorDiv(productSeriesCrudService);

        SplitLayout splitLayout = new SplitLayout();
        splitLayout.setOrientation(SplitLayout.Orientation.VERTICAL);
        splitLayout.setHeightFull();

        splitLayout.addToPrimary(attributesGridDiv);
        splitLayout.addToSecondary(attributeEditorDiv);

        setSizeFull();
        add(splitLayout);
    }
}
