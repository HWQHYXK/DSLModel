package type;

import DSL_Model.Read;

public class MyString implements Type
{
    //因为 MyString 本身为 String 的扩展，所以为了方便直接将 description 这一属性开放为 public ，供使用方便
    private String value;
    //FieldConstraint
    private int MaxLength,MinLength;
    private boolean isEmpty;

    //构造
    public MyString()
    {
        MinLength=0;
        MaxLength=100000;
    }
    public MyString(int maxLength, int minLength)
    {
        MaxLength = maxLength;
        MinLength = minLength;
    }

    //判断字符串是否符合 FieldConstraint 中的限制条件
    private boolean judge()
    {
        if(value.length() > MaxLength) return false;
        if(value.length() < MinLength) return false;
        return true;
    }

    public String getValue()
    {
        return value;
    }

    public void setValue(String value)
    {
        if(judge())
            this.value = value;
        else
            throw new RuntimeException();
    }

    @Override
    public void updateFromRead(Read read)
    {

    }

    @Override
    public String getInitializeString(String name)
    {
        String ret="(";
        ret+=MaxLength+",";
        ret+=MinLength+")";
        return "MyDouble "+name+" = new MyDouble"+ret+";";
    }
}
