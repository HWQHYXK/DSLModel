// ¶©µ¥×´Ì¬

package Inspur.Gsp.Order;

public enum OrderStatus
{
    Creating(0), Created(1);
    private int value;
    OrderStatus(int value)
    {
        this.value = value;
    }
    public int getValue()
    {
        return value;
    }
}