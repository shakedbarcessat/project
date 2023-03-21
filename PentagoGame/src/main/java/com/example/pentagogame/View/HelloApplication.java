package com.example.pentagogame.View;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class HelloApplication extends Application {
    private final int BOARD_SIZE = 6;
    private final int MINI_BOARD_SIZE=3;
    private final int NUM_OF_MINI_BOARDS=4;
    private final int PADDING = 20;

    private Button board = new Button();




    @Override
    public void start(Stage primaryStage) {
        GridPane grid = new GridPane();
        Screen screen= Screen.getPrimary();
        double x= screen.getBounds().getWidth();
        double y= screen.getBounds().getHeight();

        int num=0;
        grid.setAlignment(Pos.CENTER);

        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                Button cell = new Button();
                cell.setOnMouseClicked((mouseEvent -> {
                    Button bt = (Button) mouseEvent.getSource();
                }));
                if(row==0 & col==0)
                {
                    num=0;
                }
                else if(col%BOARD_SIZE==0)
                {
                    num=num-MINI_BOARD_SIZE-BOARD_SIZE;
                }
                if((col%BOARD_SIZE)==MINI_BOARD_SIZE){ //half board
                    num=num+BOARD_SIZE;}
                if(row==MINI_BOARD_SIZE & col==0)
                {num=MINI_BOARD_SIZE*BOARD_SIZE;}
                num++;
                cell.setId(Integer.toString(num));
                System.out.println(cell.getId());
                cell.setPrefSize(100, 100);
                Rectangle rec= new Rectangle(100, 100, Color.BROWN);
                cell.setPadding(new Insets(1));
                cell.setBackground(null);
                cell.setGraphic(rec);
                grid.add(cell, col, row);
            }
        }



        

        Scene scene = new Scene(grid, x, y);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void handleCellClick(Circle cell) {
        System.out.println(cell.getId());
    }



    public static void main(String[] args) {
        launch(args);
    }
}