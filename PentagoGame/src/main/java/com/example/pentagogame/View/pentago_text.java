package com.example.pentagogame.View;

import com.example.pentagogame.Controller.HelloController;
import com.example.pentagogame.Model.Board;
import javafx.application.Application;
import javafx.stage.Stage;


public class pentago_text extends Application {
    private HelloController con;

    @Override
    public void start(Stage stage) {
        Board b= new Board();
        View2 v= new View2(stage);
        //this.con= new HelloController(b, v);
    }
    public static void main(String[] args) {
        launch(args);
        pentago_text p= new pentago_text();
        p.start(new Stage());
    }
}
