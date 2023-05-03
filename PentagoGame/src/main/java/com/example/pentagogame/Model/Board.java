package com.example.pentagogame.Model;

public class Board {
    private long player1; //the board from player1 point of view
    private long player2; //the board from player2 point of view / ai player
    private int player_turn; //the current turn

    private long[] masks = new long[]{0b111111111000000000000000000000000000000000000000000000000000000L,
            0b000000000111111111000000000000000000000000000000000000000000000L,
            0b000000000000000000111111111000000000000000000000000000000000000L,
            0b000000000000000000000000000111111111000000000000000000000000000L}; //masks for each mini board

    private final int LEFT = 1; //rotating left
    private final int RIGHT = 2; //rotating right

    /**
     * initialize the board
     */
    public Board() {
        this.player1 = 0;
        this.player2 = 0;
        this.player_turn = 1;
    }

    /**
     * getter
     *
     * @return- returns player1 board
     */
    public long getPlayer1() {
        return this.player1;
    }

    /**
     * getter
     *
     * @return- returns player2 board
     */
    public long getPlayer2() {
        return this.player2;
    }


    /**
     * setter
     *
     * @param player1- setting player1 board
     */
    public void setPlayer1(long player1) {
        this.player1 = player1;
    }

    /**
     * setter
     *
     * @param player2- setting player1 board
     */
    public void setPlayer2(long player2) {
        this.player2 = player2;
    }

    /**
     * setter
     *
     * @param pt- setting players turn
     */
    public void setPlayer_turn(int pt) {
        this.player_turn = pt;
    }


    /**
     * creates an array of masks and casts x from long to short
     * @param num- num of mini board
     * @param board- the board
     * @return - the board in short
     */
    public short mask(int num, long board) {
        num = num - 1;
        board = board & masks[num];
        board = board >>> (21 + (3 - num) * 9); //moving the number to be in the right
        short y = (short) board;
        return y;

    }


    /**
     * changes the board after the twist
     *
     * @param num-  the mini board we want to twist
     * @param board-    the board
     * @param last- the mini board after twist
     * @return the board after change
     */
    public long opmask(int num, long board, short last) {
        num = num - 1;
        board &= ~masks[num];
        long last2 = last & (short) 0b111111111000000;
        last2 = (long) last;
        board |= (last2 << (21 + (3 - num) * 9)) & masks[num];
        return board;
    }


    /**
     * twisting the board
     *
     * @param num-    the board to twist 12
     *                                   34
     * @param rotate- 1 to rotate left and 2 to rotate right
     */
    public void create_mini_board_twist(int num, int rotate) {
        short miniboard1 = 0;
        short miniboard2 = 0;
        miniboard1 = mask(num, this.player1); //casting to short
        miniboard2 = mask(num, this.player2); //casting to short
        MiniBoard mini1 = new MiniBoard(miniboard1);
        MiniBoard mini2 = new MiniBoard(miniboard2);
        if (rotate == LEFT) {
            miniboard1 = mini1.rotate_left();
            miniboard2 = mini2.rotate_left();
        } else {
            miniboard1 = mini1.rotate_right();
            miniboard2 = mini2.rotate_right();
        }
        setPlayer1(opmask(num, this.player1, miniboard1)); //casting back to long with the mini board after change
        setPlayer2(opmask(num, this.player2, miniboard2)); //casting back to long with the mini board after change

    }


    /**
     * changes the board after adding a trophy
     *
     * @param id_cell- the place the trophy added to
     */
    public boolean change_board(int id_cell) {
        long mask = 0b100000000000000000000000000000000000000000000000000000000000000L;
        long new_mask = (mask >>> (id_cell - 1));

        if ((new_mask & this.player1) != 0 | (new_mask & this.player2) != 0) {
            return false; //already taken, invalid step
        }
        if (this.player_turn == 1) {
            setPlayer1(getPlayer1() | new_mask); //adding the trophy to the board
        } else {
            setPlayer2(getPlayer2() | new_mask); //adding the trophy to the board
        }
        return true; //valid move

    }


