package com.telek.telekutils.arrayref.oned;


import com.telek.telekmath.utils.TelekMathException;
import com.telek.telekutils.plain.TClassUtils;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Objects;

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

    ///////////////////////////////////////////


    @Override
    public String toString() {
        return "GenericArrRef{" +
                "array=" + Arrays.toString(array) +
                ", field=" + field +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GenericArrRef<?> that = (GenericArrRef<?>) o;
        if(!Objects.equals(field, that.field))
            return false;

        for (int i = 0; i < this.array.length; i++) {
            if(getValue(i) != that.getValue(i))
                return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(field);
        result = 31 * result + Arrays.hashCode(array);
        return result;
    }

}
