package com.telek.telekutils.plain;


import com.telek.telekmath.utils.TelekMathException;

import java.lang.reflect.Field;

public class TClassUtils {

    /* No Constructor */
    private TClassUtils(){}

    public static int getFieldCount(Class<?> clazz){
        return clazz.getFields().length;
    }

    public static <T extends Enum> int getEnumConstantCount(Class<T> enumClass){
        return enumClass.getEnumConstants().length;
    }



    /**
     * Same as writing array[i] but for a generic array. (T[] array) <br>
     * <b>WARNING:</b> the parameter `field` must extend Number otherwise you will get an Exception
     * @param array any array
     * @param field a field that extends {@link Number}
     * @param index any integer
     * @param <T> any class
     * @return arr[i] for a generic array
     * @throws IllegalAccessException
     */
    public static <T> double getValue(T[] array, Field field, int index) throws IllegalAccessException {
        T currentTerm = array[index]; // get the xxx object
        Number value = (Number) field.get(currentTerm); // get that object's field's value (assuming that it extends Number)
        return value.doubleValue(); // return that Number's value as a double
    }


    public static <T> Field getField(Class<T> clazz, String fieldStr) {
        try{
            return clazz.getDeclaredField(fieldStr);
        }
        catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        catch (SecurityException e) {
            e.printStackTrace();
        }
        throw new TelekMathException.ThisLineIsNeverExecutedException();
    }


}