    /**
     * returns true if current player won the game
     *
     * @return
     */
    public boolean checkForWin() {
        long board;
        if (this.player_turn == 1) {
            board = this.player1;
        } else
            board = this.player2;

        // Check for horizontal wins - אופקי
        long mask1 = 0b111000000110000000000000000000000000000000000000000000000000000L; //mask for the horizontal win
        long mask2 = 0b011000000111000000000000000000000000000000000000000000000000000L; //mask for the horizontal win
        for (int row = 0; row < 6; row++) { //6 masks
            long rowBits1=0;
            long rowBits2=0;
            if(row<3){
                rowBits1 = (mask1 >> (row * 3));
                rowBits2 = (mask2 >> (row * 3));}
            else {
                rowBits1 = (mask1 >> (9+row * 3));
                rowBits2 = (mask2 >> (9+row * 3));
            }

            if (((board & rowBits1) == rowBits1) || ((board & rowBits2) == rowBits2)) {
                return true;
            }
        }


        // Check for vertical wins - אנכי
        long colMask1 = 0b100100100000000000100100000000000000000000000000000000000000000L;//mask for the vertical win
        long colMask2 = 0b000100100000000000100100100000000000000000000000000000000000000L;//mask for the vertical win
        long colMask3 = 0b000000000100100100000000000100100000000000000000000000000000000L;//mask for the vertical win
        long colMask4 = 0b000000000000100100000000000100100100000000000000000000000000000L;//mask for the vertical win

        for (int col = 0; col < 3; col++) {
            long colBits1 = (colMask1 >> (col));
            long colBits2 = (colMask2 >> (col));
            long colBits3 = (colMask3 >> (col));
            long colBits4 = (colMask4 >> (col));
            if (((board & colBits1) == colBits1) || ((board & colBits2) == colBits2) ||
                    ((board & colBits3) == colBits3) || ((board & colBits4) == colBits4)) {
                return true;
            }
        }

        // Check for diagonal wins
        long diag1Mask = 0b100010001000000000000000000100010000000000000000000000000000000L; //monica's five winning
        long diag2Mask = 0b000010001000000000000000000100010001000000000000000000000000000L; //monica's five winning
        if (((board & diag1Mask) == diag1Mask))
            return true;
        if (((board & diag2Mask) == diag2Mask))
            return true;

        long diag3Mask = 0b000000000001010100001010000000000000000000000000000000000000000L; //monica's five winning
        long diag4Mask = 0b000000000000010100001010100000000000000000000000000000000000000L; //monica's five winning
        if (((board & diag3Mask) == diag3Mask))
            return true;
        if (((board & diag4Mask) == diag4Mask))
            return true;


        long theTripleWin =   0b010001000000000100000000000010001000000000000000000000000000000L; //the triple power play winning
        long theTripleWin2 =  0b000100010000000000001000000000100010000000000000000000000000000L; //the triple power play winning
        long theTripleWin3 =  0b000000001010100000010100000000000000000000000000000000000000000L; //the triple power play winning
        long theTripleWin4 =  0b000000000000001010000001010100000000000000000000000000000000000L; //the triple power play winning

        if (((board & theTripleWin) == theTripleWin))
            return true;
        if (((board & theTripleWin2) == theTripleWin2))
            return true;
        if (((board & theTripleWin3) == theTripleWin3))
            return true;
        if (((board & theTripleWin4) == theTripleWin4))
            return true;

        return false; //no winning
    }


    /**
     * returns true if the game ends with tie
     *
     * @return
     */
    public boolean checkForTie() {
        long mask = 0b111111111111111111111111111111111111000000000000000000000000000L;
        long boards = this.player1 | this.player2;
        if ((mask & boards) == mask) {
            return true;
        }
        int current_player= this.player_turn;
        this.player_turn=1;
        if(checkForWin()==true)
        {
            this.player_turn=0;
            if(checkForWin()==true) {
                this.player_turn=current_player;
                return true;
            }
        }
        this.player_turn=current_player;
        return false;
    }


