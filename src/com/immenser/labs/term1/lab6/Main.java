package com.immenser.labs.term1.lab6;

import java.util.Scanner;
import java.util.Random;

public class Main {
    public static void main(String[] args) {

        Scanner cs = new Scanner(System.in);
        Random rand = new Random();

        System.out.print("Number of printers: ");  //заполнение массива принтеров
        int pNumber = cs.nextInt();
        Printer[] printers = new Printer[pNumber];
        for (int i = 0; i < pNumber; i++) {
            int price = rand.nextInt(5000) + 5000;
            int year = rand.nextInt(20) + 2000;
            String[] color = new String[2];
            color[0] = "bw";
            color[1] = "cf";
            int col = rand.nextInt(2);
            if (col == 0) {
                printers[i] = new Printer(price, year, color[0]);}
            else {printers[i] = new Printer(price, year, color[1]);}
        }

        System.out.print("Number of monitors: ");  //заполнение массива мониторов
        int m_number = cs.nextInt();
        System.out.println();
        Monitor[] monitors = new Monitor[m_number];
        for (int i = 0; i < m_number; i++) {
            int price = rand.nextInt(20000) + 5000;
            int year = rand.nextInt(20) + 2000;
            int size = rand.nextInt(20) + 25;
            monitors[i] = new Monitor(price, year, size);
        }

        int minPrice = Math.min(minPrice("PRINTER", printers), minPrice("MONITOR", monitors));  //определение минимальной цены среди периферийных устройств
        System.out.println("min_price: "+minPrice);
    }

    public static int minPrice(String name, PeripheralUnit[] units) {
        int minPrice = units[0].getPrice();
        for (int i=0; i<units.length; i++)
        {
            System.out.printf("%s_%d\n", name, i);
            units[i].showPrice();
            units[i].showYear();
            units[i].showAddParam();
            System.out.println();
            if (units[i].getPrice()<minPrice) {minPrice=units[i].getPrice();}
        }
        return minPrice;
    }
}

interface PeripheralUnitInterface  //интерфейс периферийных устройств
{
    int getPrice();
    void showPrice();
    void showYear();
    void showAddParam();
}

class PeripheralUnit implements PeripheralUnitInterface //класс периферийных устройств
{
    protected int price;
    protected int year;

    public PeripheralUnit(int price, int year)   //конструктор
    {
        this.price=price;
        this.year=year;
    }

    public int getPrice()  //реализация интерфейса PeripheralUnitInterface
    {return price;}

    public void showPrice()
    {System.out.println("price: "+price);}

    public void showYear()
    {System.out.println("year: "+year);}

    public void showAddParam() {}
}

class Printer extends PeripheralUnit //класс принтеров
{
    private String color;

    public Printer(int price, int year, String color)	//конструктор
    {
        super(price, year);
        this.color=color;
    }

    public void showAddParam()  //переопределение метода
    {System.out.println("color: "+color);}
}

class Monitor extends PeripheralUnit //класс мониторов
{
    private int size;

    public Monitor(int price, int year, int size) //конструктор
    {
        super(price, year);
        this.size=size;
    }

    public void showAddParam()  //переопределение метода
    {System.out.println("size: "+size);}
}


