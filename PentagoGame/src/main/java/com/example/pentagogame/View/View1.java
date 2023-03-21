package com.example.pentagogame.View;

import com.example.pentagogame.Controller.HelloController;

import java.util.Scanner;

public class View1 {
    private int num_player;
    private int index;
    private int mini_board;
    private int rotate;
    //private HelloController c;



    public void input_player_number()
    {

        Scanner myObj = new Scanner(System.in);
        System.out.println("input a player number");
        this.num_player= myObj.nextInt();

    }
    public void input_add_tool()
    {
        Scanner myObj = new Scanner(System.in);
        System.out.println("input index to add tool");
        this.index= myObj.nextInt();

    }
    public void input_mini_board()
    {
        Scanner myObj = new Scanner(System.in);
        System.out.println("input mini board to twist");
        this.mini_board= myObj.nextInt();
    }

    public void input_rotate()
    {
        Scanner myObj = new Scanner(System.in);
        System.out.println("input which way to rotate, 1 for left, 2 for right");
        this.rotate= myObj.nextInt();
    }

    public int getNum_player() {
        return num_player;
    }

    public void setNum_player(int num_player) {
        this.num_player = num_player;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getMini_board() {
        return mini_board;
    }

    public void setMini_board(int mini_board) {
        this.mini_board = mini_board;
    }

    public int getRotate() {
        return rotate;
    }

    public void setRotate(int rotate) {
        this.rotate = rotate;
    }
}
