package com.example.pentagogame;

import com.example.pentagogame.Controller.ControllerClass;
import com.example.pentagogame.Model.Board;
import com.example.pentagogame.View.OpeningScreen;
import javafx.application.Application;
import javafx.stage.Stage;


public class DemoDriver extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    public Board b= new Board();
    public OpeningScreen v= new OpeningScreen();
    @Override
    public void start(Stage primaryStage) {
        ControllerClass c= new ControllerClass();
        c.init();
        c.start_Game(primaryStage);
    }
}
