package com.easyding.entity.enums;

import java.util.HashMap;
import java.util.Map;

public enum IndeedJobStatusEnum {

    RUNNING(1, "运行中"),
    PENDING(2, "待运行"),
    FAILED(3, "运行失败"),
    STOPPED(4, "已停止"),
    SUCCESS(5, "运行成功");

    private final Integer code;
    private final String description;

    IndeedJobStatusEnum(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    private static final Map<Integer, IndeedJobStatusEnum> IndeedJobStatusEnum_MAP = new HashMap<>();

    static {
        for (IndeedJobStatusEnum indeedJobStatusEnum : values()) {
            IndeedJobStatusEnum_MAP.put(indeedJobStatusEnum.getCode(), indeedJobStatusEnum);
        }
    }

    public static IndeedJobStatusEnum getByCode(Integer code) {
        return IndeedJobStatusEnum_MAP.get(code);
    }

    // 获取description的便捷方法
    public static String getDescriptionByCode(Integer code) {
        IndeedJobStatusEnum indeedJobStatusEnum = getByCode(code);
        return indeedJobStatusEnum != null ? indeedJobStatusEnum.getDescription() : null;
    }

}
