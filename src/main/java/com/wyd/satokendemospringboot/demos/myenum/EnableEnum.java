package com.wyd.satokendemospringboot.demos.myenum;

public enum EnableEnum {

    ENABLE_ENUM(1, "可用"),
    BAN_ENUM(0, "禁用");

    private Integer code;

    private String name;

    EnableEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
