package com.example.pentagogame;

import com.example.pentagogame.Controller.ControllerClass;
import com.example.pentagogame.Model.Board;

import java.util.Random;

public class AiPlayer {
    public static void main(String[] args) {
    }

    private final int START_MINI_2 = 10;
    private final int START_MINI_1 = 0;
    private final int END_MINI_1 = 9;
    private final int START_MINI_3 = 19;
    private final int END_MINI_2 = 18;
    private final int START_MINI_4 = 28;
    private final int MINI_BOARD_SIZE = 9;
    private final int ROW_SIZE = 6;
    private Long player1;
    private Long ai;

    Random rand = new Random();


    private long[] masks_boards_12 = {0b011000000111000000000000000000000000000000000000000000000000000L,
            0b011000000001001001000000000000000000000000000000000000000000000L,
            0b011000000100100100000000000000000000000000000000000000000000000L,
            0b100100000111000000000000000000000000000000000000000000000000000L,
            0b000001001111000000000000000000000000000000000000000000000000000L,

            0b111000000110000000000000000000000000000000000000000000000000000L,
            0b111000000001001000000000000000000000000000000000000000000000000L,
            0b111000000000100100000000000000000000000000000000000000000000000L,
            0b100100100110000000000000000000000000000000000000000000000000000L,
            0b001001001110000000000000000000000000000000000000000000000000000L};

    private long[] masks_boards_12_2 = {0b000111000000110000000000000000000000000000000000000000000000000L,
            0b000111000010010000000000000000000000000000000000000000000000000L,
            0b000111000000010010000000000000000000000000000000000000000000000L,
            0b010010010000110000000000000000000000000000000000000000000000000L,
            0b010010010000110000000000000000000000000000000000000000000000000L,

            0b000011000000111000000000000000000000000000000000000000000000000L,
            0b000011000010010010000000000000000000000000000000000000000000000L,
            0b000011000010010010000000000000000000000000000000000000000000000L,
            0b010010000000111000000000000000000000000000000000000000000000000L,
            0b000010010000111000000000000000000000000000000000000000000000000L};


    private long[] masks_boards_12_3 = {0b000000111000000110000000000000000000000000000000000000000000000L,
            0b000000111100100000000000000000000000000000000000000000000000000L,
            0b000000111000001001000000000000000000000000000000000000000000000L,
            0b001001001000000110000000000000000000000000000000000000000000000L,
            0b100100100000000110000000000000000000000000000000000000000000000L,

            0b000000011000000111000000000000000000000000000000000000000000000L,
            0b000000011100100100000000000000000000000000000000000000000000000L,
            0b000000011001001001000000000000000000000000000000000000000000000L,
            0b001001000000000111000000000000000000000000000000000000000000000L,
            0b000100100000000111000000000000000000000000000000000000000000000L};

    private int[] mini_board_12 = {rand.nextInt(2) + 3, 2, 2, 1, 1};
    private int[] mini_board_24 = {rand.nextInt(2) * 2 + 1, 4, 4, 2, 2};
    private int[] mini_board_34 = {rand.nextInt(2) + 1, 4, 4, 3, 3};
    private int[] mini_board_13 = {rand.nextInt(2) * 2 + 2, 1, 1, 3, 3};

    private int[][] mini_board = {mini_board_12, mini_board_24, mini_board_34, mini_board_13};

    private int[][] mini_boards_to_twist_whole = {{2, 4}, {3, 4}, {1, 3}};


    private int[][] indexes = {{2, 11, 3, 10, 12}, {2, 15, 3, 12, 18}, {2, 13, 3, 10, 16}, {4, 11, 1, 10, 12}, {6, 11, 9, 10, 12},
            {2, 11, 3, 10, 1}, {2, 15, 3, 12, 1}, {2, 13, 3, 16, 1}, {4, 11, 1, 10, 7}, {6, 11, 9, 10, 3}};

    private int[][] indexes_2 = {{5, 14, 4, 6, 13}, {5, 14, 4, 6, 11}, {5, 14, 4, 6, 17}, {5, 14, 2, 8, 17}, {5, 14, 2, 8, 17},
            {5, 14, 6, 13, 15}, {5, 14, 6, 11, 17}, {5, 14, 6, 11, 17}, {5, 14, 2, 13, 15}, {5, 14, 7, 13, 15}};

    private int[][] indexes_3 = {{8, 17, 7, 9, 16}, {8, 13, 7, 9, 10}, {8, 15, 7, 9, 18}, {6, 17, 3, 9, 16}, {4, 17, 1, 7, 16},
            {8, 17, 9, 16, 18}, {8, 13, 9, 10, 16}, {8, 15, 9, 12, 18}, {6, 17, 3, 16, 18}, {4, 17, 7, 16, 18}};
    private int[] twist_mini_boards_12 = {rand.nextInt(2) + 1, 1, 2, 2, 1};
    private int[] twist_mini_boards_34 = {rand.nextInt(2) + 1, 2, 1, 1, 2};

    private boolean check_4_finish = false;
    private boolean check_3_finish = false;
    private boolean check_2_finish = false;
    private boolean check_1_finish = false;
    private boolean check_0_finish = false;


    Random random = new Random(); //randomize
    private int[] possible_moves_12 = {2, 4, 6, 8, 29, 31, 33, 35}; //possible indexes to win the first strategy

    private int[] mini_board_12_possible1 = {random.nextInt(2) + 2, random.nextInt(2) + 2, 1, 1, 4, 4,
            random.nextInt(2) + 2, random.nextInt(2) + 2}; //the mini boards to twist for option 1(the better one)
    private int[] twist_board_12_possible1 = {random.nextInt(2) + 1, random.nextInt(2) + 1, 1, 2, 2, 1,
            random.nextInt(2) + 1, random.nextInt(2) + 1}; //the twists of the mini boards for option 1
    private int[] indexes_move2_12_possible1 = {4, 2, 2, 4, 33, 35, 35, 33}; //the indexes for stage 2- the next ball

    private int[] indexes_move2_12_possible2 = {6, 8, 8, 6, 31, 29, 29, 31};  //the indexes for stage 2- the next ball(the less good)
    private int[] mini_board_12_possible2 = {1, 1, 1, 1, 4, 4, 4, 4}; //the mini boards to twist for option 2
    private int[] twist_board_12_possible2 = {1, 2, 2, 2, 1, 1, 2, 1}; //the twists of the mini boards for option 2


    private int[] possible_moves_34 = {11, 13, 15, 17, 20, 22, 24, 26}; //possible indexes to win the second strategy
    private int[] mini_board_34_possible1 = {random.nextInt(2) * 3 + 1, 2, random.nextInt(2) * 3 + 1,
            2, 3, random.nextInt(2) * 3 + 1, 3, random.nextInt(2) * 3 + 1}; //the mini boards to twist for option 1(the better one)
    private int[] twist_board_34_possible1 = {random.nextInt(2) + 1, 2, random.nextInt(2) + 1,
            1, 1, random.nextInt(2) + 1, 2, random.nextInt(2) + 1}; //the twists of the mini boards for option 1
    private int[] indexes_move2_34_possible1 = {15, 11, 11, 15, 22, 26, 26, 22}; //the indexes for stage 2- the next ball
    private int[] indexes_move2_34_possible2 = {13, 17, 17, 13, 24, 20, 20, 24}; //the indexes for stage 2- the next ball(the less good)
    private int[] mini_board_34_possible2 = {2, 2, 2, 2, 3, 3, 3, 3}; //the mini boards to twist for option 2
    private int[] twist_board_34_possible2 = {2, 1, 1, 1, 2, 1, 2, 2}; //the twists of the mini boards for option 2


    public static int player_move = 0; //number of stage to put the ball - the first ball to put is on move 1;
    private int last_turn_mini_board; //last mini that took
    private int last_turn_playing_strategy; //last strategy that took


    public static int mini_board_for_twist_defense; //mini board for twisting
    public static int index_defense; //index of putting the ball
    public static int direction_rotating_defense; //the side to rotate

    public static int mini_board_for_twist_rows_columns; //mini board for twisting
    public static int index_rows_columns; //index of putting the ball
    public static int direction_rotating_rows_columns; //the side to rotate

    public static int mini_board_for_twist_triple; //mini board for twisting
    public static int index_triple; //index of putting the ball
    public static int direction_rotating_triple; //the side to rotate

    public static int mini_board_for_twist_diagonal; //mini board for twisting
    public static int index_diagonal; //index of putting the ball
    public static int direction_rotating_diagonal; //the side to rotate
    private int grade = 80;
    private int grade_rows_columns = 0;

    private int current_strategy; //the current strategy in the triple power play

    /**
     * set the initialite players
     */
    public AiPlayer() {
        setBoard_player1(ControllerClass.b.getPlayer1());
        setBoard_ai(ControllerClass.b.getPlayer2());
        player_move = 0;
    }

    /**
     * sets the players
     */
    public void setPlayers() {
        setBoard_player1(ControllerClass.b.getPlayer1());
        setBoard_ai(ControllerClass.b.getPlayer2());
    }

    /**
     * set ai board
     *
     * @param board_ai- the ai board to set
     */
    public void setBoard_ai(Long board_ai) {
        this.ai = board_ai;
    }

    /**
     * set player 1 board
     *
     * @param board_player1- the player1 board to set
     */
    public void setBoard_player1(Long board_player1) {
        this.player1 = board_player1;
    }

