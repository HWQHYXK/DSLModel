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
        Scanner scanner = new Scanner(System.in);
        XMLParser xmlParser = new XMLParser();
        System.out.print("Input Your Input File:");
        Entity entity = xmlParser.getRootEntity(scanner.next());

        System.out.print("Input Your Root Dir:");
        codeGenerator = new CodeGenerator(new File(scanner.next()));
        dfs(entity);
    }
    private static void dfs(Entity entity)
    {
        codeGenerator.setSource(entity);
        codeGenerator.addTable();
        codeGenerator.generateMainCode();
        codeGenerator.generateDaoCode();//generateDaoCode after all done!
        for(Field field:entity.fields)
        {
            //judge if field.type equals to ArrayList<Entity> something.
            if(field.type instanceof ArrayList
                    && !((ArrayList) field.type).isEmpty()
                    &&((ArrayList)field.type).get(0) instanceof Entity)
            {
                //push entityInList's father which is also entity into the stack
                codeGenerator.entityTree.push(entity);
                dfs(((ArrayList<Entity>) field.type).get(0));
            }
            else if(field.type instanceof Entity)
            {
                codeGenerator.entityTree.push(entity);
                dfs((Entity) field.type);
            }
        }
    }
}
