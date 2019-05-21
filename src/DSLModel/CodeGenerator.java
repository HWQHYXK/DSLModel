package DSLModel;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystemException;
import java.nio.file.NotDirectoryException;
import java.util.ArrayList;
import java.util.Scanner;

public class CodeGenerator
{
    private File outputDir;
    private Entity model;
    private Scanner scanner = new Scanner(System.in);
    private ArrayList<Class> classes = new ArrayList<>();
    private AnalogInput analog;

    public CodeGenerator(File outputDir)
    {
        this.outputDir = outputDir;
        checkDirectory();
    }

    public CodeGenerator(Entity model, File outputDir)
    {
        this.model = model;
        this.outputDir = outputDir;
        checkDirectory();
    }

    public void setSource(Entity model)
    {
        this.model = model;
    }

    private void createMainClass() throws CodeGenerateException
    {
        analog.generateDescription(model.properties.get("EntityName"), model.properties.get("EntityDescription"));//generate description according to EntityNameSpace;
        analog.newLine();// Enter
        analog.generatePackage(model.properties.get("EntityNameSpace"));//generate package according to EntityNameSpace;
        analog.newLine();// Enter
        extractClasses();//create import statements
        analog.createClass(model.properties.get("EntityCode"));//generate class name according to EntityCode;
        analog.leftBrace();// {
        analog.newLine();// Enter
        analog.addIndent();// Tab
    }

    private void createDaoClass() throws CodeGenerateException
    {

    }

    private void extractClasses() throws CodeGenerateException
    {
        for(Field field : model.fields)
        {
            analog.importClass(field.type.getClass());
            analog.newLine();
        }
    }

    private void generateConstructor() throws CodeGenerateException
    {
        for(Field field : model.fields)
        {

        }
    }

    private void generateFields() throws CodeGenerateException
    {
        for(Field field : model.fields)
        {

        }
    }

    private void createFiles(File file)
    {

    }

    private void checkDirectory()
    {
        try
        {
            if(!outputDir.exists())//文件夹不存在
            {
                if(!outputDir.mkdir())throw new FileSystemException(outputDir.getName());//创建文件夹失败
            }
            else if(!outputDir.isDirectory())//文件或文件夹存在 但传入的不是文件夹
            {
                throw new NotDirectoryException(outputDir.getName());
            }
            System.out.println("Directory checked!");
        }
        catch(IOException e)
        {
            e.printStackTrace();
            System.out.print("Please Try Input Output Directory Again: ");
            this.outputDir = new File(scanner.next());
            checkDirectory();
        }
    }
}
