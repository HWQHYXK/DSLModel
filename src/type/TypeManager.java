package type;


import java.util.HashMap;

public class TypeManager
{
    private HashMap<String,Class> typeHashMap;
    public TypeManager()
    {
        typeHashMap = new HashMap<>();
        try
        {
            Class MyString = Class.forName("type.MyString");
            Class MyDouble = Class.forName("type.MyDouble");
            Class Boolean = Class.forName("Boolean");
            Class Integer = Class.forName("Integer");


            typeHashMap.put("string", MyString);
            typeHashMap.put("double", MyDouble);
            typeHashMap.put("bool", Boolean);
            typeHashMap.put("int", Integer);
        }
        catch (ClassNotFoundException e)
        {

        }
    }
    public Class classQuery(String classname)
    {
        return typeHashMap.get(classname);
    }
    public Object createType(String s)
    {
        try
        {
            System.out.println(s);
            return typeHashMap.get(s).newInstance();
        }
        catch (IllegalAccessException e)
        {

        }
        catch (InstantiationException e)
        {

        }
        return null;
    }
}
