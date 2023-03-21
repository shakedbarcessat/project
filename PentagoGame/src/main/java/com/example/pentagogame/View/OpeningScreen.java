package com.example.pentagogame.View;

import com.example.pentagogame.Controller.ControllerClass;
import com.example.pentagogame.Controller.HelloController;
import com.example.pentagogame.Model.Board;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class OpeningScreen extends Application {


    private ControllerClass controller = new ControllerClass();

    public static void main(String[] args) {
        launch(args);
    }




    @Override
    public void start(Stage primaryStage) {

        BorderPane board= new BorderPane();
        Screen screen= Screen.getPrimary();
        double x= screen.getBounds().getWidth();
        double y= screen.getBounds().getHeight();
        Button hVSh= new Button("HUMAN VS HUMAN");
        hVSh.setFont(new Font("Arial", 20));
        hVSh.setPrefSize(350, 75);
        hVSh.setTranslateX(-200);//250
        hVSh.setTranslateY(0);//450


        hVSh.setOnAction(event -> {
            controller.transferToBoard(primaryStage);
        });


        Button aiVSh= new Button("HUMAN VS AI");
        aiVSh.setFont(new Font("Arial", 20));
        aiVSh.setPrefSize(350, 75);
        aiVSh.setTranslateX(-200);//250
        aiVSh.setTranslateY(0);//450
        Button instructions= new Button("Instructions");
        instructions.setFont(new Font("Arial", 20));
        instructions.setPrefSize(200, 75);
        instructions.setTranslateX(-280);//250
        instructions.setTranslateY(0);//450


        instructions.setOnAction(event -> {
            controller.transferToDistructions(primaryStage);
        });


        VBox v = new VBox(10); // 10 pixels of spacing between the buttons

        Label l= new Label("Welcome to PENTAGO game :)" +
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

        VBox v2= new VBox();
        Button exit_button= new Button("EXIT");
        exit_button.setPrefSize(120, 50);
        exit_button.setStyle("-fx-background-color: greenyellow;");


        exit_button.setOnAction(event -> {
            controller.setExitButton();
        });


        v2.getChildren().add(exit_button);
        v2.setAlignment(Pos.TOP_LEFT);

        board.setCenter(v);
        board.setLeft(v2);

        Image image = new Image("C:\\Users\\Shaked\\demo1\\src\\main\\ph\\pentago1.jpg");


        // Set the background image
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER, new BackgroundSize(100, 100,
                true, true, true, false));

        board.setBackground(new Background(backgroundImage));


        Scene scene = new Scene(board, x, y);
        primaryStage.setScene(scene);
        primaryStage.show();


    }


    private void handleCellClick(Circle cell) {
        System.out.println(cell.getId());
    }

}