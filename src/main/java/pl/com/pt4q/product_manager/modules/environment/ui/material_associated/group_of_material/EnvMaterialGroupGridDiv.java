package pl.com.pt4q.product_manager.modules.environment.ui.material_associated.group_of_material;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import lombok.Getter;
import pl.com.pt4q.product_manager.modules.environment.data.material_associated.group_of_material.EnvMaterialGroupEntity;
import pl.com.pt4q.product_manager.modules.environment.services.material_associated.material.EnvMaterialCrudService;
import pl.com.pt4q.product_manager.modules.environment.ui.material_associated.material.EnvMaterialView;
import pl.com.pt4q.product_manager.view_utils.UrlLinkWithParamCreator;

import java.util.List;

class EnvMaterialGroupGridDiv extends Div {

    @Getter
    private Grid<EnvMaterialGroupEntity> materialGroupGrid = new Grid<>();

    public EnvMaterialGroupGridDiv(EnvMaterialCrudService materialCrudService) {
        initGrid(materialCrudService);

        VerticalLayout layout = new VerticalLayout(materialGroupGrid);
        layout.setAlignItems(FlexComponent.Alignment.CENTER);
        layout.setSizeFull();

        setSizeFull();
        add(layout);
    }

    private void initGrid(EnvMaterialCrudService materialCrudService) {
        String dateTimeFormat = "yyyy-MM-dd HH:mm:ss";

        this.materialGroupGrid
                .addColumn(EnvMaterialGroupEntity::getNamePL)
                .setHeader("Group name (PL)")
                .setSortable(true)
                .setAutoWidth(true);
        this.materialGroupGrid
                .addColumn(EnvMaterialGroupEntity::getNameENG)
                .setHeader("Group name (ENG)")
                .setSortable(true)
                .setAutoWidth(true);
        this.materialGroupGrid
                .addColumn(new ComponentRenderer<>(materialGroup ->
                        new Anchor(UrlLinkWithParamCreator.createLinkWithParam(EnvMaterialView.ROUTE, EnvMaterialView.QUERY_PARAM_ID_NAME, materialGroup.getId()),
                                String.valueOf(materialCrudService.findAllByMaterialGroup(materialGroup).size())))
                )
                .setHeader("Number of materials in group")
                .setSortable(true)
                .setAutoWidth(true);

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
