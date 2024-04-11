package com.immenser.labs.term2.lab1;

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

        PeripheralUnit unit = units[0];
        int num = 0;
        int monitors = 0;
        int printers = 0;
        for (int i=0; i<5; i++)
        {
            System.out.println(units[i].getName()+"_(ID="+i+")");
            System.out.println("Price: "+units[i].getPrice());

            if (units[i].getPrice() < unit.getPrice())  //поиск устройства с наименьшей ценой
            {
                unit = units[i];
                num = i;
            }

            if (units[i].getName().equals("Monitor"))    //кол-во устройств опред.типа
            {
                monitors += 1;
                System.out.println("Matrix: "+units[i].getParam1());
                System.out.println("Diag: "+units[i].getParam2());
            }
            else
            {
                printers += 1;
                System.out.println("Color: "+units[i].getParam1());
                System.out.println("Type: "+units[i].getParam2());
            }
            System.out.println();
        }
        System.out.println("Кол-во мониторов: "+monitors);
        System.out.println("Кол-во принтеров: "+printers);
        System.out.println();
        System.out.println("Устройство с минимальной ценой: "+unit.getName()+"_(ID="+num+")");
        System.out.println("   Price: "+unit.getPrice());
    }
}
