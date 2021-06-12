package pl.com.pt4q.product_manager.modules.product.data.unit;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UnitTypeEnumWrapper {

    private Map<String, UnitTypeEnum> unitTypes;

    public UnitTypeEnumWrapper() {
        unitTypes = new HashMap<>();

        unitTypes.put("text", UnitTypeEnum.TEXT);
        unitTypes.put("decimal", UnitTypeEnum.DECIMAL);
        unitTypes.put("float", UnitTypeEnum.FLOAT);
    }

    public UnitTypeEnum getUnitTypeFromString(String unitTypeString) {
        return unitTypes.get(unitTypeString);
    }

    public List<String> getUnitsStringsForComboBox() {
        return unitTypes.keySet()
                .stream()
                .collect(Collectors.toList());
    }
}
