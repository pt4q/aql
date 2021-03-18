package com.example.application.views.test_card.creator;

import com.example.application.data.entity.test_card_associated.test_card_part.TestCardPartEntity;
import com.vaadin.flow.component.html.Div;
import lombok.Data;

@Data
class TestCardPartDiv extends Div {

    private TestCardPartEntity testCardPartEntity;

    public TestCardPartDiv(TestCardPartEntity testCardPartEntity) {
        this.testCardPartEntity = testCardPartEntity;
    }
}
