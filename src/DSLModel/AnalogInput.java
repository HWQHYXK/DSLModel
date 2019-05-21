package DSLModel;

import java.io.*;

public class AnalogInput
{
    private File outputFile;
    private BufferedWriter writer;
    private int lineNum = 1;
    private int indentNum = 0;
    private final String INDENT = "    ";
    public AnalogInput(File outputFile) throws IOException
    {
        this.outputFile = outputFile;
        writer = new BufferedWriter(new FileWriter(outputFile));
    }

    public void importClass(Class T) throws CodeGenerateException
    {
        try
        {
            indent();
            writer.write("import "+T.getName()+";");
        }catch (IOException e)
        {
            throw new CodeGenerateException(lineNum);
        }
    }
/*
* EntityName:
* EntityDescription
* */
    public void generateDescription(String name, String description) throws CodeGenerateException
    {
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
            indent();
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
            indent();
            writer.write("public class " + className);
        }catch (IOException e)
        {
            throw new CodeGenerateException(lineNum);
        }
    }

    private String indent() throws CodeGenerateException
    {
        StringBuilder indent = new StringBuilder();//use StringBuilder to enhance append performance and security
        try
        {
            for(int i=1;i<=indentNum;i++)
            {
                writer.write(INDENT);
                indent.append(INDENT);
            }
            return indent.toString();
        }catch (IOException e)
        {
            throw new CodeGenerateException(lineNum);
        }
    }

    public void addIndent() throws CodeGenerateException
    {
        indentNum++;
        try
        {
            writer.write(INDENT);
        }catch (IOException e)
        {
            throw new CodeGenerateException(lineNum);
        }
    }

    public void revertIndent() throws CodeGenerateException
    {
        try
        {
            indentNum--;
            writer.write(INDENT.replace(' ','\b')); //backspace
        }catch (IOException e)
        {
            throw new CodeGenerateException(lineNum);
        }
        indent();
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
