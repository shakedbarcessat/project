package com.example.pentagogame.Controller;

import com.example.pentagogame.Model.Board;
import com.example.pentagogame.View.Distructions;
import com.example.pentagogame.View.OpeningScreen;
import com.example.pentagogame.View.TheBoard;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.Comparator;

import static javafx.application.Application.launch;


public class ControllerClass {
    private static Board b;
    private static OpeningScreen pt;

    private int current_turn;


    public ControllerClass() {

    }
    public void init()
    {
        this.b= new Board();
        this.pt=new OpeningScreen();
    }

    public void start_Game(Stage stage) {
        this.pt.start(stage);
    }

    public void setExitButton()
    {
        Platform.exit();
    }

    public void transferToBoard(Stage stage)
    {
        TheBoard h= new TheBoard();
        h.start(stage);
    }

    public void transferToOpeningScreen(Stage stage)
    {
        OpeningScreen o= new OpeningScreen();
        o.start(stage);
    }

    public void transferToDistructions(Stage stage)
    {
        Distructions h= new Distructions();
        h.start(stage);
    }

    public String addTool(int turn, Button b, boolean check) {
        Long board;
        if(check==true){

            if(this.b.checkForTie()==true)

                return "Tie!!";
            else if (this.b.checkForWin()==true) {
                if(turn==0){
                    return "player2 \nwon!!";}
                else{
                    return "player1 \nwon!!";}
            }

            Circle c = (Circle) b.getGraphic();
            if (c.getFill() == Color.TRANSPARENT) {
                if (turn == 0) {
                    c.setFill(Color.WHITE);
                    b.setGraphic(c);
                    turn=1;
                    this.b.setPlayer_turn(1);
                    this.b.change_board(Integer.parseInt(b.getId()));


                }
                else {
                    c.setFill(Color.BLACK);
                    b.setGraphic(c);
                    turn=0;
                    this.b.setPlayer_turn(0);
                    this.b.change_board(Integer.parseInt(b.getId()));


                }
                System.out.println("player1: ");
                System.out.println(Long.toBinaryString(this.b.getPlayer1()));
                System.out.println("player2: ");
                System.out.println(Long.toBinaryString(this.b.getPlayer2()));
            }
            else
                return "error";
        }
        return "";
    }

//    public int changeTurn(int turn)
//    {
//        if(turn==0)
//        {
//            turn =1;
//            this.b.setPlayer_turn(1);
//        }
//        else
//        {
//            turn =0;
//            this.b.setPlayer_turn(0);
//        }
//        return turn;
//    }

    public boolean rotateBoard(Button b, boolean bool, Button [] buttons)
    {
        short player1;
        short player2;
        short mask= 0b100000000000000;
        if(bool==true) {
            //Integer.parseInt(b.getId())%2+1
            int numBoard = 0;
            int boardRotate = 0;
            if (Integer.parseInt(b.getId()) < 2)
                numBoard = 1;
            else if (Integer.parseInt(b.getId()) > 5)
                numBoard = 4;
            else if (Integer.parseInt(b.getId()) > 1 && Integer.parseInt(b.getId()) < 4)
                numBoard = 3;
            else
                numBoard = 2;
            if (Integer.parseInt(b.getId()) % 2 == 0)
                boardRotate = 2;
            else
                boardRotate = 1;
            this.b.create_mini_board_twist(numBoard, boardRotate);
            System.out.println("num board: " + numBoard + ", rotate 1 to left and 2 to right: " + boardRotate);
            System.out.println("player1: rotate");
            System.out.println(Long.toBinaryString(this.b.getPlayer1()));
            System.out.println("player2: rotate");
            System.out.println(Long.toBinaryString(this.b.getPlayer2()));


            System.out.println("mini player1 ");
            player1= this.b.mask(numBoard, this.b.getPlayer1());
            player2= this.b.mask(numBoard, this.b.getPlayer2());
            System.out.println(Long.toBinaryString(player1));
            System.out.println("mini player2 ");
            System.out.println(Long.toBinaryString(player2));


            Arrays.sort(buttons, Comparator.comparingInt(button -> Integer.parseInt(button.getId())));

//            Circle c= new Circle(20);
//            c.setFill(Color.TRANSPARENT);
//            for(int i=1+9*(numBoard-1); i<10+9*(numBoard-1); i++)
//            {
//                buttons[i-1].setGraphic(c);
//            }
//            System.out.println(numBoard);

            //for(int i=1+9*(numBoard-1); i<10+9*(numBoard-1); i++)//player1- white
            for(int i=1+9*(numBoard-1); i<10+9*(numBoard-1); i++)   //player1- white for(int i=1+9*(numBoard-1); i<10+9*(numBoard-1); i++)//player1- white
            {
                Circle c= new Circle(20);
                System.out.println("i: " + i);
                //System.out.println("very ");
                //System.out.println(Long.toBinaryString(player1&mask));

                //System.out.println(Long.toBinaryString(mask));
                if((player1&mask)!=0)
                {
                    System.out.println("enter 1");
                    c.setFill(Color.WHITE);
                    buttons[i-1].setGraphic(c);
                }
                else if((player2&mask)!=0)
                {
                    //System.out.println(Long.toBinaryString(player2&mask));
                    System.out.println("enter 2");
                    c.setFill(Color.BLACK);
                    buttons[i-1].setGraphic(c);
                }
                else if((player1&mask)==0 && ((player2&mask)==0))
                {
                    System.out.println("enter 3");
                    c.setFill(Color.TRANSPARENT);
                    buttons[i-1].setGraphic(c);
                }

                mask= (short) (mask>>1);
            }
        }
        return false;

    }




    public static void main(String[] args) {
        launch(args);
    }


}
