# DSLModel

## 赛题背景

    graph LR;
    user --> IDE;
    IDE--创建-->实体模型;
    实体模型--ORM-->表结构;

## Ability

*   元数据解析能力
*   软件设计模式的灵活运用能力
*   领域建模能力

## Description

编写一程序，实现实体模型构建，源代码生成器两个功能

*   利用面向对象的方法，抽象出实体模型类结构，实体属性支持扩展;
*   利用面向对象的方法，抽象出字段类型类结构，支持自定义类型扩展;
*   完成实体模型构建功能：读取文件内容，解析/转换实体模型
*   完成源代码生成功能。

## 评分标准

*   将XML或者JSON解析转换生成实体模型
*   根据实体模型，生成代码
*   代码整洁/逻辑严谨清晰，可读性高
*   运行可靠，安全性高/性能较好
*   实体模型属性支持扩展
*   字段类型结构支持扩展
