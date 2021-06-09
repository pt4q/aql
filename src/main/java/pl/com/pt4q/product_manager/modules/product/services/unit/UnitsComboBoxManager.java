package pl.com.pt4q.product_manager.modules.product.services.unit;

import lombok.Getter;
import lombok.Setter;
import pl.com.pt4q.product_manager.modules.product.data.unit.UnitEntity;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UnitsComboBoxManager {

    @Getter
    private List<UnitEntity> unitsList;
    @Getter
    private String comboBoxStringFormat = "%s";

    public UnitsComboBoxManager(List<UnitEntity> unitsList) {
        this.unitsList = unitsList;
    }

    public UnitEntity getByUnitsString(String units) {
        return this.unitsList.stream()
                .filter(unit -> unit.getUnits().equals(units))
                .findFirst()
                .orElse(null);
    }

    public List<String> getAllUnitFormattedStrings() {
        return this.unitsList.stream()
                .map(unitEntity -> String.format(this.comboBoxStringFormat, unitEntity.getUnits()))
                .collect(Collectors.toList());
    }

}
