package pl.com.pt4q.product_manager.modules.environment.data.pack;

import lombok.Getter;

public enum EnvPackagingTypeEnum {

    BAG(1),
    BOX(2),
    OTHER(3);

    @Getter
    private Integer type;

    EnvPackagingTypeEnum(Integer type) {
        this.type = type;
    }
}
