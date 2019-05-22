package DSLModel;

import type.ConstructorToUse;
import type.MyString;
import type.TypeManager;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.AnnotationFormatError;
import java.lang.reflect.Constructor;
import java.nio.file.FileSystemException;
import java.nio.file.NotDirectoryException;
import java.util.ArrayList;
import java.util.HashMap;
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

    public void generateContents()
    {
        //Create Files
        try
        {
            createMainClass(createFiles(model.properties.get("EntityCode")+".java"));//generate $EntityCode.java
        }catch (CodeGenerateException e)
        {
            e.printStackTrace();
        }
        createFiles(model.properties.get("EntityCode")+"Dao.java");//generate $EntityCode.java
    }

    public void setSource(Entity model)
    {
        this.model = model;
    }

    private void createMainClass(File file) throws CodeGenerateException
    {
        analog = new AnalogInput(file);
        analog.generateDescription(model.properties.get("EntityName"), model.properties.get("EntityDescription"));//generate description according to EntityNameSpace;
        analog.newLine();// Enter
        analog.newLine();// Enter
        analog.generatePackage(model.properties.get("EntityNameSpace"));//generate package according to EntityNameSpace;
        analog.newLine();// Enter
        analog.newLine();// Enter
        extractClasses();//create import statements
        analog.newLine();// Enter
        analog.createClass(model.properties.get("EntityCode"));//generate class name according to EntityCode;
        analog.newLine();// Enter
        analog.leftBrace();// {
        analog.newLine();// Enter
        analog.addIndent();// Tab
        generateFields();// generate fields
        analog.newLine();
        generateConstructor();// generate Constructor
        analog.revertIndent();
        analog.newLine();// Enter
        analog.rightBrace();
        analog.end();
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
        StringBuilder paras = new StringBuilder();// use StringBuilder to enhance efficiency
        for(Field field : model.fields)
        {
            if(field.type instanceof MyString)
                if(!((MyString)field.type).isEmpty())
                {
                    paras.append("MyString ").append(field.properties.get("FieldCode")).append(", ");
                }
        }
        paras.delete(paras.length()-2, paras.length());
        System.out.println(paras);
        analog.generateConstructor(model.properties.get("EntityCode"), paras.toString());
    }

    private void generateFields() throws CodeGenerateException
    {
        for(Field field : model.fields)
        {
            boolean found = false;//whether find annotation or not
            if(field.type instanceof HashMap)
                continue;
            /*customized user Class, use Java Reflect mechanism*/
            else for(Constructor constructor:field.type.getClass().getConstructors())
            {
                if(constructor.getAnnotation(ConstructorToUse.class)!=null)
                {
                    found = true;
                    ConstructorToUse annotation = (ConstructorToUse) constructor.getAnnotation(ConstructorToUse.class);
                    if(((ConstructorToUse)constructor.getAnnotation(ConstructorToUse.class)).doConstruct())//if field needs to construct itself.
                    {
                        StringBuilder paras = new StringBuilder();
                        for(String para:annotation.initializedVariable())
                        {
                            try
                            {
                                java.lang.reflect.Field field_ = field.type.getClass().getDeclaredField(para);
                                field_.setAccessible(true);
                                paras.append(field_.get(field.type).toString()).append(", ");
                            } catch (IllegalAccessException | NoSuchFieldException e)
                            {
                                e.printStackTrace();
                            }
                        }
                        paras.delete(paras.length()-2,paras.length());
                        analog.createFieldWithConstructor(field.type.getClass().getSimpleName(), field.properties.get("FieldCode"), paras.toString());
                    }
                    else//no need to construct
                    {
                        analog.createFieldWithoutConstructor(field.type.getClass().getSimpleName(), field.properties.get("FieldCode"));
                    }
                    break;
                }
            }
            //if not found, generate no constructor field.
            if(!found)analog.createFieldWithoutConstructor(field.type.getClass().getSimpleName(), field.properties.get("FieldCode"));
            analog.newLine();
        }
    }

    private File createFiles(String file)
    {
        File fileCreating = new File(outputDir.getAbsolutePath()+System.getProperty("file.separator")+file);//handle windows and linux path compatibility

            try
            {
                if(!fileCreating.exists()&&fileCreating.createNewFile())
                    System.out.println("Created new Files.");
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        return fileCreating;
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
