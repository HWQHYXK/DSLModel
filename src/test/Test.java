package test;

import DSLModel.AnalogInput;
import type.CommonType;

import java.io.File;
import java.io.IOException;

public class Test
{
    public static void main(String[] args) throws IOException
    {
        Order order = new Order("123456");
        System.out.println(hwq.hh.getValue()+hwq.www.getValue());
        AnalogInput analog = new AnalogInput(new File("hello.java"));
        analog.importClass(CommonType.class);
        analog.newLine();
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
