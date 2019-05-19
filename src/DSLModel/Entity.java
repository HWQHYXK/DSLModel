package DSLModel;

import java.util.ArrayList;

public class Entity
{
    ArrayList<Property> property;
    ArrayList<Field> fields;

    //读入信息 构造对象
    public Entity(Read read)
    {
        String s = new String();
        while(true)
        {
            read.toNextLeft();
            s = read.toNextRight();
            if(s.charAt(0) == '/') break;

            if(s.equals("EntityFieldCollection"))
            {
                //更新所有 Field
                while(true)
                {
                    read.toNextLeft();
                    s = read.toNextRight();
                    if(s.charAt(0) == '/') break;

                    //递归调用 Field 的构造方法更新
                    fields.add(new Field(read));
                }
            }
            else
            {
                //更新普通的属性 property
                String s2 = read.toNextLeft();
                read.toNextRight();
                property.add(new Property(s,s2));
            }
        }
    }
}