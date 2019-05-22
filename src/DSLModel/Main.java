package DSLModel;

import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args)
    {
        Entity entity = XMLParser.buildEntity("in/order.xml");
        System.out.print("Input Your Root Dir:");
        Scanner scanner = new Scanner(System.in);
        CodeGenerator codeGenerator = new CodeGenerator(entity, new File(scanner.next()));
        codeGenerator.generateContents();
    }
}