    /**
     * counts how many 1 are in the string
     *
     * @param s- the board
     * @return
     */
    public int count_1(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1')
                count++;
        }
        return count;
    }


    /**
     * calculates the mini board to twist according to the index(the mini board to twist will be the opposite)
     *
     * @param index- index of the button to put
     * @return- the mini board
     */
    public int calc_mini_board_diagonal_triple(int index, int strategy_mask) {
        Random rand = new Random();

        if (strategy_mask == 0) //mini boards 1, 2, 4
        {
            if (index < START_MINI_2 & index > START_MINI_1) {
                return rand.nextInt(2) * 2 + 2;// 2 or 4
            } else if (index > END_MINI_1 & index < START_MINI_3) {
                return rand.nextInt(2) * 3 + 1;// 1 or 4
            } else
                return rand.nextInt(2) + 1;// 1 or 2
        } else if (strategy_mask == 1) //mini boards 1, 3, 4
        {
            if (index < START_MINI_2 & index > START_MINI_1) {
                return rand.nextInt(2) + 3;// 3 or 4
            } else if (index > END_MINI_2 & index < START_MINI_4) {
                return rand.nextInt(2) * 3 + 1;// 1 or 4
            } else
                return rand.nextInt(2) * 2 + 1;// 1 or 3
        } else if (strategy_mask == 2) //mini boards 1, 2, 3
        {
            if (index < START_MINI_2 & index > START_MINI_1) {
                return rand.nextInt(2) + 2;// 2 or 3
            } else if (index > END_MINI_2 & index < START_MINI_4) {
                return rand.nextInt(2) + 1;// 1 or 2
            } else
                return rand.nextInt(2) * 2 + 1;// 1 or 3
        } else //mini boards 2, 3, 4
        {
            if (index > END_MINI_1 & index < START_MINI_3) {
                return rand.nextInt(2) + 3;// 3 or 4
            } else if (index > END_MINI_2 & index < START_MINI_4) {
                return rand.nextInt(2) * 2 + 2;// 2 or 4
            } else
                return rand.nextInt(2) + 2;// 2 or 3
        }
    }


    /**
     * defensing the game from vertical, horizontal or diagonal win.
     * starts from the 3 ball.
     * return- the grade for the strategy
     */
    public int defense() {
        long board = player1; //the board to check
        int grade = 0; //the grade for thr defence
        boolean need_defence = false; //true if needs defence


        // Check for horizontal wins
        index_defense = 0;
        mini_board_for_twist_defense = 0;
        check_4_finish = false; //check if there is 4 in a row
        check_3_finish = false; //check if there is 3 in a row
        int[][][] indexes_total = {indexes, indexes_2, indexes_3};
        long[][] masks_total = {masks_boards_12, masks_boards_12_2, masks_boards_12_3};

        for (int z = 0; z < 3; z++) {

            for (int x = 0; x < 4; x++) {
                long keep_ai = ai;
                long keep_player1 = player1;
                Board b = new Board();
                b.setPlayer1(player1);
                b.setPlayer2(ai);
                if (x > 0) {
                    long[] players = b.rotate_whole(mini_boards_to_twist_whole[x - 1][0], mini_boards_to_twist_whole[x - 1][1]);
                    keep_ai = players[1];
                    keep_player1 = players[0];
                }
                for (int i = 0; i < 10; i++) {
                    if (count_1(Long.toBinaryString(keep_player1 & masks_total[z][i])) == 4 & count_1(Long.toBinaryString(keep_ai & masks_total[z][i])) == 0) { //not found
                        long mask = 0b100000000000000000000000000000000000000000000000000000000000000L;
                        for (int j = 0; j < 5; j++) {
                            if (check_4_finish == false & ((mask >>> (indexes_total[z][i][j] - 1)) & keep_player1) == 0) {
                                index_defense = indexes_total[z][i][j];
                                if (x > 0) {
                                    index_defense = b.rotate_whole_opp(index_defense, mini_boards_to_twist_whole[x - 1][0], mini_boards_to_twist_whole[x - 1][1]);//the right index
                                }
                                mini_board_for_twist_defense = this.mini_board[x][(i % 5)]; //the mini board to twist
                                if (x == 2) {
                                    direction_rotating_defense = this.twist_mini_boards_34[(i % 5)]; //the direction to rotate
                                } else
                                    direction_rotating_defense = this.twist_mini_boards_12[(i % 5)]; //the direction to rotate
                                check_4_finish = true;
                                return 100;
                            }
                        }
                    }
                }
            }
        }


        if (check_4_finish == false) {
            for (int z = 0; z < 3; z++) {
                for (int x = 0; x < 4; x++) {
                    long keep_ai = ai;
                    long keep_player1 = player1;
                    Board b = new Board();
                    b.setPlayer1(player1);
                    b.setPlayer2(ai);
                    if (x > 0) {
                        long[] players = b.rotate_whole(mini_boards_to_twist_whole[x - 1][0], mini_boards_to_twist_whole[x - 1][1]);
                        keep_ai = players[1];
                        keep_player1 = players[0];
                    }
                    for (int i = 0; i < 10; i++) {
                        if (count_1(Long.toBinaryString(keep_player1 & masks_total[z][i])) == 3 & count_1(Long.toBinaryString(keep_ai & masks_total[z][i])) == 0) { //0 opponents
                            long mask = 0b100000000000000000000000000000000000000000000000000000000000000L;
                            for (int j = 0; j < 5; j++) {
                                if (((mask >>> (indexes_total[z][i][j] - 1)) & keep_ai) == 0 & ((mask >>> (indexes_total[z][i][j] - 1)) & keep_player1) == 0) { //not taken
                                    need_defence = true;
                                    index_defense = indexes_total[z][i][j];
                                    if (x > 0) {
                                        index_defense = b.rotate_whole_opp(index_defense, mini_boards_to_twist_whole[x - 1][0], mini_boards_to_twist_whole[x - 1][1]); //the right index
                                    }
                                    mini_board_for_twist_defense = this.mini_board[x][(i % 5)]; //the mini board to twist
                                    if (x == 2) {
                                        direction_rotating_defense = this.twist_mini_boards_34[(i % 5)]; //the direction to rotate
                                    } else
                                        direction_rotating_defense = this.twist_mini_boards_12[(i % 5)]; //the direction to rotate
                                    check_3_finish = true; //already found
                                }
                            }

                        }
                    }
                }
            }
        }


        if (need_defence == false) {
            // Check for diagonal wins
            int[] twist_mini_boards = {rand.nextInt(2) + 1, rand.nextInt(2) + 1, 2, 1};
            int[] mini_boards_1 = {rand.nextInt(2) + 2, 1, 4, 4};
            int[] mini_boards_2 = {rand.nextInt(2) + 2, 4, 1, 1};

            long[][] masks_diagonal1 = {{0b100010001000000000000000000100010000000000000000000000000000000L,
                    0b001010100000000000000000000100010000000000000000000000000000000L,
                    0b100010001000000000000000000000010100000000000000000000000000000L,
                    0b100010001000000000000000000001010000000000000000000000000000000L},

                    {0b000010001000000000000000000100010001000000000000000000000000000L,
                            0b000010001000000000000000000001010010000000000000000000000000000L,
                            0b001010000000000000000000000100010001000000000000000000000000000L,
                            0b000010100000000000000000000100010001000000000000000000000000000L}};

            int[][] indexes_1 = {{5, 32, 1, 9, 28}, {5, 32, 3, 7, 28}, {5, 32, 1, 9, 34}, {5, 32, 1, 9, 30}};
            int[][] indexes2 = {{5, 32, 9, 28, 36}, {5, 32, 9, 30, 34}, {5, 32, 3, 28, 36}, {5, 32, 7, 28, 36}};

            check_4_finish = false; //check if there is 4 in a row
            check_3_finish = false; //check if there is 3 in a row
            long keep_ai = ai;
            long keep_player1 = player1;

            int[][][] index_total = {indexes_1, indexes2};
            int[][] mini_boards_total = {mini_boards_1, mini_boards_2};

            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 4; j++) {
                    if (count_1(Long.toBinaryString(keep_player1 & masks_diagonal1[i][j])) == 4 & count_1(Long.toBinaryString(keep_ai & masks_diagonal1[i][j])) == 0) { //0 opponents
                        long mask = 0b100000000000000000000000000000000000000000000000000000000000000L;
                        for (int z = 0; z < 5; z++) {
                            if ((check_4_finish == false) & ((mask >>> (index_total[i][j][z] - 1)) & keep_ai) == 0 & ((mask >>> (index_total[i][j][z] - 1)) & keep_player1) == 0) { //not taken
                                need_defence = true;
                                index_defense = index_total[i][j][z];
                                mini_board_for_twist_defense = mini_boards_total[i][j]; //the mini board to twist
                                direction_rotating_defense = twist_mini_boards[j]; //the direction to rotate
                                check_4_finish = true; //already found
                                return 100;
                            }
                        }

                    }
                }
            }
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 4; j++) {
                    if (count_1(Long.toBinaryString(keep_player1 & masks_diagonal1[i][j])) == 3 & count_1(Long.toBinaryString(keep_ai & masks_diagonal1[i][j])) == 0) { //0 opponents
                        long mask = 0b100000000000000000000000000000000000000000000000000000000000000L;
                        for (int z = 0; z < 5; z++) {
                            if ((check_3_finish == false) & ((mask >>> (index_total[i][j][z] - 1)) & keep_ai) == 0 & ((mask >>> (index_total[i][j][z] - 1)) & keep_player1) == 0) { //not taken
                                need_defence = true;
                                index_defense = index_total[i][j][z];
                                mini_board_for_twist_defense = mini_boards_total[i][j]; //the mini board to twist
                                direction_rotating_defense = twist_mini_boards[j]; //the direction to rotate
                                check_3_finish = true; //already found
                            }
                        }

                    }
                }
            }


            int[] mini_boards_3 = {rand.nextInt(2) * 3 + 1, 1, 3, 3};
            int[] mini_boards_4 = {rand.nextInt(2) * 3 + 1, 3, 1, 1};

            long[][] masks_diagonal2 = {{0b000000000001010100001010000000000000000000000000000000000000000L,
                    0b000000000100010001001010000000000000000000000000000000000000000L,
                    0b000000000001010100100010000000000000000000000000000000000000000L,
                    0b000000000001010100000010001000000000000000000000000000000000000L},

                    {0b000000000000010100001010100000000000000000000000000000000000000L,
                            0b000000000000010100100010001000000000000000000000000000000000000L,
                            0b000000000000010001001010100000000000000000000000000000000000000L,
                            0b000000000100010000001010100000000000000000000000000000000000000L}};

            int[][] indexes3 = {{14, 23, 12, 16, 21}, {14, 23, 10, 18, 21}, {14, 23, 12, 16, 19}, {14, 23, 12, 16, 27}};
            int[][] indexes4 = {{14, 23, 16, 21, 25}, {14, 23, 16, 19, 27}, {14, 23, 18, 21, 25}, {14, 23, 10, 21, 25}};

            check_4_finish = false; //check if there is 4 in a row
            check_3_finish = false; //check if there is 3 in a row


            int[][][] index_total_2 = {indexes3, indexes4};
            int[][] mini_boards_total_2 = {mini_boards_3, mini_boards_4};

            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 4; j++) {
                    if (count_1(Long.toBinaryString(keep_player1 & masks_diagonal2[i][j])) == 4 & count_1(Long.toBinaryString(keep_ai & masks_diagonal2[i][j])) == 0) { //0 opponents
                        long mask = 0b100000000000000000000000000000000000000000000000000000000000000L;
                        for (int z = 0; z < 5; z++) {
                            if ((check_4_finish == false) & ((mask >>> (index_total_2[i][j][z] - 1)) & keep_ai) == 0 & ((mask >>> (index_total_2[i][j][z] - 1)) & keep_player1) == 0) { //not taken
                                need_defence = true;
                                index_defense = index_total_2[i][j][z];
                                mini_board_for_twist_defense = mini_boards_total_2[i][j]; //the mini board to twist
                                direction_rotating_defense = twist_mini_boards[j]; //the direction to rotate
                                check_4_finish = true; //already found
                                return 100;
                            }
                        }

                    }
                }
            }
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 4; j++) {
                    if (count_1(Long.toBinaryString(keep_player1 & masks_diagonal2[i][j])) == 3 & count_1(Long.toBinaryString(keep_ai & masks_diagonal2[i][j])) == 0) { //0 opponents
                        long mask = 0b100000000000000000000000000000000000000000000000000000000000000L;
                        for (int z = 0; z < 5; z++) {
                            if ((check_3_finish == false) & ((mask >>> (index_total_2[i][j][z] - 1)) & keep_ai) == 0 & ((mask >>> (index_total_2[i][j][z] - 1)) & keep_player1) == 0) { //not taken
                                System.out.println("IM INSIDE");
                                need_defence = true;
                                index_defense = index_total_2[i][j][z];
                                mini_board_for_twist_defense = mini_boards_total_2[i][j]; //the mini board to twist
                                direction_rotating_defense = twist_mini_boards[j]; //the direction to rotate
                                check_3_finish = true; //already found
                            }
                        }

                    }
                }
            }

        }


        if (need_defence == false) {
            // Check for triple power play wins
            long theTripleWin = 0b010001000000000100000000000010001000000000000000000000000000000L;
            long theTripleWin2 = 0b000100010000000000001000000000100010000000000000000000000000000L;
            long theTripleWin3 = 0b000000001010100000010100000000000000000000000000000000000000000L;
            long theTripleWin4 = 0b000000000000001010000001010100000000000000000000000000000000000L;

            long mask = 0;
            mini_board_for_twist_defense = 0;
            index_defense = 0;

            String new_bits1 = Long.toBinaryString(board & theTripleWin);
            String new_bits2 = Long.toBinaryString(board & theTripleWin2);
            String new_bits3 = Long.toBinaryString(board & theTripleWin3);
            String new_bits4 = Long.toBinaryString(board & theTripleWin4);

            long new_bits_ai1 = ai & theTripleWin;//check if already defending
            long new_bits_ai2 = ai & theTripleWin2;//check if already defending
            long new_bits_ai3 = ai & theTripleWin3;//check if already defending
            long new_bits_ai4 = ai & theTripleWin4;//check if already defending

            int count1 = count_1(new_bits1);//count how many 1 are in the board for each way of winning - the first mask
            int count2 = count_1(new_bits2);//count how many 1 are in the board for each way of winning - the second mask
            int count3 = count_1(new_bits3);//count how many 1 are in the board for each way of winning - the third mask
            int count4 = count_1(new_bits4);//count how many 1 are in the board for each way of winning - the fourth mask


            Long masks2[] = {0b010000000000000000000000000000000000000000000000000000000000000L,
                    0b000100000000000000000000000000000000000000000000000000000000000L,
                    0b000000001000000000000000000000000000000000000000000000000000000L,
                    0b000000000000001000000000000000000000000000000000000000000000000L};
            int start_indexex2[] = {2, 4, 9, 15}; //index to start
            int middle_indexes2[] = {16, 21, 13, 24}; //changed mini board index - to 2 board
            int end_indexes2[] = {29, 31, 20, 26}; //changed mini board index - to 3 board
            int counts2[] = {count1, count2, count3, count4}; //the counts
            Long boards2[] = {theTripleWin, theTripleWin2, theTripleWin3, theTripleWin4}; //the mask
            Long ai_masks2[] = {new_bits_ai1, new_bits_ai2, new_bits_ai3, new_bits_ai4}; //the ai mask
            int indexes_gap1[] = {4, 4, 2, 2}; //the gap between the indexes
            int indexes_shift1[] = {6, 9, 0, 5}; //the shift between the mini boards - 1 to 2
            int indexes_shift2[] = {13, 10, 7, 2}; //the shift between the mini boards - 2 to 3


            for (int i = 0; i < 4; i++) {
                if ((counts2[i] > 2 && ai_masks2[i] == 0)) { //need defending - mask 1
                    need_defence = true;
                    mask = masks2[i]; //find the next index to put the ball
                    index_defense = start_indexex2[i];
                    boolean found = false;
                    for (int j = 0; j < 2; j++) {
                        if (found == false) {
                            if ((mask & (board & boards2[i])) == 0) { //found an index for defending
                                found = true;
                                mini_board_for_twist_defense = calc_mini_board_diagonal_triple(index_defense, i); //the mini board to twist
                            } else index_defense = index_defense + indexes_gap1[i];
                            mask = mask >> indexes_gap1[i];
                        }
                    }


                    if (found == false) {
                        mask = mask >> indexes_shift1[i];
                        index_defense = middle_indexes2[i];
                    }
                    if (found == false) {
                        if ((mask & (board & boards2[i])) == 0) { //found an index for defending
                            found = true;
                            mini_board_for_twist_defense = calc_mini_board_diagonal_triple(index_defense, i);//the mini board to twist
                        }
                    }


                    if (found == false) {
                        mask = mask >> indexes_shift2[i];
                        index_defense = end_indexes2[i];
                    }
                    for (int j = 0; j < 2; j++) {
                        if (found == false) {
                            if ((mask & (board & boards2[i])) == 0) { //found an index for defending
                                found = true;
                                mini_board_for_twist_defense = calc_mini_board_diagonal_triple(index_defense, i);//the mini board to twist
                            } else index_defense = index_defense + indexes_gap1[i];
                            mask = mask >> indexes_gap1[i];
                        }
                    }

                }
            }
        }


        if (need_defence == true)
            grade = 100; //sets defence grade
        else grade = 0;
