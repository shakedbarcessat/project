package com.example.pentagogame.View;


import com.example.pentagogame.Controller.ControllerClass;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Arrays;
import java.util.Comparator;


public class TheBoard extends Application {
    private final int BOARD_SIZE = 6;
    private final int MINI_BOARD_SIZE = 3;

    private int turn;
    private boolean bool;
    private boolean check;

    private boolean gameOver;

    private  boolean error;

    private Label l;

    private ControllerClass controller= new ControllerClass();


    public TheBoard()
    {
        this.turn=0;
        this.bool=false;
        this.check=true;
        this.gameOver=false;
        this.error=true;
       this.l= new Label("player 1\nturn");
        l.setPrefSize(80, 80);
        l.setFont(new Font("Arial", 20));
        l.setTranslateX(220);
        l.setTranslateY(150);
    }

    public void setBool(boolean bool) {
        this.bool = bool;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public int getTurn() {
        return turn;
    }

    public void setError(boolean b)
    {
        this.error=b;
    }

    public void setCheck(boolean bool) {
        this.check = bool;
    }

    public boolean endGame(String s, Button [] b, Button [] b2)
    {
        if(s!=""){
            if(s=="error")
            {
                return true;
            }
            else {
                setL(s);
                for(int i=0; i<b.length; i++)
                {
                    b[i].setDisable(true);
                }
                for(int i=0; i<b2.length; i++)
                {
                    b2[i].setDisable(true);
                }
                return false;
            }
        }
        return false;
    }


    public void setL(String s)
    {
        this.l.setText(s);
    }

    public void start(Stage primaryStage) {
        // Create the grid pane
        GridPane grid = new GridPane();
        Screen screen= Screen.getPrimary();
        grid.setPadding(new Insets(10));
        double x= screen.getBounds().getWidth();
        double y= screen.getBounds().getHeight();
        grid.setHgap(5);
        grid.setVgap(5);
        Button[] buttons = new Button[36]; // create an array to store the buttons
        Button[] buttons_rotate=new Button[8];
        int num=0;




        //Populate the grid with buttons
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 6; col++) {
                Button cell = new Button();
                cell.setStyle("-fx-background-color: brown;");
                cell.setPrefSize(80, 80);
                //Add a white or black circle to the button


                Circle circle = new Circle(20);
                circle.setFill(Color.TRANSPARENT);
                cell.setGraphic(circle);
//                if(row==col)
//                {
//                    circle.setFill((row + col) % 2 == 0 ? Color.WHITE : Color.BLACK);
//                    cell.setGraphic(circle);
//                }


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
                //System.out.println(cell.getId());
                cell.setPadding(new Insets(1));
                grid.add(cell, col, row);
                buttons[num - 1] = cell;
                //System.out.println(cell.getId());

            }
        }

//        Arrays.sort(buttons, Comparator.comparingInt(button -> Integer.parseInt(button.getId())));
//
//        for(int i=0; i<36; i++)
//        {
//            System.out.println(buttons[i].getId());
//        }
//        Circle b1= (Circle) buttons[0].getGraphic();
//        buttons[0].setGraphic(buttons[6].getGraphic());
//        buttons[6].setGraphic(b1);



//

        // Create the border pane and add the grid to the center
        grid.setAlignment(Pos.CENTER);
        BorderPane borderPane = new BorderPane();
        borderPane.setMaxSize(800, 600);
// Create a new scene and show the stage
        borderPane.setCenter(grid);
        String [] paths= new String[2];
        paths[1]= "C:\\Users\\Shaked\\demo1\\src\\main\\photo1\\photoLeft.png";
        paths[0]= "C:\\Users\\Shaked\\demo1\\src\\main\\photo2\\photoRight.png";
        VBox vBox = new VBox(10); // 10 pixels of spacing between the buttons








        for(int i =0; i<2; i++)
        {
            Image img = new Image(paths[i]);
            ImageView view = new ImageView(img);
            view.setFitHeight(50);
            view.setPreserveRatio(true);
            //Creating a Button
            Button button = new Button();
            //Setting the location of the button
            button.setTranslateX(200);//200
            button.setTranslateY(120);
            //Setting the size of the button
            button.setPrefSize(50, 50);
            //Setting a graphic to the button
            button.setGraphic(view);
            vBox.getChildren().add(button);
            button.setId(Integer.toString(i));
            buttons_rotate[i]=button;
        }

