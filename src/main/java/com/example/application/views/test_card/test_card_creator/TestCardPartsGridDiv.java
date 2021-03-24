package com.example.application.views.test_card.test_card_creator;

import com.example.application.data.entity.test_card_associated.test_card.TestCardEntity;
import com.example.application.data.entity.test_card_associated.test_card_part.TestCardPartEntity;
import com.example.application.data.entity.test_card_associated.test_card_part_parameter_category.ParameterCategoryEntity;
import com.example.application.views.test_card.test_card_part_creator.TestCardPartCreatorView;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import lombok.Data;

import java.time.format.DateTimeFormatter;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
class TestCardPartsGridDiv extends Div {

    private Grid<TestCardPartEntity> testCardPartEntityGrid = new Grid<>();
    private AddNewTestCardPartToGridDiv addNewTestCardPartToGridDiv;

    private TestCardEntity testCardEntity;
    private LinkedHashSet<ParameterCategoryEntity> testCardCategories;

    public TestCardPartsGridDiv(TestCardEntity testCardEntity) {
        this.testCardEntity = testCardEntity;
        this.addNewTestCardPartToGridDiv = new AddNewTestCardPartToGridDiv(this.testCardEntity, testCardPartEntityGrid);
        initTestCardPartsGrid();

        VerticalLayout vl = new VerticalLayout(testCardPartEntityGrid, addNewTestCardPartToGridDiv);
        vl.setAlignItems(FlexComponent.Alignment.CENTER);
        setSizeFull();
        add(vl);
    }

    private void initTestCardPartsGrid() {
        String dateTimeFormat = "yyyy-MM-dd HH:mm:ss";
        this.testCardPartEntityGrid
                .addColumn(new ComponentRenderer<>(tcpe ->
                        new Anchor(createLinkWithParam(TestCardPartCreatorView.ROUTE, TestCardPartCreatorView.QUERY_PARAM_ID_NAME, tcpe.getId()), tcpe.getTestCardPartName())))
                .setHeader("Test card part name");
        this.testCardPartEntityGrid
                .addColumn(tcpe -> tcpe.getTestCardParameterCategories() != null ? tcpe.getTestCardParameterCategories().size() : null)
                .setHeader("Number of param category");
        this.testCardPartEntityGrid
                .addColumn(testCardPartEntity ->
                        testCardPartEntity.getCreationTime() != null ? testCardPartEntity.getCreationTime().format(DateTimeFormatter.ofPattern(dateTimeFormat)) : null)
                .setHeader("Creation time");
        this.testCardPartEntityGrid
                .addColumn(testCardPartEntity ->
                        testCardPartEntity.getModificationTime() != null ? testCardPartEntity.getModificationTime().format(DateTimeFormatter.ofPattern(dateTimeFormat)) : null)
                .setHeader("Modification time");

        this.testCardPartEntityGrid.setWidthFull();
        this.testCardPartEntityGrid.setHeightByRows(true);
    }

    private String createLinkWithParam(String url, String paramName, Long id) {
        return url + "?" + paramName + "=" + (id != null ? id.toString() : "");
    }

    public void refreshGrid(Set<TestCardPartEntity> testCardParts) {
        this.testCardPartEntityGrid.select(null);
        this.testCardPartEntityGrid.setItems(testCardParts);
    }

}
