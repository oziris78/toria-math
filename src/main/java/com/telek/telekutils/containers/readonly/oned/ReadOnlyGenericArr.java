package com.telek.telekutils.containers.readonly.oned;


import com.telek.telekmath.utils.TelekMathException;
import com.telek.telekutils.plain.TClassUtils;

import java.lang.reflect.Field;

public class ReadOnlyGenericArr<T> implements TypelessArray {

    private T[] array;
    private Field field;

    public ReadOnlyGenericArr(T[] array, Field field){
        this.array = array;
        this.field = field;
    }

    @Override
    public double getValue(int index) {
        try{
            return TClassUtils.getValue(array, field, index);
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        throw new TelekMathException.ThisLineIsNeverExecutedException();
    }

    @Override
    public int getSize() {
        return array.length;
    }

}
