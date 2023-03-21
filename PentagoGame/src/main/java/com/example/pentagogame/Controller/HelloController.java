package com.example.pentagogame.Controller;
import com.example.pentagogame.View.View1;

import com.example.pentagogame.Model.Board;


public class HelloController {

    private Board b;
    private View1 pt;
    private int current_turn;

    public HelloController(Board b, View1 v)
    {
        this.b= b;
        this.pt= v;
        //this.pt.setController(this);
    }



    public void start_Game()
    {
        pt.input_player_number();
        setCurrent_turn(this.pt.getNum_player());

        boolean bs= this.set_player();
        while(bs==false)
        {
            pt.input_player_number();
            bs= this.set_player();
        }
        pt.input_add_tool();
        boolean b= this.add_tool();
        while(b==false)
        {
            pt.input_add_tool();
            b= this.add_tool();
        }
        pt.input_mini_board();
        pt.input_rotate();
        this.twist_mini_board();
        if(this.pt.getNum_player()==1)
        {
            this.b.setPlayer_turn(2);
            this.twist_mini_board();
            this.b.setPlayer_turn(1);
        }
        else {
            this.b.setPlayer_turn(1);
            this.twist_mini_board();
            this.b.setPlayer_turn(2);
        }


        while(true)
        {
            if(this.check_win()){
                System.out.println("you won!! "+ this.pt.getNum_player());
                break;
            }
            if(this.check_tie())
            {
                System.out.println("tie!!");
                break;
            }
            this.change_current_turn();
            pt.input_player_number();
            bs= this.set_player();
            while(bs==false)
            {
                pt.input_player_number();
                bs= this.set_player();
            }
            pt.input_add_tool();
            b= this.add_tool();
            System.out.println(b);
            //System.out.println(Long.toBinaryString(this.b.getPlayer1()));
            while(b==false)
            {
                pt.input_add_tool();
                b= this.add_tool();
                System.out.println(b);
            }
            pt.input_mini_board();
            pt.input_rotate();
            this.twist_mini_board();
            if(this.pt.getNum_player()==1)
            {
                this.b.setPlayer_turn(2);
                this.twist_mini_board();
                this.b.setPlayer_turn(1);
            }
            else {
                this.b.setPlayer_turn(1);
                this.twist_mini_board();
                this.b.setPlayer_turn(2);
            }

        }

    }

    public void setCurrent_turn(int first_turn) //the first turn
    {
        this.current_turn=first_turn;
    }

    public void change_current_turn()
    {
        if(this.current_turn==1)
        {
            this.current_turn=2;
        }
        else
            this.current_turn=1;
    }

    public boolean set_player() { //check if the player is the right one and changes the turn
        if(this.current_turn==this.pt.getNum_player())
        {
            b.setPlayer_turn(this.pt.getNum_player());
            return true;
        }
        else
        {
            System.out.println("its not your turn");
            return false;
        }
    }

    public boolean add_tool()
    {
        return b.change_board(this.pt.getIndex());
    }

    public void twist_mini_board()
    {
        b.create_mini_board_twist(this.pt.getMini_board(), this.pt.getRotate());
    }

    public boolean check_win()
    {
        return b.checkForWin();
    }
    public boolean check_tie()
    {
        return b.checkForTie();
    }
}