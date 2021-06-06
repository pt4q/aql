package pl.com.pt4q.product_manager.modules.product.services.unit;

import lombok.Getter;
import lombok.Setter;
import pl.com.pt4q.product_manager.modules.product.data.unit.UnitEntity;

import java.util.List;
import java.util.stream.Collectors;

public class UnitsComboBoxManager {

    @Getter @Setter
    private List<UnitEntity> units;
    @Getter
    private String comboBoxStringFormat = "name: %s unit: %s multiplicity: %d";

    public UnitsComboBoxManager(List<UnitEntity> units) {
        this.units = units;
    }

    public UnitEntity getByName(String name){
        return null;
    }

    public List<String> getAllUnitFormattedStrings(){
        return this.units.stream()
                .map(unitEntity -> String.format(this.comboBoxStringFormat, unitEntity.getName(), unitEntity.getUnit(), unitEntity.getMultiplicity()))
                .collect(Collectors.toList());
    }

}
