package pl.com.pt4q.product_manager.modules.product_parts.ui.product_part_attribute;

import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import pl.com.pt4q.product_manager.modules.product_parts.data.product_part.ProductPartEntity;
import pl.com.pt4q.product_manager.modules.product_parts.data.product_part_attribute.ProductPartAttributeEntity;
import pl.com.pt4q.product_manager.modules.product_parts.data.product_part_attribute_version.ProductPartAttributeVersionEntity;
import pl.com.pt4q.product_manager.modules.product_parts.services.product_part.ProductPartCreatorService;
import pl.com.pt4q.product_manager.modules.product_parts.services.product_part.exceptions.ProductPartAlreadyExistsException;
import pl.com.pt4q.product_manager.modules.product_parts.services.product_part.exceptions.ProductPartAttributeNotFoundException;
import pl.com.pt4q.product_manager.modules.product_parts.services.product_part_attribute.ProductPartAttributeCreatorService;
import pl.com.pt4q.product_manager.modules.product_parts.services.product_part_attribute.ProductPartAttributeFinderService;
import pl.com.pt4q.product_manager.modules.product_parts.services.product_part_attribute.ProductPartAttributeUpdaterService;
import pl.com.pt4q.product_manager.modules.product_parts.services.product_part_attribute.exceptions.ProductPartAttributeAlreadyExistsException;
import pl.com.pt4q.product_manager.modules.product.services.unit.UnitCrudService;
import pl.com.pt4q.product_manager.modules.product_parts.ui.product_part.ProductPartDetailView;
import pl.com.pt4q.product_manager.modules.product_parts.ui.product_part_attribute_version.ProductPartAttributeVersionDetailView;
import pl.com.pt4q.product_manager.view_utils.SaveAndBackButtonsDiv;
import pl.com.pt4q.product_manager.views.main.MainView;

import java.util.List;
import java.util.Map;

@Slf4j
@Route(value = ProductPartAttributeDetailView.ROUTE, layout = MainView.class)
@PageTitle(ProductPartAttributeDetailView.PAGE_TITLE)
public class ProductPartAttributeDetailView extends Div implements HasUrlParameter<String> {

    public static final String PAGE_TITLE = "Product part attribute detail";
    public static final String ROUTE = "product-part-attribute-detail";
    public static final String QUERY_PARAM_ID_NAME = "partAttributeId";

    private SaveAndBackButtonsDiv saveAndBackButtonsDiv;
    private ProductPartAttributeEditorDiv productPartAttributeEditorDiv;
    private ProductPartAttributeVersionsGridDiv productPartAttributeVersionsGridDiv;

    private UnitCrudService unitCrudService;

    private ProductPartCreatorService productPartCreatorService;
    private ProductPartAttributeFinderService productPartAttributeFinderService;
    private ProductPartAttributeCreatorService productPartAttributeCreatorService;
    private ProductPartAttributeUpdaterService productPartAttributeUpdaterService;
    // todo: Add attributeUnits and attributeValueType (decimal, float, text etc)
    private ProductPartAttributeEntity productPartAttribute;

    @Autowired
    public ProductPartAttributeDetailView(UnitCrudService unitCrudService,
                                          ProductPartCreatorService productPartCreatorService,
                                          ProductPartAttributeFinderService productPartAttributeFinderService,
                                          ProductPartAttributeCreatorService productPartAttributeCreatorService,
                                          ProductPartAttributeUpdaterService productPartAttributeUpdaterService) {

        this.unitCrudService = unitCrudService;
        this.productPartCreatorService = productPartCreatorService;
        this.productPartAttributeFinderService = productPartAttributeFinderService;
        this.productPartAttributeCreatorService = productPartAttributeCreatorService;
        this.productPartAttributeUpdaterService = productPartAttributeUpdaterService;

        this.productPartAttribute = getProductPartAttributeFromContext();

        this.saveAndBackButtonsDiv = new SaveAndBackButtonsDiv("Save attribute");
        this.productPartAttributeEditorDiv = new ProductPartAttributeEditorDiv(this.productPartAttribute);
        this.productPartAttributeVersionsGridDiv = new ProductPartAttributeVersionsGridDiv(this.productPartAttribute);

        initUnitsComboBox();

        initSaveButtonListener();
        initBackButtonListener();
        initAddNewAttributeVersion();

        VerticalLayout layout = new VerticalLayout(
                saveAndBackButtonsDiv,
                productPartAttributeEditorDiv,
                productPartAttributeVersionsGridDiv
        );
        layout.setAlignItems(FlexComponent.Alignment.CENTER);
        layout.setWidthFull();

        setSizeFull();
        add(layout);
    }

