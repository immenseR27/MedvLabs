package com.immenser.labs.term2.lab6;

import javafx.scene.control.Label;

public class Monitor extends PeripheralUnit<String, Integer> { //класс мониторов, наследуемый от PeripheralUnit
    public Monitor(int id, String name, int price, String matrix, int diag)   //конструктор
    {
        super(id, name, price); //вызов конструктора суперкласса
        param1 = matrix;
        param2 = diag;
    }
    public Label showAddParam1()  //определение функции для вывода доп.атрибута 1
    {
        return new Label("Matrix: "+param1);
    };
    public Label showAddParam2()  //определение функции для вывода доп.атрибута 2
    {
        return new Label("Diag: "+param2);
    };

}
