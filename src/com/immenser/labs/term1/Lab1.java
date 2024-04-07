package com.immenser.labs.term1;

import java.util.Scanner;
import java.util.Random;

public class Lab1
{

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);    //Объект sc класса Scanner

        System.out.println("Input number of words");
        int N = sc.nextInt();   //Ввод кол-ва слов

        System.out.println("Input word length");
        int L = sc.nextInt();   //Ввод длины слова

        Random r = new Random();    //Объект r класса Random

        StringBuilder s = new StringBuilder();  //Пустая строка
        String rs;  //Строка для реверса

        String[] text = new String[N];  //Массив для слов текста
        String[] pal = new String[N];   //Массив для палиндромов

        int k = 0; //Счетчик палиндромов

        for (int i = 0; i < N; i++)
        {
            for (int j = 0; j < L; j++)
            {
                int num = r.nextInt(26) + 97;   //Генерация числа от 97 до 122
                s.append((char) num);   //Добавление к слову символа с номером num
            }
            rs = new StringBuffer(s.toString()).reverse().toString();  //Реверсирование слова s в rs
            text[i] = s.toString();    //Добавление слова s в массив text
            if (rs.equals(s.toString()))   //Сравнение s и rs
            {
                pal[k] = s.toString(); //Добавление палиндрома rs в pal
                k += 1;
            }
            s = new StringBuilder(); //Обнуление строки
        }

        System.out.println("Text: " + String.join(" ", text)); //Вывод текста

        if (k != 0) //Проверка наличия палиндромов
        {
            for (int i = 0; i < k; i++)
            {
                s.append(pal[i]).append(" ");
            }
            System.out.println("Palindromes: " + s);    //Вывод палиндромов
        }
        else
        {
            System.out.println("No palindromes");
        }
    }
}

