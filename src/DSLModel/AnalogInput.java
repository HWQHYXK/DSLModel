package DSLModel;

import java.io.*;
import java.util.ArrayList;

public class AnalogInput
{
    private File outputFile;
    private BufferedWriter writer;
    private int lineNum = 1;
    private int indentNum = 0;
    private final String INDENT = "    ";
    public AnalogInput(File outputFile)
    {
        this.outputFile = outputFile;
        try
        {
            writer = new BufferedWriter(new FileWriter(outputFile));
        }catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void importClass(Class T) throws CodeGenerateException
    {
        try
        {
//            indent();
            writer.write("import "+T.getName()+";");
        }catch (IOException e)
        {
            throw new CodeGenerateException(lineNum);
        }
    }

    public void generateDescription(String name, String description) throws CodeGenerateException
    {
        /*
         * EntityName:
         * EntityDescription:
         * */
        try
        {
            writer.write("/*\r\n" +
                    "* EntityName: "+ name + "\r\n" +
                    "* EntityDescription: " + description + "\r\n" +
                    "* */");
            lineNum += 3;
        }catch (IOException e)
        {
            throw new CodeGenerateException(lineNum);
        }
    }

    public void generatePackage(String namespace) throws CodeGenerateException
    {
        try
        {
//            indent();
            writer.write("package " + namespace + ";");
        }catch (IOException e)
        {
            throw new CodeGenerateException(lineNum);
        }
    }

    public void createClass(String className) throws CodeGenerateException
    {
        try
        {
//            indent();
            writer.write("public class " + className);
        }catch (IOException e)
        {
            throw new CodeGenerateException(lineNum);
        }
    }

    public void generateConstructor(String name, String parasString) throws CodeGenerateException //paras form goes like MyString a, MyString b
    {
        try
        {
//            indent();
            writer.write("public " + name + "(" + parasString + ")");
            newLine();
            leftBrace();
            newLine();
            addIndent();
            String[] paras = parasString.split(", ");
            for(String para : paras)
            {
                String variable = para.split(" ")[1];
                writer.write("this."+variable+".setValue("+variable+");");
                newLine();
            }
            revertIndent();
            newLine();
            rightBrace();
        }catch (IOException e)
        {
            throw new CodeGenerateException(lineNum);
        }
    }

    public void createFieldWithoutConstructor(String type, String variable) throws CodeGenerateException
    {
        try
        {
            writer.write("public "+type+" "+variable+";");
        }catch (IOException e)
        {
            throw new CodeGenerateException(lineNum);
        }
    }

    public void createFieldWithConstructor(String type, String variable, String paras) throws CodeGenerateException
    {
        try
        {
//            indent();
            writer.write("public "+type+" "+variable+" = new "+type+"("+paras+");");
        }catch (IOException e)
        {
            throw new CodeGenerateException(lineNum);
        }
    }

    private void indent() throws CodeGenerateException
    {
        try
        {
            for(int i=1;i<=indentNum;i++)
            {
                writer.write(INDENT);
            }
        }catch (IOException e)
        {
            throw new CodeGenerateException(lineNum);
        }
    }

    public void addIndent() throws CodeGenerateException
    {
        try
        {
            indentNum++;
            writer.write(INDENT);
        }catch (IOException e)
        {
            throw new CodeGenerateException(lineNum);
        }
    }

    public void revertIndent() throws CodeGenerateException
    {
        indentNum--;
//        try
//        {
//            indentNum--;
//            writer.write(INDENT.replace(' ','\b')); //backspace
//        }catch (IOException e)
//        {
//            throw new CodeGenerateException(lineNum);
//        }
    }

    //create brace and auto complement
    public void leftBrace() throws CodeGenerateException
    {
        try
        {
            writer.write("{");
        }catch (IOException e)
        {
            throw new CodeGenerateException(lineNum);
        }
    }

    //create brace and auto complement
    public void rightBrace() throws CodeGenerateException
    {
        try
        {
            writer.write("}");
        }catch (IOException e)
        {
            throw new CodeGenerateException(lineNum);
        }
    }

    public void newLine() throws CodeGenerateException
    {
        try
        {
            writer.write("\r\n");//避免使用bufferedWriter.newline()方法，增强兼容性
            indent();
            lineNum++;
        }catch (IOException e)
        {
            throw new CodeGenerateException(lineNum);
        }
    }

    public void end() throws CodeGenerateException
    {
        try
        {
            writer.flush();
            writer.close();
        }catch (IOException e)
        {
            throw new CodeGenerateException(lineNum);
        }
    }

}
