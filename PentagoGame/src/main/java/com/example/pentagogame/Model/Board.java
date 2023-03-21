package com.example.pentagogame.Model;
public class Board {
    private long player1;
    private long player2;
    private int player_turn;

    private long [] masks= new long[]{0b111111111000000000000000000000000000000000000000000000000000000L,
            0b000000000111111111000000000000000000000000000000000000000000000L,
            0b000000000000000000111111111000000000000000000000000000000000000L,
            0b000000000000000000000000000111111111000000000000000000000000000L};

    private final int LEFT = 1;
    private final int RIGHT = 2;

    /**
     * initialize the board
     */
    public Board()
    {
        this.player1=0;
        this.player2=0;
        this.player_turn=1;
    }

    /**
     * getter
     * @return- returns player1 board
     */
    public long getPlayer1()
    {
        return this.player1;
    }

    /**
     * getter
     * @return- returns player2 board
     */
    public long getPlayer2()
    {
        return this.player2;
    }

    /**
     * getter
     * @return- returns current player turn
     */
    public int getPlayer_turn()
    {
        return this.player_turn;
    }

    /**
     * setter
     * @param player1- setting player1 board
     */
    public void setPlayer1(long player1)
    {
        this.player1=player1;
    }

    /**
     * setter
     * @param player2- setting player1 board
     */
    public void setPlayer2(long player2)
    {
        this.player2=player2;
    }

    /**
     * setter
     * @param pt- setting players turn
     */
    public void setPlayer_turn(int pt)
    {
        this.player_turn=pt;
    }

    /**
     * switches players turn
     */
    public void switch_turns()
    {
        if(this.player_turn==1)
        {
            setPlayer_turn(2);
        }
        else
            setPlayer_turn(1);
    }


    /**
     * creates an array of masks and casts x from long to short
     * @param num- the mini board we want to twist
     * @param x- the board
     * @return returns x in short version
     */
    public short mask(int num, long x){
        num=num-1;
        x=x&masks[num];
        x=x>>>(21+(3-num)*9);
        short y = (short)x;
        return y;

    }


    /**
     * chnages the board after the twist
     * @param num- the mini board we want to twist
     * @param x- the board
     * @param last- the mini board after twist
     * @return the board after change
     */
    public long opmask(int num, long x, short last){
        num=num-1;
        x &= ~masks[num];
        long last2= last &(short) 0b111111111000000;
        last2=(long)last;
        x |= (last2 << (21+(3-num)*9)) & masks[num];
        return x;
    }


    /**
     * twisting the board
     * @param num- the board to twist 12
     *                                34
     * @param rotate- 1 to rotate left and 2 to rotate right
     */
    public void create_mini_board_twist(int num, int rotate)
    {
        short miniboard1=0;
        short miniboard2=0;
        miniboard1=mask(num, this.player1);
        miniboard2=mask(num, this.player2);
        MiniBoard mini1=new MiniBoard(miniboard1);
        MiniBoard mini2= new MiniBoard(miniboard2);
        if(rotate==LEFT)
        {
            miniboard1=mini1.rotate_left();
            miniboard2=mini2.rotate_left();
        }
        else
        {
            miniboard1=mini1.rotate_right();
            miniboard2=mini2.rotate_right();
        }
        setPlayer1(opmask(num, this.player1, miniboard1));
        setPlayer2(opmask(num, this.player2, miniboard2));
            //System.out.println("player2 ");
            //System.out.println(Long.toBinaryString(getPlayer2()));

    }

    /**
     * changes the board after adding a trophy
     * @param id_cell- the place the trophy added to
     */
    public boolean change_board(int id_cell) {
        long mask = 0b100000000000000000000000000000000000000000000000000000000000000L;
        long new_mask = (mask >>> (id_cell - 1));

        if ((new_mask & this.player1) != 0 | (new_mask & this.player2) != 0) {
            System.out.println("wrong step");
            return false;
        }
        if (this.player_turn == 1) {
            setPlayer1(getPlayer1() | new_mask);
            //System.out.println(Long.toBinaryString(getPlayer1()));
        } else {
            setPlayer2(getPlayer2() | new_mask);
            //System.out.println(Long.toBinaryString(getPlayer2()));
        }
        return true;

    }


    /**
     * returns true if current player won the game
     * @return
     */
    public boolean checkForWin() {
        long board;
        if(this.player_turn==1)
        {
            board= this.player1;
        }
        else
            board= this.player2;

        // Check for horizontal wins
        long mask1 = 0b111000000110000000000000000000000000000000000000000000000000000L;
        long mask2 = 0b011000000111000000000000000000000000000000000000000000000000000L;
        for (int row = 0; row < 6; row++) {
            long rowBits1 = (mask1 >> (row * 6));
            long rowBits2 = (mask2 >> (row * 6));
            //System.out.println(Long.toBinaryString(rowBits1));
            //System.out.println(Long.toBinaryString(rowBits2));
            if (((board & rowBits1) == rowBits1) || ((board & rowBits2) == rowBits2)) {
                return true;
            }
        }


        // Check for vertical wins
        long colMask1 = 0b100100100000000000100100000000000000000000000000000000000000000L;
        long colMask2 = 0b000100100000000000100100100000000000000000000000000000000000000L;
        long colMask3 = 0b000000000100100100000000000100100000000000000000000000000000000L;
        long colMask4 = 0b000000000000100100000000000100100100000000000000000000000000000L;

        for (int col = 0; col < 3; col++) {
            long colBits1 = (colMask1 >> (col));
            long colBits2 = (colMask2 >> (col));
            long colBits3 = (colMask3 >> (col));
            long colBits4 = (colMask4 >> (col));
            //System.out.println(Long.toBinaryString(colBits1));
            //System.out.println(Long.toBinaryString(colBits4));
            if (((board & colBits1) == colBits1) || ((board & colBits2) == colBits2) ||
                    ((board & colBits3) == colBits3) || ((board & colBits4) == colBits4)) {
                return true;
            }
        }

        // Check for diagonal wins

        long diag1Mask = 0b100010001000000000000000000100010000000000000000000000000000000L;
        long diag1Mask2 = diag1Mask >> 1;
        long diag2Mask = 0b000010001000000000000000000100010001000000000000000000000000000L;
        long diag2Mask2 = diag2Mask << 1;
        if (((board & diag1Mask) == diag1Mask) || (board & (diag1Mask2)) == diag1Mask2)
            return true;
        if (((board & diag2Mask) == diag2Mask) || (board & (diag2Mask2)) == diag2Mask2)
            return true;

        long diag3Mask = 0b000000000001010100001010000000000000000000000000000000000000000L;
        long diag3Mask2 = diag3Mask << 1;
        long diag4Mask = 0b000000000000010100001010100000000000000000000000000000000000000L;
        long diag4Mask2 = diag4Mask >> 1;
        if (((board & diag3Mask) == diag3Mask) || (board & (diag3Mask2)) == diag3Mask2)
            return true;
        if (((board & diag4Mask) == diag4Mask) || (board & (diag4Mask2)) == diag4Mask2)
            return true;

        return false;
    }


    /**
     * returns true if the game ends with tie
     * @return
     */
    public boolean checkForTie()
    {
        long mask= 0b111111111111111111111111111111111111000000000000000000000000000L;
        long boards= this.player1 | this.player2;
        if ((mask & boards)== mask)
        {
            return true;
        }
        return false;
    }


}