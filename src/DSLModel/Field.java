package DSLModel;

import type.CommonType;
import type.MyDouble;
import type.MyString;

import org.w3c.dom.*;
import java.util.ArrayList;

public class Field
{
    ArrayList<Property> property =new ArrayList<>();
    type.Type type;

    //通过 DOM 构造对象
    public Field(Element root)
    {
        NodeList nodes = root.getChildNodes();
        //先确定 type
        for(int i = 0;i < nodes.getLength();i++)
        {
            Node child = nodes.item(i);
            if (!(child instanceof Element)) continue;

            Element x = (Element) child;
            if(x.getNodeName().equals("FieldType"))
            {
                String content = x.getFirstChild().getNodeValue();
                type = getTypeObject(content);
            }
        }
        //再更新其他属性
        for(int i = 0;i < nodes.getLength();i++)
        {
            Node child0 = nodes.item(i);
            if (!(child0 instanceof Element)) continue;

            Element x = (Element) child0;
            if(x.getNodeName().equals("FieldType")) continue;//type 已经更新过了

            if(x.getNodeName().equals("FieldConstraint"))
            {
                //递归调用对应 Type 对象的方法
                type.updateFromRead(x);
            }
            else
            {
                //更新普通的属性 property
                String name = x.getNodeName();
                String content = x.getFirstChild().getNodeValue();
                property.add(new Property(name,content));
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
