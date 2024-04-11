package com.immenser.labs.term2.lab3;

import java.util.Random;

class PeripheralUnit<A, B>  //обобщенный класс с двумя типами параметров
{
    String name;
    int price;
    A param1;
    B param2;

    PeripheralUnit(String name, int price, A param1, B param2)  //конструктор
    {
        this.name = name;
        this.price = price;
        this.param1 = param1;
        this.param2 = param2;
    }

    String getName()
    {return name;}

    int getPrice()
    {return price;}

    A getParam1()
    {return param1;}

    B getParam2()
    {return param2;}

}

class UnitNumber implements Runnable //класс, реализующий Runnable
{
    PeripheralUnit[] units;
    int monitors = 0;
    int printers = 0;

    UnitNumber(PeripheralUnit[] units)
    {
        this.units = units;}

    @Override
    public void run() //реализация метода run() интерфейса Runnable
    {
        System.out.println("Поток UnitNumber начал выполнение");
        System.out.println();
        for (int i = 0; i < 5; i++) {
            if (units[i].getName().equals("Monitor"))    //кол-во устройств опред.типа
            {
                monitors += 1;
            } else {
                printers += 1;
            }
        }
        System.out.println("Кол-во мониторов: " + monitors);
        System.out.println("Кол-во принтеров: " + printers);
        System.out.println("\n"+"Поток UnitNumber завершен");
    }
}

class MinPrice extends Thread  //класс, расширяющий Thread
{
    PeripheralUnit[] units;
    PeripheralUnit unit;
    int num = 0;

    MinPrice(PeripheralUnit[] units)
    {
        this.units = units;
        unit = units[0];
    }

    @Override
    public void run()   //переопределение метода run() класса Thread
    {
        System.out.println("Поток MinPrice начал выполнение"+"\n");
        for (int i=0; i<5; i++)
        {
            if (units[i].getPrice() < unit.getPrice())  //поиск устройства с наименьшей ценой
            {
                unit = units[i];
                num = i;
            }
        }
        System.out.println("\n"+"Устройство с минимальной ценой: "+unit.getName()+"_(ID="+ num +")"+"\n");
        System.out.println("\n"+"Min_price: "+unit.getPrice()+"\n");
        System.out.println("Поток Min_price завершен"+"\n");
    }
}

public class Main {

    public static void main(String[] args)
    {
        Random random = new Random();
        PeripheralUnit[] units = new PeripheralUnit[5]; //массив для объектов периферийных устройств
        String [] matrices = {"TN", "IPS", "VA"};
        String [] colors = {"black", "colorful"};
        String [] types = {"inkjet", "laser"};

        for (int i=0; i<5; i++) //заполнение массива периферийных устройств объектами типизированного класса
        {
            int r = random.nextInt(2);
            if (r==0)
            {
                int price = random.nextInt(10000)+10000;
                int matrix = random.nextInt(3);
                int diag = random.nextInt(41)+14;
                PeripheralUnit<String, Integer> unit = new PeripheralUnit<>("Monitor", price, matrices[matrix], diag);
                units[i] = unit;
            }
            else
            {
                int price = random.nextInt(5000)+5000;
                int color = random.nextInt(2);
                int type = random.nextInt(2);
                PeripheralUnit<String, String> unit = new PeripheralUnit<>("Printer", price, colors[color], types[type]);
                units[i] = unit;
            }
        }

        Thread thread1 = new Thread(new UnitNumber(units));  //создание потока для вычисления кол-ва устройств
        thread1.start();    //запуск потока

        Thread thread2 = new MinPrice(units);    //создание потока для определения минимальной цены
        thread2.start();    //запуск потока

        for (int i=0; i<5; i++)
        {
            System.out.println("\n"+units[i].getName()+"_(ID="+i+")");
            System.out.println("Price: "+units[i].getPrice());

            if (units[i].getName().equals("Monitor"))    //кол-во устройств опред.типа
            {
                System.out.println("Matrix: " + units[i].getParam1());
                System.out.println("Diag: " + units[i].getParam2());
            } else {
                System.out.println("Color: " + units[i].getParam1());
                System.out.println("Type: " + units[i].getParam2());
            }
        }
    }
}

