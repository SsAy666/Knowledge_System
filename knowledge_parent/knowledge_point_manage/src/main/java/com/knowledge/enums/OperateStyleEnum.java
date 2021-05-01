package com.knowledge.enums;

import java.util.ArrayList;
import java.util.List;

/**
 *  操作方式枚举类
 */
public enum OperateStyleEnum {
    ADD(1,"新增"),
    EDIT(2,"编辑"),
    DELETE(3,"删除");

    OperateStyleEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    private static List<Integer> values = new ArrayList<>(16);

    static {
        for (OperateStyleEnum v : OperateStyleEnum.values()) {
            values.add(v.getCode());
        }
    }

    private Integer code;
    private String name;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static String getDesc(Integer code) {
        for (OperateStyleEnum constEnum : OperateStyleEnum.values()) {
            if (constEnum.getCode().equals(code)) {
                return constEnum.getName();
            }
        }
        return null;
    }

    /**
     * 判断是否在这范围内
     */
    public static boolean contain(Integer code) {
        return values.contains(code);
    }
}
