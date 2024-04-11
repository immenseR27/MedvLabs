package com.immenser.labs.term1.lab4;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Number of printers: ");
        int pNumber = sc.nextInt();
        System.out.println();

        ArrayList<PeripheralUnit> units = new ArrayList<>(pNumber);    //создание списочного массива для объектов класса peripheral_unit

        for (int i = 1; i <= pNumber; i++) //создание и добавление в список устройств объектов Printer
        {
            addUnit(sc,"PRINTER", i, units);
        }

        System.out.print("\nNumber of monitors: ");
        int mNumber = sc.nextInt();
        System.out.println();

        for (int i = 1; i <= mNumber; i++) //создание и добавление в список устройств объектов Monitor
        {
            addUnit(sc,"MONITOR", i, units);
        }

        int minPrice = units.get(0).getPrice();
        for (int i = 0; i < pNumber + mNumber; i++)   //определение минимальной цены устройства
        {
            if (units.get(i).getPrice()<minPrice)
            {
                minPrice = units.get(i).getPrice();
            }
        }
        System.out.println("\nMin_price: "+minPrice);
    }

    public static void addUnit(Scanner sc, String name, int i, ArrayList<PeripheralUnit> units) {
        System.out.printf("%s_%d\n", name, i);
        System.out.print("Price: ");
        int price = sc.nextInt();
        System.out.print("Year: ");
        int year = sc.nextInt();
        PeripheralUnit unit = null;
        switch (name){
            case "PRINTER" -> {
                System.out.print("Color: ");
                String color = sc.next();
                unit = new Printer(price, year, color);
            }
            case "MONITOR" -> {
                System.out.print("Size: ");
                int size = sc.nextInt();
                unit = new Monitor(price, year, size);
            }
        }
        units.add(unit);
    }
}

abstract class PeripheralUnit  //абстрактный класс периферийных устройств
{
    private int price;
    private int year;

    public PeripheralUnit(int price, int year) //конструктор
    {
        this.price = price;
        this.year = year;
    }

    public int getPrice()  //получение атрибута price
    {
        return price;
    }

    public void showPrice()   //вывод значения атрибута price
    {
        System.out.println("Price: "+price);
    }

    public void showYear() //вывод значения атрибута year
    {
        System.out.println("Year: "+year);
    }
    public abstract void showAddParam(); //определение абстрактной функция для вывода значения доп.атрибута
}

class Printer extends PeripheralUnit //класс принтеров, наследуемый от PeripheralUnit
{
    private String color;

    public Printer(int price, int year, String color)   //конструктор
    {
        super(price, year); //вызов конструктора суперкласса
        this.color = color;
    }

    public void showAddParam()    //реализация абстрактной функции для вывода значения доп.атрибута color
    {
        System.out.println("Color: "+color);
    }
};

class Monitor extends PeripheralUnit   //класс мониторов, наследуемый от PeripheralUnit
{
    private int size;

    public Monitor(int price, int year, int size)   //конструктор
    {
        super(price, year); //вызов конструктора суперкласса
        this.size = size;
    }

    public void showAddParam()    //реализация абстрактной функции для вывода значения доп.атрибута size
    {
        System.out.println("Size: "+size);
    }
};



