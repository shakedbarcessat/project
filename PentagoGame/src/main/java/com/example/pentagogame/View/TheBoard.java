package com.example.pentagogame.View;


import com.example.pentagogame.AiPlayer;
import com.example.pentagogame.Controller.ControllerClass;
import javafx.application.Application;
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

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class TheBoard extends Application {
    private final int BOARD_SIZE = 6;
    private final int MINI_BOARD_SIZE = 3;
    private final int ROTATE_BUTTONS= 8;
    private int turn; //the current turn
    private boolean rotateOnce; //rotating only once
    private boolean forceRotating; //forcing to rotate
    private boolean gameOver; //checks for victory ot tie
    private boolean error; //checks for invalid moves

    private boolean winning=false;

    private int mini_board;

    private int index;

    private int rotating;
    private Label l; //label for current turn and winner or tie
    private ControllerClass controller = new ControllerClass();//connection to the controller
    private Button[] buttons = new Button[BOARD_SIZE*BOARD_SIZE]; // create an array to store the buttons- of the board itself
    private Button[] buttons_rotate = new Button[ROTATE_BUTTONS]; //create an array to store the buttons- of the rotate

    Integer[] rightBold = new Integer[]{3,6,24,27};//contains buttons to bold in right side
    Integer[] leftBold = new Integer[]{10,13,31,34};//contains buttons to bold in left side
    Integer[] upBold = new Integer[]{29,30,19,20};//contains buttons to bold in up
    Integer[] downBold = new Integer[]{7,8,17,18};//contains buttons to bold in down

    List<Integer> right = new ArrayList<>(Arrays.asList(rightBold));
    List<Integer> left = new ArrayList<>(Arrays.asList(leftBold));
    List<Integer> up = new ArrayList<>(Arrays.asList(upBold));
    List<Integer> down = new ArrayList<>(Arrays.asList(downBold));

    Random random = new Random();

    private final int RIGHT_DOWN_BOLD=9;
    private final int RIGHT_UP_BOLD=21;
    private final int LEFT_DOWN_BOLD=16;
    private final int LEFT_UP_BOLD=28;

    /**
     * initialize the board
     */
    public TheBoard() {
        this.turn = 0;
        this.rotateOnce = false;
        this.forceRotating = true;
        this.gameOver = false;
        this.error = true;
        this.l = new Label("player 1\nturn");//the starting player
        l.setPrefSize(80, 80);
        l.setFont(new Font("Arial", 20));
        l.setTranslateX(220);
        l.setTranslateY(150);
    }

    /**
     * sets the rotate once
     * @param rotateOnce- rotating only once
     */
    public void setRotateOnce(boolean rotateOnce) {
        this.rotateOnce = rotateOnce;
    }

    /**
     * sets the turn
     * @param turn- current turn to change
     */
    public void setTurn(int turn) {
        this.turn = turn;
    }

    /**
     * gets the current turn
     * @return thr current turn
     */
    public int getTurn() {
        return turn;
    }

    /**
     * sets the error
     * @param b- invalid move
     */
    public void setError(boolean b) {
        this.error = b;
    }

    /**
     * sets the force rotating
     * @param bool
     */
    public void setForceRotating(boolean bool) {
        this.forceRotating = bool;
    }

    /**
     * sets the label- the current turn or the winner or tie
     * @param s
     */
    public void setL(String s) {
        this.l.setText(s);
    }

    /**
     * checks if end of game, if yes than retuens the winner or tie, checks if there was an invalid move
     * @param s- the string that indicates if there was a win, a tie, an error or the rotating worked well
     * @param b- the buttons of the board
     * @param b2- the buttons of the rotate
     * @return true if there was an error or false otherwise
     */
    public boolean endGame(String s, Button[] b, Button[] b2) {
        System.out.println(s);
        if (s != "") {
            if (s == "error") {
                return true;
            } else {
                this.winning=true;
                setL(s);
                for (int i = 0; i < b.length; i++) {
                    b[i].setDisable(true);//finish game
                }
                for (int i = 0; i < b2.length; i++) {
                    b2[i].setDisable(true);//finish game
                }
                return false;
            }
        }
        return false;
    }

    /**
     * initializes the board itself
     * @param primaryStage- the stage to work with
     */
    public void initialize_the_board(Stage primaryStage)
    {
        GridPane grid = new GridPane();//the buttons of the board itself
        Screen screen = Screen.getPrimary();
        grid.setPadding(new Insets(10));
        double x = screen.getBounds().getWidth();//the screen size
        double y = screen.getBounds().getHeight();//the screen size
        grid.setHgap(5);
        grid.setVgap(5);

        //initialies the board itself
        int num = 0;//the id for each button
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {

                Button cell = new Button();
                cell.setPrefSize(80, 80);
                Circle circle = new Circle(20);
                circle.setFill(Color.TRANSPARENT);//sets the initialization color to TRANSPARENT
                cell.setGraphic(circle);
                if (row == 0 & col == 0) {
                    num = 0;
                } else if (col % BOARD_SIZE == 0) {
                    num = num - MINI_BOARD_SIZE - BOARD_SIZE;
                }
                if ((col % BOARD_SIZE) == MINI_BOARD_SIZE) { //half board
                    num = num + BOARD_SIZE;
                }
                if (row == MINI_BOARD_SIZE & col == 0) {
                    num = MINI_BOARD_SIZE * BOARD_SIZE;
                }
                num++;
                cell.setId(Integer.toString(num));//sets the id
                cell.setPadding(new Insets(1));
                if(right.contains(Integer.parseInt(cell.getId()))|left.contains(Integer.parseInt(cell.getId()))|
                        up.contains(Integer.parseInt(cell.getId()))|down.contains(Integer.parseInt(cell.getId()))){
                    if(right.contains(Integer.parseInt(cell.getId())))//right bold
                    {
                        cell.setStyle("-fx-background-color: brown; -fx-border-color: black;" +
                                "    -fx-border-width: 0 7px 0 0;");
                    }
                    else if(left.contains(Integer.parseInt(cell.getId())))//left bold
                    {
                        cell.setStyle("-fx-background-color: brown; -fx-border-color: black;" +
                                "    -fx-border-width: 0 0 0 7px;");
                    }
                    else if(up.contains(Integer.parseInt(cell.getId())))//up bold
                    {
                        cell.setStyle("-fx-background-color: brown; -fx-border-color: black;" +
                                "    -fx-border-width: 7px 0 0 0;");
                    }
                    else if(down.contains(Integer.parseInt(cell.getId())))//down bold
                    {
                        cell.setStyle("-fx-background-color: brown; -fx-border-color: black;" +
                                "    -fx-border-width: 0 0 7px 0;");
                    }

                }
                else if(Integer.parseInt(cell.getId())==RIGHT_DOWN_BOLD)
                {
                    cell.setStyle("-fx-background-color: brown; -fx-border-color: black;" +
                            "    -fx-border-width: 0 7px 7px 0;");
                }
                else if(Integer.parseInt(cell.getId())==LEFT_DOWN_BOLD)
                {
                    cell.setStyle("-fx-background-color: brown; -fx-border-color: black;" +
                            "    -fx-border-width: 0 0 7px 7px;");
                }
                else if(Integer.parseInt(cell.getId())==RIGHT_UP_BOLD)
                {
                    cell.setStyle("-fx-background-color: brown; -fx-border-color: black;" +
                            "    -fx-border-width: 7px 7px 0 0;");
                } else if (Integer.parseInt(cell.getId())==LEFT_UP_BOLD) {
                    cell.setStyle("-fx-background-color: brown; -fx-border-color: black;" +
                            "    -fx-border-width:  7px 0 0 7px;");
                }
                else {
                    cell.setStyle("-fx-background-color: brown;");//regular buttons
                }
                grid.add(cell, col, row);//adding to the grid
                buttons[num - 1] = cell;//adding to the array of buttons

            }
        }

        // Create the border pane and add the grid to the center
        grid.setAlignment(Pos.CENTER);
        BorderPane borderPane = new BorderPane();//the main board
        borderPane.setMaxSize(800, 600);
// Create a new scene and show the stage
        borderPane.setCenter(grid);
        start_rotate_buttons(primaryStage, borderPane); //sets the rotation buttons
    }

    /**
     * starts the rotation buttons
     * @param primaryStage- the stage to work with
     * @param borderPane- the same borderPane as the board itself
     */
    public void start_rotate_buttons(Stage primaryStage, BorderPane borderPane)
    {
        Screen screen = Screen.getPrimary();
        double x = screen.getBounds().getWidth();//the screen size
        double y = screen.getBounds().getHeight();//the screen size
        String[] paths = new String[2];

        File file = new File("src/main/photo1/photoLeft.png");
        String filePath = file.getAbsolutePath();

        File file2 = new File("src/main/photo2/photoRight.png");
        String filePath2 = file2.getAbsolutePath();

        paths[1] = filePath; //rotate left photo
        paths[0] = filePath2; //rotate right photo
        VBox vBox = new VBox(10); // 10 pixels of spacing between the buttons


        for (int i = 0; i < 2; i++) { //top left button for miniboard number 1
            Image img = new Image(paths[i]);
            ImageView view = new ImageView(img);
            view.setFitHeight(50);
            view.setPreserveRatio(true);
            Button button = new Button();
            button.setTranslateX(200);
            button.setTranslateY(220);
            button.setPrefSize(50, 50);
            button.setGraphic(view);
            vBox.getChildren().add(button);
            button.setId(Integer.toString(i));
            buttons_rotate[i] = button; //adding to the array of buttons
        }

        for (int i = 0; i < 2; i++) { //buttom left button for miniboard number 3
            Image img = new Image(paths[i]);
            ImageView view = new ImageView(img);
            view.setFitHeight(50);
            view.setPreserveRatio(true);
            Button button = new Button();
            button.setTranslateX(200);
            button.setTranslateY(400);
            button.setPrefSize(50, 50);
            button.setGraphic(view);
            vBox.getChildren().add(button);
            button.setId(Integer.toString(i + 2));
            buttons_rotate[i + 2] = button;//adding to the array of buttons
        }
        vBox.getChildren().add(this.l);
        vBox.setAlignment(Pos.BOTTOM_LEFT);
        vBox.setAlignment(Pos.TOP_LEFT);



        Button exit_button = new Button("EXIT");//exit button
        exit_button.setPrefSize(120, 50);
        exit_button.setTranslateX(0);
        exit_button.setStyle("-fx-background-color: greenyellow;");
        exit_button.setTranslateY(-350);
        exit_button.setOnAction(event -> {
            controller.setExitButton();//exits the game
        });
        vBox.getChildren().add(exit_button);



        Button backButton = new Button("Back to Open");///back button
        backButton.setPrefSize(120, 50);
        backButton.setTranslateX(0);
        backButton.setStyle("-fx-background-color: greenyellow;");
        backButton.setTranslateY(-325);
        backButton.setOnAction(event -> {
            controller.transferToOpeningScreen(primaryStage); //transfers to the opening screen
        });
        vBox.getChildren().add(backButton);
        vBox.setAlignment(Pos.TOP_LEFT);
        borderPane.setLeft(vBox);



        VBox vBox3 = new VBox(10); // 10 pixels of spacing between the buttons
        for (int i = 0; i < 2; i++) {//top right buttons- for miniboard number 2
            Image img = new Image(paths[i]);
            ImageView view = new ImageView(img);
            view.setFitHeight(50);
            view.setPreserveRatio(true);
            Button button = new Button();
            button.setTranslateX(-200);//-200
            button.setTranslateY(-400);//-480 -700
            button.setPrefSize(50, 50);
            button.setGraphic(view);
            vBox3.getChildren().add(button);
            button.setId(Integer.toString(i + 4));
            buttons_rotate[i + 4] = button; //adding to array buttons
        }

        for (int i = 0; i < 2; i++) {//buttom right buttons- for miniboard number 4
            Image img = new Image(paths[i]);
            ImageView view = new ImageView(img);
            view.setFitHeight(50);
            view.setPreserveRatio(true);
            Button button = new Button();
            button.setTranslateX(-200);
            button.setTranslateY(-180); //-150 -300
            button.setPrefSize(50, 50);
            button.setGraphic(view);
            vBox3.getChildren().add(button);
            button.setId(Integer.toString(i + 6));
            buttons_rotate[i + 6] = button; // adding to the array button
        }
        vBox3.setAlignment(Pos.TOP_RIGHT);
        vBox3.setAlignment(Pos.BOTTOM_RIGHT);
        borderPane.setRight(vBox3);


        // Create the scene and set it to the stage
        Scene scene = new Scene(borderPane, x, y);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void ai_play(AiPlayer a)
    {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Button b;
        if (this.turn == 1) {
            a.setPlayers();
            AiPlayer.player_move++;
            System.out.println("turn: " + turn);
            System.out.println("move " + AiPlayer.player_move);
            a.triple_power_play();
            mini_board = AiPlayer.mini_board_for_twist;
            index = AiPlayer.index;
            rotating = AiPlayer.direction_rotating;
            System.out.println("index: " + index);
            System.out.println("mini board: " + mini_board);
            if (rotating == 1)
                System.out.println("left");
            else System.out.println("right");
            System.out.println(this.turn);
            b = buttons[index - 1];
            System.out.println("id    " + b.getId());
            System.out.println("board ai before");
            System.out.println(Long.toBinaryString(a.getBoard_ai()));
//            setForceRotating(true);
            System.out.println(this.forceRotating);
            setError(endGame((controller.addToolAI(getTurn(), b, this.forceRotating)), buttons, buttons_rotate));//adding a tool
            if(this.winning==false) {
                a.setPlayers();
                System.out.println("board ai");
                System.out.println(Long.toBinaryString(a.getBoard_ai()));
                System.out.println("board player1");
                System.out.println(Long.toBinaryString(a.getPlayer1()));
                if (this.error == false) {//valid move
                    if (this.turn == 0)
                        setTurn(1);//change turn
                    else
                        setTurn(0);
                    setRotateOnce(true);
                    setForceRotating(false);
                }
                Button b2;
                if (mini_board == 1) {
                    if (rotating == 1)
                        b2 = buttons_rotate[1];
                    else
                        b2 = buttons_rotate[0];
                } else if (mini_board == 2) {
                    if (rotating == 1)
                        b2 = buttons_rotate[5];
                    else
                        b2 = buttons_rotate[4];
                } else if (mini_board == 3) {
                    if (rotating == 1)
                        b2 = buttons_rotate[3];
                    else
                        b2 = buttons_rotate[2];
                } else {
                    if (rotating == 1)
                        b2 = buttons_rotate[7];
                    else
                        b2 = buttons_rotate[6];
                }

                if (this.error == false) {
                    if (getTurn() == 0) //still not human
                    {
                        setRotateOnce(controller.rotateBoardAi(true, mini_board,
                                rotating, this.rotateOnce, b2, buttons));//rotating the miniboard
                    } else {
                        setRotateOnce(controller.rotateBoardAi(false, 0,
                                0, this.rotateOnce, b2, buttons));//rotating the miniboard
                    }
                    if (getTurn() == 1)
                        setL("ai\nturn");//sets player turn
                    else
                        setL("player 1\nturn");
                    setForceRotating(true);
                }
            }

        }

    }

    /**
     * starts the game
     * @param primaryStage- the main stage
     */
    public void start(Stage primaryStage) {
        initialize_the_board(primaryStage); //initializes the board
        if(OpeningScreen.num==0) { //hVSh
            for (int i = 0; i < BOARD_SIZE * BOARD_SIZE; i++) {
                Button b = buttons[i];
                b.setOnAction(e -> {
                    setError(endGame((controller.addTool(getTurn(), b, this.forceRotating)), buttons, buttons_rotate));//adding a tool
                    if (this.error == false) {//valid move
                        if (this.turn == 0)
                            setTurn(1);//change turn
                        else
                            setTurn(0);
                        setRotateOnce(true);
                        setForceRotating(false);
                    }
                });

                for (int j = 0; j < ROTATE_BUTTONS; j++) {
                    Button b2 = buttons_rotate[j];
                    b2.setOnAction(e2 -> {
                        if (this.error == false) {
                            setRotateOnce(controller.rotateBoard(b2, this.rotateOnce, buttons));//rotating the miniboard
                            if (getTurn() == 1)
                                setL("player 2\nturn");//sets player turn
                            else
                                setL("player 1\nturn");
                            setForceRotating(true);
                        }

                    });
                }
            }
        }












        else if(OpeningScreen.num==1){ //aiVSh
            AiPlayer a= new AiPlayer();
            this.turn=1;
            ai_play(a);
            for (int i = 0; i < BOARD_SIZE * BOARD_SIZE; i++) {
                Button b;
//                System.out.println("turn: " + turn);
//                System.out.println("move " + AiPlayer.player_move);
                b = buttons[i];
//                System.out.println(i);
                b.setOnAction(e -> {
                    setError(endGame((controller.addToolAI(getTurn(), b, this.forceRotating)), buttons, buttons_rotate));//adding a tool
                    System.out.println(this.error);
//                    System.out.println("the current ai: ");
//                    System.out.println(Long.toBinaryString(a.getBoard_ai()));
                    if (this.error == false) {//valid move
                        if (this.turn == 0)
                            setTurn(1);//change turn
                        else
                            setTurn(0);
                        setRotateOnce(true);
                        setForceRotating(false);
                    }
                    System.out.println(turn);
                });

                for (int j = 0; j < ROTATE_BUTTONS; j++) {
                    Button b2 = buttons_rotate[j];
                    b2.setOnAction(e2 -> {
                        System.out.println("error: "+ error);
                        if (this.error == false) {
                            if (getTurn() == 1) //still not ai
                            {
                                setRotateOnce(controller.rotateBoard(b2, this.rotateOnce, buttons));//rotating the miniboard
                                try {
                                    Thread.sleep(100);
                                } catch (InterruptedException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                            if (getTurn() == 1){
                                setL("ai\nturn");//sets player turn
                                setForceRotating(true);
                                ai_play(a);
                            }
                            else
                                setL("player 1\nturn");
                            setForceRotating(true);
                        }
                    });
                }
            }



        }

    }

}
