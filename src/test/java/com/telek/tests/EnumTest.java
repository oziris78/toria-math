package com.telek.tests;

import com.telek.telekutils.plain.TClassUtils;

public class EnumTest {

    enum MyEnum{
        BRUH(100),
        MOMENT(200);


        MyEnum(double x){
            this.x = x;
        }

        class a{}
        class b{}

        interface c{}

        abstract class d{}

        double x = -999;
        double x2 = 0;
        double x3 = 0;
        double x4 = 0;
        double x5 = 0;
        public void f(){ System.out.println(this.x);}
    }


    public static void main(String[] args) {

        MyEnum e = MyEnum.BRUH;
        e.f();

        System.out.println(TClassUtils.getEnumConstantCount(MyEnum.class));
        System.out.println(TClassUtils.getFieldCount(MyEnum.class));
    }

}
