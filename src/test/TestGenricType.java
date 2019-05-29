package test;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;


public class TestGenricType {

    public static void main(String[] args) {

        Class<person> dtoClass = person.class;
        Field[]  fields = dtoClass.getDeclaredFields();
        for (Field field : fields ) {
            judgeFieldType(field);
        }
    }

    private static void judgeFieldType(Field field) {
        String name = field.getName();

        if (field.getGenericType() instanceof ParameterizedType) {
            ParameterizedType pt = (ParameterizedType) field.getGenericType();

            //判断具体类的类型

            if (pt.getRawType().equals(List.class)) {

                // 判断泛型类的类型
                if (pt.getActualTypeArguments()[0].equals(String.class)) {
                    System.out.println(name + " is List<String>;");
                } else if (pt.getActualTypeArguments()[0].equals(int.class)
                        || pt.getActualTypeArguments()[0].equals(Integer.class)) {
                    System.out.println(name + " is List<int>;");
                }
            }
        }else if (field.getGenericType().equals(String.class)) {
            System.out.println(name + " is String;");
        } else if (field.getGenericType().equals(int.class) || field.getGenericType().equals(Integer.class)) {
            System.out.println(name + " is int;");
        }
    }

}

class person
{

    public String name;
    public int age;
    public List<String> cord;
    public List<Integer> meter;
}
