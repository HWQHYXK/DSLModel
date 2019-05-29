package type;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
            Class List = Class.forName("java.util.ArrayList");

            typeHashMap.put("string", MyString);
            typeHashMap.put("double", MyDouble);
            typeHashMap.put("bool", Boolean);
            typeHashMap.put("int", Integer);
            typeHashMap.put("DateTime", Date);
            typeHashMap.put("List", List);
        }
        catch (ClassNotFoundException e)
        {

        }
    }
    public Object createType(String s)
    {
        System.out.println(s);

        //去除<>中的内容 统一以泛型代替返回

        List left = new ArrayList();
        for(int i = 0;i < s.length();i++)
        {
            if(s.charAt(i) == '<') left.add(i);
            else if(s.charAt(i) == '>')
            {
                if(left.isEmpty()) throw new RuntimeException();

                String newString = new String();
                if((int)left.get(left.size() - 1) > 0)
                    newString=s.substring(0,(int)left.get(left.size() - 1));
                if(i < s.length()-1)
                    newString+=s.substring(i+1);

                i = (int)left.get(left.size() - 1);
                s = newString;
            }
        }

        //删除<>内容为空 表示本身所给为泛型
        if(s.isEmpty()) return null;

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
