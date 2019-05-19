package DSLModel;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan=new Scanner(System.in);

        //把输入内容记入 read 中，便于适应各种形式的输入
        String s=new String();
        while(scan.hasNext())
            s+=scan.next();
        Read read=new Read();

        while(read.end())
        {
            read.toNextLeft();
            s = read.toNextRight();
            if(s.equals("Entity"))
            {
                Entity entity=new Entity(read);
                break;
            }
        }

        return ;
    }
}