        for(int i =0; i<2; i++)
        {
            Image img = new Image(paths[i]);
            ImageView view = new ImageView(img);
            view.setFitHeight(50);
            view.setPreserveRatio(true);
            //Creating a Button
            Button button = new Button();
            //Setting the location of the button
            button.setTranslateX(200);//200
            button.setTranslateY(520);//450
            //Setting the size of the button
            button.setPrefSize(50, 50);
            //Setting a graphic to the button
            button.setGraphic(view);
            vBox.getChildren().add(button);
            button.setId(Integer.toString(i+2));
            buttons_rotate[i+2]=button;
        }




        vBox.getChildren().add(this.l);




        //Set the alignment of the buttons to the center of the VBox
        vBox.setAlignment(Pos.BOTTOM_LEFT);
// Set the VBox as the left node of the BorderPane
        vBox.setAlignment(Pos.TOP_LEFT);
// Set the VBox as the left node of the BorderPane
        Button exit_button= new Button("EXIT");
        exit_button.setPrefSize(120, 50);
        exit_button.setTranslateX(0);
        exit_button.setStyle("-fx-background-color: greenyellow;");
        exit_button.setTranslateY(-350);

        exit_button.setOnAction(event -> {
            controller.setExitButton();
        });

        vBox.getChildren().add(exit_button);


        Button backButton= new Button("Back to Open");
        backButton.setPrefSize(120, 50);
        backButton.setTranslateX(0);
        backButton.setStyle("-fx-background-color: greenyellow;");
        backButton.setTranslateY(-325);


        backButton.setOnAction(event -> {
            controller.transferToOpeningScreen(primaryStage);
        });


        vBox.getChildren().add(backButton);

        vBox.setAlignment(Pos.TOP_LEFT);
        borderPane.setLeft(vBox);

        VBox vBox3 = new VBox(10); // 10 pixels of spacing between the buttons

        for(int i =0; i<2; i++)
        {
            Image img = new Image(paths[i]);
            ImageView view = new ImageView(img);
            view.setFitHeight(50);
            view.setPreserveRatio(true);
            //Creating a Button
            Button button = new Button();
            //Setting the location of the button
            button.setTranslateX(-200);//-200
            button.setTranslateY(-480);
            //Setting the size of the button
            button.setPrefSize(50, 50);
            //Setting a graphic to the button
            button.setGraphic(view);
            vBox3.getChildren().add(button);
            button.setId(Integer.toString(i+4));
            buttons_rotate[i+4]=button;
        }

        for(int i =0; i<2; i++)
        {
            Image img = new Image(paths[i]);
            ImageView view = new ImageView(img);
            view.setFitHeight(50);
            view.setPreserveRatio(true);
            //Creating a Button
            Button button = new Button();
            //Setting the location of the button
            button.setTranslateX(-200);
            button.setTranslateY(-80);
            //Setting the size of the button
            button.setPrefSize(50, 50);
            //Setting a graphic to the button
            button.setGraphic(view);
            vBox3.getChildren().add(button);
            button.setId(Integer.toString(i+6));
            buttons_rotate[i+6]=button;
        }

        //Set the alignment of the buttons to the center of the VBox
        vBox3.setAlignment(Pos.TOP_RIGHT);
        vBox3.setAlignment(Pos.BOTTOM_RIGHT);
// Set the VBox as the left node of the BorderPane

        for (int i = 0; i < 36; i++) {
            Button b = buttons[i];
            b.setOnAction(e -> {
                setError(endGame((controller.addTool(getTurn(), b, this.check)), buttons, buttons_rotate));
                System.out.println("error: "+ this.error);
                if(this.error==false){
                    if (this.turn == 0)
                        setTurn(1);
                    else
                        setTurn(0);
                    setBool(true);
                    setCheck(false);
                }
            });

        //System.out.println(this.check);
            for (int j = 0; j < 8; j++) {
                Button b2 = buttons_rotate[j];
                b2.setOnAction(e2 -> {
                    if(this.error==false){
                        setBool(controller.rotateBoard(b2, this.bool, buttons));
                        System.out.println("bool: "+ this.bool);
                        if (getTurn() == 1)
                            setL("player 2\nturn");
                        else
                            setL("player 1\nturn");
                        setCheck(true);
                    }

                });
            }
        }


        borderPane.setRight(vBox3);


        // Create the scene and set it to the stage
        Scene scene = new Scene(borderPane, x, y);
        primaryStage.setScene(scene);
        primaryStage.show();


    }




//    public static void main(String[] args) {
//        launch(args);
//    }

}
