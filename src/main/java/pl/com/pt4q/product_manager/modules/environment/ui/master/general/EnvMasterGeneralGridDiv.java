package pl.com.pt4q.product_manager.modules.environment.ui.master.general;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import lombok.Getter;
import pl.com.pt4q.product_manager.modules.environment.data.master.EnvMasterEntity;
import pl.com.pt4q.product_manager.modules.environment.ui.master.detail.EnvMasterDetailView;
import pl.com.pt4q.product_manager.modules.environment.ui.weee.EnvWeeeView;
import pl.com.pt4q.product_manager.view_utils.UrlLinkWithParamCreator;

import java.util.List;

class EnvMasterGeneralGridDiv extends Div {

    @Getter
    private Grid<EnvMasterEntity> masterGrid = new Grid<>();

    public EnvMasterGeneralGridDiv() {
        initGrid();

        VerticalLayout layout = new VerticalLayout(masterGrid);
        layout.setAlignItems(FlexComponent.Alignment.CENTER);
        layout.setSizeFull();

        setSizeFull();
        add(layout);
    }

    private void initGrid() {
        String dateTimeFormat = "yyyy-MM-dd HH:mm:ss";

        this.masterGrid
                .addColumn(new ComponentRenderer<>(envMasterEntity ->
                        new Anchor(createLinkWithParam(EnvMasterDetailView.ROUTE, EnvMasterDetailView.QUERY_PARAM_ID_NAME, envMasterEntity.getId()), envMasterEntity.getProduct().getSku())))
                .setHeader("Product SKU")
                .setSortable(true)
                .setAutoWidth(true);
        this.masterGrid
                .addColumn(envMasterEntity -> envMasterEntity.getProduct().getDescriptionENG())
                .setHeader("Description (ENG)")
                .setSortable(true)
                .setAutoWidth(true);
        this.masterGrid
                .addColumn(EnvMasterEntity::getValidFrom)
                .setHeader("Valid from")
                .setSortable(true)
                .setAutoWidth(true);
        this.masterGrid
                .addColumn(EnvMasterEntity::getValidTo)
                .setHeader("Valid to")
                .setSortable(true)
                .setAutoWidth(true);
        this.masterGrid
                .addColumn(EnvMasterEntity::getGrossWeight)
                .setHeader("Gross weight of article")
                .setSortable(true)
                .setAutoWidth(true);
        this.masterGrid
                .addColumn(envMasterEntity -> envMasterEntity.getGrossWeightUnit().getUnits())
                .setHeader("Weight Unit")
                .setSortable(true)
                .setAutoWidth(true);
        this.masterGrid
                .addColumn(envMasterEntity -> envMasterEntity.getProduct().getManufacturer().getAddress() != null ? envMasterEntity.getProduct().getManufacturer().getAddress().getCountry() : "")
                .setHeader("Country of origin")
                .setSortable(true)
                .setAutoWidth(true);
        this.masterGrid
                .addColumn(new ComponentRenderer<>(masterEntity -> masterEntity.getWeee() != null ?
                        new Anchor(UrlLinkWithParamCreator.createLinkWithParam(EnvWeeeView.ROUTE, EnvWeeeView.QUERY_PARAM_ID_NAME, masterEntity.getId()), "Yes")
                        : new Anchor(UrlLinkWithParamCreator.createLinkWithParam(EnvMasterDetailView.ROUTE, EnvMasterDetailView.QUERY_PARAM_ID_NAME, masterEntity.getId()),"No")))
                .setHeader("WEEE")
                .setSortable(true)
                .setAutoWidth(true);
//        this.masterGrid
//                .addColumn(envMasterEntity -> envMasterEntity.getLightSource() != null ?
//                        new ComponentRenderer<>(ls -> new Anchor(createLinkWithParam(EnvLightSourceView.ROUTE, EnvLightSourceView.QUERY_PARAM_ID_NAME, envMasterEntity.getId()), "Yes")) :
//                        "No"
//                )
//                .setHeader("LS")
//                .setSortable(true)
//                .setAutoWidth(true);
//        this.masterGrid
//                .addColumn(envMasterEntity -> envMasterEntity.getBattery() != null ?
//                        new ComponentRenderer<>(ls -> new Anchor(createLinkWithParam(EnvBatView.ROUTE, EnvBatView.QUERY_PARAM_ID_NAME, envMasterEntity.getId()), "Yes")) :
//                        "No"
//                )
//                .setHeader("BAT")
//                .setSortable(true)
//                .setAutoWidth(true);
//        this.masterGrid
//                .addColumn(envMasterEntity -> envMasterEntity.getPackaging() != null ?
//                        new ComponentRenderer<>(ls -> new Anchor(createLinkWithParam(EnvBatView.ROUTE, EnvBatView.QUERY_PARAM_ID_NAME, envMasterEntity.getId()), "Yes")) :
//                        "No"
//                )
//                .setHeader("PACK")
//                .setSortable(true)
//                .setAutoWidth(true);
//
//        this.masterGrid.setWidthFull();
//        this.masterGrid.setHeightByRows(true);
    }

    private String createLinkWithParam(String url, String paramName, Long id) {
        return url + "?" + paramName + "=" + id.toString();
    }

    public void refreshGrid(List<EnvMasterEntity> masterEntityList) {
        this.masterGrid.select(null);
        this.masterGrid.setItems(masterEntityList);
    }
}
