package DSLModel;

public class Property
{
    protected String name,content;

    public Property()
    {
        name = new String();
        content = new String();
    }

    public Property(String name,String content)
    {
        this.name = name;
        this.content = content;
    }

    //返回一段创建并初始化该 property 的代码
    public String getInitializeString(String name)
    {
        return "String " + name + " = new String(\"" + content + "\");";
    }
}
