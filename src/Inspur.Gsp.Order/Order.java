/*
* EntityName: 销售订单
* EntityDescription: 销售订单模型
* */

package Inspur.Gsp.Order;

import java.util.Date;
import type.MyDouble;
import java.lang.Integer;
import type.MyString;
import java.lang.Boolean;
import java.util.ArrayList;

public class Order
{
    public MyString ID = new MyString(36, 36);// 内码
    public MyString Code = new MyString(50, 1);// 订单编号
    public MyString Name = new MyString(50, 1);// 订单名称
    public MyDouble Price = new MyDouble(0.0, 8, 2);// 单价
    public Integer OrderCount;// 订单数目
    public MyDouble OrderAmount = new MyDouble(0.0, 8, 2);// 订单金额
    public OrderStatus Status;// 订单状态
    public Boolean IsVip;// 是否会员
    public Date CreateTime;// 创建时间
    public ArrayList<OrderItem> OrderItems = new ArrayList<OrderItem>();// 订单明细项
    
    public Order(String ID, String Code, String Name)
    {
        this.ID.setValue(ID);
        this.Code.setValue(Code);
        this.Name.setValue(Name);
        
    }
}