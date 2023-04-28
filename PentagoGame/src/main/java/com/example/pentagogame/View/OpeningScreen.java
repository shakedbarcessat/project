package com.example.pentagogame.View;
import com.example.pentagogame.Controller.ControllerClass;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.File;

public class OpeningScreen extends Application {

    private ControllerClass controller = new ControllerClass(); //connection to the controller
    public static int num=0; //0- hVSh, 1- aiVSh

    public static void main(String[] args) {
        launch(args);
    }


    /**
     * //initializes the opening screen
     * @param primaryStage- the stage to start the screen
     */
    @Override
    public void start(Stage primaryStage) {//initializes the opening screen

        BorderPane board = new BorderPane();//the main board
        Screen screen = Screen.getPrimary();
        double x = screen.getBounds().getWidth();//the screen size
        double y = screen.getBounds().getHeight();//the screen size
        Button hVSh = new Button("HUMAN VS HUMAN");
        hVSh.setFont(new Font("Arial", 20));
        hVSh.setPrefSize(350, 75);
        hVSh.setTranslateX(-200);
        hVSh.setTranslateY(0);


        hVSh.setOnAction(event -> {
            controller.transferToBoard(primaryStage);//transfers to the board screen
        });


        Button aiVSh = new Button("HUMAN VS AI");
        aiVSh.setFont(new Font("Arial", 20));
        aiVSh.setPrefSize(350, 75);
        aiVSh.setTranslateX(-200);
        aiVSh.setTranslateY(0);


        aiVSh.setOnAction(event -> {
            controller.transferToAI(primaryStage);//transfers to the AI board screen
        });


        Button instructions = new Button("Instructions");
        instructions.setFont(new Font("Arial", 20));
        instructions.setPrefSize(200, 75);
        instructions.setTranslateX(-280);
        instructions.setTranslateY(0);


        instructions.setOnAction(event -> {
            controller.transferToistructions(primaryStage);//transfers to the instructions screen
        });


        VBox v = new VBox(10); // 10 pixels of spacing between the buttons

        Label l = new Label("Welcome to PENTAGO game :)" +
                "\nchoose one of the above options!" +
                "\nenjoy the game!!!\n\n\n\n");
        l.setTranslateX(-200);//250
        l.setTranslateY(0);//450
        l.setFont(new Font("Arial", 25));


        v.getChildren().add(l);
        v.getChildren().add(hVSh);
        v.getChildren().add(aiVSh);
        v.getChildren().add(instructions);
        v.setAlignment(Pos.TOP_RIGHT);

        VBox v2 = new VBox();
        Button exit_button = new Button("EXIT");
        exit_button.setPrefSize(120, 50);
        exit_button.setStyle("-fx-background-color: greenyellow;");


        exit_button.setOnAction(event -> {
            controller.setExitButton();//exit the game
        });

        v2.getChildren().add(exit_button);
        v2.setAlignment(Pos.TOP_LEFT);

        board.setCenter(v);
        board.setLeft(v2);




        File file = new File("src/main/ph/pentago1.jpg");
        String filePath = file.getAbsolutePath();
        Image image = new Image(filePath);//adding background image




        // Set the background image
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER, new BackgroundSize(100, 100,
                true, true, true, false));

        board.setBackground(new Background(backgroundImage));


        Scene scene = new Scene(board, x, y);
        primaryStage.setScene(scene);
        primaryStage.show();


    }

}