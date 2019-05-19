package type;

import DSLModel.Read;

public class CommonType implements Type
{
    String type;

    public CommonType(String type)
    {
        this.type = type;
    }

    @Override
    public void updateFromRead(Read read)
    {

    }

    @Override
    public String getInitializeString(String name)
    {
        return type + " " + name + ";";
    }
}
