package DSLModel;

import java.io.IOException;

public class CodeGenerateException extends IOException
{
    public CodeGenerateException(int lineNum)
    {
        super("Error in line " + lineNum);
    }
}
