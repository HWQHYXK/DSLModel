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
    @ConstructorToUse(initializedVariable = {"val", "Length", "Precision"})
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

    public int getLength()
    {
        return Length;
    }

    public int getPrecision()
    {
        return Precision;
    }
}