    /**
     * rotates the whole board - all the mini boards to right or left
     * @param mini_board1- the first mini board to rotate
     * @param mini_board2- the second mini board to trotate
     * @return the players after the change
     */
    public long [] rotate_whole(int mini_board1, int mini_board2)
    {
        long [] players= new long[2]; //keeps the players

        if(mini_board1==2 & mini_board2==3) //diagonal, mini board 2 to mini board 1 and mini board 3 to mini board 4
        {
            create_mini_board_twist(2, 1); //rotate left
            create_mini_board_twist(3, 1); //rotate left

            //player2
            long masking_36= 0b111111111111111111111111111111111111000000000000000000000000000L; //mask of the entire board - 36 bits from the left
            long mini_board_second= getPlayer2()>>>18;
            mini_board_second=mini_board_second&masking_36; //deletes all the mini boards besides mini board 2
            mini_board_second=mini_board_second<<27; //move it to mini board 1
            long mini_board_third= getPlayer2()<<18; //deletes the mini boards 1, 2
            mini_board_third=mini_board_third>>>27;//deletes the mini boards 4
            mini_board_third=mini_board_third&masking_36; //deletes all the mini boards besides mini board 3
            setPlayer2(mini_board_second | mini_board_third); //connects the 2 mini board


            //player1
            mini_board_second= getPlayer1()>>>18;
            mini_board_second=mini_board_second&masking_36; //deletes all the mini boards besides mini board 2
            mini_board_second=mini_board_second<<27; //move it to mini board 1
            mini_board_third= getPlayer1()<<18;//deletes the mini boards 1, 2
            mini_board_third=mini_board_third>>>27;//deletes the mini boards 4
            mini_board_third=mini_board_third&masking_36; //deletes all the mini boards besides mini board 3
            setPlayer1(mini_board_second | mini_board_third); //connects the 2 mini board


        }



        if(mini_board1==2 & mini_board2==4) //rows and columns, mini board 2 to be mini board 1, and mini board 4 to
            // be mini board 2
        {
            create_mini_board_twist(2, 1); //rotate left
            create_mini_board_twist(4, 1); //rotate left

            //player2
            long masking_36= 0b111111111111111111111111111111111111000000000000000000000000000L; //mask of the entire board - 36 bits from the left
            long mini_board_second= getPlayer2()<<9; //deletes the first mini board
            mini_board_second=mini_board_second>>>27; //move it to be mini board 4
            mini_board_second=mini_board_second&masking_36; //deletes all the mini boards besides mini board 2
            mini_board_second=mini_board_second<<27; //moves it to be in mini board 1
            long mini_board_fourth = getPlayer2()<<27; //deletes all mini board besides mini board 4
            mini_board_fourth=mini_board_fourth>>>9; //moves it to mini board 2
            setPlayer2(mini_board_second | mini_board_fourth); //connects the 2 mini board with the 1


            //player1
            mini_board_second= getPlayer1()<<9;//deletes the first mini board
            mini_board_second=mini_board_second>>>27;//move it to be mini board 4
            mini_board_second=mini_board_second&masking_36; //deletes all the mini boards besides mini board 2
            mini_board_second=mini_board_second<<27;//moves it to be in mini board 1
            mini_board_fourth = getPlayer1()<<27;//deletes all mini board besides mini board 4
            mini_board_fourth=mini_board_fourth>>>9;//moves it to mini board
            setPlayer1(mini_board_second | mini_board_fourth); //connects the 2 mini board with the 1


        }
        else if(mini_board1==1 & mini_board2==3)//rows and columns, mini board 3 to be mini board 1, and mini board
            // 1 to be mini
            // board 2
        {
            create_mini_board_twist(1, 2); //rotate right
            create_mini_board_twist(3, 2); //rotate right


            //player2
            long masking_36= 0b111111111111111111111111111111111111000000000000000000000000000L;//mask of the entire board - 36 bits from the left
            long mini_board_first= getPlayer2()>>>27; //moving mini board 1 to be 4
            mini_board_first=mini_board_first&masking_36; //deletes all mini board besides mini board 1
            mini_board_first=mini_board_first<<18; //moves it to mini board 2
            long mini_board_third = getPlayer2()<<18; //moving mini board 3 to be 1
            mini_board_third=mini_board_third>>>27; //moving mini board 3 to be 4
            mini_board_third=mini_board_third&masking_36;//deletes all mini board besides mini board 3
            mini_board_third=mini_board_third<<27; //moves it to mini board 1
            setPlayer2(mini_board_first | mini_board_third);//connects the 2 mini board with the 1


            //player1
            mini_board_first= getPlayer1()>>>27;//moving mini board 1 to be 4
            mini_board_first=mini_board_first&masking_36;//deletes all mini board besides mini board 1
            mini_board_first=mini_board_first<<18;//moves it to mini board 2
            mini_board_third = getPlayer1()<<18;//moving mini board 3 to be 1
            mini_board_third=mini_board_third>>>27; //moving mini board 3 to be 4
            mini_board_third=mini_board_third&masking_36;//deletes all mini board besides mini board 3
            mini_board_third=mini_board_third<<27;//moves it to mini board 1
            setPlayer1(mini_board_first | mini_board_third);//connects the 2 mini board with the 1


        }
        else if(mini_board1==3 & mini_board2==4)//rows and columns, mini board 3 to be mini board 1, and mini board 4 to
            // be mini board 2
        {

            setPlayer1(getPlayer1()<<18); //moving mini boards to be 1 and 2
            setPlayer2(getPlayer2()<<18); //moving mini boards to be 1 and 2
            mirroring(); //mirroring the mini board
        }
        players[0]=getPlayer1();
        players[1]=getPlayer2();
        return players;
    }


