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

        initPopulateAttributeFormAfterSelectAttributeOnGrid();
        initCreateNewOrUpdateAttributeAfterClickSaveButton();

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

    public void refreshAttributesGrid(ProductPartEntity productPart){
        try {
            attributesGridDiv.refreshGrid(productPartAttributeFinderService.findAllProductPartsAttributesByProductPart(productPart));
        } catch (ProductPartNotFoundException e) {
            Notification.show(e.getMessage());
        }
    }

    private void initCreateNewOrUpdateAttributeAfterClickSaveButton(){
        this.attributeEditorDiv.getSaveButton().addClickListener(buttonClickEvent -> {

        });
    }

    private void initDeleteAttributeAfterClickDeleteButton(){
        this.attributeEditorDiv.getDeleteButton().addClickListener(buttonClickEvent -> {

        });
    }
}
