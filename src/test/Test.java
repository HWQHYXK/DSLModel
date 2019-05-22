package test;

import DSLModel.AnalogInput;
import type.CommonType;
import type.ConstructorToUse;
import type.MyString;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.HashMap;

public class Test
{
    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchMethodException, InstantiationException
    {
        Order order = new Order("123456");
        Boolean b = new Boolean("0");
        System.out.println(b);
        System.out.println(hwq.hh.getValue()+hwq.www.getValue());
//        AnalogInput analog = new AnalogInput(new File("hello.java"));
//        analog.importClass(CommonType.class);
//        analog.newLine();
//        analog.end();
        MyString myString = new MyString(6,6, true);
        myString.setValue("lalala");
        System.out.println(MyString.class.getDeclaredFields().length);
        for(Constructor constructor:myString.getClass().getConstructors())
        {
            if (constructor.getAnnotation(ConstructorToUse.class)!=null)
            {
                ConstructorToUse annotation = (ConstructorToUse) constructor.getAnnotation(ConstructorToUse.class);
                if (((ConstructorToUse) constructor.getAnnotation(ConstructorToUse.class)).doConstruct())
                {
                    for (String para : annotation.initializedVariable())
                    {
                        try
                        {
                            Field field = myString.getClass().getDeclaredField(para);
                            field.setAccessible(true);
                            System.out.println(field.get(myString));
                        } catch (IllegalAccessException | NoSuchFieldException e)
                        {
                            e.printStackTrace();
                        }

                    }
    //            System.out.println(constructor.getAnnotation(ConstructorToUse.class)!=null?((ConstructorToUse)(constructor.getAnnotation(ConstructorToUse.class))).parametersName():"");
                }
            }
        }
    }
    enum hwq
    {
        hh(10),www(1),qqq(2);

        private int value;

        hwq(int index)
        {
            value = index;
        }

        public int getValue()
        {
            return value;
        }

        @Override
        public String toString()
        {
            return ""+value;
        }
    }
}