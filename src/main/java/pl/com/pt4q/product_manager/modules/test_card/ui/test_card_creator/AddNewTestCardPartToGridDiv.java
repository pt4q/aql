package pl.com.pt4q.product_manager.modules.test_card.ui.test_card_creator;

import pl.com.pt4q.product_manager.modules.test_card.data.test_card.TestCardEntity;
import pl.com.pt4q.product_manager.modules.test_card.data.test_card_part.TestCardPartEntity;
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
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

class AddNewTestCardPartToGridDiv extends Div {

    private Label testCardPartToAddLabel = new Label("add test card part");
    private ComboBox testCardPartToAddComboBox = new ComboBox();
    private Button addNewTestCardPartButton = new Button(new Icon(VaadinIcon.PLUS_CIRCLE_O));

    private Grid<TestCardPartEntity> testCardPartEntityGrid;

    private TestCardEntity testCardEntity;
    private List<TestCardPartEntity> testCardPartSet;

    public AddNewTestCardPartToGridDiv(TestCardEntity testCardEntity, Grid<TestCardPartEntity> testCardPartEntityGrid) {
        this.testCardEntity = testCardEntity;
        this.testCardPartEntityGrid = testCardPartEntityGrid;

        this.testCardPartSet = initTestCardPartsSet();
        initAddNewTestCardPartButton();

        HorizontalLayout layout = new HorizontalLayout(testCardPartToAddLabel, testCardPartToAddComboBox, addNewTestCardPartButton);
        layout.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);

        add(layout);
    }

    private List<TestCardPartEntity> initTestCardPartsSet() {
        List<TestCardPartEntity> testCardParts = this.testCardEntity.getTestCardParts();
        return testCardParts != null ? testCardParts : new LinkedList<>();
    }

    private void initAddNewTestCardPartButton() {
        this.addNewTestCardPartButton.addClickListener(e -> {
            TestCardPartEntity testCardPartEntity = TestCardPartEntity
                    .builder()
                    .testCard(testCardEntity)
                    .testCardPartName("new test card part name")
                    .build();

            this.testCardPartSet.add(testCardPartEntity);
            refreshGrid(this.testCardPartSet);
        });
    }

    public void refreshGrid(List<TestCardPartEntity> testCardParts) {
        this.testCardPartEntityGrid.select(null);
        this.testCardPartEntityGrid.setItems(testCardParts);
    }
}
