package type;


public class MyString
{
    private String value;
    //FieldConstraint

    private int MaxLength,MinLength;
    private boolean IsEmpty;
    //构造
    public MyString()
    {
        MinLength = 0;
        MaxLength = 100000;
    }
    @ConstructorToUse(initializedVariable = {"MaxLength", "MinLength"})
    public MyString(int maxLength, int minLength)
    {
        MaxLength = maxLength;
        MinLength = minLength;
    }

    public MyString(int maxLength, int minLength, boolean isEmpty)
    {
        MaxLength = maxLength;
        MinLength = minLength;
        IsEmpty = isEmpty;
    }

    //判断字符串是否符合 FieldConstraint 中的限制条件
    private boolean judge()
    {
        if(value.length() > MaxLength) return false;
        if(value.length() < MinLength) return false;
        return true;
    }

    public boolean isEmpty()
    {
        return IsEmpty;
    }

    public int getMaxLength()
    {
        return MaxLength;
    }

    public int getMinLength()
    {
        return MinLength;
    }

    public String getValue()
    {
        return value;
    }

    public void setValue(String value)
    {
        this.value = value;
        if(!judge())
            throw new RuntimeException();
    }
}
