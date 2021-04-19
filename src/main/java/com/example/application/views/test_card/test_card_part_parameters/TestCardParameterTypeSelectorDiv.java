package com.example.application.views.test_card.test_card_part_parameters;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.select.Select;

class TestCardParameterTypeSelectorDiv extends Div {

    private Select<String> parameterTypeSelector;

    public TestCardParameterTypeSelectorDiv() {
        this.parameterTypeSelector = new Select<>("Alphanumeric", "Numeric", "Boolean");

        setWidthFull();
        add(parameterTypeSelector);
    }
}
