package pl.com.pt4q.product_manager.modules.product.data.unit;

import lombok.Getter;

public enum UnitTypeEnum {

    TEXT(1),
    DECIMAL(2),
    FLOAT(3);

    @Getter
    private Integer type;

    UnitTypeEnum(Integer type) {
        this.type = type;
    }
}
