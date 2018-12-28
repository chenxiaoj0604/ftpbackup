package com.backup.enums;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by sunshuyang on 2018/8/10.
 * 任务状态
 */
public enum Rwzt {

    KQ("1","开启"),TY("0","停用");
    private String index;
    private String name;

    Rwzt(String index, String name){
        this.index = index;
        this.name = name;
    }

    public static Map<String,String> getValueMap() {
        Map<String,String> enumMap = new LinkedHashMap<>();
        for (Rwzt rwzt : Rwzt.values()){
            enumMap.put(rwzt.index,rwzt.name);
        }
        return enumMap;
    }

    public String getIndex() {
        return index;
    }
}
