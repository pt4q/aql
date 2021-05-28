package pl.com.pt4q.product_manager.modules.product.ui.product_part_attribute;

import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;
import pl.com.pt4q.product_manager.modules.product.data.product_part.ProductPartEntity;
import pl.com.pt4q.product_manager.modules.product.data.product_part_attribute.ProductPartAttributeEntity;
import pl.com.pt4q.product_manager.modules.product.ui.product_part.ProductPartDetailView;
import pl.com.pt4q.product_manager.view_utils.SaveObjectAndBackButtonsDiv;
import pl.com.pt4q.product_manager.views.main.MainView;

@Route(value = ProductPartAttributeDetailView.ROUTE, layout = MainView.class)
@PageTitle(ProductPartAttributeDetailView.PAGE_TITLE)
public class ProductPartAttributeDetailView extends Div implements HasUrlParameter<String> {

    public static final String PAGE_TITLE = "Product part attribute detail";
    public static final String ROUTE = "product-part-attribute-detail";
    public static final String QUERY_PARAM_ID_NAME = "productPartId";

    private SaveObjectAndBackButtonsDiv saveObjectAndBackButtonsDiv;
    private ProductPartAttributeEditorDiv productPartAttributeEditorDiv;
    private ProductPartAttributeValueVersionsDiv productPartAttributeValueVersionsDiv;

    private ProductPartAttributeEntity productPartAttribute;

    public ProductPartAttributeDetailView() {
        this.productPartAttribute = getProductPartAttributeFromContext();

        this.saveObjectAndBackButtonsDiv = new SaveObjectAndBackButtonsDiv("Save attribute");
        this.productPartAttributeEditorDiv = new ProductPartAttributeEditorDiv(this.productPartAttribute);
        this.productPartAttributeValueVersionsDiv = new ProductPartAttributeValueVersionsDiv(this.productPartAttribute);

        initSaveButtonListener();
        initBackButtonListener();

        VerticalLayout layout = new VerticalLayout(
                saveObjectAndBackButtonsDiv,
                productPartAttributeEditorDiv,
                productPartAttributeValueVersionsDiv
        );
        layout.setAlignItems(FlexComponent.Alignment.CENTER);
        layout.setWidthFull();

        setSizeFull();
        add(layout);
    }

    private ProductPartAttributeEntity getProductPartAttributeFromContext() {
        return ComponentUtil.getData(UI.getCurrent(), ProductPartAttributeEntity.class);
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, @OptionalParameter String s) {

    }

    private void initSaveButtonListener() {
        this.saveObjectAndBackButtonsDiv.getSaveButton().addClickListener(buttonClickEvent -> {

        });
    }

    private void initBackButtonListener() {
        ProductPartEntity productPart = this.productPartAttribute.getPart();
        this.saveObjectAndBackButtonsDiv.createBackButtonClickListenerWithSaveObjectFromContext(ProductPartDetailView.ROUTE, ProductPartEntity.class, productPart);
    }
}
