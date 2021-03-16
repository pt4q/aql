package com.example.application.views.test_card;

import com.example.application.data.entity.test_card_associated.test_card.TestCardEntity;
import com.example.application.data.service.product_category.ProductCategoryCrudService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.function.ValueProvider;
import lombok.*;

import java.util.List;


@Data
class TestCardsGridDiv extends Div {

    private Grid<TestCardEntity> testCardsGrid = new Grid<>();

    public Div create(){
        initGrid();
        add(testCardsGrid);
        return this;
    }

    public void refreshGrid (List<TestCardEntity> testCards){
        this.testCardsGrid.select(null);
        this.testCardsGrid.setItems(testCards);
    }

    private void initGrid(){
        this.testCardsGrid.addColumn(testCardEntity -> testCardEntity.getProductCategory().getProductCategoryName());
        this.testCardsGrid.addColumn(TestCardEntity::getTestCardName);
    }
}
