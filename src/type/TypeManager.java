package type;


import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
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
    public <T> Object createType(String s,T... parameter)
    {
        try
        {
            System.out.println(s);

            Class class0 = typeHashMap.get(s);
            Class classes[] = new Class[parameter.length];
            for(int i = 0;i < parameter.length;i++)
                classes[i] = parameter[i].getClass();

            Constructor constructors[] = class0.getConstructors();
            for(int i = 0;i < constructors.length;i++)
            {
                if(classes.length != constructors[i].getParameterCount()) continue;

                boolean match = true;
                Class[] parameterTypes = constructors[i].getParameterTypes();
                for(int j = 0;match && j < parameterTypes.length;j++)
                    match = (classes[j] != parameterTypes[j]);

                if(match)
                    return constructors[i].newInstance(parameter);
            }

            //throw new RuntimeException();
            return null;
        }
        catch (IllegalAccessException e)
        {

        }
        catch (InstantiationException e)
        {

        }
        catch (InvocationTargetException e)
        {

        }
        return null;
    }
}
