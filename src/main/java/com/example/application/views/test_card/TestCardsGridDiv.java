package com.example.application.views.test_card;

import com.example.application.data.entity.test_card_associated.test_card.TestCardEntity;
import com.example.application.data.service.product_category.ProductCategoryCrudService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import lombok.*;


@Data
class TestCardsGridDiv extends Div {

    private Grid<TestCardEntity> testCardsGrid = new Grid<>();

    public Div create(){

        return this;
    }
}
