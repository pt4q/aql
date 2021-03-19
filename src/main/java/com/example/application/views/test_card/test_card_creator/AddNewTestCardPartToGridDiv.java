package com.example.application.views.test_card.test_card_creator;

import com.example.application.data.entity.test_card_associated.test_card.TestCardEntity;
import com.example.application.data.entity.test_card_associated.test_card_part.TestCardPartEntity;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

import java.util.LinkedHashSet;
import java.util.Set;

class AddNewTestCardPartToGridDiv extends Div {

    private Label testCardPartToAddLabel = new Label("add test card part");
    private ComboBox testCardPartToAddComboBox = new ComboBox();
    private Button addNewTestCardPartButton = new Button(new Icon(VaadinIcon.PLUS_CIRCLE_O));

    private Grid<TestCardPartEntity> testCardPartEntityGrid;

    private TestCardEntity testCardEntity;

    public AddNewTestCardPartToGridDiv(TestCardEntity testCardEntity, Grid<TestCardPartEntity> testCardPartEntityGrid) {
        this.testCardEntity = testCardEntity;
        this.testCardPartEntityGrid = testCardPartEntityGrid;

        initAddNewTestCardPartButton();

        HorizontalLayout layout = new HorizontalLayout(testCardPartToAddLabel, testCardPartToAddComboBox, addNewTestCardPartButton);
        layout.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);

        add(layout);
    }

    private void initAddNewTestCardPartButton() {
        this.addNewTestCardPartButton.addClickListener(e -> {
            Set<TestCardPartEntity> testCardPartSet = this.testCardEntity.getTestCardParts();

            if (testCardPartSet == null)
                testCardPartSet = new LinkedHashSet<>();

            String testCardPartName = "new test card part name";
            TestCardPartEntity testCardPartEntity = TestCardPartEntity
                    .builder()
                    .testCard(testCardEntity)
                    .testCardPartName(testCardPartName)
                    .build();

            testCardPartSet.add(testCardPartEntity);
//            this.testCardEntity.setTestCardParts(testCardPartSet);

            refreshGrid(testCardPartSet);
        });
    }

    public void refreshGrid(Set<TestCardPartEntity> testCardParts) {
        this.testCardPartEntityGrid.select(null);
        this.testCardPartEntityGrid.setItems(testCardParts);
    }
}