    /**
     * returns the index matching to the right mini board by doing the rotate back
     * @param index - the index to put the trophy according to mini board 1, 2 for rows and cols
     *              or mini boards 1, 4 for diagonal
     * @param mini_board1- the first mini board to rotate
     * @param mini_board2- the second mini board to rotate
     * @return
     */
    public int rotate_whole_opp(int index, int mini_board1, int mini_board2)
    {
        long mask = 0b100000000000000000000000000000000000000000000000000000000000000L;
        Board b;

        if(mini_board1==2 & mini_board2==3)//diagonal
        {
            long id_mask = (mask >>> (index - 1)); //the place to put the trophy
            long new_mask = (long) id_mask >>>9;//mini board 1 to 2
            id_mask = id_mask <<9; //mini board 4 to be 3
            long mask_total= id_mask | new_mask;//matching them

            if(mask_total==0){//
                b= new Board();
                b.setPlayer1(0);
                b.setPlayer2(id_mask);
            }
            else {
                b= new Board();
                b.setPlayer1(0);
                b.setPlayer2(mask_total);}
            b.create_mini_board_twist(2, 2);//rotate back to the right
            b.create_mini_board_twist(3, 2);//rotate back to the right
            int count=1;
            while((mask & b.getPlayer2()) ==0)//count the new index according to mini board 2, 3
            {
                count++;
                mask=mask>>>1;
            }
            return count;

        }

        if(mini_board1==2 & mini_board2==4)
        {
            long id_mask = (mask >>> (index - 1)); //the place to put the trophy
            id_mask = id_mask >>>9; //moving mini board 1 to be 2 - the original
            long new_mask = (long) (id_mask << 18); //mini board 2 to be in 1
            new_mask = (long) (new_mask >>>27);//moves it to mini board 4
            if(new_mask ==0){//mini board 4 is empty
                b= new Board();
                b.setPlayer1(0);
                b.setPlayer2(id_mask);
            }
            else {b= new Board();
                b.setPlayer1(0);
                b.setPlayer2(new_mask);
            }

            b.create_mini_board_twist(2, 2); //rotate back to the right
            b.create_mini_board_twist(4, 2); //rotate back to the right

            int count=1;
            while((mask & b.getPlayer2()) ==0) //count the new index according to mini board 2, 4
            {
                count++;
                mask=mask>>>1;
            }
            return count;
        }





        else if(mini_board1==1 & mini_board2==3)
        {
            mask = 0b100000000000000000000000000000000000000000000000000000000000000L;
            long id_mask = (mask >>> (index - 1));//the place to put the trophy
            long new_mask = (long) id_mask >>>27;//mini board 1 to 4
            new_mask = new_mask <<9; //mini board 1 to be 3
            id_mask = id_mask <<9; //mini board 2 to be 1
            long mask_total= id_mask | new_mask;//matching them

            if(mask_total==0){//
                b= new Board();
                b.setPlayer1(0);
                b.setPlayer2(id_mask);
            }
            else {
                b= new Board();
                b.setPlayer1(0);
                b.setPlayer2(mask_total);}
            b.create_mini_board_twist(1, 1);//rotate back to the left
            b.create_mini_board_twist(3, 1);//rotate back to the left

            int count=1;
            while((mask & b.getPlayer2()) ==0)//count the new index according to mini board 1, 3
            {
                count++;
                mask=mask>>>1;
            }
            return count;
        }


        else if(mini_board1==3 & mini_board2==4)
        {
            mask = 0b100000000000000000000000000000000000000000000000000000000000000L;
            long id_mask = (mask >>> (index - 1));//the place to put the trophy
            long save_player2=getPlayer2();
            long save_player1=getPlayer1();
            setPlayer2(id_mask);
            mirroring(); //mirroring back
            long get= getPlayer2()>>>18; //move it back
            setPlayer2(save_player2);
            setPlayer1(save_player1);
            int count=1;
            while((mask & get) ==0)//count the new index according to mini board 3, 4
            {
                count++;
                mask=mask>>>1;
            }
            return count;

        }
        return 1;
    }


