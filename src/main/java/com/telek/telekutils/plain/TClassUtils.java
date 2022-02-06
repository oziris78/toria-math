package com.telek.telekutils.plain;


public class TClassUtils {


    public static int getFieldCount(Class<?> clazz){
        return clazz.getFields().length;
    }

    public static <T extends Enum> int getEnumConstantCount(Class<T> enumClass){
        return enumClass.getEnumConstants().length;
    }


}
