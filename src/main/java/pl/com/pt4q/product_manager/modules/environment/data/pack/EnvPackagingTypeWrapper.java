package pl.com.pt4q.product_manager.modules.environment.data.pack;

import pl.com.pt4q.product_manager.modules.environment.data.weee.EnvSourceTypeEnum;

import java.util.*;

public class EnvPackagingTypeWrapper {

    private Map<EnvPackagingTypeEnum, String> types;

    public EnvPackagingTypeWrapper() {
        types = new HashMap<>();

        types.put(EnvPackagingTypeEnum.BAG, "bag");
        types.put(EnvPackagingTypeEnum.BOX, "box");
        types.put(EnvPackagingTypeEnum.OTHER, "other");
    }

    public EnvPackagingTypeEnum getTypeOfPackagingFromString(String unitTypeString) {
        Optional<EnvPackagingTypeEnum> sourceTypeEnum = types.entrySet()
                .stream()
                .filter(entry -> entry.getValue().equals(unitTypeString))
                .map(Map.Entry::getKey)
                .findFirst();
        return sourceTypeEnum.orElse(null);
    }

    public String getTypeOfPackagingStringFromEnum(EnvSourceTypeEnum typeEnum){
        return types.get(typeEnum);
    }

    public List<String> getTypesStringsForComboBox() {
        return new ArrayList<>(types.values());
    }
}
