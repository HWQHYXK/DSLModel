package DSLModel;

public class Property
{
    protected String name,content;

    public Property(String name,String content)
    {
        this.name = name;
        this.content = content;
    }

    public String getName()
    {
        return name;
    }

    public String getContent()
    {
        return content;
    }
}