//        direction_rotating_defence=rand.nextInt(2)+1;
        return grade;
    }


    /**
     * checks if the strategy is over- lose.
     * it happens when player1 puts 2 trophy in the middle of mini board that are not diagonal
     *
     * @return true if lise, else false
     */
    public boolean check_for_lose() {
        long[] masks =
                {0b000010000000010000000000000000000000000000000000000000000000000L,
                        0b000010000000000000000010000000000000000000000000000000000000000L,
                        0b000000000000010000000000000000010000000000000000000000000000000L,
                        0b000000000000000000000010000000010000000000000000000000000000000L};
        int count = 0;
        for (int i = 0; i < 4; i++) {
            if ((player1 & masks[i]) == masks[i])//the mask matches- lose
            {
                count++;
            }
        }
        if (count > 0) //lose
            return true;
        return false;
    }

    /**
     * check for the lose of the left diagonal.
     * if there is a trophy of player 1 in the middle
     *
     * @return true if mask1 is lose, else false
     */
    public boolean check_for_lose_1() {
        long mask1 =
                0b000010000000000000000000000000010000000000000000000000000000000L;
        if (count_1(Long.toBinaryString(mask1 & player1)) > 0)//lose
        {
            return true;
        }
        return false;
    }

    /**
     * repeating the checking after every strategy
     *
     * @param check-        check if already finished
     * @param i-            the i
     * @param keep_ai-      the ai board
     * @param keep_player1- the player 1 board
     * @param j-            the j
     * @param b-            the board
     * @param r-            the right schema
     * @return
     */
    public boolean repeat_diagonal(boolean check, int i, long keep_ai,
                                   long keep_player1, int j, Board b, int r) {
        int[] twist_mini_boards_1 = {rand.nextInt(2) + 1, rand.nextInt(2) + 1, 2, 1}; //direction rotating for
        // the left diagonal
        int[] mini_boards_1 = {rand.nextInt(2) + 2, 1, 4, 4}; //mini boards for left diagonal
        int[] mini_boards_2 = {rand.nextInt(2) + 2, 4, 1, 1}; //mini boards for left diagonal
        int[][] indexes_1 = {{5, 32, 1, 9, 28}, {5, 32, 3, 7, 28}, {5, 32, 1, 9, 34}, {5, 32, 1, 9, 30}}; //indexes
        // to put the trophy
        int[][] indexes2 = {{5, 32, 9, 28, 36}, {5, 32, 9, 30, 34}, {5, 32, 3, 28, 36}, {5, 32, 7, 28, 36}};//indexes
        // to put the trophy
        int[][][] index_total = {indexes_1, indexes2};
        int[][] mini_boards_total = {mini_boards_1, mini_boards_2};

        int[] twist_mini_boards_2 = {rand.nextInt(2) + 1, rand.nextInt(2) + 1, 1, 2};//direction rotating for
        // the right diagonal
        int[] mini_boards_3 = {rand.nextInt(2) * 3 + 1, 1, 3, 3};//mini boards for right diagonal
        int[] mini_boards_4 = {rand.nextInt(2) * 3 + 1, 3, 1, 1};//mini boards for right diagonal
        int[][] mini_boards_total_2 = {mini_boards_3, mini_boards_4};


        int[][] twist_all = {twist_mini_boards_1, twist_mini_boards_2};
        int[][][] mini_boards_all = {mini_boards_total, mini_boards_total_2};


        long mask = 0b100000000000000000000000000000000000000000000000000000000000000L;
        for (int z = 0; z < 5; z++) {
            if ((check == false) & ((mask >>> (index_total[i][j][z] - 1)) & keep_ai) == 0 & ((mask >>> (index_total[i][j][z] - 1)) & keep_player1) == 0) { //not taken
                index_diagonal = index_total[i][j][z];
                if (r == 1) { //needs to make twisting
                    index_diagonal = b.rotate_whole_opp(index_diagonal, 2, 3);
                }
                mini_board_for_twist_diagonal = mini_boards_all[r][i][j];
                //the mini board to twist
                direction_rotating_diagonal = twist_all[r][j];
                check = true; //found
            }

        }
        if (check == true)
            return true;
        return false;
    }

    /**
     * diagonal check
     *
     * @return returns the grade for the strategy-
     * 30 for less than 4 trophy
     * 120 for 4 trophy- winning
     */
    public int diagonal() {

        long[][] masks_diagonal1 = {{0b100010001000000000000000000100010000000000000000000000000000000L,
                0b001010100000000000000000000100010000000000000000000000000000000L,
                0b100010001000000000000000000000010100000000000000000000000000000L,
                0b100010001000000000000000000001010000000000000000000000000000000L},

                {0b000010001000000000000000000100010001000000000000000000000000000L,
                        0b000010001000000000000000000001010010000000000000000000000000000L,
                        0b001010000000000000000000000100010001000000000000000000000000000L,
                        0b000010100000000000000000000100010001000000000000000000000000000L}};

        check_4_finish = false; //check if there is 4 in a row
        check_3_finish = false; //check if there is 3 in a row
        check_2_finish = false;//check if there is 2 in a row
        check_1_finish = false;//check if there is 1 in a row
        check_0_finish = false;//check if there is 0 in a row
        long keep_ai = ai;
        long keep_player1 = player1;

        if (check_for_lose() == false) { //no lose
            for (int r = 0; r < 2; r++) {
                if (check_for_lose_1() == true)//lose in left diagonal
                {
                    r = 1; //go to right diagonal
                }
                for (int i = 0; i < 2; i++) {
                    keep_ai = ai;
                    keep_player1 = player1;
                    Board b = new Board();
                    b.setPlayer1(player1);
                    b.setPlayer2(ai);
                    if (r == 1) {
                        long[] players = b.rotate_whole(2, 3);
                        keep_ai = players[1];
                        keep_player1 = players[0];
                    }
                    for (int j = 0; j < 4; j++) {

                        if (count_1(Long.toBinaryString(keep_ai & masks_diagonal1[i][j])) == 4 & count_1(Long.toBinaryString(keep_player1 & masks_diagonal1[i][j])) == 0) { //0 opponents
                            check_4_finish = repeat_diagonal(check_4_finish, i,
                                    keep_ai, keep_player1, j, b, r);
                            if (check_4_finish == true) return 120; //sure win
                        }
                    }
                }
            }
        }
        if (check_4_finish == false & check_for_lose() == false) { //no lose
            for (int r = 0; r < 2; r++) {
                if (check_for_lose_1() == true)//lose in left diagonal
                {
                    r = 1; //go to right diagonal
                }
                for (int i = 0; i < 2; i++) {
                    keep_ai = ai;
                    keep_player1 = player1;
                    Board b = new Board();
                    b.setPlayer1(player1);
                    b.setPlayer2(ai);
                    if (r == 1) {
                        long[] players = b.rotate_whole(2, 3);
                        keep_ai = players[1];
                        keep_player1 = players[0];
                    }
                    for (int j = 0; j < 4; j++) {
                        if (count_1(Long.toBinaryString(keep_ai & masks_diagonal1[i][j])) == 3 & count_1(Long.toBinaryString(keep_player1 & masks_diagonal1[i][j])) < 2) { //less than 2 opponents
                            check_3_finish = repeat_diagonal(check_3_finish, i,
                                    keep_ai, keep_player1, j, b, r);
                            if (check_3_finish == true) return 30;
                        }
                    }
                }
            }
        }


        if (check_4_finish == false & check_3_finish == false & check_for_lose() == false) {//no lose
            for (int r = 0; r < 2; r++) {
                if (check_for_lose_1() == true) //lose in left diagonal
                {
                    r = 1; //go to right diagonal
                }
                for (int i = 0; i < 2; i++) {
                    keep_ai = ai;
                    keep_player1 = player1;
                    Board b = new Board();
                    b.setPlayer1(player1);
                    b.setPlayer2(ai);
                    if (r == 1) {
                        long[] players = b.rotate_whole(2, 3);
                        keep_ai = players[1];
                        keep_player1 = players[0];
                    }
                    for (int j = 0; j < 4; j++) {
                        if (count_1(Long.toBinaryString(keep_ai & masks_diagonal1[i][j])) == 2 & count_1(Long.toBinaryString(keep_player1 & masks_diagonal1[i][j])) < 2) { // less than 2 opponents
                            check_2_finish = repeat_diagonal(check_2_finish, i,
                                    keep_ai, keep_player1, j, b, r);
                            if (check_2_finish == true) return 30;
                        }
                    }
                }
            }
        }


        if (check_4_finish == false & check_3_finish == false & check_2_finish == false & check_for_lose() == false) {
            //no lose
            for (int r = 0; r < 2; r++) {
                if (check_for_lose_1() == true) //lose in left diagonal
                {
                    r = 1; //go to right diagonal
                }
                for (int i = 0; i < 2; i++) {
                    keep_ai = ai;
                    keep_player1 = player1;
                    Board b = new Board();
                    b.setPlayer1(player1);
                    b.setPlayer2(ai);
                    if (r == 1) {
                        long[] players = b.rotate_whole(2, 3);
                        keep_ai = players[1];
                        keep_player1 = players[0];
                    }
                    for (int j = 0; j < 4; j++) {
                        if (count_1(Long.toBinaryString(keep_ai & masks_diagonal1[i][j])) == 1 & count_1(Long.toBinaryString(keep_player1 & masks_diagonal1[i][j])) < 2) { //less than 2 opponents
                            check_1_finish = repeat_diagonal(check_1_finish, i,
                                    keep_ai, keep_player1, j, b, r);
                            if (check_1_finish == true) return 30;
                        }
                    }
                }
            }
        }


        if (check_4_finish == false & check_3_finish == false & check_2_finish == false & check_1_finish == false & check_for_lose() == false) {//no lose
            for (int r = 0; r < 2; r++) {
                if (check_for_lose_1() == true) //lose in left diagonal
                {
                    r = 1;//go to right diagonal
                }
                for (int i = 0; i < 2; i++) {
                    keep_ai = ai;
                    keep_player1 = player1;
                    Board b = new Board();
                    b.setPlayer1(player1);
                    b.setPlayer2(ai);
                    if (r == 1) {
                        long[] players = b.rotate_whole(2, 3);
                        keep_ai = players[1];
                        keep_player1 = players[0];
                    }
                    for (int j = 0; j < 4; j++) {
                        if (count_1(Long.toBinaryString(keep_ai & masks_diagonal1[i][j])) == 0 & count_1(Long.toBinaryString(keep_player1 & masks_diagonal1[i][j])) < 2) { //less than 2 opponents
                            check_0_finish = repeat_diagonal(check_0_finish, i,
                                    keep_ai, keep_player1, j, b, r);
                            if (check_0_finish == true) return 30;
                        }
                    }
                }
            }
        }
        if (check_for_lose() == true) return 0;//lose
        return 30;

    }


    /**
     * move 1, putting the first ball
     *
     * @param playing_strategy- the strategy 1 or 2
     */
    private void player_move_1(int playing_strategy) {
        if (check_for_lose_sides(current_strategy) == false & check_for_lose(current_strategy) == false) {
            boolean found_empty_place = false;
            int index = 0;
            int[] possible_moves_12_copy = {2, 4, 6, 8, 29, 31, 33, 35}; //possible indexes to win the first strategy
            int[] mini_board_12_possible1_copy = {random.nextInt(2) + 2, random.nextInt(2) + 2, 1, 1, 4, 4,
                    random.nextInt(2) + 2, random.nextInt(2) + 2}; //the mini boards to twist for option 1(the better one)
            int[] twist_board_12_possible1_copy = {random.nextInt(2) + 1, random.nextInt(2) + 1, 1, 2, 2, 1,
                    random.nextInt(2) + 1, random.nextInt(2) + 1}; //the twists of the mini boards for option 1
            int[] possible_moves_34_copy = {11, 13, 15, 17, 20, 22, 24, 26}; //possible indexes to win the second strategy
            int[] mini_board_34_possible1_copy = {random.nextInt(2) * 3 + 1, 2, random.nextInt(2) * 3 + 1,
                    2, 3, random.nextInt(2) * 3 + 1, 3, random.nextInt(2) * 3 + 1}; //the mini boards to twist for option 1(the better one)
            int[] twist_board_34_possible1_copy = {random.nextInt(2) + 1, 2, random.nextInt(2) + 1,
                    1, 1, random.nextInt(2) + 1, 2, random.nextInt(2) + 1}; //the twists of the mini boards for option 1


            while (found_empty_place == false) {
                long mask_possible = 0b100000000000000000000000000000000000000000000000000000000000000L; //follow each bit
                int i = rand.nextInt(8 - index++);
                if (playing_strategy == 1) {
                    if (((mask_possible >>> (possible_moves_12_copy[i] - 1)) & player1) == 0 &
                            ((mask_possible >>> (possible_moves_12_copy[i] - 1)) & ai) == 0) //place not taken
                    {
                        found_empty_place = true;
                        AiPlayer.index_triple = possible_moves_12_copy[i];
                        AiPlayer.mini_board_for_twist_triple = mini_board_12_possible1_copy[i];
                        AiPlayer.direction_rotating_triple = twist_board_12_possible1_copy[i];
                    } else { //not found
                        possible_moves_12_copy[i] = possible_moves_12_copy[8 - index];
                        mini_board_12_possible1_copy[i] = mini_board_12_possible1_copy[8 - index];
                        twist_board_12_possible1_copy[i] = twist_board_12_possible1_copy[8 - index];
                    }
                } else if (playing_strategy == 2) {
                    if (((mask_possible >>> (possible_moves_34_copy[i] - 1)) & player1) == 0 &
                            ((mask_possible >>> (possible_moves_34_copy[i] - 1)) & ai) == 0) //place not taken
                    {
                        found_empty_place = true;
                        AiPlayer.index_triple = possible_moves_34_copy[i];
                        AiPlayer.mini_board_for_twist_triple = mini_board_34_possible1_copy[i];
                        AiPlayer.direction_rotating_triple = twist_board_34_possible1_copy[i];
                    } else {
                        possible_moves_34_copy[i] = possible_moves_34_copy[8 - index];
                        mini_board_34_possible1_copy[i] = mini_board_34_possible1_copy[8 - index];
                        twist_board_34_possible1_copy[i] = twist_board_34_possible1_copy[8 - index];
                    }
                }
            }
        }
    }

    /**
     * move 2, putting the second ball
     */
    private void player_move_2() {
        long mask12 = 0b010000000000000000000000000000000000000000000000000000000000000L; //mask to follow the bits - first strategy
        long mask34 = 0b000000000010000000000000000000000000000000000000000000000000000L; //mask to follow the bits - second strategy
        boolean check_finish_12 = false;
        boolean check_finish_34 = false;
        if (check_for_lose(current_strategy) == false & check_for_lose_sides(current_strategy) == false) {
            for (int i = 0; i < 8; i++) {
                if (check_finish_12 == false) {
                    long mask_possible = 0b100000000000000000000000000000000000000000000000000000000000000L; //follow each bit
                    if ((mask12 & ai) != 0) {
                        check_finish_12 = true; //not continue the for
                        if (((mask_possible >>> (indexes_move2_12_possible1[i] - 1)) & player1) == 0 &
                                ((mask_possible >>> (indexes_move2_12_possible1[i] - 1)) & ai) == 0) { //check opponent
                            AiPlayer.index_triple = indexes_move2_12_possible1[i];
                            AiPlayer.mini_board_for_twist_triple = mini_board_12_possible1[i];
                            AiPlayer.direction_rotating_triple = twist_board_12_possible1[i];

                        } else if (((mask_possible >>> (indexes_move2_12_possible2[i] - 1)) & player1) == 0 &
                                ((mask_possible >>> (indexes_move2_12_possible2[i] - 1)) & ai) == 0) {
                            AiPlayer.index_triple = indexes_move2_12_possible2[i];
                            AiPlayer.mini_board_for_twist_triple = mini_board_12_possible2[i];
                            AiPlayer.direction_rotating_triple = twist_board_12_possible2[i];
                        }
                    }
                    if (i == 3) { //changing mini board
                        mask12 = mask12 >>> 21;
                    } else mask12 = mask12 >>> 2;
                }
            }
            if (check_finish_12 == false) {
                for (int i = 0; i < 8; i++) {
                    if (check_finish_34 == false) {
                        long mask_possible = 0b100000000000000000000000000000000000000000000000000000000000000L; //follow each bit
                        if ((mask34 & ai) != 0) {
                            check_finish_34 = true;
                            System.out.println(Long.toBinaryString(mask_possible >>> (indexes_move2_34_possible1[i] - 1)));
                            if (((mask_possible >>> (indexes_move2_34_possible1[i] - 1)) & player1) == 0 &
                                    ((mask_possible >>> (indexes_move2_34_possible1[i] - 1)) & ai) == 0) { //check opponent
                                AiPlayer.index_triple = indexes_move2_34_possible1[i];
                                AiPlayer.mini_board_for_twist_triple = mini_board_34_possible1[i];
                                AiPlayer.direction_rotating_triple = twist_board_34_possible1[i];

                            } else if (((mask_possible >>> (indexes_move2_34_possible2[i] - 1)) & player1) == 0 &
                                    ((mask_possible >>> (indexes_move2_34_possible2[i] - 1)) & ai) == 0) {
                                AiPlayer.index_triple = indexes_move2_34_possible2[i];
                                AiPlayer.mini_board_for_twist_triple = mini_board_34_possible2[i];
                                AiPlayer.direction_rotating_triple = twist_board_34_possible2[i];
                            }
                        }
                        if (i == 3) { //changing mini board
                            mask34 = mask34 >>> 3;
                        } else mask34 = mask34 >>> 2;
                    }
                }
            }
        }
    }

    /**
     * check for losing - 2 bits of opponent across each other
     *
     * @return
     */
    private boolean check_for_lose(int strategy) {
        long mask_lose1 = 0b010000010000000000000000000000000000000000000000000000000000000L;//>>27, >>9, >>18
        long mask_lose2 = 0b000101000000000000000000000000000000000000000000000000000000000L;//>>27, >>18, >>9
        long mask_win_1;
        long mask_win_2;
        if (strategy == 1) {
            mask_win_1 = 0b010001000000000100000000000010001000000000000000000000000000000L; //winning 1
            mask_win_2 = 0b000100010000000000001000000000100010000000000000000000000000000L; //winning 2
        } else {
            mask_win_1 = 0b000000001010100000010100000000000000000000000000000000000000000L;
            mask_win_2 = 0b000000000000001010000001010100000000000000000000000000000000000L;
        }
        for (int i = 0; i < 2; i++) {
            if ((count_1(Long.toBinaryString(ai & mask_win_1)) == 4 & count_1(Long.toBinaryString(player1 & mask_win_1)) == 0) ||
                    (count_1(Long.toBinaryString(ai & mask_win_2)) == 4 & count_1(Long.toBinaryString(player1 & mask_win_2)) == 0)) {
                grade = 120; //one turn before winning
            }
        }

        if ((((mask_lose1 & player1) == mask_lose1) || (((mask_lose1 >> 27) & player1) == (mask_lose1 >> 27)) ||
                ((mask_lose2 & player1) == mask_lose2) || (((mask_lose2 >> 27) & player1) == (mask_lose2 >> 27)))
                & ((((mask_lose1 >> 9) & player1) == (mask_lose1 >> 9)) || (((mask_lose1 >> 18) & player1) == (mask_lose1 >> 18)) ||
                (((mask_lose2 >> 9) & player1) == (mask_lose2 >> 9)) || (((mask_lose2 >> 18) & player1) == (mask_lose2 >> 18)))) { //both strategies are off
            grade = 0;
            return true;
        }

        if (strategy == 1) {
            if (((mask_lose1 & player1) == mask_lose1) || (((mask_lose1 >> 27) & player1) == (mask_lose1 >> 27)) ||
                    ((mask_lose2 & player1) == mask_lose2) || (((mask_lose2 >> 27) & player1) == (mask_lose2 >> 27))) {
                AiPlayer.player_move = 1; //go for the second strategy
                current_strategy = 2;
                player_move_1(2);
                return true;
            }
        } else if ((((mask_lose1 >> 9) & player1) == (mask_lose1 >> 9)) || (((mask_lose1 >> 18) & player1) == (mask_lose1 >> 18)) ||
                (((mask_lose2 >> 9) & player1) == (mask_lose2 >> 9)) || (((mask_lose2 >> 18) & player1) == (mask_lose2 >> 18))) {
            AiPlayer.player_move = 1; //go for the first strategy
            current_strategy = 1;
            player_move_1(1);
            return true;
        }
        return false;
    }

    /**
     * check for lose if all 4 corners
     *
     * @return
     */
    private boolean check_for_lose_sides(int strategy) {
        long mask_lose = 0b101000101000000000000000000000000000000000000000000000000000000L;
        if (((((mask_lose >> 9) & player1) == (mask_lose >> 9)) || (((mask_lose >> 18) & player1) == (mask_lose >> 18)))
                & ((((mask_lose) & player1) == (mask_lose)) || (((mask_lose >> 27) & player1) == (mask_lose >> 27)))) { //both strategies are off
            grade = 0;
            return true;
        }
        if (strategy == 1) {
            if ((((mask_lose >> 9) & player1) == (mask_lose >> 9)) || (((mask_lose >> 18) & player1) == (mask_lose >> 18))) {
                AiPlayer.player_move = 1; //go for the second strategy
                current_strategy = 2;
                player_move_1(2);
                return true;
            }
        } else if ((((mask_lose) & player1) == (mask_lose)) || (((mask_lose >> 27) & player1) == (mask_lose >> 27))) {
            AiPlayer.player_move = 1; //go for the first strategy
            current_strategy = 1;
            player_move_1(1);
            return true;
        }
        return false;
    }

    /**
     * sets the next place that is not taken by any of the players - moving from 1 mini board to another
     *
     * @param playing_strategy - the current strategy
     * @param start_index      - the start index to look for - 0 to first mini board and 4 to second mini board
     * @param end_index        - the end index to look for - 3 to first mini board and 7 to second mini board
     */
    private void start_next_mini_board(int playing_strategy, int start_index, int end_index) {
        boolean already_taken = true; //if the ball is taken

        while (already_taken == true) {
            long mask12 = 0b010000000000000000000000000000000000000000000000000000000000000L; //checks the first strategy
            long mask34 = 0b000000000010000000000000000000000000000000000000000000000000000L; //checks the second strategy
            int index = random.nextInt(start_index, end_index + 1); //random index
            if (playing_strategy == 1) {
                AiPlayer.index_triple = possible_moves_12[index];
                if (index < 4 & index > -1) { //first mini board
                    if ((player1 & (mask12 >>> (index * 2))) == 0 & (ai & (mask12 >>> (index * 2))) == 0) { //this place is not taken
                        already_taken = false;
                        AiPlayer.mini_board_for_twist_triple = this.mini_board_12_possible1[index];
                        AiPlayer.direction_rotating_triple = this.twist_board_12_possible1[index];
                    }
                } else {
                    if ((player1 & (mask12 >>> (27 + ((index % 4) * 2)))) == 0 & (ai & (mask12 >>> (27 + ((index % 4) * 2)))) == 0) { //this place in not taken
                        already_taken = false;
                        AiPlayer.mini_board_for_twist_triple = this.mini_board_12_possible1[index];
                        AiPlayer.direction_rotating_triple = this.twist_board_12_possible1[index];
                    }
                }

            } else {
                AiPlayer.index_triple = possible_moves_34[index];
                if (index < 4 & index > -1) { //first mini board
                    if ((player1 & (mask34 >>> (index * 2))) == 0 & (ai & (mask34 >>> (index * 2))) == 0) { //this place in not taken
                        already_taken = false;
                        AiPlayer.mini_board_for_twist_triple = this.mini_board_34_possible1[index];
                        AiPlayer.direction_rotating_triple = this.twist_board_34_possible1[index];
                    }
                } else {
                    if ((player1 & (mask34 >>> (9 + ((index % 4) * 2)))) == 0 & (ai & (mask34 >>> (9 + ((index % 4) * 2)))) == 0) { //this place in not taken
                        already_taken = false;
                        AiPlayer.mini_board_for_twist_triple = this.mini_board_34_possible1[index];
                        AiPlayer.direction_rotating_triple = this.twist_board_34_possible1[index];
                    }
                }
            }
        }
    }

    /**
     * move 3, putting the third ball
     */
    private void player_move_3() {
        if (check_for_lose(current_strategy) == false & check_for_lose_sides(current_strategy) == false) {
            boolean play_board_12 = false;
            boolean play_board_34 = false;
            long mask12_1 = 0b010000000000000000000000000000000000000000000000000000000000000L; //mask for first mini board in
            // strategy 1
            long mask34_1 = 0b000000000010000000000000000000000000000000000000000000000000000L; //mask for first mini board in
            // strategy 2
            long mask12_2 = mask12_1 >>> 27;//mask for second mini board in strategy 1
            long mask34_2 = mask34_1 >>> 9;//mask for second mini board in strategy 2
            for (int i = 0; i < 4; i++) { //first mini board
                if (current_strategy == 1) {
                    if (play_board_12 == false) {
                        if ((mask12_1 & ai) != 0) { //already has a ball in there, so needs to take the second one
                            play_board_12 = true;
                        }
                    }
                } else {
                    if (play_board_34 == false) {
                        if ((mask34_1 & ai) != 0) { //already has a ball in there, so needs to take the second one
                            play_board_34 = true;
                        }
                    }
                }

                mask12_1 = mask12_1 >>> 2;
                mask34_1 = mask34_1 >>> 2;
            }
            if (play_board_12 == true) {
                start_next_mini_board(1, 4, 7); //put a ball
                this.last_turn_mini_board = 4;
                this.last_turn_playing_strategy = 1;
            } else if (play_board_34 == true) {
                start_next_mini_board(2, 4, 7); //put a ball
                this.last_turn_playing_strategy = 2;
                this.last_turn_mini_board = 3;
            }
            if (play_board_12 == false & play_board_34 == false) {
                for (int i = 0; i < 4; i++) { //first mini board
                    if (current_strategy == 1) {
                        if (play_board_12 == false) {
                            if ((mask12_2 & ai) != 0) { //already has a ball in there, so needs to take the second one
                                play_board_12 = true;
                            }
                        }
                    } else {
                        if (play_board_34 == false) {
                            if ((mask34_2 & ai) != 0) { //already has a ball in there, so needs to take the second one
                                play_board_34 = true;
                            }
                        }
                    }

                    mask12_2 = mask12_2 >>> 2;
                    mask34_2 = mask34_2 >>> 2;
                }

                if (play_board_12 == true) {
                    start_next_mini_board(1, 0, 3); //put a ball
                    this.last_turn_playing_strategy = 1;
                    this.last_turn_mini_board = 1;
                } else if (play_board_34 == true) {
                    start_next_mini_board(2, 0, 3); //put a ball
                    this.last_turn_playing_strategy = 2;
                    this.last_turn_mini_board = 2;
                }
            }
        }
    }


    /**
     * finds the next index to put that is on the winning strategy
     *
     * @param mini_board - mini board that tells us the mask to work with
     * @return
     */
    private int find_index(int mini_board) {
        if (this.last_turn_playing_strategy == 1) {
            int[] places_win = {};
            int[] places1 = {2, 6, 16, 29, 33}; //indexes of the winning places - first mini board
            int[] places2 = {4, 8, 21, 31, 35}; //indexes of the winning places - second mini board
            long mask = 0b100000000000000000000000000000000000000000000000000000000000000L; //mask to check these indexes
            if (mini_board == 2) { //first
                places_win = places1;
            } else if (mini_board == 3) {//second
                places_win = places2;
            }
            for (int i = 0; i < 5; i++) {
                if (((mask >>> (places_win[i] - 1)) & ai) == 0 & ((mask >>> (places_win[i] - 1)) & player1) == 0)//empty
                {
                    return places_win[i];
                }
            }
            for (int i = 1; i < 37; i++) { //if all the places are taken, will choose another one
                if (((mask >>> (i - 1)) & ai) == 0 & ((mask >>> (i - 1)) & player1) == 0)//empty
                {
                    return i;
                }
            }
            return -1;
        } else {
            int[] places_win = {};
            int[] places1 = {9, 11, 13, 20, 22}; //indexes of the winning places - first mini board
            int[] places2 = {15, 17, 24, 26, 28}; //indexes of the winning places - second mini board
            long mask = 0b100000000000000000000000000000000000000000000000000000000000000L; //mask to check these indexes
            if (mini_board == 1) { //first
                places_win = places1;
            } else if (mini_board == 4) { //second
                places_win = places2;
            }
            for (int i = 0; i < 5; i++) {
                if (((mask >>> (places_win[i] - 1)) & ai) == 0 & ((mask >>> (places_win[i] - 1)) & player1) == 0)//empty
                {
                    return places_win[i];
                }
            }
            for (int i = 1; i < 37; i++) { //if all the places are taken, will choose another one
                if (((mask >>> (i - 1)) & ai) == 0 & ((mask >>> (i - 1)) & player1) == 0)//empty
                {
                    return i;
                }
            }
            return -1;
        }
    }


    /**
     * finds the best rotation for next move
     *
     * @param mini_board - mini board that tells us the mask to work with
     */
    private void find_rotate(int mini_board) {
        if (this.last_turn_playing_strategy == 1) {
            int[][] places_win1 = {}; //first indexes
            int[][] places_win2 = {}; //second indexes
            int[] mini_board_to_twist1 = {}; //first mini boards
            int[] mini_board_to_twist2 = {}; //second mini boards
            int[] twist1 = {}; //first rotation
            int[] twist2 = {}; //second rotation

            int[] mini_board11 = {1, 1, 1};
            int[] mini_board12 = {3, 4, 4, 4};
            int[] twisting11 = {2, 1, random.nextInt(2) + 1};
            int[] twisting12 = {random.nextInt(2) + 1, 2, 1, random.nextInt(2) + 1};
            int[] mini_board21 = {1, 1, 1};
            int[] mini_board22 = {2, 4, 4, 4};
            int[] twisting21 = {1, 2, random.nextInt(2) + 1};
            int[] twisting22 = {random.nextInt(2) + 1, 1, 2, random.nextInt(2) + 1};
            int[][] places11 = {{2, 6}, {2, 4}, {6, 8}, {8, 4}}; //the pairs to check
            int[][] places12 = {{29, 33}, {29, 31}, {33, 35}, {31, 35}}; //the pairs to check
            int[][] places21 = {{4, 8}, {2, 4}, {6, 8}, {2, 6}}; //the pairs to check
            int[][] places22 = {{31, 35}, {29, 31}, {33, 35}, {29, 33}}; //the pairs to check

            long mask = 0b100000000000000000000000000000000000000000000000000000000000000L;
            if (mini_board == 2) { //first
                places_win1 = places11;
                places_win2 = places12;
                mini_board_to_twist1 = mini_board11;
                mini_board_to_twist2 = mini_board12;
                twist1 = twisting11;
                twist2 = twisting12;
            } else if (mini_board == 3) { //second
                places_win1 = places21;
                places_win2 = places22;
                mini_board_to_twist1 = mini_board21;
                mini_board_to_twist2 = mini_board22;
                twist1 = twisting21;
                twist2 = twisting22;
            }
            boolean check_twice = false;
            boolean check_second_mini_board = false;
            for (int i = 0; i < 4; i++) {
                if (check_twice == false) {
                    if ((((mask >>> (places_win1[0][0] - 1)) & ai) != 0) & (((mask >>> (places_win1[0][1] - 1)) & ai) != 0)) { //the first of the fourth
                        check_twice = true;
                        check_second_mini_board = true;
                    } else if ((((mask >>> (places_win1[i][0] - 1)) & ai) != 0) & (((mask >>> (places_win1[i][1] - 1)) & ai) != 0)) {//both exists
                        check_twice = true;
                        AiPlayer.mini_board_for_twist_triple = mini_board_to_twist1[i - 1];
                        AiPlayer.direction_rotating_triple = twist1[i - 1];
                    }
                }
            }
            if (check_second_mini_board == true) {
                check_twice = false;
                for (int i = 0; i < 4; i++) {
                    if (check_twice == false) {
                        if ((((mask >>> (places_win2[i][0] - 1)) & ai) != 0) & (((mask >>> (places_win2[i][1] - 1)) & ai) != 0)) {//both exists
                            check_twice = true;
                            AiPlayer.mini_board_for_twist_triple = mini_board_to_twist2[i];
                            AiPlayer.direction_rotating_triple = twist2[i];
                        }
                    }
                }
            }
        } else {
            int[][] places_win1 = {}; //first indexes
            int[][] places_win2 = {}; //second indexes
            int[] mini_board_to_twist1 = {}; //first mini boards
            int[] mini_board_to_twist2 = {}; //second mini boards
            int[] twist1 = {}; //first twists
            int[] twist2 = {}; //second twists

            int[] mini_board11 = {2, 2, 2};
            int[] mini_board12 = {4, 3, 3, 3};
            int[] twisting11 = {2, 1, random.nextInt(2) + 1};
            int[] twisting12 = {random.nextInt(2) + 1, 2, 1, random.nextInt(2) + 1};
            int[] mini_board21 = {2, 2, 2};
            int[] mini_board22 = {1, 3, 3, 3};
            int[] twisting21 = {1, 2, random.nextInt(2) + 1};
            int[] twisting22 = {random.nextInt(2) + 1, 1, 2, random.nextInt(2) + 1};
            int[][] places11 = {{11, 13}, {13, 17}, {11, 15}, {15, 17}};
            int[][] places12 = {{20, 22}, {22, 26}, {20, 24}, {24, 26}};
            int[][] places21 = {{15, 17}, {13, 17}, {11, 15}, {11, 13}};
            int[][] places22 = {{24, 26}, {22, 26}, {20, 24}, {20, 22}};

            long mask = 0b100000000000000000000000000000000000000000000000000000000000000L;
            if (mini_board == 1) { //first
                places_win1 = places11;
                places_win2 = places12;
                mini_board_to_twist1 = mini_board11;
                mini_board_to_twist2 = mini_board12;
                twist1 = twisting11;
                twist2 = twisting12;
            } else if (mini_board == 4) { //second
                places_win1 = places21;
                places_win2 = places22;
                mini_board_to_twist1 = mini_board21;
                mini_board_to_twist2 = mini_board22;
                twist1 = twisting21;
                twist2 = twisting22;
            }
            boolean check_twice = false;
            boolean check_second_mini_board = false;
            for (int i = 0; i < 4; i++) {
                if (check_twice == false) {
                    if ((((mask >>> (places_win1[0][0] - 1)) & ai) != 0) & (((mask >>> (places_win1[0][1] - 1)) & ai) != 0)) {//the first one
                        check_twice = true;
                        check_second_mini_board = true;
                    } else if ((((mask >>> (places_win1[i][0] - 1)) & ai) != 0) & (((mask >>> (places_win1[i][1] - 1)) & ai) != 0)) {//both exists
                        check_twice = true;
                        AiPlayer.mini_board_for_twist_triple = mini_board_to_twist1[i - 1];
                        AiPlayer.direction_rotating_triple = twist1[i - 1];
                    }
                }
            }
            if (check_second_mini_board == true) {
                check_twice = false;
                for (int i = 0; i < 4; i++) {
                    if (check_twice == false) {
                        if ((((mask >>> (places_win2[i][0] - 1)) & ai) != 0) & (((mask >>> (places_win2[i][1] - 1)) & ai) != 0)) {//both exists
                            check_twice = true;
                            AiPlayer.mini_board_for_twist_triple = mini_board_to_twist2[i];
                            AiPlayer.direction_rotating_triple = twist2[i];
                        }
                    }
                }
            }
        }
    }

    /**
     * checks for the next move after the knowledge of two whites
     */
    private void my_color_ball() {
        if (this.last_turn_playing_strategy == 1) {
            long mask_win_1 = 0b010001000000000100000000000010001000000000000000000000000000000L; //winning 1
            long mask_win_2 = 0b000100010000000000001000000000100010000000000000000000000000000L; //winning 2
            int[] places1 = {16, 10, 18, 12}; //indexes of possible to the third mini board
            int[] twisting1 = {1, 1, 2, random.nextInt(2) + 1}; //twisting for the third mini board
            int[] places = {};
            int[] twist = {};
            int[] places2 = {21, 19, 27, 25}; //indexes of possible to the third mini board
            int[] twisting2 = {2, 2, 1, random.nextInt(2) + 1}; //twisting for the third mini board
            int mini_board = 0;

            if ((count_1(Long.toBinaryString(mask_win_1 & ai)) > count_1(Long.toBinaryString(mask_win_2 & ai)))
                    || ((count_1(Long.toBinaryString(mask_win_1 & ai)) == count_1(Long.toBinaryString(mask_win_2 & ai))) &
                    (((0b100000000000000000000000000000000000000000000000000000000000000L >>> 15) & ai) != 0))) {
                //first is better than the second, or first is better than the second
                places = places1;
                twist = twisting1;
                mini_board = 2;
            } else if ((count_1(Long.toBinaryString(mask_win_1 & ai)) < count_1(Long.toBinaryString(mask_win_2 & ai)))
                    || ((count_1(Long.toBinaryString(mask_win_1 & ai)) == count_1(Long.toBinaryString(mask_win_2 & ai))) &
                    (((0b100000000000000000000000000000000000000000000000000000000000000L >>> 20) & ai) != 0))) {
                //second is better than the first, or second is better than the first

                places = places2;
                twist = twisting2;
                mini_board = 3;
            } else {
                places = places1;
                twist = twisting1;
                mini_board = 2;
            }

            boolean check = false;
            for (int i = 0; i < 4; i++) {
                if (check == false) {
                    if (((0b100000000000000000000000000000000000000000000000000000000000000L >>> (places[i] - 1)) & ai) == 0
                            & ((0b100000000000000000000000000000000000000000000000000000000000000L >>> (places[i] - 1)) & player1) == 0)//empty
                    {
                        check = true;
                        if (i > 0) {
                            AiPlayer.index_triple = places[i];
                            AiPlayer.mini_board_for_twist_triple = mini_board;
                            AiPlayer.direction_rotating_triple = twist[i];
                        } else if (i == 0) {
                            AiPlayer.index_triple = places[i];
                            find_rotate(mini_board);
                        }
                    } else if (((0b100000000000000000000000000000000000000000000000000000000000000L >>> (places[i] - 1)) & ai) != 0)//white
                    {
                        check = true;
                        if (i > 0) {
                            AiPlayer.index_triple = find_index(mini_board);
                            AiPlayer.mini_board_for_twist_triple = mini_board;
                            AiPlayer.direction_rotating_triple = twist[i];
                        } else if (i == 0) {
                            AiPlayer.index_triple = find_index(mini_board);
                            find_rotate(mini_board);
                        }
                    }
                }
            }
        } else { //strategy 2
            long mask_win_1 = 0b000000001010100000010100000000000000000000000000000000000000000L;
            long mask_win_2 = 0b000000000000001010000001010100000000000000000000000000000000000L;
            int[] places1 = {9, 3, 7, 1};
            int[] twisting1 = {1, 2, 1, random.nextInt(2) + 1};
            int[] places = {};
            int[] twist = {};
            int[] places2 = {28, 30, 34, 36};
            int[] twisting2 = {1, 1, 2, random.nextInt(2) + 1};
            int mini_board = 0;


            if ((count_1(Long.toBinaryString(mask_win_1 & ai)) > count_1(Long.toBinaryString(mask_win_2 & ai)))
                    || ((count_1(Long.toBinaryString(mask_win_1 & ai)) == count_1(Long.toBinaryString(mask_win_2 & ai))) &
                    (((0b100000000000000000000000000000000000000000000000000000000000000L >>> 8) & ai) != 0))) {
                places = places1;
                twist = twisting1;
                mini_board = 1;
            } else if ((count_1(Long.toBinaryString(mask_win_1 & ai)) < count_1(Long.toBinaryString(mask_win_2 & ai)))
                    || ((count_1(Long.toBinaryString(mask_win_1 & ai)) == count_1(Long.toBinaryString(mask_win_2 & ai))) &
                    (((0b100000000000000000000000000000000000000000000000000000000000000L >>> 27) & ai) != 0))) {
                places = places2;
                twist = twisting2;
                mini_board = 4;
            } else {
                places = places1;
                twist = twisting1;
                mini_board = 1;
            }

            boolean check = false;
            for (int i = 0; i < 4; i++) {
                if (check == false) {
                    if (((0b100000000000000000000000000000000000000000000000000000000000000L >>> (places[i] - 1)) & ai) == 0
                            & ((0b100000000000000000000000000000000000000000000000000000000000000L >>> (places[i] - 1)) & player1) == 0)//empty
                    {
                        check = true;
                        if (i > 0) {
                            AiPlayer.index_triple = places[i];
                            AiPlayer.mini_board_for_twist_triple = mini_board;
                            AiPlayer.direction_rotating_triple = twist[i];
                        } else if (i == 0) {
                            AiPlayer.index_triple = places[i];
                            find_rotate(mini_board);
                        }
                    } else if (((0b100000000000000000000000000000000000000000000000000000000000000L >>> (places[i] - 1)) & ai) != 0)//white
                    {
                        check = true;
                        if (i > 0) {
                            AiPlayer.index_triple = find_index(mini_board);
                            AiPlayer.mini_board_for_twist_triple = mini_board;
                            AiPlayer.direction_rotating_triple = twist[i];
                        } else if (i == 0) {
                            AiPlayer.index_triple = find_index(mini_board);
                            find_rotate(mini_board);
                        }
                    }
                }
            }
        }

    }

    /**
     * move above 3
     */
    private void player_move_above_3() {
        if (check_for_lose(current_strategy) == false & check_for_lose_sides(current_strategy) == false) {
            if (this.last_turn_playing_strategy == 1) {
                long mask = 0b010000000000000000000000000000000000000000000000000000000000000L;
                boolean check = false;
                for (int i = 0; i < 4; i++) {
                    if (check == false) {
                        long mask_possible = 0b100000000000000000000000000000000000000000000000000000000000000L;
                        if ((mask & ai) != 0) {
                            check = true;
                            if (((mask_possible >>> (indexes_move2_12_possible1[i] - 1)) & player1) != 0) //opponent
                            {
                                if (((mask_possible >>> (indexes_move2_12_possible2[i] - 1)) & ai) == 0) //empty
                                {
                                    AiPlayer.index_triple = indexes_move2_12_possible2[i];
                                    AiPlayer.mini_board_for_twist_triple = mini_board_12_possible2[i];
                                    AiPlayer.direction_rotating_triple = twist_board_12_possible2[i];
                                } else if (((mask_possible >>> (indexes_move2_12_possible2[i] - 1)) & ai) != 0) //my ball
                                {
                                    my_color_ball();
                                }

                            } else if (((mask_possible >>> (indexes_move2_12_possible1[i] - 1)) & ai) != 0) //my ball
                            {
                                my_color_ball();
                            } else //empty
                            {
                                if ((((mask_possible >>> (indexes_move2_12_possible2[i] - 1)) & player1) != 0)
                                        || ((((mask_possible >>> (indexes_move2_12_possible2[i] - 1)) & ai) == 0))) //opponent or empty
                                {
                                    AiPlayer.index_triple = indexes_move2_12_possible1[i];
                                    AiPlayer.mini_board_for_twist_triple = mini_board_12_possible1[i];
                                    AiPlayer.direction_rotating_triple = twist_board_12_possible1[i];
                                } else //white ball
                                {
                                    my_color_ball();
                                }
                            }
                        }
                    }
                    mask = mask >>> 2;
                }
            } else {
                long mask = 0b000000000010000000000000000000000000000000000000000000000000000L;
                boolean check = false;
                for (int i = 0; i < 4; i++) {
                    if (check == false) {
                        long mask_possible = 0b100000000000000000000000000000000000000000000000000000000000000L;
                        if ((mask & ai) != 0) {
                            check = true;
                            if (((mask_possible >>> (indexes_move2_34_possible1[i] - 1)) & player1) != 0) //opponent
                            {
                                if (((mask_possible >>> (indexes_move2_34_possible2[i] - 1)) & ai) == 0) //empty
                                {
                                    AiPlayer.index_triple = indexes_move2_34_possible2[i];
                                    AiPlayer.mini_board_for_twist_triple = mini_board_34_possible2[i];
                                    AiPlayer.direction_rotating_triple = twist_board_34_possible2[i];
                                } else if (((mask_possible >>> (indexes_move2_34_possible2[i] - 1)) & ai) != 0) //my ball
                                {
                                    my_color_ball();
                                }

                            } else if (((mask_possible >>> (indexes_move2_34_possible1[i] - 1)) & ai) != 0) //my ball
                            {
                                my_color_ball();
                            } else //empty
                            {
                                if ((((mask_possible >>> (indexes_move2_34_possible2[i] - 1)) & player1) != 0)
                                        || ((((mask_possible >>> (indexes_move2_34_possible2[i] - 1)) & ai) == 0))) //opponent or empty
                                {
                                    AiPlayer.index_triple = indexes_move2_34_possible1[i];
                                    AiPlayer.mini_board_for_twist_triple = mini_board_34_possible1[i];
                                    AiPlayer.direction_rotating_triple = twist_board_34_possible1[i];
                                } else //white ball
                                {
                                    my_color_ball();
                                }
                            }
                        }
                    }
                    mask = mask >>> 2;
                }
            }

        }
    }

    /**
     * the strategy with all the steps
     * return- the grade for the strategy
     */
    public int triple_power_play() {
        if (player_move == 1) //first ball to put on board
        {
            int playing_strategy = random.nextInt(2) + 1; // generates either 1 or 2, 1 for winning 12, and 2 for winning 34
            current_strategy = playing_strategy;
            player_move_1(playing_strategy);
        } else if (player_move == 2) //second ball to put on board
        {
            player_move_2();
        } else if (player_move == 3) { //third ball to put on board

            player_move_3();
        } else if (player_move > 3) {
            player_move_above_3(); //above third ball to put on board
        }
        return grade;

    }


    /**
     * repeating the checking after every strategy
     *
     * @param check-        check if already finished
     * @param i-            the i
     * @param keep_ai-      the ai board
     * @param keep_player1- the player 1 board
     * @param x-            the x
     * @param b-            the boars
     * @param r-            the right schema
     * @return
     */
    public boolean repeat(boolean check, int i, long keep_ai, long keep_player1, int x, Board b, int r) {
        int[][][] indexes_total = {indexes, indexes_2, indexes_3};
        long mask = 0b100000000000000000000000000000000000000000000000000000000000000L;
        for (int j = 0; j < 5; j++) {
            if (check_4_finish == false & ((mask >>> (indexes_total[r][i][j] - 1)) & keep_ai) == 0 & ((mask >>> (indexes_total[r][i][j] - 1)) & keep_player1) == 0) { //not taken
                index_rows_columns = indexes_total[r][i][j];
                if (x > 0) {
                    index_rows_columns = b.rotate_whole_opp(index_rows_columns, mini_boards_to_twist_whole[x - 1][0], mini_boards_to_twist_whole[x - 1][1]); //the right index
                }
                mini_board_for_twist_rows_columns = this.mini_board[x][(i % 5)]; //the mini board to twist
                if (x == 2) {
                    direction_rotating_rows_columns = this.twist_mini_boards_34[(i % 5)]; //the direction to rotate
                } else direction_rotating_rows_columns = this.twist_mini_boards_12[(i % 5)]; //the direction to rotate
                check_4_finish = true; //already found
                if (r == 1)
                    grade_rows_columns = 40;
                else grade_rows_columns = 60;
            }
        }
        if (check == true)
            return true;
        return false;
    }

    /**
     * find the rows_and_columns
     *
     * @return- the grade for the strategy
     */
    public int rows_columns() {
        check_4_finish = false; //check if there is 4 in a row
        check_3_finish = false; //check if there is 3 in a row
        check_2_finish = false; //check if there is 2 in a row
        check_1_finish = false; //check if there is 1 in a row
        check_0_finish = false; //check if there is 0 in a row
        int[][][] indexes_total = {indexes, indexes_2, indexes_3};
        long[][] masks_total = {masks_boards_12, masks_boards_12_2, masks_boards_12_3};
        for (int r = 0; r < 3; r++) {
            for (int x = 0; x < 4; x++) {
                long keep_ai = ai;
                long keep_player1 = player1;
                Board b = new Board();
                b.setPlayer1(player1);
                b.setPlayer2(ai);
                if (x > 0) {
                    long[] players = b.rotate_whole(mini_boards_to_twist_whole[x - 1][0], mini_boards_to_twist_whole[x - 1][1]);
                    keep_ai = players[1];
                    keep_player1 = players[0];
                }
                for (int i = 0; i < 10; i++) {
                    if (count_1(Long.toBinaryString(keep_ai & masks_total[r][i])) == 4 & count_1(Long.toBinaryString(keep_player1 & masks_total[r][i])) == 0) { //not found
                        long mask = 0b100000000000000000000000000000000000000000000000000000000000000L;
                        for (int j = 0; j < 5; j++) {
                            if (check_4_finish == false & ((mask >>> (indexes_total[r][i][j] - 1)) & keep_ai) == 0) {
                                index_rows_columns = indexes_total[r][i][j];
                                if (x > 0) {
                                    index_rows_columns = b.rotate_whole_opp(index_rows_columns, mini_boards_to_twist_whole[x - 1][0], mini_boards_to_twist_whole[x - 1][1]);//the right index
                                }
                                mini_board_for_twist_rows_columns = this.mini_board[x][(i % 5)]; //the mini board to twist
                                if (x == 2) {
                                    direction_rotating_rows_columns = this.twist_mini_boards_34[(i % 5)]; //the direction to rotate
                                } else
                                    direction_rotating_rows_columns = this.twist_mini_boards_12[(i % 5)]; //the direction to rotate
                                check_4_finish = true;
                                return 120;
                            }
                        }
                    }
                }
            }
        }


        if (check_4_finish == false) {
            for (int r = 0; r < 3; r++) {
                for (int x = 0; x < 4; x++) {
                    long keep_ai = ai;
                    long keep_player1 = player1;
                    Board b = new Board();
                    b.setPlayer1(player1);
                    b.setPlayer2(ai);
                    if (x > 0) {
                        long[] players = b.rotate_whole(mini_boards_to_twist_whole[x - 1][0], mini_boards_to_twist_whole[x - 1][1]);
                        keep_ai = players[1];
                        keep_player1 = players[0];
                    }
                    for (int i = 0; i < 10; i++) {
                        if (count_1(Long.toBinaryString(keep_ai & masks_total[r][i])) == 3 & count_1(Long.toBinaryString(keep_player1 & masks_total[r][i])) < 2) { //less than 2 opponents
                            check_3_finish = repeat(check_3_finish, i, keep_ai, keep_player1, x, b, r);
                        }
                    }
                }
            }
        }

        if (check_4_finish == false & check_3_finish == false) {
            for (int r = 0; r < 3; r++) {
                for (int x = 0; x < 4; x++) {
                    long keep_ai = ai;
                    long keep_player1 = player1;
                    Board b = new Board();
                    b.setPlayer1(player1);
                    b.setPlayer2(ai);
                    if (x > 0) {
                        long[] players = b.rotate_whole(mini_boards_to_twist_whole[x - 1][0], mini_boards_to_twist_whole[x - 1][1]);
                        keep_ai = players[1];
                        keep_player1 = players[0];
                    }
                    for (int i = 0; i < 10; i++) {
                        if (count_1(Long.toBinaryString(keep_ai & masks_total[r][i])) == 2 & count_1(Long.toBinaryString(keep_player1 & masks_total[r][i])) < 2) { //less than 2 opponents
                            check_2_finish = repeat(check_2_finish, i, keep_ai, keep_player1, x, b, r);
                        }
                    }
                }
            }
        }

        if (check_4_finish == false & check_3_finish == false & check_2_finish == false) {
            for (int r = 0; r < 3; r++) {
                for (int x = 0; x < 4; x++) {
                    long keep_ai = ai;
                    long keep_player1 = player1;
                    Board b = new Board();
                    b.setPlayer1(player1);
                    b.setPlayer2(ai);
                    if (x > 0) {
                        long[] players = b.rotate_whole(mini_boards_to_twist_whole[x - 1][0], mini_boards_to_twist_whole[x - 1][1]);
                        keep_ai = players[1];
                        keep_player1 = players[0];
                    }
                    for (int i = 0; i < 10; i++) {
                        if (count_1(Long.toBinaryString(keep_ai & masks_total[r][i])) == 1 & count_1(Long.toBinaryString(keep_player1 & masks_total[r][i])) < 2) { //less than 2 opponents
                            check_1_finish = repeat(check_1_finish, i, keep_ai, keep_player1, x, b, r);
                        }
                    }
                }
            }
        }


        if (check_4_finish == false & check_3_finish == false & check_2_finish == false & check_1_finish == false) {
            for (int r = 0; r < 3; r++) {
                for (int x = 0; x < 4; x++) {
                    long keep_ai = ai;
                    long keep_player1 = player1;
                    Board b = new Board();
                    b.setPlayer1(player1);
                    b.setPlayer2(ai);
                    if (x > 0) {
                        long[] players = b.rotate_whole(mini_boards_to_twist_whole[x - 1][0], mini_boards_to_twist_whole[x - 1][1]);
                        keep_ai = players[1];
                        keep_player1 = players[0];

                    }
                    for (int i = 0; i < 10; i++) {
                        if (count_1(Long.toBinaryString(keep_ai & masks_total[r][i])) == 0 & count_1(Long.toBinaryString(keep_player1 & masks_total[r][i])) < 2) { //less than 2 opponents
                            long mask = 0b100000000000000000000000000000000000000000000000000000000000000L;
                            for (int j = 0; j < 5; j++) {
                                if (check_0_finish == false & ((mask >>> (indexes_total[r][i][j] - 1)) & keep_ai) == 0 & ((mask >>> (indexes_total[r][i][j] - 1)) & keep_player1) == 0) { //not found
                                    index_rows_columns = indexes_total[r][i][j];
                                    mini_board_for_twist_rows_columns = this.mini_board_12[(i % 5)]; //the mini board to twist
                                    if (x == 2) {
                                        direction_rotating_rows_columns = this.twist_mini_boards_34[(i % 5)]; //the direction to rotate
                                    } else
                                        direction_rotating_rows_columns = this.twist_mini_boards_12[(i % 5)]; //the direction to rotate
                                    check_0_finish = true;
                                    if (r == 1)
                                        grade_rows_columns = 40;
                                    else grade_rows_columns = 60;
                                }
                            }
                        }
                    }
                }
            }
        }
        return grade_rows_columns;

    }

}
