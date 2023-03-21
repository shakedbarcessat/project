package com.example.pentagogame.View;

import com.example.pentagogame.Controller.ControllerClass;
import com.example.pentagogame.DemoDriver;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Distructions extends Application {
    private ControllerClass controller= new ControllerClass();



    @Override
    public void start(Stage primaryStage) {
        BorderPane rulesPane= new BorderPane();
        Screen screen= Screen.getPrimary();
        double x= screen.getBounds().getWidth();
        double y= screen.getBounds().getHeight();
        Rectangle2D bounds = screen.getVisualBounds();

        VBox v = new VBox();
        Label titleLabel = new Label("\nGame Rules");
        titleLabel.setFont(new Font("Arial", 40));
        Label top1= new Label("\n\n"+"Object of the Game\n");
        top1.setFont(new Font("Arial", 30));
        Label rulesLabel = new Label(
                "The object is to get five marbles in a row before your opponent does.\n" +
                        "The mind twisting part of Pentago is that each player will also twist one of the four game blocks 90 degrees" +
                        ".\n"+"A 180 degree twist is not allowed. The twist is the key to create winning positions in Pentago."+"\n\n\n\n\n");

        Label top2= new Label("Game Play\n");
        top2.setFont(new Font("Arial", 30));

        Label rulesLabel2= new Label("\nPlayers take turns at placing marbles on the game board and twisting the game blocks.\n" +
                "A player is free to twist any of the game blocks, regardless of which game block the player placed the marble on.\n" +
                "\n\n"  +
                "               \n");

        rulesLabel.setFont(new Font("Arial", 20));
        rulesLabel2.setFont(new Font("Arial", 20));
        Image i= new Image("C:\\Users\\Shaked\\demo1\\src\\main\\photob\\צילום מסך 2023-03-06 210855.png");
        ImageView imageView = new ImageView(i);

        Label top3= new Label("\n\n"+"End of the Game\n");
        top3.setFont(new Font("Arial", 30));
        Label rulesLabel3= new Label("\nA winning row of five marbles can occur vertically, horizontally or diagonally, anywhere on the board and will span two or three game blocks.\n" +
                "\n" +
                "What seems like a simple five-in-a-row game quickly gets mind twisting as the board fills up and both players are twisting the game blocks, " +
                "\ncreating a constantly changing and challenging game scenario.\n" +
                "\n" +
                "You'll want to really watch your opponents position as it relates to yours and play as much defensive as you do offense.\n\n"  +
                "               \n");
        rulesLabel3.setFont(new Font("Arial", 20));
        Button backButton = new Button("Back to Open");

        backButton.setOnAction(event -> {
            controller.transferToOpeningScreen(primaryStage);
        });

        backButton.setPrefSize(120, 50);
        backButton.setStyle("-fx-background-color: greenyellow;");

        Button exit_button= new Button("EXIT");
        exit_button.setPrefSize(120, 50);
        exit_button.setStyle("-fx-background-color: greenyellow;");
        exit_button.setTranslateY(25);

        exit_button.setOnAction(event -> {
            controller.setExitButton();
        });

        VBox v2= new VBox();
        v2.getChildren().addAll(backButton, exit_button);
        v2.setAlignment(Pos.TOP_LEFT);

        v.getChildren().addAll(titleLabel, top1, rulesLabel,
                top2, rulesLabel2, imageView, top3, rulesLabel3);
        v.setAlignment(Pos.TOP_CENTER);


        rulesPane.setCenter(v);
        rulesPane.setLeft(v2);


        ScrollPane sp = new ScrollPane();
        sp.setPrefSize(x, y);
        rulesPane.setPrefSize(x, 1.5*y);
        sp.setContent(rulesPane);


        Scene scene = new Scene(sp);

        primaryStage.setScene(scene);
        primaryStage.show();


    }
}