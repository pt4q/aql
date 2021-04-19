package pl.com.pt4q.product_manager.modules.test_card.ui.test_card_part_creator;

import pl.com.pt4q.product_manager.modules.test_card.data.test_card_part.TestCardPartEntity;
import pl.com.pt4q.product_manager.modules.test_card.data.test_card_part_parameter_category_parameter.ParameterEntity;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import lombok.Data;

import java.util.LinkedHashSet;
import java.util.Set;

@Data
class TestCardPartParamsGridDiv extends Div {

    private Grid<ParameterEntity> testCardPartParamsGrid = new Grid<>();

    private TestCardPartEntity testCardPartEntity;
    private LinkedHashSet<ParameterEntity> testCardCategories;

    public TestCardPartParamsGridDiv(TestCardPartEntity testCardPartEntity) {
        this.testCardPartEntity = testCardPartEntity;
        initTestCardPartsGrid();

        VerticalLayout vl = new VerticalLayout(testCardPartParamsGrid);
        vl.setAlignItems(FlexComponent.Alignment.CENTER);
        setSizeFull();
        add(vl);
    }

    private void initTestCardPartsGrid() {
        String dateTimeFormat = "yyyy-MM-dd HH:mm:ss";
        String ifTrue = "+";
        String ifFalse = "";
        this.testCardPartParamsGrid
                .addColumn(ParameterEntity::getParameterName)
                .setHeader("Parameter name");
        this.testCardPartParamsGrid
                .addColumn(ParameterEntity::getParameterPoints)
                .setHeader("Points");
        this.testCardPartParamsGrid
                .addColumn(pe -> pe.getBooleanParameter() != null ? ifTrue : ifFalse)
                .setHeader("Boolean param type");
        this.testCardPartParamsGrid
                .addColumn(pe -> pe.getNumericParameter() != null ? ifTrue : ifFalse)
                .setHeader("Numeric param type");
        this.testCardPartParamsGrid
                .addColumn(pe -> pe.getAlphanumericParameter() != null ? ifTrue : ifFalse)
                .setHeader("Alphanumeric param type");
//        this.testCardPartParamsGrid
//                .addColumn(pe-> pe.getNumericParameter() != null ? pe.getNumericParameter().getNumericPossibleRequiredValues().size()
//                        :pe.getAlphanumericParameter())
//                .setHeader("Number of required possible values");
//        this.testCardPartParamsGrid
//                .addColumn(new ComponentRenderer<>(param ->
//                        new Anchor(createLinkWithParam(TestCardPartCreatorView.ROUTE, TestCardPartCreatorView.QUERY_PARAM_ID_NAME, param.getId()), param.getParameterName())))
//                .setHeader("Test card part name");
//        this.testCardPartParamsGrid
//                .addColumn(tcpe -> tcpe.getTestCardParameterCategories() != null ? tcpe.getTestCardParameterCategories().size() : null)
//                .setHeader("Number of param category");
//        this.testCardPartParamsGrid
//                .addColumn(testCardPartEntity ->
//                        testCardPartEntity.getCreationTime() != null ? testCardPartEntity.getCreationTime().format(DateTimeFormatter.ofPattern(dateTimeFormat)) : null)
//                .setHeader("Creation time");
//        this.testCardPartParamsGrid
//                .addColumn(testCardPartEntity ->
//                        testCardPartEntity.getModificationTime() != null ? testCardPartEntity.getModificationTime().format(DateTimeFormatter.ofPattern(dateTimeFormat)) : null)
//                .setHeader("Modification time");

        this.testCardPartParamsGrid.setWidthFull();
        this.testCardPartParamsGrid.setHeightByRows(true);
    }

    private String createLinkWithParam(String url, String paramName, Long id) {
        return url + "?" + paramName + "=" + (id != null ? id.toString() : "");
    }

    public void refreshGrid(Set<ParameterEntity> parameterEntities) {
        this.testCardPartParamsGrid.select(null);
        this.testCardPartParamsGrid.setItems(parameterEntities);
    }

}
