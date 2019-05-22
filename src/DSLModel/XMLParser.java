package DSLModel;

import org.w3c.dom.*;
import java.io.IOException;
import java.util.HashMap;

import org.xml.sax.SAXException;
import type.TypeManager;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class XMLParser
{
    TypeManager typeManager = new TypeManager();
    public Entity buildEntity(String url)
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

    private void updateEntity(Element root,Entity entity)
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

    private void updateField(Element root,Field field)
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
                field.type = typeManager.createType(content);
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

    private void updateType(Element root,Object type)
    {
        Class class0 = type.getClass();
        NodeList nodes = root.getChildNodes();
        if(type instanceof Integer)
        {
            //判断是什么子类型
            for(int i = 0;i < nodes.getLength();i++)
            {
                Node child0 = nodes.item(i);
                if (!(child0 instanceof Element)) continue;

                Element x = (Element) child0;
                String name = x.getNodeName();
                String content = x.getFirstChild().getNodeValue();

                if(name.equals("IsEnum") && content.equals("true"))
                {
                    //因为要 break 所以这里重复使用前面的变量没有问题
                    HashMap<Integer,String> Enum=new HashMap<>();
                    for(i = 0;i < nodes.getLength();i++)
                    {
                        child0 = nodes.item(i);
                        if (!(child0 instanceof Element)) continue;

                        x = (Element) child0;
                        if(x.getNodeName().equals("EnumCollection"))
                        {
                            int Key=-1;
                            String Value = new String();
                            NodeList fieldNodes = x.getChildNodes();
                            for(int j = 0;j < fieldNodes.getLength();j++)
                            {
                                Node child1 = fieldNodes.item(j);
                                if (!(child1 instanceof Element)) continue;

                                Element y = (Element) child1;
                                if(y.getNodeName().equals( "Key"))
                                    Key = Integer.parseInt(y.getFirstChild().getNodeValue());
                                else Value = y.getFirstChild().getNodeValue();
                            }
                            if(Key != -1 && !Value.isEmpty())
                                Enum.put(Key,Value);
                        }
                    }
                    type = Enum;
                    break;
                }

                if(name.equals("Int16") && content.equals("true"))
                {
                    type = new Short("0");
                    break;
                }
                if(name.equals("Int32") && content.equals("true"))
                {
                    type = new Integer("0");
                    break;
                }
                if(name.equals("Int64") && content.equals("true"))
                {
                    type = new Long("0");
                    break;
                }
            }
        }
        else
        {
            for(int i = 0;i < nodes.getLength();i++)
            {
                Node child0 = nodes.item(i);
                if (!(child0 instanceof Element)) continue;

                Element x = (Element) child0;
                String name = x.getNodeName();
                String content = x.getFirstChild().getNodeValue();
                try
                {
                    //通过反射机制 强行改变更新对应的属性
                    java.lang.reflect.Field tt = class0.getDeclaredField(name);
                    tt.setAccessible(true);
                    if(tt.getType() == int.class)
                        tt.setInt(type,Integer.parseInt(content));
                    else if(tt.getType() == boolean.class)
                        tt.setBoolean(type,Boolean.parseBoolean(content));
                    else if(tt.getType() == double.class)
                        tt.setDouble(type,Double.parseDouble(content));
                    else tt.set(type,content);
                }
                catch (NoSuchFieldException e)
                {

                }
                catch (IllegalAccessException e)
                {

                }
            }
        }
    }
}
