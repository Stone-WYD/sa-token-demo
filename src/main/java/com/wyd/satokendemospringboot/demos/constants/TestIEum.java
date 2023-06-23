package com.wyd.satokendemospringboot.demos.constants;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = IEnumJsonSerializer.class)
@JsonDeserialize(using = IEnumJsonDeserializer.class)
public enum TestIEum implements IEnum {
    WYD(1, "王玉东"),
    YXY(2, "叶絮依")
    ;

    private final Integer value;
    private final String desc;

    TestIEum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    @Override
    public Integer getValue() {
        return value;
    }


    @Override
    public String toString() {
        return "TestIEum{" +
                "value=" + value +
                ", desc='" + desc + '\'' +
                '}';
    }
}
