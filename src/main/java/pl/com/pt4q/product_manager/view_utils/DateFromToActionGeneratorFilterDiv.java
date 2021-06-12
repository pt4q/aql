package pl.com.pt4q.product_manager.view_utils;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import lombok.Getter;

@Getter
public class DateFromToActionGeneratorFilterDiv extends Div {

    private DatePicker dateFromDatePicker;
    private DatePicker dateToDatePicker;
    private Button actionButton;

    public DateFromToActionGeneratorFilterDiv(String dateFromLabel, String dateToLabel, String actionButtonLabel) {
        this.dateFromDatePicker = new DatePicker(dateFromLabel);
        this.dateToDatePicker = new DatePicker(dateToLabel);
        this.actionButton = new Button(actionButtonLabel);

        HorizontalLayout hl = new HorizontalLayout(this.dateFromDatePicker, this.dateToDatePicker, this.actionButton);
        hl.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.BASELINE);
        hl.setWidthFull();

        hl.setWidthFull();
        add(hl);
    }
}
