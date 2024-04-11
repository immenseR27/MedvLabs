package com.immenser.labs.term1.lab5;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<PeripheralUnit> units = new ArrayList<>();    //создание списка для объектов класса PeripheralUnit

        System.out.print("Number of printers: ");
        int pNumber = sc.nextInt();
        System.out.println();

        //добавление в список объектов Printer
        createUnitList(sc, "PRINTER", pNumber, units);

        System.out.print("Number of monitors: ");
        int mNumber = sc.nextInt();
        System.out.println();

        //добавление в список объектов Monitor
        createUnitList(sc, "MONITOR", mNumber, units);

        System.out.print("Изменить данные?(y/n): ");
        String ans = sc.next();
        System.out.println();
        while (ans.equals("y")) //изменение данных
        {
            System.out.print("Введите номер устройства: ");
            int ans1 = sc.nextInt();
            System.out.println();
            if (units.get(ans1).getClass().getSimpleName().equals("Printer")) {
                changeParams("PRINTER", ans1, pNumber, units, sc);
            }
            if (units.get(ans1).getClass().getSimpleName().equals("Monitor")) {
                changeParams("MONITOR", ans1, pNumber, units, sc);
            }
            System.out.println();
            System.out.print("Продолжить изменение?(y/n): ");
            ans = sc.next();
            System.out.println();
        }

        System.out.print("Вывести информацию об устройствах?(y/n): ");
        ans = sc.next();
        System.out.println();
        while (ans.equals("y")) //вывод информации
        {
            System.out.print("Введите номер устройства: ");
            int ans1 = sc.nextInt();
            System.out.println();

            if (units.get(ans1).getClass().getSimpleName().equals("Printer"))
            {
                int p = ans1;
                System.out.println("PRINTER_" + p);
            }
            if (units.get(ans1).getClass().getSimpleName().equals("Monitor"))
            {
                int m = ans1 - pNumber;
                System.out.println("MONITOR_" + m);
            }
            units.get(ans1).showPrice();
            units.get(ans1).showYear();
            units.get(ans1).showAddParam();
            System.out.print("Availability: ");
            if (PeripheralUnit.availArr[ans1])
                {System.out.println("yes");}
            else
                {System.out.println("no");}
            System.out.println();

            System.out.print("Продолжить вывод?(y/n): ");
            sc.nextLine();
            ans = sc.nextLine();
            System.out.println();
        }

        System.out.print("Найти минимальную цену устройства?(y/n): ");
        ans = sc.next();
        if (ans.equals("y"))
        {int minPrice = units.get(0).getPrice();
            //определение минимальной стоимости устройства
            for (PeripheralUnit unit : units) {
                if (unit.getPrice() < minPrice) {
                    minPrice = unit.getPrice();
                }
            }
            System.out.println("Min_price: "+minPrice);}
    }

    public static void createUnitList(Scanner sc, String name, int num, ArrayList<PeripheralUnit> units) {
        for (int i = 0; i < num; i++) //создание и добавление объектов в список устройств
        {
            System.out.printf("%s_%d\n", name, i);
            System.out.print("Price: ");
            int price = sc.nextInt();
            System.out.print("Year: ");
            int year = sc.nextInt();
            int availIndex = 0;
            switch (name){
                case "PRINTER" -> {
                    System.out.print("Color: ");
                    String color = sc.next();
                    units.add(new Printer(price, year, color));
                    availIndex = i;
                }
                case "MONITOR" -> {
                    System.out.print("Size: ");
                    int size = sc.nextInt();
                    units.add(new Monitor(price, year, size));
                    availIndex = num + i;
                }
            }
            System.out.print("Availability(y/n): ");
            sc.nextLine();
            String avail = sc.nextLine();
            PeripheralUnit.setAvail(availIndex, avail); //вызов статического метода
            System.out.println();
        }
    }

    public static void changeParams(String name, int ans, int pNum, ArrayList<PeripheralUnit> units, Scanner sc) {
        int pos = 0;
        switch (name) {
            case "PRINTER" -> {
                pos = ans;
            }
            case "MONITOR" -> {
                pos = ans - pNum;
            }
        }
        System.out.printf("%s_%d\n", name, pos);
        System.out.print("Price: ");
        int price = sc.nextInt();
        System.out.print("Year: ");
        int year = sc.nextInt();
        switch (name) {
            case "PRINTER" -> {
                System.out.print("Color: ");
                String color = sc.next();
                units.set(ans, new Printer(price, year, color));
            }
            case "MONITOR" -> {
                System.out.print("Size: ");
                int size = sc.nextInt();
                units.set(ans, new Monitor(price, year, size));
            }
        }
        System.out.print("Availability(y/n): ");
        String avail = sc.next();
        //изменение статической переменной
        PeripheralUnit.availArr[ans] = avail.equals("y");
    }
}

class PeripheralUnit  //класс периферийных устройств
{
    private int price;
    private int year;
    static boolean[] availArr = new boolean[100];	//статическая переменная

    static  //статический блок инициализации
    {
        for (int i=0; i<100; i++)
        {availArr[i]=false;}
    }
    {  //простой блок инициализации
        System.out.println("Склад периферийных устройств");
    }
    public PeripheralUnit(int price, int year) //конструктор
    {
        this.price = price;
        this.year = year;
    }
    static public void setAvail(int i, String a)   //статический метод
    {
        if (a.equals("y"))
        {availArr[i] = true;}
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
    public void showAddParam(){}; //объявление функции для вывода доп.атрибута
}

class Printer extends PeripheralUnit //класс принтеров, наследуемый от peripheral_unit
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
}

class Monitor extends PeripheralUnit   //класс мониторов, наследуемый от peripheral_unit
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
}


