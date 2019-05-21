package DSLModel;

import java.io.*;

public class AnalogInput
{
    File outputFile;
    BufferedWriter writer;
    int lineNum = 1;
    public AnalogInput(File outputFile) throws IOException
    {
        this.outputFile = outputFile;
        writer = new BufferedWriter(new FileWriter(outputFile));
    }

    public void importClass(Class T)
    {
        try
        {
            writer.append("import "+T.getName()+";");
        }catch (IOException e)
        {
            System.out.println(" "+lineNum);
        }
    }

    public void newLine()
    {
        try
        {
            writer.append("\r\n");//避免使用bufferedWriter.newline()方法，增强兼容性
            lineNum++;
        }catch (IOException e)
        {
            System.out.println("can't add new line in line "+lineNum);
        }
    }


}
