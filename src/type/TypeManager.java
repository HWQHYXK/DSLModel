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
        try
        {
            System.out.println(s);

            Class class0 = typeHashMap.get(s);
            if (Class.forName("java.lang.Number") == class0.getSuperclass() || class0.equals(Boolean.class))
            {
                Constructor constructor0 = class0.getConstructor(new String().getClass());
                return constructor0.newInstance("0");
            }
            return class0.newInstance();
        }
        catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException | ClassNotFoundException e)
        {

        }
        return null;
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
