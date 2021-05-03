package pl.com.pt4q.product_manager.modules.product.ui.product_part;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import pl.com.pt4q.product_manager.modules.product.data.product.ProductEntity;
import pl.com.pt4q.product_manager.modules.product.data.product_part.ProductPartAttributeEntity;
import pl.com.pt4q.product_manager.modules.product.data.product_part.ProductPartEntity;
import pl.com.pt4q.product_manager.modules.product.services.product_part.ProductPartAttributeCrudService;

import java.util.List;

class AddNewPartAttributeToGridDiv extends Div {

    private Button addNewTestCardPartButton = new Button(new Icon(VaadinIcon.PLUS_CIRCLE_O));

    private Grid<ProductPartAttributeEntity> partAttributesGrid;

    private ProductPartEntity partEntity;
    private List<ProductPartAttributeEntity> partAttributes;

    public AddNewPartAttributeToGridDiv(
            ProductPartEntity partEntity,
            Grid<ProductPartAttributeEntity> partAttributesGrid) {

        this.partAttributesGrid = partAttributesGrid;
        this.partEntity = partEntity;

        initAddProductPartButton();

        HorizontalLayout layout = new HorizontalLayout(addNewTestCardPartButton);
        layout.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);

        add(layout);
    }

    private void initAddProductPartButton() {
        this.addNewTestCardPartButton.addClickListener(e -> {
            Notification.show("add attribute");
        });
    }
}
