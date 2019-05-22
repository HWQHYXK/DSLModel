/*
* EntityName: ���۶���
* EntityDescription: ���۶���ģ��
* */

package Inspur.Gsp.Order;

import java.lang.Integer;
import java.util.Date;
import java.lang.Boolean;
import type.MyString;
import type.MyDouble;

public class Order
{
    public MyString ID = new MyString(36, 36);// ����
    public MyString Code = new MyString(50, 1);// �������
    public MyString Name = new MyString(50, 1);// ��������
    public MyDouble Price = new MyDouble(0.0, 8, 2);// ����
    public Integer OrderCount;// ������Ŀ
    public MyDouble OrderAmount = new MyDouble(0.0, 8, 2);// �������
    public OrderStatus Status;// ����״̬
    public Boolean IsVip;// �Ƿ��Ա
    public Date Date;// ����ʱ��
    
    public Order(String ID, String Code, String Name)
    {
        this.ID.setValue(ID);
        this.Code.setValue(Code);
        this.Name.setValue(Name);
        
    }
}