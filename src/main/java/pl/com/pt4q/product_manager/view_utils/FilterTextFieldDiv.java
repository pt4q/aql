package pl.com.pt4q.product_manager.view_utils;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import lombok.Getter;

@Getter
public class FilterTextFieldDiv extends Div {

    private TextField filerTextField;

    public FilterTextFieldDiv(String filterLabel) {
        this.filerTextField = new TextField(filterLabel);
        initFilter();
        add(filerTextField);
    }

    private void initFilter() {
        this.filerTextField.setAutofocus(true);
        this.filerTextField.setAutoselect(true);
        this.filerTextField.setRequired(true);
        this.filerTextField.setAutocorrect(true);
        this.filerTextField.setValueChangeMode(ValueChangeMode.LAZY);
        this.filerTextField.setValueChangeTimeout(1000);
    }
}
