/*
* EntityName: 销售订单
* EntityDescription: 销售订单模型
* */

package Inspur.Gsp.Order;

import type.MyString;
import type.MyDouble;
import java.util.ArrayList;
import java.lang.Integer;
import type.DemoType;
import java.util.Date;
import java.lang.Boolean;

public class Order
{
    private static final String EntityName = "销售订单";
    private static final String EntityCode = "Order";
    private static final String EntityDescription = "销售订单模型";
    private static final String EntityNameSpace = "Inspur.Gsp.Order";
    private static final String EntityTableCode = "GspOrder";
    
    public MyString ID = new MyString(36, 36);// 内码
    public MyString Code = new MyString(50, 1);// 订单编号
    public MyString Name = new MyString(50, 1);// 订单名称
    public MyDouble Price = new MyDouble(0.0, 8, 2);// 单价
    public Integer OrderCount;// 订单数目
    public MyDouble OrderAmount = new MyDouble(0.0, 8, 2);// 订单金额
    public OrderStatus Status;// 订单状态
    public Boolean IsVip;// 是否会员
    public Date CreateTime;// 创建时间
    public DemoType Demo = new DemoType(5, 6);// 字母
    public ArrayList<OrderItem> OrderItems = new ArrayList<OrderItem>();// 订单明细项
    
    public Order(String ID, String Code, String Name)
    {
        this.ID.setValue(ID);
        this.Code.setValue(Code);
        this.Name.setValue(Name);
        
    }
}