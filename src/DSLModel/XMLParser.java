package DSLModel;

import org.w3c.dom.*;
import java.io.IOException;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class XMLParser
{
    static Entity buildEntity(String url)
    {
        Entity entity = new Entity();
        try
        {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document document = dBuilder.parse(url);

            updateEntity(document.getDocumentElement(),entity);
            return entity;
        }
        catch (ParserConfigurationException e)
        {

        }
        catch (SAXException e)
        {

        }
        catch (IOException e)
        {

        }
        return null;
    }

    private static void updateEntity(Element root,Entity entity)
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
                        Field field = new Field();
                        updateField(y,field);
                        entity.fields.add(field);
                    }
                    else throw new RuntimeException();
                }
            }
            else
            {
                //更新普通的属性 properties
                String name = x.getNodeName();
                String content = x.getFirstChild().getNodeValue();
                entity.properties.put(name, content);
            }
        }
    }

    private static void updateField(Element root,Field field)
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
                field.type = new type.TypeManager().createType(content);
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
                // TODO TODO TODO TODO TODO TODO
                //递归处理 FieldConstraint
                updateType(x,field.type);
            }
            else
            {
                //更新普通的属性 properties
                String name = x.getNodeName();
                String content = x.getFirstChild().getNodeValue();
                field.properties.put(name,content);
            }
        }
    }

    private static void updateType(Element root,Object type)
    {
        //TODO TODO TODO TODO TODO TODO
    }
}
