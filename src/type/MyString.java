package type;

import DSLModel.Read;

public class MyString implements Type
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
        String s = new String();
        while(true)
        {
            read.toNextLeft();
            s = read.toNextRight();
            if(s.charAt(0) == '/') break;

            String s2 = read.toNextLeft();
            read.toNextRight();
            switch(s)
            {
                case "MaxLength":
                    MaxLength = Integer.parseInt(s2);
                    break;
                case "MinLength":
                    MinLength = Integer.parseInt(s2);
                    break;
                case "IsEmpty":
                    IsEmpty = Boolean.parseBoolean(s2);
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public String getInitializeString(String name)
    {
        String ret = "(";
        ret += MaxLength+",";
        ret += MinLength+")";
        return "MyDouble " + name + " = new MyDouble" + ret + ";";
    }
}
