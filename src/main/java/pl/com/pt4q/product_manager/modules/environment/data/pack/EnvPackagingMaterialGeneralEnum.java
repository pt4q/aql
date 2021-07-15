package pl.com.pt4q.product_manager.modules.environment.data.pack;

import lombok.Getter;

public enum EnvPackagingMaterialGeneralEnum {

    CARDBOARD(1),
    PLASTIC(2);

    @Getter
    private Integer type;

    EnvPackagingMaterialGeneralEnum(Integer type) {
        this.type = type;
    }
}
