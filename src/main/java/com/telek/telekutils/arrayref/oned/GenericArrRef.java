package com.telek.telekutils.arrayref.oned;


import com.telek.telekmath.utils.TelekMathException;
import com.telek.telekutils.plain.TClassUtils;

import java.lang.reflect.Field;

public class GenericArrRef<T> implements ArrayRef {

    private T[] array;
    private Field field;

    public GenericArrRef(T[] array, Field field){
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
