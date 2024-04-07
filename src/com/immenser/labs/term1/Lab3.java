package com.immenser.labs.term1;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.Date;

public class Lab3 {
    static void p(String s) {
        System.out.println(s);
    }
    static void addressString(File f) {
        System.out.println("\nВы находитесь здесь: " + f.getPath());
    }
    static void list(File dir) { // метод для вывода хранящихся в каталоге объектов
        String[] paths; // массив названий объектов, хранящихся в данном каталоге
        paths = dir.list();
        String x;
        int c = paths.length; // кол-во объектов в каталоге
        if (c == 0) System.out.println("В каталоге пусто");
        else {
            System.out.println("В данном каталоге находятся следующие объекты:");
            for (String path:paths) {
                File f = new File(dir.getPath() + "/" + path);
                if (f.isFile()) x = "файл";
                else if (f.isDirectory()) x = "каталог";
                else x = "";
                System.out.println(path + " - " + x);
            }
        }
    }
    static void makeDir(File currentDir, String nameNewDir) { //метод для создания каталога
        try {
            File f = new File(currentDir + "/" + nameNewDir);
            if (f.mkdir()) System.out.println("Каталог успешно создан");
            else System.out.println("Каталог уже существует");
        }
        catch (Exception e) {
            System.err.println("Что-то пошло не так");
        }
    }
    static void makeFile(File currentDir, String nameNewFile) { //метод для создания файла
        try {
            File f = new File(currentDir + "/" + nameNewFile + ".txt");
            if (f.createNewFile()) System.out.println("Файл успешно создан");
            else System.out.println("Файл уже существует");
        } catch (Exception e) {
            System.err.println("Что-то пошло не так");
        }
    }
    static void delete(File currentDir, String name) { // метод для удаления файла/каталога
        File f = new File(currentDir + "/" + name);
        try {
            if (f.isDirectory()) {
                String[] paths; // массив названий объектов, хранящихся в данном каталоге
                paths = f.list();
                int c = paths.length; // кол-во объектов в каталоге
                if (c != 0) { // если папка не пустая
                    System.out.println("Каталог " + f.getName() + " не пустой. Вы хотите удалить каталог и все объекты в нём?");
                    System.out.println("1 - да");
                    System.out.println("2 - нет");
                    Scanner in = new Scanner(System.in);
                    int x = in.nextInt();
                    if (x == 1) {
                        for (String path : paths) { // удалить все файлы в каталоге
                            //System.out.println(paths[i]);
                            delete(f, path);
                            String[] tempPaths; // массив названий объектов, хранящихся в данном каталоге
                            tempPaths = f.list();
                            int k = tempPaths.length; // кол-во объектов в каталоге
                            if (k == 0)
                                delete(f, ""); // для удаления корневого (текущего) каталога, в котором только что были удалены все файлы
                        }
                    }
                } else { // если каталог пустая
                    if (f.delete()) System.out.println("Каталог " + f.getName() + " успешно удалён");
                    else System.out.println("Невозможно удалить каталог " + f.getName());
                }
            } else { // если это файл
                if (f.delete()) System.out.println("Файл " + f.getName() + " успешно удалён");
                else System.out.println("Невозможно удалить файл" + f.getName());
            }
        } catch (Exception e) {
            System.out.println("Что-то пошло не так");
        }
    }
    static void properties(File currentDir, String name) { // метод для вывода свойств файла/каталога
        File f = new File(currentDir + "/" + name);
        if (f.exists()) {
            if (f.isDirectory()) {
                System.out.println("Это не файл, а каталог!");
                p("Имя каталога: " + f.getName());
                p("Путь каталога : " + f.getPath());
                p("Размер каталога : " + f.length() + " байт");
                p("Последнее изменение в каталоге :" + new Date(f.lastModified()));
            } else {
                p("Имя файла: " + f.getName());
                p("Путь файла : " + f.getPath());
                p("Размер файла : " + f.length() + " байт");
                p("Последнее изменение в файле :" + new Date(f.lastModified()));
                p(f.canWrite() ? "Файл доступен для записи" : "Файл не доступен для записи");
                p(f.canRead() ? "Файл доступен для чтения" : "Файл не доступен для чтения");
            }
        } else System.out.println("Файл/каталог не существует");
    }
    static void TextEdit(File currentDir, String name) throws IOException { // открытие файла в текстовом редакторе
        File f = new File(currentDir + "/" + name);
        if (f.exists()) {
            if (f.isDirectory()) {
                System.out.println("Это не файл, а каталог!");
            }
            else {
                Runtime runtime = Runtime.getRuntime();
                Process process = runtime.exec("notepad " + currentDir + "/" + name);
            }
        }
    }
    static void menu() throws IOException { // метод для взаимодействия с пользователем (меню)
        File startDir = new File("D://3 курс"); // стартовая директория, в которой пользователь находится в момент запуска программы
        File currentDir = new File(startDir.getPath()); // текущая директория
        System.out.println("Добро пожаловать в файловый менеджер!");
        System.out.println("Ваша исходная директория: " + startDir.getPath());
        Scanner in = new Scanner(System.in);
        int ans = 1;
        while (ans != 0) {
            System.out.println("\nВыберите действие:");
            System.out.println("0 - выход");
            System.out.println("1 - отобразить список файлов/папок в каталоге");
            System.out.println("2 - перейти в выбранный каталог");
            System.out.println("3 - перейти на уровень выше");
            System.out.println("4 - создать новый каталог в текущем каталоге");
            System.out.println("5 - создать новый файл в текущем каталоге");
            System.out.println("6 - удалить выбранный файл/папку");
            System.out.println("7 - вывести свойства выбранного файла");
            System.out.println("8 - открыть выбранный файл в текстовом редакторе");
            ans = in.nextInt();
            if (ans == 1) {
                addressString(currentDir);
                list(currentDir);
            } else if (ans == 2) {
                addressString(currentDir);
                System.out.println("Выберите каталог из списка");
                list(currentDir);
                Scanner in1 = new Scanner(System.in);
                String chooseDir = in1.nextLine();
                currentDir = new File(currentDir.getPath() + "/" + chooseDir);
                addressString(currentDir);
            } else if (ans == 3) {
                currentDir = new File(currentDir.getParent());
                addressString(currentDir);
            } else if (ans == 4) {
                addressString(currentDir);
                System.out.println("Введите название для нового каталога");
                Scanner in1 = new Scanner(System.in);
                String nameNewDir = in1.nextLine();
                makeDir(currentDir, nameNewDir);
                addressString(currentDir);
            } else if (ans == 5) {
                addressString(currentDir);
                System.out.println("Введите название для нового файла");
                Scanner in1 = new Scanner(System.in);
                String nameNewFile = in1.nextLine();
                makeFile(currentDir, nameNewFile);
                addressString(currentDir);
            } else if (ans == 6) {
                addressString(currentDir);
                list(currentDir);
                System.out.println("Выберите файл/папку для удаления");
                Scanner in1 = new Scanner(System.in);
                String chooseFile = in1.nextLine();
                delete(currentDir, chooseFile);
            } else if (ans == 7) {
                addressString(currentDir);
                list(currentDir);
                System.out.println("Выберите файл для вывода его свойств");
                Scanner in1 = new Scanner(System.in);
                String chooseFile = in1.nextLine();
                properties(currentDir, chooseFile);
                addressString(currentDir);
            } else if (ans == 8) {
                addressString(currentDir);
                list(currentDir);
                System.out.println("Выберите файл для открытия");
                Scanner in1 = new Scanner(System.in);
                String chooseFile = in1.nextLine();
                TextEdit(currentDir, chooseFile);
                addressString(currentDir);
            }
        }
    }
    public static void main(String[] args) throws IOException {
        menu();
    }
}

