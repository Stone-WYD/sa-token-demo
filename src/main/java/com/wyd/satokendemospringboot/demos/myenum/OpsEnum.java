package com.wyd.satokendemospringboot.demos.myenum;

public enum OpsEnum {

    CREATE("c", "create"),
    READ("r","read"),
    UPDATE("u", "update"),
    DELETE("d", "delete"),
    ALL("all", "*");

    private String code;

    private String name;

    OpsEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
