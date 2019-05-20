package test;

public class Test
{
    public static void main(String[] args)
    {
        Order order = new Order("123456");
        System.out.println(hwq.hh.getValue()+hwq.www.getValue());
    }
    enum hwq
    {
        hh(10),www(1),qqq(2);

        private int value;

        hwq(int index)
        {
            value = index;
        }

        public int getValue()
        {
            return value;
        }

        @Override
        public String toString()
        {
            return ""+value;
        }
    }
}
