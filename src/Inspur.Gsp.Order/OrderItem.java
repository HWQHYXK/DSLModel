/*
* EntityName: ���۶�����ϸ
* EntityDescription: ���۶�����ϸģ��
* */

package Inspur.Gsp.Order;

import type.MyString;

public class OrderItem
{
    public MyString ID = new MyString(36, 36);// ����
    public MyString OrderId = new MyString(36, 36);// �������۶�������
    public MyString ProductCode = new MyString(50, 1);// ��Ʒ���
    public MyString ProductName = new MyString(50, 1);// ��Ʒ����
    
    public OrderItem(String ID, String OrderId, String ProductCode, String ProductName)
    {
        this.ID.setValue(ID);
        this.OrderId.setValue(OrderId);
        this.ProductCode.setValue(ProductCode);
        this.ProductName.setValue(ProductName);
        
    }
}