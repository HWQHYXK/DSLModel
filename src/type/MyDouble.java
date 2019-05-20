package type;

import org.w3c.dom.Element;

public class MyDouble implements Type
{
    private double val;

    //FieldConstraint
    private int Length,Precision;

    //构造
    public MyDouble()
    {
        Length = 2;
        Precision = 8;
    }
    public MyDouble(double val,int length, int precision)
    {
        this.val = val;
        Length = length;
        Precision = precision;
    }

    public double getVal()
    {
        return val;
    }
    public void setVal(double val)
    {
        this.val = val;
    }

    @Override
    public void updateFromRead(Element root)
    {

    }

    @Override
    public String getInitializeString(String name)
    {
        String ret = "(";
        ret += Length + ",";
        ret += Precision + ")";
        return "MyDouble " + name + " = new MyDouble" + ret + ";";
    }
}
