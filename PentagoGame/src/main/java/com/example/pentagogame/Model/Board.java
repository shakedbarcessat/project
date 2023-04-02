package com.example.pentagogame.Model;

public class Board {
    private long player1; //the board from player1 point of view
    private long player2; //the board from player2 point of view
    private long aiPlayer; //ai player
    private int player_turn; //the current turn

    private long[] masks = new long[]{0b111111111000000000000000000000000000000000000000000000000000000L,
            0b000000000111111111000000000000000000000000000000000000000000000L,
            0b000000000000000000111111111000000000000000000000000000000000000L,
            0b000000000000000000000000000111111111000000000000000000000000000L}; //masks

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
     * getter
     *
     * @return- returns ai board
     */
    public long getAiPlayer(){return this.aiPlayer;}

    /**
     * getter
     *
     * @return- returns current player turn
     */
    public int getPlayer_turn() {
        return this.player_turn;
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
     * @param ai- setting ai board
     */
    public void setAiPlayer(long ai){this.aiPlayer=ai;}

    /**
     * setter
     *
     * @param pt- setting players turn
     */
    public void setPlayer_turn(int pt) {
        this.player_turn = pt;
    }

    /**
     * switches players turn
     */
    public void switch_turns() {
        if (this.player_turn == 1) {
            setPlayer_turn(2);
        } else
            setPlayer_turn(1);
    }


    /**
     * creates an array of masks and casts x from long to short
     *
     * @param num- the mini board we want to twist
     * @param x-   the board
     * @return returns x in short version
     */
    public short mask(int num, long x) {
        num = num - 1;
        x = x & masks[num];
        x = x >>> (21 + (3 - num) * 9);
        short y = (short) x;
        return y;

    }


    /**
     * chnages the board after the twist
     *
     * @param num-  the mini board we want to twist
     * @param x-    the board
     * @param last- the mini board after twist
     * @return the board after change
     */
    public long opmask(int num, long x, short last) {
        num = num - 1;
        x &= ~masks[num];
        long last2 = last & (short) 0b111111111000000;
        last2 = (long) last;
        x |= (last2 << (21 + (3 - num) * 9)) & masks[num];
        return x;
    }


    /**
     * twisting the board
     *
     * @param num-    the board to twist 12
     *                34
     * @param rotate- 1 to rotate left and 2 to rotate right
     */
    public void create_mini_board_twist(int num, int rotate) {
        short miniboard1 = 0;
        short miniboard2 = 0;
        miniboard1 = mask(num, this.player1);
        miniboard2 = mask(num, this.player2);
        MiniBoard mini1 = new MiniBoard(miniboard1);
        MiniBoard mini2 = new MiniBoard(miniboard2);
        if (rotate == LEFT) {
            miniboard1 = mini1.rotate_left();
            miniboard2 = mini2.rotate_left();
        } else {
            miniboard1 = mini1.rotate_right();
            miniboard2 = mini2.rotate_right();
        }
        setPlayer1(opmask(num, this.player1, miniboard1));
        setPlayer2(opmask(num, this.player2, miniboard2));

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
            System.out.println("wrong step");
            return false;
        }
        if (this.player_turn == 1) {
            setPlayer1(getPlayer1() | new_mask);
        } else {
            setPlayer2(getPlayer2() | new_mask);
        }
        return true;

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

        // Check for horizontal wins
        long mask1 = 0b111000000110000000000000000000000000000000000000000000000000000L;
        long mask2 = 0b011000000111000000000000000000000000000000000000000000000000000L;
        for (int row = 0; row < 6; row++) {
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
            if (((board & colBits1) == colBits1) || ((board & colBits2) == colBits2) ||
                    ((board & colBits3) == colBits3) || ((board & colBits4) == colBits4)) {
                return true;
            }
        }

        // Check for diagonal wins
        long diag1Mask = 0b100010001000000000000000000100010000000000000000000000000000000L;
        long diag2Mask = 0b000010001000000000000000000100010001000000000000000000000000000L;
        if (((board & diag1Mask) == diag1Mask))
            return true;
        if (((board & diag2Mask) == diag2Mask))
            return true;

        long diag3Mask = 0b000000000001010100001010000000000000000000000000000000000000000L;
        long diag4Mask = 0b000000000000010100001010100000000000000000000000000000000000000L;
        if (((board & diag3Mask) == diag3Mask))
            return true;
        if (((board & diag4Mask) == diag4Mask))
            return true;


        long theTripleWin =   0b010001000000000100000000000010001000000000000000000000000000000L;
        long theTripleWin2 =  0b000100010000000000001000000000100010000000000000000000000000000L;
        long theTripleWin3 =  0b000000001010100000010100000000000000000000000000000000000000000L;
        long theTripleWin4 =  0b000000000000001010000001010100000000000000000000000000000000000L;

        if (((board & theTripleWin) == theTripleWin))
            return true;
        if (((board & theTripleWin2) == theTripleWin2))
            return true;
        if (((board & theTripleWin3) == theTripleWin3))
            return true;
        if (((board & theTripleWin4) == theTripleWin4))
            return true;






        return false;
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
        return false;
    }
}