package DSLModel;

public class Read
{
    //Read类 支持从该类中读入数据
    private int p;
    private String content;

    //构造
    public Read()
    {
        p=0;
        content=new String();
    }
    public Read(String content)
    {
        p = 0;
        this.content = content;
    }

    //将位置 p 移动到下一个"<" 并返回移动中经过字符的字符串（不包括首尾的尖括号）
    public String toNextLeft()
    {
        int x=p;
        for(; content.charAt(p) != '<'; ++p)
            if(p >= content.length()) break;
        String ret= content.substring(x,p);
        if(ret.charAt(0) == '<' || ret.charAt(0) == '>')
            ret=ret.substring(1);
        return ret;
    }

    //将位置 p 移动到下一个">" 并返回移动中经过字符的字符串（不包括首尾的尖括号）
    public String toNextRight()
    {
        int x=p;
        for(; content.charAt(p) != '>'; ++p)
            if(p >= content.length()) break;
        String ret= content.substring(x,p);
        if(ret.charAt(0) == '<' || ret.charAt(0) == '>')
            ret=ret.substring(1);
        return ret;
    }

    //判断是否已经到达末尾
    public boolean end()
    {
        return p + 1 >= content.length();
    }
}
