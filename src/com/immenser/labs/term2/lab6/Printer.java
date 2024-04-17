package com.immenser.labs.term2.lab6;

import javafx.scene.control.Label;

public class Printer extends PeripheralUnit <String, String> {    //класс принтеров, наследуемый от PeripheralUnit
    public Printer(int id, String name, int price, String color, String type)   //конструктор
    {
        super(id, name, price); //вызов конструктора суперкласса
        param1 = color;
        param2 = type;
    }
    public Label showAddParam1()  //определение функции для вывода доп.атрибута 1
    {
        return new Label("Color: "+param1);
    };
    public Label showAddParam2()  //определение функции для вывода доп.атрибута 2
    {
        return new Label("Type: "+param2);
    };

}
