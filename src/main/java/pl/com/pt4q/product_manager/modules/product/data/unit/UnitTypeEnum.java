package pl.com.pt4q.product_manager.modules.product.data.unit;

import java.util.ArrayList;
import java.util.List;

public enum UnitTypeEnum {

    TEXT("text"),
    DECIMAL("decimal"),
    FLOAT("float");

    private String type;

    UnitTypeEnum(String type) {
        this.type = type;
    }

    public List<String> toList() {
        return new ArrayList<String>() {{
            add(TEXT.type);
            add(DECIMAL.type);
            add(FLOAT.type);
        }};
    }
}
