package type;

import DSLModel.Read;

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
                case "Length":
                    Length = Integer.parseInt(s2);
                    break;
                case "Precision":
                    Precision = Integer.parseInt(s2);
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
        ret += Length + ",";
        ret += Precision + ")";
        return "MyDouble " + name + " = new MyDouble" + ret + ";";
    }
}
