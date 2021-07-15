package pl.com.pt4q.product_manager.modules.environment.data.weee;

import java.util.*;

public class EnvSourceTypeEnumWrapper {

    private Map<EnvSourceTypeEnum, String> sources;

    public EnvSourceTypeEnumWrapper() {
        sources = new HashMap<>();

        sources.put(EnvSourceTypeEnum.NON_EU, "Importer (non-EU)");
        sources.put(EnvSourceTypeEnum.EU, "Exporter (EU)");
    }

    public EnvSourceTypeEnum getUnitTypeFromString(String unitTypeString) {
        Optional<EnvSourceTypeEnum> sourceTypeEnum = sources.entrySet()
                .stream()
                .filter(entry -> entry.getValue().equals(unitTypeString))
                .map(Map.Entry::getKey)
                .findFirst();
        return sourceTypeEnum.orElse(null);
    }

    public String getUnitTypeStringFromEnum(EnvSourceTypeEnum typeEnum){
        return sources.get(typeEnum);
    }

    public List<String> getUnitsStringsForComboBox() {
        return new ArrayList<>(sources.values());
    }
}
