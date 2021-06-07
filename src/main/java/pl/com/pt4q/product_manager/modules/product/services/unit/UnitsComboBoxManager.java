package pl.com.pt4q.product_manager.modules.product.services.unit;

import lombok.Getter;
import lombok.Setter;
import pl.com.pt4q.product_manager.modules.product.data.unit.UnitEntity;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UnitsComboBoxManager {

    @Getter @Setter
    private List<UnitEntity> units;
    @Getter
    private String comboBoxStringFormat = "%s";

    public Optional<UnitEntity> getByName(String name){
        return units.stream()
                .filter(unit ->unit.getUnits().equals(name))
                .findFirst();
    }

    public List<String> getAllUnitFormattedStrings(){
        return this.units.stream()
                .map(unitEntity -> String.format(this.comboBoxStringFormat, unitEntity.getUnits()))
                .collect(Collectors.toList());
    }

}