    /**
     * mirroring mini board 1,2 back to 3, 4 / mini board 3, 4 to 1, 2
     */
    public void mirroring ()
    {
        long keep_it=getPlayer2();
        long [] players= new long[2];
        long mask= 0b100000000000000000000000000000000000000000000000000000000000000L;

        for(int i=0; i<2; i++) //both mini boards
        {
            for(int j=0; j<9; j++) //checking all the bits of each mini board
            {
                if(j<3)
                {
                    if((mask & keep_it)!=0) //place is taken
                    {
                        if((getPlayer2() & mask >>>(6))==0) { //place of the mirrored not taken
                            setPlayer2(getPlayer2() | mask >>> (6)); //moving it to mirrored
                            setPlayer2(getPlayer2() ^ mask);
                        }

                    }
                } else if (j>5)
                {

                    if((mask & keep_it)!=0) { //place is taken
                        if((getPlayer2() & mask <<(6))==0) {//place of the mirrored not taken
                            setPlayer2(getPlayer2() | mask << (6)); //moving it to mirrored
                            setPlayer2(getPlayer2() ^ mask);
                        }
                    }

                }
                mask=mask>>>1;
            }
        }


        mask= 0b100000000000000000000000000000000000000000000000000000000000000L;
        keep_it=getPlayer1();

        for(int i=0; i<2; i++)//both mini boards
        {
            for(int j=0; j<9; j++)//checking all the bits of each mini board
            {
                if(j<3)
                {
                    if((mask & keep_it)!=0)//place is taken
                    {
                        setPlayer1(getPlayer1() | mask >>>(6));//place of the mirrored not taken
                        setPlayer1(getPlayer1() ^ mask); //moving it to mirrored
                    }
                } else if (j>5)
                {
                    if((mask & keep_it)!=0) {//place is taken
                        setPlayer1(getPlayer1() | mask << (6));//place of the mirrored not taken
                        setPlayer1(getPlayer1() ^ mask); //moving it to mirrored
                    }
                }
                mask=mask>>>1;
            }
        }
    }

}