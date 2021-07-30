package pl.com.pt4q.product_manager.modules.environment.ui.material_associated.group_of_material;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import lombok.Getter;
import pl.com.pt4q.product_manager.modules.environment.data.master.EnvMasterEntity;
import pl.com.pt4q.product_manager.modules.environment.data.material_associated.group_of_material.EnvMaterialGroupEntity;

import java.util.List;

class EnvMaterialGroupGridDiv extends Div {

    @Getter
    private Grid<EnvMaterialGroupEntity> materialGroupGrid = new Grid<>();

    public EnvMaterialGroupGridDiv() {
        initGrid();

        VerticalLayout layout = new VerticalLayout(materialGroupGrid);
        layout.setAlignItems(FlexComponent.Alignment.CENTER);
        layout.setSizeFull();

        setSizeFull();
        add(layout);
    }

    private void initGrid() {
        String dateTimeFormat = "yyyy-MM-dd HH:mm:ss";

        this.materialGroupGrid
                .addColumn(EnvMaterialGroupEntity::getName)
                .setHeader("Group name")
                .setSortable(true)
                .setAutoWidth(true);

//        this.materialGroupGrid
//                .addColumn(new ComponentRenderer<>(envMasterEntity ->
//                        new Anchor(createLinkWithParam(EnvMasterDetailView.ROUTE, EnvMasterDetailView.QUERY_PARAM_ID_NAME, envMasterEntity.getId()), envMasterEntity.getProduct().getSku())))
//                .setHeader("Product SKU")
//                .setSortable(true)
//                .setAutoWidth(true);

        this.materialGroupGrid.setSelectionMode(Grid.SelectionMode.SINGLE);
        this.materialGroupGrid.setSizeFull();
        this.materialGroupGrid.setHeightByRows(true);
    }

    private String createLinkWithParam(String url, String paramName, Long id) {
        return url + "?" + paramName + "=" + id.toString();
    }

    public void refreshGrid(List<EnvMaterialGroupEntity> groups) {
        this.materialGroupGrid.select(null);
        this.materialGroupGrid.setItems(groups);
    }
}
