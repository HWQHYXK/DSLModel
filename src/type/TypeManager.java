package type;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class TypeManager
{
    private HashMap<Class,Class> baseTypeMap;
    private HashMap<String,Class> typeHashMap;
    public TypeManager()
    {
        baseTypeMap = new HashMap<>();
        baseTypeMap.put(Integer.class,int.class);
        baseTypeMap.put(Short.class,short.class);
        baseTypeMap.put(Long.class,long.class);
        baseTypeMap.put(Character.class,char.class);
        baseTypeMap.put(Double.class,double.class);
        baseTypeMap.put(Float.class,float.class);
        baseTypeMap.put(Boolean.class,boolean.class);

        typeHashMap = new HashMap<>();
        typeHashMap.put("string", MyString.class);
        typeHashMap.put("double", MyDouble.class);
        typeHashMap.put("bool", Boolean.class);
        typeHashMap.put("int", Integer.class);
        typeHashMap.put("DateTime", Date.class);
        typeHashMap.put("List", ArrayList.class);
    }
    public void addUserDefinedClassMap(String typeNameInXML, Class clazz)
    {
        typeHashMap.put(typeNameInXML, clazz);
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
            //TODO
            Constructor constructor = class0.getDeclaredConstructor();
            constructor.setAccessible(true);
            ret = constructor.newInstance();
        }
        catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) { }

        if(ret == null)
        {
            try
            {
                Constructor constructor0 = class0.getConstructor(baseTypeMap.get(class0));
                if(class0.equals(Boolean.class))
                    ret = constructor0.newInstance(false);
                else
                    ret = constructor0.newInstance('0');
            }
            catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e)
            {
                e.printStackTrace();
            }
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
