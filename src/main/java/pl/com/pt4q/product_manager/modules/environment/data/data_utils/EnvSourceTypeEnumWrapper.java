package pl.com.pt4q.product_manager.modules.environment.data.data_utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EnvSourceTypeEnumWrapper {

    private Map<String, EnvSourceTypeEnum> sources;

    public EnvSourceTypeEnumWrapper() {
        sources = new HashMap<>();

        sources.put("Importer (non-EU)", EnvSourceTypeEnum.NON_EU);
        sources.put("Exporter (EU)", EnvSourceTypeEnum.EU);
    }

    public EnvSourceTypeEnum getUnitTypeFromString(String unitTypeString) {
        return sources.get(unitTypeString);
    }

    public List<String> getUnitsStringsForComboBox() {
        return sources.keySet()
                .stream()
                .collect(Collectors.toList());
    }
}
