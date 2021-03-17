package com.example.application.views.test_card.creator;

import com.example.application.data.entity.test_card_associated.parameter_category.ParameterCategoryEntity;
import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.details.DetailsVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.textfield.TextField;
import lombok.Data;

import java.util.LinkedHashSet;

@Data
class TestCardParamCategoriesFactoryDiv extends Div {

    private LinkedHashSet<ParameterCategoryEntity> testCardCategories;

    private Accordion categoriesAccordion = new Accordion();

    public TestCardParamCategoriesFactoryDiv(LinkedHashSet<ParameterCategoryEntity> testCardCategories) {
        this.testCardCategories = testCardCategories;
    }

    public Div create (){
        addCategoriesToAccordion(testCardCategories);

        categoriesAccordion.add("Panel 1", new Span("Panel content"))
                .addThemeVariants(DetailsVariant.SMALL);

        categoriesAccordion.add("Panel 2", new Span("Panel content"))
                .addThemeVariants(DetailsVariant.SMALL);

        categoriesAccordion.add("Panel 3", new Span("Panel content"))
                .addThemeVariants(DetailsVariant.SMALL);

        add(categoriesAccordion);
        return this;
    }

    private void addCategoriesToAccordion(LinkedHashSet<ParameterCategoryEntity> categories){
        if(categories != null){
            categories
                    .forEach(category -> {
                        this.categoriesAccordion.add(category.getParameterCategoryName(), new TextField(String.valueOf(category.getId())));
                    });
            this.categoriesAccordion.add("new category", addCreateNewParameterButton());
        }
    }

    private Button addCreateNewParameterCategoryButton(){
        Button createNewParameterCategoryButton = new Button("Create new parameter category");
        createNewParameterCategoryButton.addClickListener(buttonClickEvent -> {

        });
        return createNewParameterCategoryButton;
    }

    private Button addCreateNewParameterButton(){
        Button createNewParameterButton = new Button("Create new parameter");
        createNewParameterButton.addClickListener(buttonClickEvent -> {

        });
        return createNewParameterButton;
    }
}
