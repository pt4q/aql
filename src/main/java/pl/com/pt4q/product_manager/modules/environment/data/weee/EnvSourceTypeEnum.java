package pl.com.pt4q.product_manager.modules.environment.data.weee;

import lombok.Getter;

public enum EnvSourceTypeEnum {

    NON_EU(1),
    EU(2);

    @Getter
    private Integer source;

    EnvSourceTypeEnum(Integer source) {
        this.source = source;
    }
}
