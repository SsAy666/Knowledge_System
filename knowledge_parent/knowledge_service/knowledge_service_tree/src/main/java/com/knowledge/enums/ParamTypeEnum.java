package com.knowledge.enums;


/**
 * @author CuiZheng
 * @title: ParamTypeEnum
 * @description:参数类型
 * @date 2020/10/19 15:10
 */
public enum ParamTypeEnum {

    STR("str"),
    DIGIT("dig"),
    OBJ("obj"),
    ARRAY("array"),
    BOOL("bool");

    private String value;

    ParamTypeEnum(String value) {
        this.value = value;
    }

    public String value() {
        return this.value;
    }
}
