package pl.com.pt4q.product_manager.modules.product.ui.product_part;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import lombok.Getter;
import pl.com.pt4q.product_manager.modules.product.data.product_part.ProductPartAttributeEntity;
import pl.com.pt4q.product_manager.modules.product.data.product_part.ProductPartEntity;
import pl.com.pt4q.product_manager.modules.product.services.product_part.ProductPartAttributeFinderService;
import pl.com.pt4q.product_manager.modules.product.services.product_part.ProductPartAttributesInMemoryManager;
import pl.com.pt4q.product_manager.modules.product.services.product_part.ProductPartCrudSaver;
import pl.com.pt4q.product_manager.modules.product.services.product_part.exceptions.ProductPartNotFoundException;
import pl.com.pt4q.product_manager.modules.product.services.product_series.ProductSeriesCrudService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class PartAttributesDiv extends Div {
    @Getter
    private PartAttributesGridDiv attributesGridDiv;
    @Getter
    private PartAttributesEditorDiv attributeEditorDiv;

    private ProductSeriesCrudService productSeriesCrudService;
    private ProductPartCrudSaver productPartCrudSaver;
    private ProductPartAttributeFinderService productPartAttributeFinderService;

    private ProductPartAttributesInMemoryManager productPartAttributesInMemoryManager;

    private ProductPartEntity productPart;

    public PartAttributesDiv(ProductPartEntity productPart,
                             ProductSeriesCrudService productSeriesCrudService,
                             ProductPartCrudSaver productPartCrudSaver,
                             ProductPartAttributeFinderService productPartAttributeFinderService) {

        this.productPart = productPart;
        this.productSeriesCrudService = productSeriesCrudService;
        this.productPartCrudSaver = productPartCrudSaver;
        this.productPartAttributeFinderService = productPartAttributeFinderService;

        this.attributesGridDiv = new PartAttributesGridDiv();
        this.attributeEditorDiv = new PartAttributesEditorDiv();

        initProductPartAttributesInMemoryManager();

        initPopulateAttributeFormAfterSelectAttributeOnGrid();
        initSaveOrUpdateAttributeAction();

        SplitLayout splitLayout = new SplitLayout();
        splitLayout.setOrientation(SplitLayout.Orientation.VERTICAL);
        splitLayout.setHeightFull();

        splitLayout.addToPrimary(attributesGridDiv);
        splitLayout.addToSecondary(attributeEditorDiv);

        setSizeFull();
        add(splitLayout);
    }

    private void initPopulateAttributeFormAfterSelectAttributeOnGrid() {
        this.attributesGridDiv.getPartAttributesGrid()
                .addItemClickListener(event -> {
                    ProductPartAttributeEntity attribute = event.getItem();
                    this.attributeEditorDiv.populateAttributeForm(attribute);
                });
    }

    public void refreshAttributesGrid(ProductPartEntity productPart) {
        try {
            attributesGridDiv.refreshGrid(productPartAttributeFinderService.findAllProductPartsAttributesByProductPart(productPart));
        } catch (ProductPartNotFoundException e) {
            attributesGridDiv.refreshGrid(Collections.emptyList());
        }
    }

    private void initProductPartAttributesInMemoryManager() {
        List<ProductPartAttributeEntity> attributes = new ArrayList<>();
        try {
            attributes = productPartAttributeFinderService.findAllProductPartsAttributesByProductPart(productPart);
        } catch (ProductPartNotFoundException e) {
            Notification.show(e.getMessage());
        }
        this.productPartAttributesInMemoryManager = new ProductPartAttributesInMemoryManager(attributes);
    }

    private void initSaveOrUpdateAttributeAction() {
        this.attributeEditorDiv.getSaveButton().addClickListener(buttonClickEvent -> {
            ProductPartAttributeEntity attribute = attributeEditorDiv.getAttributeEntity();
            this.productPart
                    .setPartAttributes(
                            productPartAttributesInMemoryManager.addToList(attribute)
                    );
        });
    }

    private void initDeleteAttributeAction() {
        this.attributeEditorDiv.getDeleteButton().addClickListener(buttonClickEvent -> {
            ProductPartAttributeEntity attribute = attributeEditorDiv.getAttributeEntity();

        });
    }
}
