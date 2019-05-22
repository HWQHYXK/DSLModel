package DSLModel;
 
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.apache.log4j.Logger;
 
/**
* @projectName：DSLModel    
 */
public class DaoCreator {
	private static final Logger LOGGER = Logger.getLogger(Thread
			.currentThread().getStackTrace()[1].getname());
	/**
	 * @param daoFilePath
	 * @param tableName
	 */
	public static void DaoCreatorImplFile(String daoFilePath, String tableName){
		String lowerCaseName = tableName.toLowerCase();
		String fistLetter = lowerCaseName.substring(0,1).toUpperCase();
		String name = fistLetter + lowerCaseName.substring(1);
		String fileName = daoFilePath + "/" + name + "DaoImpl.java";
		File file = new File(fileName);
		if(!file.exists()){
			try {
				boolean isSuccess = file.createNewFile();
				if(!isSuccess){
					LOGGER.error("创建dao文件失败");
					return;
				}
			} catch (IOException e) {
				e.printStackTrace();
				LOGGER.error("create dao file error:", e);
			}
		}
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(file);
			//注解
			fileWriter.write("@Service(\"" + name + "DaoImpl\")");
			fileWriter.write("\n");
			//接口开始
			fileWriter.write("public class " + name + "DaoImpl extends BaseDao implements " + name + "Dao {");
            fileWriter.write("\n");
            //create
            fileWriter.write("public static String Create(String name,String filePath){");
            printTab(fileWriter, 1);
            fileWriter.write("try {");
            printTab(fileWriter, 2);
            fileWriter.write("Class<?> clz = Class.forName(\"+name+\");");
            printTab(fileWriter, 2);
            fileWriter.write(name "= clz.getSimpleName();");
            printTab(fileWriter, 2);
            fileWriter.write("Field[] fields = clz.getDeclaredFields();");
            printTab(fileWriter, 2);
            fileWriter.write("StringBuffer column = new StringBuffer();");
            printTab(fileWriter, 2);
            fileWriter.write("String varchar = \" varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,\";");
            printTab(fileWriter, 2);
            fileWriter.write("for (Field f : fields) {");
            printTab(fileWriter, 3);
            fileWriter.write("	column.append(\" \n `\"+f.getName()+"`").append(varchar);");
            printTab(fileWriter, 2);
            fileWriter.write("}");
            printTab(fileWriter, 2);
            fileWriter.write("StringBuffer sql = new StringBuffer();");
            printTab(fileWriter, 2);
            fileWriter.write("sql.append(\"\n DROP TABLE IF EXISTS `\"+name+\"`; \")");
            printTab(fileWriter, 2);
            fileWriter.write("	.append(\" \n CREATE TABLE `\"+name+\"`  (\")");
            printTab(fileWriter, 2);
            fileWriter.write("	.append(\" \n `id` int(11) NOT NULL AUTO_INCREMENT,\")");
            printTab(fileWriter, 2);
            fileWriter.write("	.append(\" \n \"+column)\")");
            printTab(fileWriter, 2);
            fileWriter.write("	.append(\" \n PRIMARY KEY (`id`) USING BTREE,\")");
            printTab(fileWriter, 2);
            fileWriter.write("	.append(\n INDEX `id`(`id`) USING BTREE \" )");
            printTab(fileWriter, 2);
            fileWriter.write("	.append( \n ) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci;\");");
            printTab(fileWriter, 2);
            fileWriter.write("return sql.toString();");
            printTab(fileWriter, 1);
            fileWriter.write("} catch (ClassNotFoundException e) {");
            printTab(fileWriter, 2);
            fileWriter.write("	logger.debug(\"该类未找到！\");");
            printTab(fileWriter, 2);
            fileWriter.write("	return null;");
            printTab(fileWriter, 1);
            fileWriter.write("}");
			fileWriter.write("\n");
			//select
			printTab(fileWriter, 1);
			fileWriter.write("public List<" + name + "> select" + name + "s(Map<String, Object> index){");
			printTab(fileWriter, 2);
			fileWriter.write("return (List<" + name + ">)this.getSqlMapClientTemplate().queryForList(\"select" + name + "s\", index);");
			printTab(fileWriter, 1);
			fileWriter.write("}");
			fileWriter.write("\n");
			//select count
			printTab(fileWriter, 1);
			fileWriter.write("public int select" + name + "sCount(Map<String, Object> index){");
			printTab(fileWriter, 2);
			fileWriter.write("return (Integer)this.getSqlMapClientTemplate().queryForObject(\"select" + name + "sCount\", index);");
			printTab(fileWriter, 1);
			fileWriter.write("}");
			fileWriter.write("\n");
			//insert
			printTab(fileWriter, 1);
			fileWriter.write("public void insert" + name + "(" + name + " instance){");
			printTab(fileWriter, 2);
			fileWriter.write("this.getSqlMapClientTemplate().insert(\"insert" + name + "\", instance);");
			printTab(fileWriter, 1);
			fileWriter.write("}");
			fileWriter.write("\n");
			//update
			printTab(fileWriter, 1);
			fileWriter.write("public void update" + name + "(Map<String, Object> index){");
			printTab(fileWriter, 2);
			fileWriter.write("this.getSqlMapClientTemplate().update(\"update" + name + "\", index);");
			printTab(fileWriter, 1);
			fileWriter.write("}");
			fileWriter.write("\n");
			//delete
			printTab(fileWriter, 1);
			fileWriter.write("public void delete" + name + "(Map<String, Object> index){");
			printTab(fileWriter, 2);
			fileWriter.write("this.getSqlMapClientTemplate().delete(\"delete" + name + "\", index);");
			printTab(fileWriter, 1);
			fileWriter.write("}");
			fileWriter.write("\n");
			//结束
			fileWriter.write("}");
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e);
		}finally{
			try {
				if(fileWriter != null){
					fileWriter.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	/**
	 * 
	 * @param daoFilePath
	 * @param tableName
	 */
	public static void DaoCreatorFile(String daoFilePath, String tableName){
		String lowerCaseName = tableName.toLowerCase();
		String fistLetter = lowerCaseName.substring(0,1).toUpperCase();
		String name = fistLetter + lowerCaseName.substring(1);
		String fileName = daoFilePath + "/" + name + "Dao.java";
		File file = new File(fileName);
		if(!file.exists()){
			try {
				boolean isSuccess = file.createNewFile();
				if(!isSuccess){
					LOGGER.error("创建dao文件失败");
					return;
				}
			} catch (IOException e) {
				e.printStackTrace();
				LOGGER.error("create dao file error:", e);
			}
		}
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(file);
			//接口开始
			fileWriter.write("public interface " + name + "Dao{");
            fileWriter.write("\n");
            //create
            printTab(fileWriter, 1);
            fileWriter.write("public static String Create(String " + name + ",String filePath);");
            fileWriter.write("\n");
			//select
			printTab(fileWriter, 1);
			fileWriter.write("public List<" + name + "> select" + name + "s(Map<String, Object> index);");
			fileWriter.write("\n");
			//select count
			printTab(fileWriter, 1);
			fileWriter.write("public int select" + name + "sCount(Map<String, Object> index);");
			fileWriter.write("\n");
			//insert
			printTab(fileWriter, 1);
			fileWriter.write("public void insert" + name + "(" + name + " instance);");
			fileWriter.write("\n");
			//update
			printTab(fileWriter, 1);
			fileWriter.write("public void update" + name + "(Map<String, Object> index);");
			fileWriter.write("\n");
			//delete
			printTab(fileWriter, 1);
			fileWriter.write("public void delete" + name + "(Map<String, Object> index);");
			fileWriter.write("\n");
			//结束
			fileWriter.write("}");
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e);
		}finally{
			try {
				if(fileWriter != null){
					fileWriter.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	private static void printTab(FileWriter fileWriter, int count) throws IOException {
		fileWriter.write("\n");
		for(int i = 0 ; i < count; i++){
			fileWriter.write("\t");
		}
	}
}