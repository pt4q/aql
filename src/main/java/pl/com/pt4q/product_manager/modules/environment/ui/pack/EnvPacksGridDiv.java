package pl.com.pt4q.product_manager.modules.environment.ui.pack;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import lombok.Getter;
import pl.com.pt4q.product_manager.modules.environment.data.pack.EnvPackagingEntity;

import java.util.Set;

class EnvPacksGridDiv extends Div {

    @Getter
    private Grid<EnvPackagingEntity> grid = new Grid<>();

    public EnvPacksGridDiv() {
        initGrid();

        setId("grid-wrapper");
        setSizeFull();
        add(this.grid);
    }

    private void initGrid() {
        this.grid
                .addColumn(envPackagingEntity -> envPackagingEntity.getTypeOfPackaging().name())
                .setHeader("Type of packaging")
                .setSortable(true);
        this.grid
                .addColumn(envPackagingEntity -> envPackagingEntity.getMaterialGeneral().getNameENG())
                .setHeader("Material general")
                .setSortable(true);
        this.grid
                .addColumn(envPackagingEntity -> envPackagingEntity.getMaterialDetail().getNameENG())
                .setHeader("Material detail")
                .setSortable(true);
        this.grid
                .addColumn(EnvPackagingEntity::getNetWeight)
                .setHeader("Net weight")
                .setSortable(true);
        this.grid
                .addColumn(envPackagingEntity -> envPackagingEntity.getNetWeightUnit().getUnits())
                .setHeader("Net weight unit")
                .setSortable(true);
    }

    public void reloadGrid(Set<EnvPackagingEntity> packages) {
        this.grid.setItems(packages);
    }
}
