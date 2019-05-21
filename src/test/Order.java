package test;

import type.MyString;

public class Order
{
    public MyString ID = new MyString(6, 6);
    public Order(String ID)
    {
        this.ID.setValue(ID);
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