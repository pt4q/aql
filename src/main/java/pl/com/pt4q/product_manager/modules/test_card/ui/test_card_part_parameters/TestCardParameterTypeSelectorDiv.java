package pl.com.pt4q.product_manager.modules.test_card.ui.test_card_part_parameters;

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
