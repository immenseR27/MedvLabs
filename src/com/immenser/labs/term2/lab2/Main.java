package com.immenser.labs.term2.lab2;

import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Iterator;

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
    public A getAddParam1(){return param1;} //получение доп.атрибута 1
    public B getAddParam2(){return param2;} //получение доп.атрибута 2
    public void showAddParam1(){}  //объявление функции для вывода доп.атрибута 1
    public void showAddParam2(){}  //объявление функции для вывода доп.атрибута 2
}

class Printer extends PeripheralUnit<String, String>  //класс принтеров, наследуемый от Peripheral_Unit
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
    }
    public void showAddParam2()  //определение функции для вывода доп.атрибута 2
    {
        System.out.println("Type: "+param2);
    }
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
    }
    public void showAddParam2()  //определение функции для вывода доп.атрибута 2
    {
        System.out.println("Diag: "+param2);
    }
}

public class Main {

    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        long curr;
        Random random = new Random();
        ArrayList<PeripheralUnit> array_list = new ArrayList<>(); //ArrayList для объектов периферийных устройств
        LinkedList<PeripheralUnit> linked_list = new LinkedList<>(); //LinkedList для объектов периферийных устройств

        String [] matrix_arr = {"TN", "IPS", "VA"};
        String [] color_arr = {"black", "colorful"};
        String [] type_arr = {"inkjet", "laser"};

        PeripheralUnit ob;
        Iterator <PeripheralUnit> iter;
        int min_price;

        System.out.println("1.Создать список");
        System.out.println("2.Поиск элемента списка по значению параметра");
        System.out.println("3.Удаление объекта из списка с заданным параметром");
        System.out.println("4.Расчет вычисляемого показателя");
        System.out.println("5.Выход из программы");

        System.out.print("Ваш ответ: ");
        int ans = in.nextInt();
        while (ans!= 5)
        {
            switch (ans)
            {
                case (1):   //заполнение списков объектами классов-наследников
                    curr = System.currentTimeMillis();
                    for (int i=0; i<100000; i++)
                    {
                        int r = random.nextInt(2);
                        if (r==0)
                        {
                            int price = random.nextInt(10000)+10000;
                            int matrix = random.nextInt(3);
                            int diag = random.nextInt(41)+14;
                            Monitor obj = new Monitor (i+1,"Monitor", price, matrix_arr[matrix], diag);
                            array_list.add(obj);    //добавление в array_list
                            linked_list.add(obj);   //добавление в linked_list
                        }
                        else
                        {
                            int price = random.nextInt(5000)+5000;
                            int color = random.nextInt(2);
                            int type = random.nextInt(2);
                            Printer obj = new Printer (i+1,"Printer", price, color_arr[color], type_arr[type]);
                            array_list.add(obj);    //добавление в array_list
                            linked_list.add(obj);   //добавление в linked_list
                        }
                    }
                    System.out.println(System.currentTimeMillis()-curr);
                    System.out.println("\nСписки сформированы\n");
                    break;

                case (2):   //поиск устройства по id
                    System.out.print("\nВведите id для поиска: ");
                    int id = in.nextInt();

                    ob = array_list.get(0);
                    iter = array_list.iterator();   //поиск по array_list
                    curr = System.currentTimeMillis();
                    while (ob.getId()!=id)
                    {ob = iter.next();}
                    System.out.println(System.currentTimeMillis()-curr);

                    ob = linked_list.get(0);
                    iter = linked_list.iterator();  //поиск по linked_list
                    curr = System.currentTimeMillis();
                    while (ob.getId()!=id)
                    {ob = iter.next();}
                    System.out.println(System.currentTimeMillis()-curr);

                    System.out.println("Найдено устройство: "+ob.getName());
                    System.out.println("Price: "+ob.getPrice());
                    ob.showAddParam1();
                    ob.showAddParam2();
                    System.out.println();
                    break;

                case (3):   //удаление устройства по id
                    System.out.print("\nВведите id для удаления: ");
                    id = in.nextInt();

                    ob = array_list.get(0);
                    iter = array_list.iterator();   //удаление в array_list
                    while (ob.getId()!=id)
                    {ob = iter.next();}

                    System.out.println("Удаляемое устройство: "+ob.getName());
                    System.out.println("Price: "+ob.getPrice());
                    ob.showAddParam1();
                    ob.showAddParam2();

                    curr = System.currentTimeMillis();
                    array_list.remove(ob);
                    System.out.println(System.currentTimeMillis()-curr);
                    System.out.println("Устройство удалено из списка array_list");

                    ob = linked_list.get(0);
                    iter = linked_list.iterator();  //удаление в linked_list
                    while (ob.getId()!=id)
                    {ob = iter.next();}
                    curr = System.currentTimeMillis();
                    linked_list.remove(ob);
                    System.out.println(System.currentTimeMillis()-curr);
                    System.out.println("Устройство удалено из списка linked_list\n");
                    break;

                case (4):   //вычисление минимальной цены устройства
                    ob = array_list.get(0);
                    min_price = ob.getPrice();
                    curr = System.currentTimeMillis();
                    for (PeripheralUnit o : array_list)    //по array_list
                    {
                        if (o.getPrice()<min_price)
                        {
                            min_price=o.getPrice();
                            ob=o;
                        }
                    }
                    System.out.println(System.currentTimeMillis()-curr);

                    curr = System.currentTimeMillis();
                    ob = linked_list.get(0);
                    min_price = ob.getPrice();
                    for (PeripheralUnit o : linked_list)   //по linked_list
                    {
                        if (o.getPrice()<min_price)
                        {
                            min_price=o.getPrice();
                            ob=o;
                        }
                    }
                    System.out.println(System.currentTimeMillis()-curr);

                    System.out.println("\nУстройство с минимальной ценой: "+ob.getName());
                    System.out.println("Id: "+ob.getId());
                    System.out.println("Price: "+ob.getPrice());
                    ob.showAddParam1();
                    ob.showAddParam2();
                    System.out.println();
                    break;
            }

            System.out.println("1.Создать список");
            System.out.println("2.Поиск элемента списка по значению параметра");
            System.out.println("3.Удаление объекта из списка с заданным параметром");
            System.out.println("4.Расчет вычисляемого показателя");
            System.out.println("5.Выход из программы");
            System.out.print("Ваш ответ: ");
            ans = in.nextInt();
        }
    }
}
