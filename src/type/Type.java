//暂时废弃 ！！！

package type;

import org.w3c.dom.Element;

//非基本类型的 字段类型 接口
public interface Type
{
    //通过 DOM 更新属性
    void updateFromRead(Element root);

    //返回一段创建并初始化 Type 对象的代码
    String getInitializeString(String name);
}

//接口 Type 中的方法为帮助构造实体类 Entity 所用
//因此 Type 中所包含的方法 即为输出代码中不需要的方法