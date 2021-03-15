package com.example.application.views.test_card.test_card_creator;

import com.example.application.data.entity.test_card_associated.parameter_category.ParameterCategoryEntity;
import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.html.Div;
import lombok.Data;

import java.util.LinkedHashSet;

@Data
class TestCardParamCategoriesDiv extends Div {

    private LinkedHashSet<ParameterCategoryEntity> testCardCategories;

    private Accordion categories = new Accordion();

    public TestCardParamCategoriesDiv(LinkedHashSet<ParameterCategoryEntity> testCardCategories) {
        this.testCardCategories = testCardCategories;
    }

    public Div create (){

        return this;
    }
}
