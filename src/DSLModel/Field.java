package DSLModel;

import type.CommonType;
import type.MyDouble;
import type.MyString;

import java.util.ArrayList;

public class Field
{
    ArrayList<Property> property;
    type.Type type;

    //读入信息 构造对象
    public Field(Read read)
    {
        String s = new String();
        while(true)
        {
            read.toNextLeft();
            s = read.toNextRight();
            if (s.charAt(0) == '/') break;

            if(s.equals("FieldType"))
            {
                //创建对应类型的 Type 对象
                String s2 = read.toNextLeft();
                read.toNextRight();
                type = getTypeObject(s2);
            }
            else if(s.equals("FieldConstraint"))
            {
                //递归调用对应 Type 对象的构造方法
                type.updateFromRead(read);
            }
            else
            {
                //更新普通的属性 property
                String s2=read.toNextLeft();
                read.toNextRight();
                property.add(new Property(s,s2));
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
            case "double":
                return new MyDouble();
            case "DateTime":
                return new CommonType("Date");
            case "int":
                return new CommonType("int");
            default:
                return null;
        }
    }
}
