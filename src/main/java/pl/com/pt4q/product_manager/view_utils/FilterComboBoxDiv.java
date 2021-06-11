package pl.com.pt4q.product_manager.view_utils;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Div;
import lombok.Getter;

@Getter
public class FilterComboBoxDiv extends Div {

    private ComboBox<String> filterComboBox;

    public FilterComboBoxDiv(String filterLabel) {
       this.filterComboBox = new ComboBox<>(filterLabel);
       initFilter();
       add(filterComboBox);
    }

    private void initFilter(){
        this.filterComboBox.setAutofocus(true);
    }
}
