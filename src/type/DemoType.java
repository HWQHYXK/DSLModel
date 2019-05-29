package type;

public class DemoType
{
    private int number1;
    private int number2;
    public String equation1;
    public String equation2;
    private DemoType()
    {
        equation1 = "1+1=2";
        equation2 = "1-1=0";
    }
    @ConstructorToUse(initializedVariable = {"number1", "number2"})
    public DemoType(int parameter1, int parameter2)
    {
        number1 = parameter1;
        number2 = parameter2;
        equation1 = number1+"+"+number2+"="+(number1+number2);
        equation2 = number1+"-"+number2+"="+(number1-number2);
    }
}
