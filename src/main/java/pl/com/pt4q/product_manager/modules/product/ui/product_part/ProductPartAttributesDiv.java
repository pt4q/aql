package pl.com.pt4q.product_manager.modules.product.ui.product_part;

import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import lombok.Getter;
import pl.com.pt4q.product_manager.modules.product.data.product_part_attribute.ProductPartAttributeEntity;
import pl.com.pt4q.product_manager.modules.product.data.product_part.ProductPartEntity;
import pl.com.pt4q.product_manager.modules.product.services.product_part_attribute.ProductPartAttributeFinderService;
import pl.com.pt4q.product_manager.modules.product.services.product_part_attribute.ProductPartAttributesInMemoryManager;
import pl.com.pt4q.product_manager.modules.product.services.product_part.ProductPartCrudSaver;
import pl.com.pt4q.product_manager.modules.product.services.product_part.exceptions.ProductPartAttributeNotFoundException;
import pl.com.pt4q.product_manager.modules.product.services.product_series.ProductSeriesCrudService;
import pl.com.pt4q.product_manager.modules.product.ui.product_part_attribute.ProductPartAttributeDetailView;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class ProductPartAttributesDiv extends Div {

    private Grid<ProductPartAttributeEntity> partAttributesGrid = new Grid<>();
    @Getter
    private Button addNewAttributeButton = new Button(new Icon(VaadinIcon.PLUS_CIRCLE_O));

    private ProductSeriesCrudService productSeriesCrudService;
    private ProductPartCrudSaver productPartCrudSaver;
    private ProductPartAttributeFinderService productPartAttributeFinderService;

    private ProductPartAttributesInMemoryManager productPartAttributesInMemoryManager;

    private ProductPartEntity productPart;

    public ProductPartAttributesDiv(ProductPartEntity productPart,
                                    ProductSeriesCrudService productSeriesCrudService,
                                    ProductPartCrudSaver productPartCrudSaver,
                                    ProductPartAttributeFinderService productPartAttributeFinderService) {

        this.productPart = productPart;
        this.productSeriesCrudService = productSeriesCrudService;
        this.productPartCrudSaver = productPartCrudSaver;
        this.productPartAttributeFinderService = productPartAttributeFinderService;

        Div attributesGridDiv = initAttributesDiv();
        initAddNewAttributeButton();
        initProductPartAttributesInMemoryManager();

        VerticalLayout vl = new VerticalLayout(attributesGridDiv, addNewAttributeButton);
        vl.setAlignItems(FlexComponent.Alignment.CENTER);
        vl.setWidthFull();

        setSizeFull();
        add(vl);
    }

    private Div initAttributesDiv(){
        initPartAttributesGrid();
        Div attributesDiv = new Div(partAttributesGrid);
        attributesDiv.setId("grid-wrapper");
        attributesDiv.setWidthFull();
        return attributesDiv;
    }

    private void initPartAttributesGrid() {
        String dateTimeFormat = "yyyy-MM-dd";

        this.partAttributesGrid
                .addColumn(new ComponentRenderer<>(attr ->  new Anchor(createLinkWithParam(ProductPartAttributeDetailView.ROUTE, ProductPartAttributeDetailView.QUERY_PARAM_ID_NAME, attr.getId()), attr.getAttributeName())))
                .setHeader("Attribute name")
                .setSortable(true);
        this.partAttributesGrid
                .addColumn(attribute -> attribute.getActualValueVersion().getAttributeValue())
                .setHeader("Attribute actual value");
        this.partAttributesGrid
                .addColumn(attribute -> attribute.getActualValueVersion().getProductSeries() != null ? attribute.getActualValueVersion().getProductSeries().getSeries() : "")
                .setHeader("Actual product series")
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

    private void initProductPartAttributesInMemoryManager() {
        List<ProductPartAttributeEntity> attributes = new ArrayList<>();
        try {
            this.productPartAttributesInMemoryManager = new ProductPartAttributesInMemoryManager(productPartAttributeFinderService.findAllProductPartsAttributesByProductPart(productPart));
        } catch (ProductPartAttributeNotFoundException e) {
            this.productPartAttributesInMemoryManager = new ProductPartAttributesInMemoryManager(Collections.emptyList());
        }
    }

    private void initAddNewAttributeButton() {
        this.addNewAttributeButton.getElement().setProperty("title", "add new attribute to the part");
        this.addNewAttributeButton.addClickListener(buttonClickEvent -> {
            UI ui = UI.getCurrent();
            ComponentUtil.setData(ui, ProductPartEntity.class, productPart);
            ui.navigate(ProductPartAttributeDetailView.ROUTE);
        });
    }
}
