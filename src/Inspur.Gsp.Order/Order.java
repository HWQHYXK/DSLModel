/*
* EntityName: ���۶���
* EntityDescription: ���۶���ģ��
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
    private static final String EntityName = "���۶���";
    private static final String EntityCode = "Order";
    private static final String EntityDescription = "���۶���ģ��";
    private static final String EntityNameSpace = "Inspur.Gsp.Order";
    private static final String EntityTableCode = "GspOrder";
    
    public MyString ID = new MyString(36, 36);// ����
    public MyString Code = new MyString(50, 1);// �������
    public MyString Name = new MyString(50, 1);// ��������
    public MyDouble Price = new MyDouble(0.0, 8, 2);// ����
    public Integer OrderCount;// ������Ŀ
    public MyDouble OrderAmount = new MyDouble(0.0, 8, 2);// �������
    public OrderStatus Status;// ����״̬
    public Boolean IsVip;// �Ƿ��Ա
    public Date CreateTime;// ����ʱ��
    public DemoType Demo = new DemoType(5, 6);// ��ĸ
    public ArrayList<OrderItem> OrderItems = new ArrayList<OrderItem>();// ������ϸ��
    
    public Order(String ID, String Code, String Name)
    {
        this.ID.setValue(ID);
        this.Code.setValue(Code);
        this.Name.setValue(Name);
        
    }
}