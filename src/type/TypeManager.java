package type;


import java.lang.reflect.Constructor;
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
    public <T> Object createType(String s,T... parameter)
    {
        try
        {
            System.out.println(s);
            Class class0 = typeHashMap.get(s);
            /*
            for(int i = 0;i < parameter.length;i++)
            {
                parameter[i].getClass();
            }
            Constructor constructor = class0.getConstructor();
            */
            return constructor.newInstance();
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
