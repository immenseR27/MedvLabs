package com.immenser.labs.term2.lab4;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Random;

@Retention(RetentionPolicy.RUNTIME) //политика удержания аннотации
@interface MyAnnotation //создание аннотации
{
    String value(); //спец.переменная
    String str() default  "class object was created."; //переменная String со значением по умолчанию
}
class PeripheralUnit<A, B>  //базовый класс периферийных устройств
{
    private int id;
    private String name;
    private int price;
    protected A param1;
    protected B param2;

    public PeripheralUnit(){};

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

    public A getAddParam1() //получение доп.атрибута 1
    {
        return param1;
    }

    public B getAddParam2() //получение доп.атрибута 2
    {
        return param2;
    }

    public void showAddParam1() {} //объявление функции для вывода доп.атрибута 1

    public void showAddParam2() {}    //объявление функции для вывода доп.атрибута 2

    @MyAnnotation("Printer")    //аннотирование внутреннего класса Printer
    class Printer extends PeripheralUnit<String, String>   //класс принтеров, наследуемый от Peripheral_Unit
    {
        public Printer(int id, String name, int price, String color, String type)   //конструктор
        {
            super(id, name, price); //вызов конструктора суперкласса
            param1 = color;
            param2 = type;
        }

        public void showAddParam1()    //определение функции для вывода доп.атрибута 1
        {
            System.out.println("Color: " + param1);
        }

        public void showAddParam2()    //определение функции для вывода доп.атрибута 2
        {
            System.out.println("Type: " + param2);
        }
    }

    @MyAnnotation("Monitor") //аннотирование внутреннего класса Monitor
    class Monitor extends PeripheralUnit<String, Integer>   //класс мониторов, наследуемый от Peripheral_Unit
    {
        public Monitor(int id, String name, int price, String matrix, int diag)   //конструктор
        {
            super(id, name, price); //вызов конструктора суперкласса
            param1 = matrix;
            param2 = diag;
        }

        public void showAddParam1()    //определение функции для вывода доп.атрибута 1
        {
            System.out.println("Matrix: " + param1);
        }

        public void showAddParam2()    //определение функции для вывода доп.атрибута 2
        {
            System.out.println("Diag: " + param2);
        }
    }

    PeripheralUnit[] units = new PeripheralUnit[5];

    public PeripheralUnit[] generateArray()
    {
        Random random = new Random();

        String [] matrices = {"TN", "IPS", "VA"};
        String [] colors = {"black", "colorful"};
        String [] types = {"inkjet", "laser"};

        Class cl;
        MyAnnotation annotation;

        for (int i = 0; i < 5; i++)
        {
            int r = random.nextInt(2)+1;
            if (r==1)
            {
                int price = random.nextInt(10000)+10000;
                int matrix = random.nextInt(3);
                int diag = random.nextInt(41)+14;
                Monitor monitor = new Monitor (i+1,"Monitor", price, matrices[matrix], diag);
                units[i]=monitor;    //добавление Monitor в array
                cl = monitor.getClass();    //получение объекта типа class для объекта класса Monitor
                annotation = (MyAnnotation) cl.getAnnotation(MyAnnotation.class); //получение аннотации MyAnnotation для класса Monitor
                System.out.println(annotation.value()+" "+annotation.str()); //вывод значений полей аннотации
            }
            if (r==2)
            {
                int price = random.nextInt(5000)+5000;
                int color = random.nextInt(2);
                int type = random.nextInt(2);
                Printer printer = new Printer (i+1,"Printer", price, colors[color], types[type]);
                units[i]=printer;    //добавление Printer в array
                cl = printer.getClass();    //получение объекта типа class для объекта класса Printer
                annotation = (MyAnnotation) cl.getAnnotation(MyAnnotation.class);  //получение аннотации MyAnnotation для класса Printer
                System.out.println(annotation.value()+" "+annotation.str());  //вывод значений полей аннотации
            }
        }
        System.out.println();
        return units;
    }
}

public class Main {

    public static void main(String[] args)
    {
        PeripheralUnit unit = new PeripheralUnit();
        PeripheralUnit units[] = unit.generateArray();

        int minPrice;
        //вычисление минимальной цены устройства
        for (PeripheralUnit peripheralUnit : units)
        {
            System.out.println("Name: " + peripheralUnit.getName());
            System.out.println("Id: " + peripheralUnit.getId());
            System.out.println("Price: " + peripheralUnit.getPrice());
            peripheralUnit.showAddParam1();
            peripheralUnit.showAddParam2();
            System.out.println();

            minPrice = units[0].getPrice();
            if (peripheralUnit.getPrice() <= minPrice) {
                unit = peripheralUnit;
            }
        }

        System.out.println("Устройство с минимальной ценой: "+unit.getName());
        System.out.println("Id: "+unit.getId());
        System.out.println("Price: "+unit.getPrice());
        unit.showAddParam1();
        unit.showAddParam2();
    }
}

