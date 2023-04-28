package com.example.pentagogame.Controller;
import com.example.pentagogame.DemoDriver;
import com.example.pentagogame.Model.Board;
import com.example.pentagogame.View.Instructions;
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
    public static Board b; //the board of the model
    private static OpeningScreen pt; //the opening screen of the view
    private final int NUM_BUTTONS_MINI_BOARD=9; //num of buttons in each mini board

    /**
     * initializes the class
     */
    public void init()
    {
        this.b= new Board();
        this.pt=new OpeningScreen();
    }


    /**
     * starts the game
     * @param stage- the main stage
     */
    public void start_Game(Stage stage) {
        this.pt.start(stage);
    }

    /**
     * exits the game
     */
    public void setExitButton()
    {
        Platform.exit();
    }

    /**
     * transfering to board screen
     * @param stage- the main stage
     */
    public void transferToBoard(Stage stage)
    {
        DemoDriver d= new DemoDriver();
        Stage stage1=new Stage();
        d.start(stage1);
        TheBoard h= new TheBoard();
        OpeningScreen.num=0; //hVSh
        h.start(stage);
    }


    /**
     * transfering to AI board screen
     * @param stage- the main stage
     */
    public void transferToAI(Stage stage)
    {
        DemoDriver d= new DemoDriver();
        Stage stage1=new Stage();
        d.start(stage1);
        TheBoard h= new TheBoard();
        OpeningScreen.num=1; //aiVSh
        h.start(stage);
    }

    /**
     * transfer to opening screen
     * @param stage- the main stage
     */
    public void transferToOpeningScreen(Stage stage)
    {
        OpeningScreen o= new OpeningScreen();
        o.start(stage);
    }

    /**
     * transfer to instructions screen
     * @param stage- the main stage
     */
    public void transferToistructions(Stage stage)
    {
        Instructions h= new Instructions();
        h.start(stage);
    }

    /**
     * adds a tool to the board(the model), and checks for win or tie.
     * changes the current turn and checks if the pushed button is legal
     * @param turn- gets the current turn
     * @param b- the pushed button
     * @param check- only if the older player twisted the board
     * @return- string that indicates if there has benn an error or tie or which player won
     */
    public String addTool(int turn, Button b, boolean check) {
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
                    this.b.setPlayer_turn(1);//switches turn
                    this.b.change_board(Integer.parseInt(b.getId()));//adding the tool


                }
                else {
                    c.setFill(Color.BLACK);
                    b.setGraphic(c);
                    turn=0;
                    this.b.setPlayer_turn(0);//switches turn
                    this.b.change_board(Integer.parseInt(b.getId()));//adding the tool


                }
            }
            else
                return "error"; //trying to add a button in a taken place- invalid move
        }
        return ""; //continue
    }

    /**
     * rotates the board
     * @param b- the rotate button that pushed
     * @param bool- true if didn't twist yet
     * @param buttons- all buttons
     * @return- returns false in order to allow twisting only once
     */
    public boolean rotateBoard(Button b, boolean bool, Button [] buttons)
    {
        short player1;
        short player2;
        short mask= 0b100000000000000;
        if(bool==true) {
            //Integer.parseInt(b.getId())%2+1
            int numBoard = 0;//num of the board to rotate
            int boardRotate = 0;//rotating right or left
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
            this.b.create_mini_board_twist(numBoard, boardRotate);//twistingin model
            player1= this.b.mask(numBoard, this.b.getPlayer1());
            player2= this.b.mask(numBoard, this.b.getPlayer2());

            Arrays.sort(buttons, Comparator.comparingInt(button -> Integer.parseInt(button.getId()))); //sorts the array of buttons by id

            for(int i=1+NUM_BUTTONS_MINI_BOARD*(numBoard-1); i<NUM_BUTTONS_MINI_BOARD+1+NUM_BUTTONS_MINI_BOARD*(numBoard-1); i++)
            {
                Circle c= new Circle(20);
                if((player1&mask)!=0)
                {
                    c.setFill(Color.WHITE); //change to white
                    buttons[i-1].setGraphic(c);
                }
                else if((player2&mask)!=0)
                {
                    c.setFill(Color.BLACK); //change to black
                    buttons[i-1].setGraphic(c);
                }
                else if((player1&mask)==0 && ((player2&mask)==0))
                {
                    c.setFill(Color.TRANSPARENT); //change to empty
                    buttons[i-1].setGraphic(c);
                }

                mask= (short) (mask>>1);
            }
        }
        return false;

    }


    /**
     * adds a tool to the board(the model), and checks for win or tie. add tool in the AI mode
     * changes the current turn and checks if the pushed button is legal
     * @param turn- gets the current turn
     * @param b- the pushed button
     * @param check- only if the older player twisted the board
     * @return- string that indicates if there has benn an error or tie or which player won
     */
    public String addToolAI(int turn, Button b, boolean check) {
        if(check==true){
            if(this.b.checkForTie()==true)
                return "Tie!!";
            else if (this.b.checkForWin()==true) {
                if(turn==0){
                    return "AI \nwon!!";} //ai is 1
                else{
                    return "player1 \nwon!!";} //player1 is 0
            }

            Circle c = (Circle) b.getGraphic();
            if (c.getFill() == Color.TRANSPARENT) {
                if (turn == 0) { //player1
                    c.setFill(Color.WHITE);
                    b.setGraphic(c);
                    turn=1;
                    this.b.setPlayer_turn(1);//switches turn
                    this.b.change_board(Integer.parseInt(b.getId()));//adding the tool


                }
                else { //ai player
                    c.setFill(Color.BLACK);
                    b.setGraphic(c);
                    turn=0;
                    this.b.setPlayer_turn(0);//switches turn
                    this.b.change_board(Integer.parseInt(b.getId()));//adding the tool


                }
            }
            else
                return "error"; //trying to add a button in a taken place- invalid move
        }
        return ""; //continue
    }

    /**
     * rotates the board
     * @param numBoard- num board to twist
     * @param boardRotate- direction of rotating
     * @param bool- true if didn't twist yet
     * @param buttons - all buttons
     * @return
     */
    public boolean rotateBoardAi(int numBoard, int boardRotate, boolean bool, Button [] buttons)
    {
        short player1;
        short player2;
        short mask= 0b100000000000000;
        if(bool==true) {
            this.b.create_mini_board_twist(numBoard, boardRotate);//twisting in model
            player1= this.b.mask(numBoard, this.b.getPlayer1());
            player2= this.b.mask(numBoard, this.b.getPlayer2());

            Arrays.sort(buttons, Comparator.comparingInt(button -> Integer.parseInt(button.getId()))); //sorts the array of buttons by id

            for(int i=1+NUM_BUTTONS_MINI_BOARD*(numBoard-1); i<NUM_BUTTONS_MINI_BOARD+1+NUM_BUTTONS_MINI_BOARD*(numBoard-1); i++)
            {
                Circle c= new Circle(20);
                if((player1&mask)!=0)
                {
                    c.setFill(Color.WHITE); //change to white
                    buttons[i-1].setGraphic(c);
                }
                else if((player2&mask)!=0)
                {
                    c.setFill(Color.BLACK); //change to black
                    buttons[i-1].setGraphic(c);
                }
                else if((player1&mask)==0 && ((player2&mask)==0))
                {
                    c.setFill(Color.TRANSPARENT); //change to empty
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
