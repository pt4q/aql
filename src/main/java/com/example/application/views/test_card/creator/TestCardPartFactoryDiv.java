package com.example.application.views.test_card.creator;

import com.example.application.data.entity.test_card_associated.test_card.TestCardEntity;
import com.example.application.data.entity.test_card_associated.test_card_part.TestCardPartEntity;
import com.example.application.data.entity.test_card_associated.test_card_part_parameter_category.ParameterCategoryEntity;
import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.accordion.AccordionPanel;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.details.DetailsVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import lombok.Data;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;

@Data
class TestCardPartFactoryDiv extends Div {

    private Accordion categoriesAccordion = new Accordion();
    private Label testCardPartToAddLabel = new Label("add test card part");
    private ComboBox testCardPartToAddComboBox = new ComboBox();
    private Button addNewTestCardPartButton = new Button(new Icon(VaadinIcon.PLUS_CIRCLE_O));

    private List<TestCardPartDiv> testCardPartsDivs = new LinkedList<>();
    private List<AccordionPanel> testCardPartAccordionPanels = new LinkedList<>();

    private TestCardEntity testCardEntity;
    private LinkedHashSet<ParameterCategoryEntity> testCardCategories;

    public TestCardPartFactoryDiv() {
        initAddNewTestCardPartButton();

        VerticalLayout vl = new VerticalLayout();
        HorizontalLayout addNewTestCardPartLayout = new HorizontalLayout(testCardPartToAddLabel, testCardPartToAddComboBox, addNewTestCardPartButton);
        addNewTestCardPartLayout.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);

        vl.add(categoriesAccordion, addNewTestCardPartLayout);
        add(vl);
    }

    private void initTestCardPartsAccordion() {

        categoriesAccordion.add("Panel 1", new Span("Panel content"))
                .addThemeVariants(DetailsVariant.SMALL);

        categoriesAccordion.add("Panel 2", new Span("Panel content"))
                .addThemeVariants(DetailsVariant.SMALL);

        categoriesAccordion.add("Panel 3", new Span("Panel content"))
                .addThemeVariants(DetailsVariant.SMALL);
    }

    private void initAddNewTestCardPartButton() {
        this.addNewTestCardPartButton.addClickListener(e -> {
            addNewTestCardPartToAccordion();
        });
    }

    private void addNewTestCardPartToAccordion(){
        String testCardPartName = "new test card part name";
        TestCardPartEntity testCardPartEntity = TestCardPartEntity
                .builder()
                .testCard(testCardEntity)
                .testCardPartName(testCardPartName)
                .build();
        TestCardPartDiv newTestCardPartDiv = new TestCardPartDiv(testCardPartEntity);
        this.testCardPartsDivs.add(newTestCardPartDiv);
        this.categoriesAccordion.add(newTestCardPartDiv.getTestCardPartEntity().getTestCardPartName(), newTestCardPartDiv);
    }

}
