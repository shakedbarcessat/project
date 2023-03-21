package com.example.pentagogame.View;

import com.example.pentagogame.Controller.HelloController;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class View2 {
    private HelloController controller;
    private Stage stage;
    private Label l;
    private TextField tx;
    private TextField tx2;
    private TextField tx3;
    private TextField tx4;
    private Button button;

    public View2(Stage stage)
    {
        this.stage=stage;
        this.stage.setTitle("Button Example");
        tx= new TextField();
        tx.setPromptText("input player number");
        StackPane r = new StackPane();
        // add textfield
        r.setAlignment(Pos.TOP_RIGHT);
        r.getChildren().add(tx);

        tx2= new TextField();
        tx2.setPromptText("input mini board to twist");
        StackPane r2 = new StackPane();
        r2.setAlignment(Pos.TOP_LEFT);
        // add textfield
        r2.getChildren().add(tx2);

        tx3= new TextField();
        tx3.setPromptText("input which way to rotate, 1 for left, 2 for right");
        StackPane r3 = new StackPane();
        r3.setAlignment(Pos.TOP_LEFT);
        // add textfield
        r3.getChildren().add(tx3);

        tx4= new TextField();
        tx4.setPromptText("input index to add tool");
        StackPane r4 = new StackPane();
        r4.setAlignment(Pos.TOP_LEFT);
        // add textfield
        r4.getChildren().add(tx4);

        //Creating a Button
        button = new Button();
        //Setting text to the button
//        button.setText("start play");
//        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent mouseEvent) {
//                controller.start_button();
//            }
//        });


        l= new Label();



        //Setting the location of the button
        //Setting the stage
        VBox vBox= new VBox();
        vBox.setAlignment(Pos.TOP_LEFT);
        vBox.getChildren().add(r);
        vBox.getChildren().add(r2);
        vBox.getChildren().add(r3);
        vBox.getChildren().add(r4);
        vBox.getChildren().add(l);
        BorderPane root = new BorderPane();
        root.setCenter(button);
        root.setLeft(vBox);

        Scene scene = new Scene(root, 595, 150, Color.BEIGE);
        stage.setScene(scene);
    }
    public void show()
    {
        this.stage.show();
    }
    public void setController(HelloController c){
        this.controller= c;
    }

    public Label getL() {
        return l;
    }

    public TextField getTx() {
        return tx;
    }

    public TextField getTx2() {
        return tx2;
    }

    public TextField getTx3() {
        return tx3;
    }

    public void setL(Label l) {
        this.l = l;
    }

    public void setTx(TextField tx) {
        this.tx = tx;
    }

    public void setTx2(TextField tx2) {
        this.tx2 = tx2;
    }

    public void setTx3(TextField tx3) {
        this.tx3 = tx3;
    }

    public void setTx4(TextField tx4) {
        this.tx4 = tx4;
    }

    public TextField getTx4() {
        return tx4;
    }

    public Button getButton() {
        return button;
    }
}
