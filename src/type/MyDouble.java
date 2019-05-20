package type;

public class MyDouble
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
}
