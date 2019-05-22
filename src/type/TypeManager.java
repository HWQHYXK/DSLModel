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
            Class Boolean = Class.forName("java.lang.Boolean");
            Class Integer = Class.forName("java.lang.Integer");
            Class Date = Class.forName("java.util.Date");

            typeHashMap.put("string", MyString);
            typeHashMap.put("double", MyDouble);
            typeHashMap.put("bool", Boolean);
            typeHashMap.put("int", Integer);
            typeHashMap.put("DateTime", Date);
        }
        catch (ClassNotFoundException e)
        {

        }
    }
    public Object createType(String s)
    {
        System.out.println(s);

        Object ret = null;
        Class class0 = typeHashMap.get(s);
        try
        {
            ret = class0.newInstance();
        }
        catch (InstantiationException | IllegalAccessException e) { }

        if(ret == null)
        {
            try
            {
                Constructor constructor0 = class0.getConstructor(new String().getClass());
                ret = constructor0.newInstance("0");
            }
            catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) { }
        }

        return ret;
    }

    public <T> Object createType(String s,T... parameter)
    {
        try
        {

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
