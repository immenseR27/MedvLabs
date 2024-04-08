package com.immenser.labs.term1;

import java.util.Scanner;

public class Lab2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String answer = "y";

        while (answer.equals("y"))
        {System.out.println("Введите текст:");
            String text = sc.nextLine();

            //String s = "абвгдеёжзийклмнопрстуфхцчшщъыьэюяАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЭЮЯ1234567890 .,!?–-:;";    //набор символов алфавита
            String s = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890 .,!?–-:;'\"";
            int []codes = new int[s.length()];  //массив кодов символов
            for (int i=0; i<s.length(); i++)
            {codes[i]=i+1;}

            System.out.print("\nВыберите способ шифрования:\n1-Сдвиг\n2-Побитовое шифрование\nОтвет: ");
            int way = sc.nextInt();

            if (way==1) //метод сдвига
            {System.out.print("Введите величину сдвига: ");
                int r = sc.nextInt();   //сдвиг на r символов
                char []text1 = text.toCharArray();
                System.out.print("Зашифрованный текст: ");
                for (int i=0; i<text1.length; i++)
                {if (s.indexOf(text1[i])+r%s.length()+1 <= s.length())
                {text1[i]=s.charAt(s.indexOf(text1[i])+r%s.length());}
                else
                {text1[i]=s.charAt(r%s.length()-(s.length()-s.indexOf(text1[i])-1));}}
                System.out.println(text1);}
            sc.close();

            if (way==2) //побитовое шифрование
            {//String key = "СЕКРЕТ";
                String key = "SECRET";
                String []key_arr = new String[key.length()];
                for (int i=0; i< key_arr.length; i++)   //массив двоичных кодов символов ключа
                {
                    key_arr[i] = "0"+Integer.toBinaryString(codes[s.indexOf(key.toCharArray()[i])]);
                }
                String []text_arr = new String[text.length()];
                for (int i=0; i< text_arr.length; i++)  //массив двоичных кодов символов текста
                {
                    text_arr[i] = Integer.toBinaryString(codes[s.indexOf(text.toCharArray()[i])]);
                    while (text_arr[i].length()!=7)
                    {text_arr[i]="0"+text_arr[i];}
                }

                String [] new_arr = new String[text_arr.length];
                String [] result = new String[text_arr.length];
                for (int i=0; i<text_arr.length; i++)   //исключающее ИЛИ ключа и текста
                {
                    new_arr[i] = key_arr[i%key.length()];
                    StringBuilder res = new StringBuilder();
                    for (int j=0; j<7; j++)
                    {
                        if (new_arr[i].toCharArray()[j]==text_arr[i].toCharArray()[j])
                        {
                            res.append("0");}
                        else
                        {
                            res.append("1");}
                    }
                    result[i]= res.toString();
                }
                int []text2 = new int[result.length];
                char []text3 = new char[result.length];
                System.out.print("Зашифрованный текст: ");
                for (int i=0; i<text2.length; i++)  //получение символов кода по номерам
                {
                    text2[i]=Integer.parseInt(result[i], 2);
                    for (int j=0; j<codes.length; j++)
                    {if (text2[i]==codes[j])
                    {text3[i]=s.toCharArray()[j];
                        System.out.print(text3[i]);}
                    }
                }
                System.out.println();
                for (int i=0; i<text_arr.length; i++)   //исключающее ИЛИ ключа и шифрокода
                {
                    new_arr[i] = key_arr[i%6];
                    StringBuilder res = new StringBuilder();
                    for (int j=0; j<7; j++)
                    {
                        if (new_arr[i].toCharArray()[j]==result[i].toCharArray()[j])
                        {
                            res.append("0");}
                        else
                        {
                            res.append("1");}
                    }
                    result[i]= res.toString();
                }
                System.out.print("Расшифрованный текст: ");
                for (int i=0; i<text2.length; i++)  //получение символов исходного текста по номерам
                {
                    text2[i]=Integer.parseInt(result[i], 2);
                    for (int j=0; j<codes.length; j++)
                    {if (text2[i]==codes[j])
                    {text3[i]=s.toCharArray()[j];
                        System.out.print(text3[i]);}
                    }
                }}
            System.out.println("Продолжить?(y/n)");
            sc.nextLine();
            answer=sc.nextLine();
        }}
}

