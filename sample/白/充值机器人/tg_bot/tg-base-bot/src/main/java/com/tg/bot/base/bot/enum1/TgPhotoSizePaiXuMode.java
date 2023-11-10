package com.tg.bot.base.bot.enum1;

import lombok.Getter;

/**
 * tg图片排序规则
 */
@Getter
public enum TgPhotoSizePaiXuMode {
    TG_PHOTO_SIZE_PAIXU_MODE_DESC("DESC"),
    TG_PHOTO_SIZE_PAIXU_MODE_ASC("ASC"),
    ;

    private String mode;


    TgPhotoSizePaiXuMode(String mode) {
        this.mode = mode;
    }
}
