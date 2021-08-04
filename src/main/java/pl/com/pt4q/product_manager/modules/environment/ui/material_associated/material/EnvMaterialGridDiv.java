package pl.com.pt4q.product_manager.modules.environment.ui.material_associated.material;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import lombok.Getter;
import pl.com.pt4q.product_manager.modules.environment.data.material_associated.material.EnvMaterialEntity;

import java.util.List;

class EnvMaterialGridDiv extends Div {

    @Getter
    private Grid<EnvMaterialEntity> materialGrid = new Grid<>();

    public EnvMaterialGridDiv() {
        initGrid();

        VerticalLayout layout = new VerticalLayout(materialGrid);
        layout.setAlignItems(FlexComponent.Alignment.CENTER);
        layout.setSizeFull();

        setSizeFull();
        add(layout);
    }

    private void initGrid() {
        String dateTimeFormat = "yyyy-MM-dd HH:mm:ss";

        this.materialGrid
                .addColumn(EnvMaterialEntity::getNamePL)
                .setHeader("Material name (PL)")
                .setSortable(true)
                .setAutoWidth(true);
        this.materialGrid
                .addColumn(EnvMaterialEntity::getNameENG)
                .setHeader("Material name (ENG)")
                .setSortable(true)
                .setAutoWidth(true);

        this.materialGrid.setSelectionMode(Grid.SelectionMode.SINGLE);
        this.materialGrid.setSizeFull();
        this.materialGrid.setHeightByRows(true);
    }

    public void refreshGrid(List<EnvMaterialEntity> materials) {
        this.materialGrid.select(null);
        this.materialGrid.setItems(materials);
    }
}
