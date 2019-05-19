package test;

import type.MyString;

public class Order
{
    public Order(String ID)
    {
        this.ID.setValue(ID);
    }
    private String EntityName = "销售订单";
    /*ID是内码*/
    private MyString ID = new MyString(36, 36);

    public MyString getID()
    {
        return ID;
    }

    public void setID(MyString ID)
    {
        this.ID = ID;
    }
}

/*
* <Entity>
      <EntityCode>Order</EntityCode>
      <EntityName>销售订单</EntityName>
      <EntityFieldCollection>
        <EntityField>
          <FieldCode>ID</FieldCode>
          <FieldName>内码</FieldName>
          <FieldType>string</FieldType>
          <FieldConstraint>
            <MaxLength>36</MaxLength>
            <MinLength>36</MinLength>
            <IsEmpty>false</IsEmpty>
          </FieldConstraint>
        </EntityField>
    </EntityFieldCollection>
</Entity>
*/