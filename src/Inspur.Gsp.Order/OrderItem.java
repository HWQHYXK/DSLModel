/*
* EntityName: 销售订单明细
* EntityDescription: 销售订单明细模型
* */

package Inspur.Gsp.Order;

import type.MyString;

public class OrderItem
{
    private static final String EntityName = "销售订单明细";
    private static final String EntityCode = "OrderItem";
    private static final String EntityDescription = "销售订单明细模型";
    private static final String EntityNameSpace = "Inspur.Gsp.Order";
    private static final String EntityTableCode = "GspOrderItem";
    
    public MyString ID = new MyString(36, 36);// 内码
    public MyString OrderId = new MyString(36, 36);// 所属销售订单内码
    public MyString ProductCode = new MyString(50, 1);// 产品编号
    public MyString ProductName = new MyString(50, 1);// 产品名称
    
    public OrderItem(String ID, String OrderId, String ProductCode, String ProductName)
    {
        this.ID.setValue(ID);
        this.OrderId.setValue(OrderId);
        this.ProductCode.setValue(ProductCode);
        this.ProductName.setValue(ProductName);
        
    }
}