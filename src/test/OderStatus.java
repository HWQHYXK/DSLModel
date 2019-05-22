package test;

public enum OderStatus
{
    Creating(0),Created(1);
    private int value;
    OderStatus(int value)
    {
        this.value = value;
    }
    public int getValue()
    {
        return value;
    }
}
