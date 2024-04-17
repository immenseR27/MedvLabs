package com.immenser.labs.term2.lab6;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import java.util.Random;

public class App extends Application {  //класс App, наследующий класс Application
    static PeripheralUnit func(Search obj, PeripheralUnit[] units)  //метод, в который передается ссылка на объект класса, реализующего интерфейс Search
    {
        return obj.minPrice(units);
    }

    Search lambdaObj = (arr) ->    //объект, реализующий метод интерфейса Search с помощью лямбда-выражения
    {
        int minPrice = arr[0].getPrice();
        PeripheralUnit obj = arr[0];

        for (PeripheralUnit peripheralUnit : arr)
        {
            if (peripheralUnit.getPrice() < minPrice)
            {
                minPrice = peripheralUnit.getPrice();
                obj = peripheralUnit;
            }
        }
        return obj;
    };

    public void showUnits(String unit, PeripheralUnit[] units, HBox hBox){    //функция для отображения параметров устройств
        for (PeripheralUnit peripheralUnit : units){
            VBox vBox = new VBox();
//            vBox.setAlignment(Pos.CENTER);
            vBox.setPadding(new Insets(10, 10, 0, 0));
            hBox.getChildren().add(vBox);
            if (peripheralUnit.getName().equals(unit) || unit.equals("All")){
                Label l1 = new Label("Name: " + peripheralUnit.getName());
                Label l2 = new Label("Id: " + peripheralUnit.getId());
                Label l3 = new Label("Price: " + peripheralUnit.getPrice());
                vBox.getChildren().addAll(l1, l2, l3, peripheralUnit.showAddParam1(), peripheralUnit.showAddParam2());
            }
        }
    }

    public void showMin(PeripheralUnit[] units, HBox hBox){   //функция для отображения устройства с минимальной ценой
        VBox vBox = new VBox();
//        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(10, 10, 0, 0));
        hBox.getChildren().add(vBox);
        Label l1 = new Label("Устройство с минимальной ценой: "+func(lambdaObj, units).getName());
        Label l2 = new Label("Id: "+func(lambdaObj, units).getId());
        Label l3 = new Label("Price: "+func(lambdaObj, units).getPrice());
        Label l4 = new Label(""+func(lambdaObj, units).getAddParam1());
        Label l5 = new Label(""+func(lambdaObj, units).getAddParam2());
        vBox.getChildren().addAll(l1, l2, l3, l4, l5);
    }

    public static void main(String[] args) {    //вызов в главной функции метода запуска приложения
        Application.launch();
    }

    @Override
    public void start(Stage stage) {    //переопределение метода start() класса Application

        Random random = new Random();

        String [] matrices = {"TN", "IPS", "VA"};
        String [] colors = {"black", "colorfull"};
        String [] types = {"inkjet", "laser"};

        PeripheralUnit [] units = new PeripheralUnit[10];

        for (int i=0; i<10; i++) //заполнение массива объектами периферийных устройств
        {
            int r = random.nextInt(2);
            if (r==0)
            {
                int price = random.nextInt(10000)+10000;
                int matrix = random.nextInt(3);
                int diag = random.nextInt(41)+14;
                Monitor obj = new Monitor (i+1,"Monitor", price, matrices[matrix], diag);
                units[i] = obj;
            }
            else
            {
                int price = random.nextInt(5000)+5000;
                int color = random.nextInt(2);
                int type = random.nextInt(2);
                Printer obj = new Printer (i+1,"Printer", price, colors[color], types[type]);
                units[i] = obj;
            }
        }

        VBox root = new VBox(); //корневой узел сцены
//        root.setAlignment(Pos.CENTER);
        Scene scene = new Scene(root, 1000, 500);
        stage.setScene(scene);

        ComboBox<String> type = new ComboBox<>();   //выпадающее меню для выбора типа устройств
        type.getItems().setAll("All", "Printers", "Monitors");
        type.setValue("Выберите тип устройства");

        HBox hBox = new HBox();
//        hBox.setAlignment(Pos.CENTER);
        hBox.setPadding(new Insets(10, 10, 10, 10));
        Label label = new Label("Показать устройство с минимальной ценой");
        CheckBox minPrice = new CheckBox();    //чекбокс для вывода устройства с минимальной ценой
        hBox.getChildren().addAll(minPrice, label);

        Button showBtn = new Button("Показать");    //кнопка

        root.getChildren().addAll(type, hBox, showBtn); //добавление узлов на сцену

        HBox hBox1 = new HBox();
//        hBox1.setAlignment(Pos.CENTER);
        hBox1.setPadding(new Insets(10, 10, 10, 10));
        HBox hBox2 = new HBox();
//        hBox2.setAlignment(Pos.CENTER);
        hBox2.setPadding(new Insets(10, 10, 10, 10));
        root.getChildren().addAll(hBox1, hBox2);

        //реакция на нажатие кнопки
        showBtn.setOnAction(ае -> {

            hBox1.getChildren().clear();
            hBox2.getChildren().clear();

            if (type.getValue().equals("All")) {    //вызов метода showUnits() для вывода всех устройств
                showUnits("All", units, hBox1);
            }

            if (type.getValue().equals("Printers")) {   //вызов метода showUnits() для вывода всех принтеров
                showUnits("Printer", units, hBox1);
            }

            if (type.getValue().equals("Monitors")){    //вызов метода showUnits() для вывода всех мониторов
                showUnits("Monitor", units, hBox1);
            }

            if (minPrice.isSelected()){    //вызов метода showMin() для вывода устройства с минимальной ценой
                showMin(units, hBox2);
            }
        });

        stage.show();
    }

}
