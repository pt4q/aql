package com.example.application.views.test_card;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import lombok.Data;

@Data
class ProductCategoryFilterDiv extends Div {

    private TextField productCategoryFiler;

    public ProductCategoryFilterDiv() {
        initTestCardsFilter();
        add(productCategoryFiler);
    }

    private void initTestCardsFilter() {
        this.productCategoryFiler.setAutofocus(true);
        this.productCategoryFiler.setAutoselect(true);
        this.productCategoryFiler.setRequired(true);
        this.productCategoryFiler.setAutocorrect(true);
        this.productCategoryFiler.setValueChangeMode(ValueChangeMode.LAZY);
        this.productCategoryFiler.setValueChangeTimeout(1000);
        this.productCategoryFiler.addValueChangeListener(e -> {

        });

    }
}
