package pl.com.pt4q.product_manager.modules.environment.data.data_utils;

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
