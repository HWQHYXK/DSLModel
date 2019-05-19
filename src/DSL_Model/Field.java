package DSL_Model;

import type.CommonType;
import type.MyString;

import java.util.ArrayList;

public class Field
{
    ArrayList<Property> property;
    type.Type type;

    //读入信息 构造对象
    public Field(Read read)
    {
        String s=new String();
        while(true)
        {
            read.toNextLeft();
            s = read.toNextRight();
            if (s.charAt(0) == '/') break;

            if(s.equals("FieldType"))
            {
                String s2=read.toNextLeft();
                read.toNextRight();

            }
        }
    }

    //通过字符串s 创建对应的 Type 对象
    private type.Type getTypeObject(String s)
    {
        switch(s)
        {
            case "string":
                return new MyString();
            case "bool":
                return new CommonType("boolean");
            default:
                return null;
        }
    }
}
