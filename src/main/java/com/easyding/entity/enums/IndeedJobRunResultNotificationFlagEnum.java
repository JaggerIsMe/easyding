package com.easyding.entity.enums;

import java.util.HashMap;
import java.util.Map;

public enum IndeedJobRunResultNotificationFlagEnum {

    UN_NOTIFIED(0, "未通知"),
    NOTIFIED(1, "已通知");

    private final Integer code;
    private final String description;

    IndeedJobRunResultNotificationFlagEnum(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    private static final Map<Integer, IndeedJobRunResultNotificationFlagEnum> IndeedJobRunResultNotificationFlagEnum_MAP = new HashMap<>();

    static {
        for (IndeedJobRunResultNotificationFlagEnum indeedJobRunResultNotificationFlagEnum : values()) {
            IndeedJobRunResultNotificationFlagEnum_MAP.put(indeedJobRunResultNotificationFlagEnum.getCode(), indeedJobRunResultNotificationFlagEnum);
        }
    }

    public static IndeedJobRunResultNotificationFlagEnum getByCode(Integer code) {
        return IndeedJobRunResultNotificationFlagEnum_MAP.get(code);
    }

    // 获取description的便捷方法
    public static String getDescriptionByCode(Integer code) {
        IndeedJobRunResultNotificationFlagEnum indeedJobRunResultNotificationFlagEnum = getByCode(code);
        return indeedJobRunResultNotificationFlagEnum != null ? indeedJobRunResultNotificationFlagEnum.getDescription() : null;
    }

}
