package DSLModel;

import java.io.File;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Scanner;

public class Main
{
    private static CodeGenerator codeGenerator;
    public static void main(String[] args)
    {
        XMLParser xmlParser = new XMLParser();
        Entity entity = xmlParser.getRootEntiry("in/order.xml");

        System.out.print("Input Your Root Dir:");
        Scanner scanner = new Scanner(System.in);
        codeGenerator = new CodeGenerator(new File(scanner.next()));
        dfs(entity);
        codeGenerator.generateDaoCode();//generateDaoCode after all done!
    }
    private static void dfs(Entity entity)
    {
        codeGenerator.setSource(entity);
        codeGenerator.generateMainCode();
        for(Field field:entity.fields)
        {
            //judge if field.type equals to ArrayList<Entity> something.
            if(field.type instanceof ArrayList
                    &&((ParameterizedType)field.type.getClass().getGenericSuperclass()).getActualTypeArguments()[0].equals(Entity.class))
            {
                for(Entity entityInList:(ArrayList<Entity>)field.type)
                {
                    dfs(entityInList);
                }
            }
        }
    }
}
