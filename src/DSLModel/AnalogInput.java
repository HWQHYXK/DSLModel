package DSLModel;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class AnalogInput
{
    private File outputFile;
    private BufferedWriter writer;
    private int lineNum = 1;
    private int indentNum = 0;
    private final String INDENT = "    ";
    public AnalogInput(File outputFile)
    {
        this.outputFile = outputFile;
        try
        {
            writer = new BufferedWriter(new FileWriter(outputFile));
        }catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void importClass(Class T) throws CodeGenerateException
    {
        try
        {
//            indent();
            writer.write("import "+T.getName()+";");
        }catch (IOException e)
        {
            throw new CodeGenerateException(lineNum);
        }
    }

    public void generateDescription(String name, String description) throws CodeGenerateException
    {
        /*
         * EntityName:
         * EntityDescription:
         * */
        try
        {
            writer.write("/*\r\n" +
                    "* EntityName: "+ name + "\r\n" +
                    "* EntityDescription: " + description + "\r\n" +
                    "* */");
            lineNum += 3;
        }catch (IOException e)
        {
            throw new CodeGenerateException(lineNum);
        }
    }

    public void generatePackage(String namespace) throws CodeGenerateException
    {
        try
        {
//            indent();
            writer.write("package " + namespace + ";");
        }catch (IOException e)
        {
            throw new CodeGenerateException(lineNum);
        }
    }

    public void createClass(String className) throws CodeGenerateException
    {
        try
        {
//            indent();
            writer.write("public class " + className);
        }catch (IOException e)
        {
            throw new CodeGenerateException(lineNum);
        }
    }

    public void createEnum(String enumName, HashMap<Integer, String> map) throws CodeGenerateException
    {
        try
        {
            writer.write("public enum "+enumName);
            newLine();
            leftBrace();
            newLine();
            addIndent();
            StringBuilder builder = new StringBuilder();
            for(Integer key : map.keySet())
            {
                builder.append(map.get(key)).append("(").append(key).append("), ");
            }
            builder.delete(builder.length()-2,builder.length());
            builder.append(";");
            writer.write(builder.toString());
            newLine();
            writer.write("private int value;\r\n" +
                    "    OrderStatus(int value)\r\n" +
                    "    {\r\n" +
                    "        this.value = value;\r\n" +
                    "    }\r\n" +
                    "    public int getValue()\r\n" +
                    "    {\r\n" +
                    "        return value;\r\n" +
                    "    }");
            lineNum+=8;
            revertIndent();
            newLine();
            rightBrace();
        }catch (IOException e)
        {
            throw new CodeGenerateException(lineNum);
        }
    }

    public void createDaoMainBody(String className) throws CodeGenerateException
    {
        try
        {
            writer.write("private static ArrayList<String> sqls = new ArrayList<>();");
            newLine();
            newLine();
            writer.write("public "+className+"() throws Exception\r\n" +
                    "    {\r\n" +
                    "        init();\r\n" +
                    "        Class.forName(\"com.mysql.jdbc.Driver\");\r\n" +
                    "        System.out.println(\"Driver Load Properly!\");\r\n" +
                    "\r\n" +
                    "        Connection connection = null;\r\n" +
                    "        Statement statement = null;\r\n" +
                    "        ResultSet resultSet = null;\r\n" +
                    "\r\n" +
                    "        try\r\n" +
                    "        {\r\n" +
                    "            String url = \"jdbc:mysql://localhost:3306/\";\r\n" +
                    "            connection = DriverManager.getConnection(url);\r\n" +
                    "            System.out.println(\"Database Connected!\");\r\n" +
                    "\r\n" +
                    "            statement = connection.createStatement();\r\n" +
                    "            for(String sql:sqls)\r\n" +
                    "            {\r\n" +
                    "                resultSet = statement.executeQuery(sql);\r\n" +
                    "\r\n" +
                    "                resultSet.beforeFirst();\r\n" +
                    "                while (resultSet.next())\r\n" +
                    "                {\r\n" +
                    "                    System.out.println(resultSet.getString(1));\r\n" +
                    "                }\r\n" +
                    "                System.out.println(\"Valid Operation!\");\r\n" +
                    "            }\r\n" +
                    "        } catch(Throwable t)\r\n" +
                    "        {\r\n" +
                    "            // TODO Handle Exception\r\n" +
                    "            t.printStackTrace();\r\n" +
                    "        } finally\r\n" +
                    "        {\r\n" +
                    "            if (resultSet != null)\r\n" +
                    "                resultSet.close();\r\n" +
                    "            if (statement != null)\r\n" +
                    "                statement.close();\r\n" +
                    "            if (connection != null)\r\n" +
                    "                connection.close();\r\n" +
                    "            System.out.println(\"Close Properly!\");\r\n" +
                    "        }\r\n" +
                    "    }\r\n" +
                    "\r\n" +
                    "    private static void handleSQL(String sql) throws Exception\r\n" +
                    "    {\r\n" +
                    "        handleSQL(sql, null);\r\n" +
                    "    }\r\n" +
                    "\r\n" +
                    "    private static void handleSQL(String sql, String queryField) throws Exception\r\n" +
                    "    {\r\n" +
                    "        Connection connection = null;\r\n" +
                    "        Statement statement = null;\r\n" +
                    "        ResultSet resultSet = null;\r\n" +
                    "\r\n" +
                    "        try\r\n" +
                    "        {\r\n" +
                    "            String url = \"jdbc:mysql://localhost:3306/\";\r\n" +
                    "            connection = DriverManager.getConnection(url);\r\n" +
                    "            System.out.println(\"Database Connected!\");\r\n" +
                    "\r\n" +
                    "            statement = connection.createStatement();\r\n" +
                    "            resultSet = statement.executeQuery(sql);\r\n" +
                    "            resultSet.beforeFirst();\r\n" +
                    "            while (resultSet.next())\r\n" +
                    "            {\r\n" +
                    "                if (queryField != null)\r\n" +
                    "                    System.out.println(resultSet.getString(queryField));\r\n" +
                    "            }\r\n" +
                    "            System.out.println(\"Valid Operation!\");\r\n" +
                    "        } catch(Throwable t)\r\n" +
                    "        {\r\n" +
                    "            // TODO Handle Exception\r\n" +
                    "            t.printStackTrace();\r\n" +
                    "        } finally\r\n" +
                    "        {\r\n" +
                    "            if (resultSet != null)\r\n" +
                    "                resultSet.close();\r\n" +
                    "            if (statement != null)\r\n" +
                    "                statement.close();\r\n" +
                    "            if (connection != null)\r\n" +
                    "                connection.close();\r\n" +
                    "            System.out.println(\"Close Properly!\");\r\n" +
                    "        }\r\n" +
                    "    }");
            lineNum+=86;
        }catch (IOException e)
        {
            throw new CodeGenerateException(lineNum);
        }
    }

    public void generateConstructor(String name, String parasString) throws CodeGenerateException //paras form goes like MyString a, MyString b
    {
        try
        {
//            indent();
            writer.write("public " + name + "(" + parasString + ")");
            newLine();
            leftBrace();
            newLine();
            addIndent();
            String[] paras = parasString.split(", ");
            for(String para : paras)
            {
                String variable = para.split(" ")[1];
                writer.write("this."+variable+".setValue("+variable+");");
                newLine();
            }
            revertIndent();
            newLine();
            rightBrace();
        }catch (IOException e)
        {
            throw new CodeGenerateException(lineNum);
        }
    }

    public void createFieldWithoutConstructor(String type, String variable) throws CodeGenerateException
    {
        try
        {
            writer.write("public "+type+" "+variable+";");
        }catch (IOException e)
        {
            throw new CodeGenerateException(lineNum);
        }
    }

    public void generateDaoInitMethod(ArrayList<String> sqls) throws CodeGenerateException
    {
        try
        {
            writer.write("private void init()");
            newLine();
            leftBrace();
            newLine();
            addIndent();
            for(String sql:sqls)
            {
                writer.write("sqls.add(\""+sql+"\");");
                newLine();
            }
            revertIndent();
            newLine();
            rightBrace();
        }catch (IOException e)
        {
            throw new CodeGenerateException(lineNum);
        }
    }

    public void generateDaoInsertMethod(String tableName, ArrayList<String> fieldNames) throws CodeGenerateException
    {
        try
        {
            StringBuilder builder = new StringBuilder();
            for(String fieldName:fieldNames)
            {
                builder.append("String ").append(fieldName).append(", ");
            }
            builder.delete(builder.length()-2, builder.length());
            writer.write("public void insertOne("+builder.toString()+") throws Exception");
            newLine();
            leftBrace();
            newLine();
            addIndent();
            StringBuilder anotherBuilder = new StringBuilder();
            for(String fieldName:fieldNames)
            {
                anotherBuilder.append(fieldName).append(", ");
            }
            anotherBuilder.delete(anotherBuilder.length()-2, anotherBuilder.length());
            writer.write("String insertSQL = \"INSERT INTO "+tableName+" ("+anotherBuilder.toString()+") VALUES \";");
            newLine();
            writer.write("StringBuilder builder = new StringBuilder(insertSQL);");
            newLine();
            StringBuilder thirdBuilder = new StringBuilder();
            // builder.append("("+"\""+ID+"\", "+"\""+Code+"\", "+"\""+Name+"\""+");");
            for(String fieldName:fieldNames)
            {
                thirdBuilder.append("\"\\\"\"+").append(fieldName).append("+\"\\\", \"");
                thirdBuilder.append("+");
            }
            thirdBuilder.delete(thirdBuilder.length()-3, thirdBuilder.length());
            thirdBuilder.append("\"");
            System.out.println(thirdBuilder);
            writer.write("builder.append(\"(\"+"+thirdBuilder.toString()+"+\");\");");
            newLine();
            writer.write("handleSQL(builder.toString());");
            revertIndent();
            newLine();
            rightBrace();
        }catch (IOException e)
        {
            throw new CodeGenerateException(lineNum);
        }
    }

    public void generateDaoSelectMethod(String tableName) throws CodeGenerateException
    {
        try
        {
            writer.write("public void select(String queryField, String field, String value) throws Exception");
            newLine();
            leftBrace();
            newLine();
            addIndent();
            writer.write("String selectSQL = \"SELECT * FROM "+tableName+" WHERE \"+field+\"=\"+value+\";\";");
            newLine();
            writer.write("handleSQL(selectSQL, queryField);");
            revertIndent();
            newLine();
            rightBrace();
        }catch (IOException e)
        {
            throw new CodeGenerateException(lineNum);
        }
    }

    public void generateDaoUpdateMethod(String tableName) throws CodeGenerateException
    {
        try
        {
            writer.write("public void updateOne(String queryField, String queryValue, String field, String value) throws Exception");
            newLine();
            leftBrace();
            newLine();
            addIndent();
            writer.write("String updateSQL = \"UPDATE "+tableName+" SET \"+queryField+\"=\"+queryValue+\"WHERE \"+field+\"=\"+value+\";\";");
            newLine();
            writer.write("handleSQL(updateSQL);");
            revertIndent();
            newLine();
            rightBrace();
        }catch (IOException e)
        {
            throw new CodeGenerateException(lineNum);
        }
    }

    public void generateDaoDeleteMethod(String tableName) throws CodeGenerateException
    {
        try
        {
            writer.write("public void delete(String field, String value) throws Exception");
            newLine();
            leftBrace();
            newLine();
            addIndent();
            writer.write("String deleteSQL = \"DELETE FROM "+tableName+" WHERE \"+field+\"=\"+value+\";\";");
            newLine();
            writer.write("handleSQL(deleteSQL);");
            revertIndent();
            newLine();
            rightBrace();
        }catch (IOException e)
        {
            throw new CodeGenerateException(lineNum);
        }
    }

    public void generateFieldDescription(String fieldName) throws CodeGenerateException
    {
        try
        {
            writer.write("// "+fieldName);
        }catch (IOException e)
        {
            throw new CodeGenerateException(lineNum);
        }
    }

    public void createFieldWithConstructor(String type, String variable, String paras) throws CodeGenerateException
    {
        try
        {
//            indent();
            writer.write("public "+type+" "+variable+" = new "+type+"("+paras+");");
        }catch (IOException e)
        {
            throw new CodeGenerateException(lineNum);
        }
    }

    private void indent() throws CodeGenerateException
    {
        try
        {
            for(int i=1;i<=indentNum;i++)
            {
                writer.write(INDENT);
            }
        }catch (IOException e)
        {
            throw new CodeGenerateException(lineNum);
        }
    }

    public void addIndent() throws CodeGenerateException
    {
        try
        {
            indentNum++;
            writer.write(INDENT);
        }catch (IOException e)
        {
            throw new CodeGenerateException(lineNum);
        }
    }

    public void revertIndent() throws CodeGenerateException
    {
        indentNum--;
//        try
//        {
//            indentNum--;
//            writer.write(INDENT.replace(' ','\b')); //backspace
//        }catch (IOException e)
//        {
//            throw new CodeGenerateException(lineNum);
//        }
    }

    //create brace and auto complement
    public void leftBrace() throws CodeGenerateException
    {
        try
        {
            writer.write("{");
        }catch (IOException e)
        {
            throw new CodeGenerateException(lineNum);
        }
    }

    //create brace and auto complement
    public void rightBrace() throws CodeGenerateException
    {
        try
        {
            writer.write("}");
        }catch (IOException e)
        {
            throw new CodeGenerateException(lineNum);
        }
    }

    public void newLine() throws CodeGenerateException
    {
        try
        {
            writer.write("\r\n");//避免使用bufferedWriter.newline()方法，增强兼容性
            indent();
            lineNum++;
        }catch (IOException e)
        {
            throw new CodeGenerateException(lineNum);
        }
    }

    public void end() throws CodeGenerateException
    {
        try
        {
            writer.flush();
            writer.close();
        }catch (IOException e)
        {
            throw new CodeGenerateException(lineNum);
        }
    }

}
