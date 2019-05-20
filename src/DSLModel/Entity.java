package DSLModel;

import org.w3c.dom.*;
import java.util.ArrayList;

public class Entity
{
    ArrayList<Property> property = new ArrayList<>();
    ArrayList<Field> fields = new ArrayList<>();

    //通过 DOM 构造对象
    public Entity(Element root)
    {
        NodeList nodes = root.getChildNodes();
        for(int i = 0;i < nodes.getLength();i++)
        {
            Node child0 = nodes.item(i);
            if(!(child0 instanceof Element)) continue;

            Element x = (Element) child0;
            if(x.getNodeName().equals("EntityFieldCollection"))
            {
                //更新所有 Field
                NodeList fieldNodes = x.getChildNodes();
                for(int j = 0;j < fieldNodes.getLength();j++)
                {
                    Node child1 = fieldNodes.item(j);
                    if(!(child1 instanceof Element)) continue;

                    Element y = (Element) child1;
                    //根据 EntityFieldCollection 下层的 EntityField 创建一个个的 Field 并更新到 ArrayList
                    if(y.getNodeName().equals("EntityField"))
                    {
                        //递归调用 Field 的构造方法更新
                        fields.add(new Field(y));
                    }
                    else throw new RuntimeException();
                }
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
}