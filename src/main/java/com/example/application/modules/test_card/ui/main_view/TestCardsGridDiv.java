package com.example.application.modules.test_card.ui.main_view;

import com.example.application.modules.test_card.data.test_card.TestCardEntity;
import com.example.application.modules.test_card.services.test_card.TestCardFinder;
import com.example.application.modules.test_card.ui.test_card_creator.TestCardCreatorView;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import lombok.Data;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Data
class TestCardsGridDiv extends Div {

    private Grid<TestCardEntity> testCardsGrid = new Grid<>();
    private TestCardFinder testCardFinder;

    public TestCardsGridDiv(TestCardFinder testCardFinder) {
        this.testCardFinder = testCardFinder;

        initGrid();

        VerticalLayout gridLayout = new VerticalLayout(this.testCardsGrid);
        gridLayout.setAlignItems(FlexComponent.Alignment.CENTER);

        setSizeFull();
        add(gridLayout);
    }

    private void initGrid() {
        String dateTimeFormat = "yyyy-MM-dd HH:mm:ss";

        this.testCardsGrid
                .addColumn(testCardEntity -> testCardEntity.getProductCategory().getProductCategoryName())
                .setHeader("Product category")
                .setSortable(true);
        this.testCardsGrid
                .addColumn(new ComponentRenderer<>(tc ->
                        new Anchor(createLinkWithParam(TestCardCreatorView.ROUTE, TestCardCreatorView.QUERY_PARAM_ID_NAME, tc.getId()), tc.getTestCardName())))
                .setHeader("Test card name")
                .setSortable(true);
        this.testCardsGrid
                .addColumn(testCardEntity -> testCardEntity.getTestCardParts().size())
                .setHeader("Number of param categories")
                .setSortable(true);
        this.testCardsGrid
                .addColumn(testCardEntity ->
                        testCardEntity.getCreationTime() != null ? testCardEntity.getCreationTime().format(DateTimeFormatter.ofPattern(dateTimeFormat)) : null)
                .setHeader("Creation time")
                .setSortable(true);
        this.testCardsGrid
                .addColumn(testCardEntity ->
                testCardEntity.getModificationTime() != null ? testCardEntity.getModificationTime().format(DateTimeFormatter.ofPattern(dateTimeFormat)) : null)
                .setHeader("Modification time")
                .setSortable(true);

        this.testCardsGrid.setWidthFull();
        this.testCardsGrid.setHeightByRows(true);
        this.refreshGrid(testCardFinder.getAll());
    }

    private String createLinkWithParam(String url, String paramName, Long id) {
        return url + "?" + paramName + "=" + id.toString();
    }

    public void refreshGrid(List<TestCardEntity> testCards) {
        this.testCardsGrid.select(null);
        this.testCardsGrid.setItems(testCards);
    }
}
