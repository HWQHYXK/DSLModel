package DSLModel;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class DSLModelGenerator
{
    private CodeGenerator codeGenerator;
    private XMLParser xmlParser;
    public static void main(String[] args)
    {
        DSLModelGenerator DSLModelGenerator = new DSLModelGenerator();
        DSLModelGenerator.addUserDefinedMAP("DemoType", type.DemoType.class, "VARCHAR(10)");
        DSLModelGenerator.work();
    }
    public DSLModelGenerator()
    {
        xmlParser = new XMLParser();
        codeGenerator = new CodeGenerator();
    }
    public void work()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Input Your Input File:");
        Entity entity = xmlParser.getRootEntity(scanner.next());

        System.out.print("Input Your Root Dir:");
        codeGenerator.setOutputDir(new File(scanner.next()));
        dfs(entity);
    }
    public void addUserDefinedMAP(String typeNameInXML, Class classNameInJava, String typeNameInSQL)
    {
        xmlParser.typeManager.addUserDefinedClassMap(typeNameInXML, classNameInJava);
        codeGenerator.addSQLMap(classNameInJava, typeNameInSQL);
    }
    private void dfs(Entity entity)
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
