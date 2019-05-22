//暂时废弃 ！！！

package type;
@Deprecated
public class CommonType
{
    private String type;

    public CommonType(String type)
    {
        this.type = type;
    }

    public String getType()
    {
        return type;
    }

    /*
    @Override
    public void updateFromRead(Read read)
    {
        //通过读入 FieldConstraint 中的 xml 来更新 Type 的属性
        if(!type.equals("int"))
            throw new RuntimeException();//若不是 int 类型 则不应具有 FieldConstraint
        else
        {
            ArrayList<String> list = new ArrayList<>();
            HashMap<String,Integer> hashMap = new HashMap<>();

            String s = new String();
            while(true)
            {
                read.toNextLeft();
                s = read.toNextRight();
                if (s.charAt(0) == '/')
                {
                    //FieldConstraint 结束
                    //若类型现在已为 enum 则更新enumList
                    //TODO
                    break;
                }

                if(s.equals("IsEnum"))
                {
                    String s2 = read.toNextLeft();
                    read.toNextRight();
                    if(s2.equals("true"))
                    {
                        //此时 type 应该更改为 Enum
                        type = "Enum";
                    }
                }
                else if(s.equals("EnumCollection"))
                {
                    //收集 EnumCollection 中 enum 的相关信息
                    //先存在 hashMap 和 list 中
                    //最后 FieldConstraint 结束完了后 再统计存在 enumList 中
                    int key=-1;
                    String value = new String();
                    while(true)
                    {
                        read.toNextLeft();
                        s = read.toNextRight();
                        if (s.charAt(0) == '/')
                        {
                            if(!value.isEmpty())//若该 value 的 String 为空 则认为数据缺失 跳过该步
                            {
                                if(key == -1)
                                    list.add(value);//如果没有 key 则默认放到最后
                                hashMap.put(value, key);//把该 enum 加入到 hashMap 中
                            }
                            break;
                        }

                        String s2 = read.toNextLeft();
                        read.toNextRight();
                        if(s.equals("Key"))
                            key = Integer.parseInt(s2);
                        else if(s.equals("Value"))
                            value = s2;
                    }
                }
            }
        }
    }
    */
}
