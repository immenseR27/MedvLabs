package com.immenser.labs.term2.lab5;

import java.util.Random;

interface Search    //функциональный интерфейс
{
    PeripheralUnit minPrice(PeripheralUnit[] array);
}

class ForMethod    //класс, содержащий собственный метод min_price
{
    static PeripheralUnit minPrice(PeripheralUnit[] units)
    {
        int minPrice = units[0].getPrice();
        PeripheralUnit unit = units[0];

        for (PeripheralUnit peripheralUnit : units)
        {
            if (peripheralUnit.getPrice() < minPrice)
            {
                minPrice = peripheralUnit.getPrice();
                unit = peripheralUnit;
            }
        }
        return unit;
    }
}

class PeripheralUnit<A, B>  //базовый класс периферийных устройств
{
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
    public A get_add_param1(){return param1;} //получение доп.атрибута 1
    public B get_add_param2(){return param2;} //получение доп.атрибута 2
    public void showAddParam1(){};//объявление функции для вывода доп.атрибута 1
    public void showAddParam2(){};    //объявление функции для вывода доп.атрибута 2
}

class Printer extends PeripheralUnit<String, String>   //класс принтеров, наследуемый от Peripheral_Unit
{
    public Printer(int id, String name, int price, String color, String type)   //конструктор
    {
        super(id, name, price); //вызов конструктора суперкласса
        param1 = color;
        param2 = type;
    }
    public void showAddParam1()  //определение функции для вывода доп.атрибута 1
    {
        System.out.println("Color: "+param1);
    };
    public void showAddParam2()  //определение функции для вывода доп.атрибута 2
    {
        System.out.println("Type: "+param2);
    };
}

class Monitor extends PeripheralUnit<String, Integer>   //класс мониторов, наследуемый от Peripheral_Unit
{
    public Monitor(int id, String name, int price, String matrix, int diag)   //конструктор
    {
        super(id, name, price); //вызов конструктора суперкласса
        param1 = matrix;
        param2 = diag;
    }
    public void showAddParam1()  //определение функции для вывода доп.атрибута 1
    {
        System.out.println("Matrix: "+param1);
    };
    public void showAddParam2()  //определение функции для вывода доп.атрибута 2
    {
        System.out.println("Diag: "+param2);
    };
}

public class Main {

    static PeripheralUnit func(Search obj, PeripheralUnit[] units)  //метод, в который передается ссылка на объект класса, реализующего интерфейс Search
    {
        return obj.minPrice(units);
    }

    public static void main(String[] args)
    {
        Random random = new Random();

        String [] matrices = {"TN", "IPS", "VA"};
        String [] colors = {"black", "colorful"};
        String [] types = {"inkjet", "laser"};

        PeripheralUnit[] units = new PeripheralUnit[5];

        for (int i=0; i<5; i++) //заполнение массива объектами периферийных устройств
        {
            int r = random.nextInt(2);
            if (r==0)
            {
                int price = random.nextInt(10000)+10000;
                int matrix = random.nextInt(3);
                int diag = random.nextInt(41)+14;
                Monitor obj = new Monitor (i+1,"Monitor", price, matrices[matrix], diag);
                units[i] = obj;
            }
            else
            {
                int price = random.nextInt(5000)+5000;
                int color = random.nextInt(2);
                int type = random.nextInt(2);
                Printer obj = new Printer (i+1,"Printer", price, colors[color], types[type]);
                units[i] = obj;
            }
        }

        for (PeripheralUnit peripheralUnit : units)   //вывод значений полей объектов массива
        {
            System.out.println("Name: " + peripheralUnit.getName());
            System.out.println("Id: " + peripheralUnit.getId());
            System.out.println("Price: " + peripheralUnit.getPrice());
            peripheralUnit.showAddParam1();
            peripheralUnit.showAddParam2();
            System.out.println();
        }

        Search lambdaObj = (peripheralUnits) ->    //объект, реализующий метод интерфейса Search с помощью лямбда-выражения
        {
            int minPrice = peripheralUnits[0].getPrice();
            PeripheralUnit unit = peripheralUnits[0];

            for (PeripheralUnit peripheralUnit : peripheralUnits)
            {
                if (peripheralUnit.getPrice() < minPrice)
                {
                    minPrice = peripheralUnit.getPrice();
                    unit = peripheralUnit;
                }
            }
            return unit;
        };

        System.out.println("***Вычисление заданного показателя с использованием лямбда-выражения***");
        System.out.println("Устройство с минимальной ценой: "+func(lambdaObj, units).getName());
        System.out.println("Id: "+func(lambdaObj, units).getId());
        System.out.println("Price: "+func(lambdaObj, units).getPrice());
        func(lambdaObj, units).showAddParam1();
        func(lambdaObj, units).showAddParam2();

        System.out.println("\n***Вычисление заданного показателя с использованием ссылки на метод***");
        System.out.println("Устройство с минимальной ценой: "+func(ForMethod::minPrice, units).getName());
        System.out.println("Id: "+func(ForMethod::minPrice, units).getId());
        System.out.println("Price: "+func(ForMethod::minPrice, units).getPrice());
        func(ForMethod::minPrice, units).showAddParam1();
        func(ForMethod::minPrice, units).showAddParam2();
    }
}

