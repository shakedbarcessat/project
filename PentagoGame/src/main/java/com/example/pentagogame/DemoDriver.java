package com.example.pentagogame;
import com.example.pentagogame.Controller.ControllerClass;
import javafx.application.Application;
import javafx.stage.Stage;


public class DemoDriver extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * initialize the controller
     *
     * @param primaryStage- the main stage to start the game
     */
    @Override
    public void start(Stage primaryStage) {
        ControllerClass c = new ControllerClass();
        c.init(); //initialize the model and the view
        c.start_Game(primaryStage); //starts the opening screen
    }
}
