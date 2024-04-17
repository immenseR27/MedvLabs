package com.immenser.labs.term2.lab6;

import javafx.scene.control.Label;
public class PeripheralUnit<A,B> {  //базовый класс периферийных устройств
    private int id;
    private String name;
    private int price;
    protected A param1;
    protected B param2;

    public PeripheralUnit(int id, String name, int price) //конструктор
    {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId()  //получение атрибута id
    {
        return id;
    }
    public String getName()  //получение атрибута name
    {
        return name;
    }
    public int getPrice()  //получение атрибута price
    {
        return price;
    }
    public A getAddParam1(){return param1;} //получение доп.атрибута 1
    public B getAddParam2(){return param2;} //получение доп.атрибута 2
    public Label showAddParam1(){return null;};//объявление функции для вывода доп.атрибута 1
    public Label showAddParam2(){return null;};    //объявление функции для вывода доп.атрибута 2
}