    private ProductPartAttributeEntity getProductPartAttributeFromContext() {
        return ComponentUtil.getData(UI.getCurrent(), ProductPartAttributeEntity.class);
    }

    private void saveAttributeToContext(ProductPartAttributeEntity attributeEntity) {
        ComponentUtil.setData(UI.getCurrent(), ProductPartAttributeEntity.class, attributeEntity);
    }

    private void populatePartAttributeForm(ProductPartAttributeEntity attributeEntity) {
        if (attributeEntity != null)
            this.productPartAttributeEditorDiv.populateForm(attributeEntity);
    }

    private void refreshPartAttributeVersions(ProductPartAttributeEntity attributeEntity) {

    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, @OptionalParameter String s) {
        Location location = beforeEvent.getLocation();
        QueryParameters queryParameters = location.getQueryParameters();
        Map<String, List<String>> parametersMap = queryParameters.getParameters();

        if (parametersMap.containsKey(QUERY_PARAM_ID_NAME)) {
            Long id = Long.valueOf(parametersMap.get(QUERY_PARAM_ID_NAME).get(0));
            try {
                this.productPartAttribute = productPartAttributeFinderService.findByIdOrThrowException(id);
                saveAttributeToContext(this.productPartAttribute);
                populatePartAttributeForm(this.productPartAttribute);
                refreshPartAttributeVersions(this.productPartAttribute);

            } catch (ProductPartAttributeNotFoundException ignored) {
                log.warn(String.format("%s: GET parameter is incorrect - %s=%d", PAGE_TITLE, QUERY_PARAM_ID_NAME, id));
            }
        }
    }

    private void initUnitsComboBox() {
        this.productPartAttributeEditorDiv.setUnitComboBoxOptions(unitCrudService.getAll());
    }

    private void initSaveButtonListener() {
        this.saveAndBackButtonsDiv.getSaveButton().addClickListener(buttonClickEvent -> {
            ProductPartAttributeEntity partAttributeFromForm = productPartAttributeEditorDiv.getProductPartAttributeEntityBinder().getBean();
            ProductPartEntity productPartFromPartAttribute = createProductPartIfNotCreatedYet(partAttributeFromForm.getPart());

            try {
                this.productPartAttribute = this.productPartAttributeCreatorService.create(partAttributeFromForm);
                Notification.show(String.format("%s: Attribute %s has been added to %s for %s product",
                        PAGE_TITLE,
                        partAttributeFromForm.getAttributeName(),
                        productPartFromPartAttribute.getPartModelOrPartName(),
                        productPartFromPartAttribute.getProduct().getSku()
                ));
            } catch (ProductPartAttributeAlreadyExistsException e) {
                try {
                    this.productPartAttribute = this.productPartAttributeUpdaterService.update(partAttributeFromForm);
                } catch (ProductPartAttributeNotFoundException ex) {
                    log.error(String.format("%s: save button action error - %s", PAGE_TITLE, ex.getMessage()));
                }
            }
        });
    }

    private ProductPartEntity createProductPartIfNotCreatedYet(ProductPartEntity productPart) {
        if (productPart.getId() == null) {
            try {
                productPart = productPartCreatorService.create(productPart);
                this.productPartAttribute.setPart(productPart);
            } catch (ProductPartAlreadyExistsException e) {
                log.error(String.format("%s: save button action error - create part - %s", PAGE_TITLE, e.getMessage()));
            }
            return productPart;
        }
        return productPart;
    }

    private void initBackButtonListener() {
        this.saveAndBackButtonsDiv.getBackButton().addClickListener(buttonClickEvent -> {
            ProductPartEntity productPart = this.productPartAttribute.getPart();
            UI ui = UI.getCurrent();
            ComponentUtil.setData(ui, ProductPartEntity.class, productPart);
            ui.navigate(ProductPartDetailView.ROUTE);
        });
    }

    private void initAddNewAttributeVersion() {
        this.productPartAttributeVersionsGridDiv.getAddNewValueVersionButton().addClickListener(buttonClickEvent -> {
            UI ui = UI.getCurrent();
            ComponentUtil.setData(ui, ProductPartAttributeVersionEntity.class, ProductPartAttributeVersionEntity.builder().partAttribute(this.productPartAttribute).build());
            ui.navigate(ProductPartAttributeVersionDetailView.ROUTE);
        });
    }
}
