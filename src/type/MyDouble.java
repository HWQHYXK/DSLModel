package type;

import DSL_Model.Read;

public class MyDouble implements Type
{
    //因为 MyDouble 本身为 Double 的扩展，所以为了方便直接将 val 这一属性开放为 public ，供使用方便
    private double val;

    //FieldConstraint
    private int Length,Precision;

    //构造
    public MyDouble()
    {
        Length=2;
        Precision=8;
    }
    public MyDouble(double val,int length, int precision)
    {
        this.val=val;
        Length = length;
        Precision = precision;
    }

    //改变 FieldConstraint
    public void setLength(int length)
    {
        Length = length;
    }
    public void setPrecision(int precision)
    {
        Precision = precision;
    }

    @Override
    public void updateFromRead(Read read)
    {

    }

    @Override
    public String getInitializeString(String name)
    {
        String ret="(";
        ret+=Length+",";
        ret+=Precision+")";
        return "MyDouble "+name+" = new MyDouble"+ret+";";
    }
}
