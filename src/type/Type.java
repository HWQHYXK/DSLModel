//暂时废弃 ！！！

package type;
import org.w3c.dom.Element;

//非基本类型的 字段类型 接口
@Deprecated
public interface Type
{
    boolean isEmpty = true;
}

//接口 Type 中的方法为帮助构造实体类 Entity 所用
//因此 Type 中所包含的方法 即为输出代码中不需要的方法