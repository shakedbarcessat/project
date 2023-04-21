package com.example.pentagogame;

import com.example.pentagogame.Controller.ControllerClass;
import com.example.pentagogame.Model.Board;

import java.util.Random;

public class AiPlayer {
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

    private Board b;

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

    private int[] mini_board_12 = {rand.nextInt(2) + 3, 2, 2, 1, 1};
    private int[] mini_board_24 = {rand.nextInt(2) * 2 + 1, 4, 4, 2, 2};
    private int[] mini_board_34 = {rand.nextInt(2) +1, 4, 4, 3, 3};
    private int[] mini_board_13 = {rand.nextInt(2) * 2 + 2, 1, 1, 3, 3};

    private int [][] mini_board={mini_board_12, mini_board_24,mini_board_34, mini_board_13};

    private int [][]mini_boards_to_twist_whole={{2, 4}, {3, 4}, {1, 3}};


    private int[][] indexes = {{2, 11, 3, 10, 12}, {2, 15, 3, 12, 18}, {2, 13, 3, 10, 16}, {4, 11, 1, 10, 12}, {6, 11, 9, 10, 12},
            {2, 11, 3, 10, 1}, {2, 15, 3, 12, 1}, {2, 13, 3, 16, 1}, {4, 11, 1, 10, 7}, {6, 11, 9, 10, 3}};

    private int[] twist_mini_boards_12 = {rand.nextInt(2) + 1, 1, 2, 2, 1};
    private int[] twist_mini_boards_34 = {rand.nextInt(2) + 1,2, 1, 1, 2};

    private boolean check_4 = false;

    private boolean check_3_finish = false;

    private boolean check_2_finish = false;

    private boolean check_1_finish = false;

    private boolean check_0_finish = false;

    public static int mini_board_for_twist; //mini board for twisting
    public static int index; //index of putting the ball

    public static int direction_rotating; //the side to rotate

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

    public AiPlayer() {
        setBoard_player1(ControllerClass.b.getPlayer1());
        setBoard_ai(ControllerClass.b.getPlayer2());
    }

    public void setPlayers() {
        setBoard_player1(ControllerClass.b.getPlayer1());
        setBoard_ai(ControllerClass.b.getPlayer2());
    }

    public void setBoard_ai(Long board_ai) {
        this.ai = board_ai;
    }

    public long getBoard_ai() {
        return this.ai;
    }

    public long getPlayer1() {
        return this.player1;
    }
    public void setBoard_player1(Long board_player1) {
        this.player1 = board_player1;
    }

    /**
     * setting the mini board for twist
     *
     * @param num- the mini board twisting
     */
    public void setMini_board_for_twist(int num) {
        mini_board_for_twist = num;
    }

    /**
     * setting the index to put the ball
     *
     * @param num- the ball to put the ball
     */
    public void setIndex(int num) {
        index = num;
    }

    /**
     * calculates the the mini board to twist according to the index(the mini board to twist will be the opposite)
     *
     * @param index- index of the button to put
     * @return- the mini board
     */
    public int calc_mini_board_horizontal(int index) {
        if (index < START_MINI_2 & index > START_MINI_1) {
            return 2;
        } else if (index > END_MINI_1 & index < START_MINI_3) {
            return 1;
        } else if (index > END_MINI_2 & index < START_MINI_4) {
            return 4;
        } else
            return 3;
    }

    /**
     * calculates the the mini board to twist according to the index(the mini board to twist will be the opposite)
     *
     * @param index- index of the button to put
     * @return- the mini board
     */
    public int calc_mini_board_vertical(int index) {
        if (index < START_MINI_2 & index > START_MINI_1) {
            return 3;
        } else if (index > END_MINI_1 & index < START_MINI_3) {
            return 4;
        } else if (index > END_MINI_2 & index < START_MINI_4) {
            return 1;
        } else
            return 2;
    }

    /**
     * calculates the the mini board to twist according to the index(the mini board to twist will be the opposite)
     *
     * @param index- index of the button to put
     * @return- the mini board
     */
    public int calc_mini_board_diagonal(int index) {
        if (index < START_MINI_2 & index > START_MINI_1) {
            return 4;
        } else if (index > END_MINI_1 & index < START_MINI_3) {
            return 3;
        } else if (index > END_MINI_2 & index < START_MINI_4) {
            return 2;
        } else
            return 1;
    }

    /**
     * calculates the the mini board to twist according to the index(the mini board to twist will be the opposite)
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
     * defencing the game from vertical, horizontal or diagonal win.
     * starts from the 4 ball.
     */
    public void defence() {
        long board = player1; //the board to check
        int mini_board_for_twist = 0; //the mini board to twist for later
        int index = 0; //the index to put the ball

        // Check for horizontal wins
        long mask1 = 0b111000000110000000000000000000000000000000000000000000000000000L;
        long mask2 = 0b011000000111000000000000000000000000000000000000000000000000000L;
        for (int row = 0; row < 6; row++) {
            long rowBits1 = 0;
            long rowBits2 = 0;
            if (row < 3) {
                rowBits1 = (mask1 >> (row * 3));
                rowBits2 = (mask2 >> (row * 3));
            } else {
                rowBits1 = (mask1 >> (MINI_BOARD_SIZE + row * 3));
                rowBits2 = (mask2 >> (MINI_BOARD_SIZE + row * 3));
            }

            int count1 = 0;//count how many 1 are in the board for each way of winning - the first mask
            int count2 = 0;//count how many 1 are in the board for each way of winning - the second mask
            long mask = 0b100000000000000000000000000000000000000000000000000000000000000L; //check for next place to put the ball
            mask = mask >> (row * 3);
            String new_bits1 = Long.toBinaryString(board & rowBits1);
            String new_bits2 = Long.toBinaryString(board & rowBits2);
            long new_bits_ai1 = ai & rowBits1; //check if the ai already defending - the first mask
            long new_bits_ai2 = ai & rowBits2; //check if the ai already defending - the second mask
            count2 = count_1(new_bits2);
            count1 = count_1(new_bits1);

            if ((count1 > 2 && new_bits_ai1 == 0)) {//need defence - the first mask
                boolean found_empty_place = false; //check if already found an index
                if (row == 3) {
                    index = (ROW_SIZE * row);
                    mask = mask >> (MINI_BOARD_SIZE);
                } else if (row > 3) {
                    index = (3 * row + MINI_BOARD_SIZE);
                    mask = mask >> (MINI_BOARD_SIZE);
                } else index = (3 * row);
                for (int i = 0; i < ROW_SIZE; i++) {
                    if (found_empty_place == false) {
                        if (row < 3) {
                            if (index == (row * 3 + 3)) {
                                mask = mask >> ROW_SIZE;
                                index = index + ROW_SIZE;
                            }
                        }
                        if (row > 2) {
                            if (index == ((row * 3) + 12)) {
                                mask = mask >> ROW_SIZE;
                                index = index + ROW_SIZE;
                            }
                        }
                        index++;
                        if ((mask & (board & rowBits1)) == 0) { //found an index
                            found_empty_place = true;
                            mini_board_for_twist = calc_mini_board_horizontal(index); //calc the mini board to twist
                        }
                        mask = mask >> 1; //continue to next bit
                    }
                }

            } else if ((count2 > 2 && new_bits_ai2 == 0)) {//need defence
                mask = 0b010000000000000000000000000000000000000000000000000000000000000L;
                if (row < 3) {
                    mask = mask >> (row * 3);
                    index = (3 * row) + 1;
                } else if (row == 3) {
                    index = (ROW_SIZE * row) + 1;
                    mask = mask >> (MINI_BOARD_SIZE + row * 3);
                } else {
                    index = (3 * row + 10);
                    mask = mask >> (MINI_BOARD_SIZE + row * 3);
                }
                boolean found_empty_place = false;
                for (int i = 0; i < ROW_SIZE; i++) {
                    if (found_empty_place == false) {
                        if (row < 3) {
                            if (index == (row * 3 + 3)) {
                                mask = mask >> ROW_SIZE;
                                index = index + ROW_SIZE;
                            }
                        }
                        if (row > 2) {
                            if (index == ((row * 3) + 12)) {
                                mask = mask >> ROW_SIZE;
                                index = index + ROW_SIZE;
                            }
                        }
                        index++;
                        if ((mask & (board & rowBits2)) == 0) {
                            found_empty_place = true;
                            mini_board_for_twist = calc_mini_board_horizontal(index);
                        }
                        mask = mask >> 1;
                    }
                }
            }
        }
        System.out.println("horizontal");
        System.out.println(mini_board_for_twist + ", " + index);


        // Check for vertical wins
        long colMask1 = 0b100100100000000000100100000000000000000000000000000000000000000L;
        long colMask2 = 0b000100100000000000100100100000000000000000000000000000000000000L;
        long colMask3 = 0b000000000100100100000000000100100000000000000000000000000000000L;
        long colMask4 = 0b000000000000100100000000000100100100000000000000000000000000000L;
        long mask = 0;
        mini_board_for_twist = 0;
        index = 0;
        for (int col = 0; col < 3; col++) {
            long colBits1 = (colMask1 >> (col));
            long colBits2 = (colMask2 >> (col));
            long colBits3 = (colMask3 >> (col));
            long colBits4 = (colMask4 >> (col));

            String new_bits1 = Long.toBinaryString(board & colBits1);
            String new_bits2 = Long.toBinaryString(board & colBits2);
            String new_bits3 = Long.toBinaryString(board & colBits3);
            String new_bits4 = Long.toBinaryString(board & colBits4);

            long new_bits_ai1 = ai & colBits1;//check if already defending
            long new_bits_ai2 = ai & colBits2;//check if already defending
            long new_bits_ai3 = ai & colBits3;//check if already defending
            long new_bits_ai4 = ai & colBits4;//check if already defending

            int count1 = count_1(new_bits1);//count how many 1 are in the board for each way of winning - the first mask
            int count2 = count_1(new_bits2);//count how many 1 are in the board for each way of winning - the second mask
            int count3 = count_1(new_bits3);//count how many 1 are in the board for each way of winning - the third mask
            int count4 = count_1(new_bits4);//count how many 1 are in the board for each way of winning - the fourth mask


            Long masks[] = {0b100000000000000000000000000000000000000000000000000000000000000L,
                    0b000100000000000000000000000000000000000000000000000000000000000L,
                    0b000000000100000000000000000000000000000000000000000000000000000L,
                    0b000000000000100000000000000000000000000000000000000000000000000L};
            int start_indexex[] = {1, 4, 10, 13};
            int middle_indexes[] = {19, 19, 28, 28};
            int counts[] = {count1, count2, count3, count4};
            Long boards[] = {colBits1, colBits2, colBits3, colBits4};
            Long ai_masks[] = {new_bits_ai1, new_bits_ai2, new_bits_ai3, new_bits_ai4};
            int indexes_for_1[] = {3, 2, 3, 2};
            int indexes_for_2[] = {2, 3, 2, 3};


            for (int i = 0; i < 4; i++) {
                if ((counts[i] > 2 && ai_masks[i] == 0)) { //need defending - mask 1
                    mask = masks[i]; //find the next index to put the ball
                    mask = mask >> (col);
                    index = start_indexex[i] + col;
//                System.out.println(count1);
                    boolean found = false;
                    for (int j = 0; j < indexes_for_1[i]; j++) {
                        if (found == false) {
//                        System.out.println(Long.toBinaryString(board));
//                        System.out.println(Long.toBinaryString(board&diag1Mask));
//                        System.out.println(Long.toBinaryString((mask & (board & diag1Mask))));
                            if ((mask & (board & boards[i])) == 0) { //found an index for defending
                                found = true;
                                mini_board_for_twist = calc_mini_board_vertical(index); //the mini board to twist
                            } else index = index + 3;
                            mask = mask >> 3;
                        }
                    }
                    if (found == false) {
                        mask = mask >> 9;
                        index = middle_indexes[i] + col;
                    }
                    for (int j = 0; j < indexes_for_2[i]; j++) {
                        if (found == false) {
                            if ((mask & (board & boards[i])) == 0) { //found an index for defending
                                found = true;
                                mini_board_for_twist = calc_mini_board_vertical(index);//the mini board to twist
                            } else index = index + 3;
                            mask = mask >> 3;
                        }
                    }
                }
            }
        }
        System.out.println("vertical");
        System.out.println(mini_board_for_twist + ", " + index);


        // Check for diagonal wins
        long diag1Mask = 0b100010001000000000000000000100010000000000000000000000000000000L;
        long diag2Mask = 0b000010001000000000000000000100010001000000000000000000000000000L;
        long diag3Mask = 0b000000000001010100001010000000000000000000000000000000000000000L;
        long diag4Mask = 0b000000000000010100001010100000000000000000000000000000000000000L;
        mask = 0;
        mini_board_for_twist = 0;
        index = 0;

        String new_bits1 = Long.toBinaryString(board & diag1Mask);
        String new_bits2 = Long.toBinaryString(board & diag2Mask);
        String new_bits3 = Long.toBinaryString(board & diag3Mask);
        String new_bits4 = Long.toBinaryString(board & diag4Mask);

        long new_bits_ai1 = ai & diag1Mask;//check if already defending
        long new_bits_ai2 = ai & diag2Mask;//check if already defending
        long new_bits_ai3 = ai & diag3Mask;//check if already defending
        long new_bits_ai4 = ai & diag4Mask;//check if already defending

        int count1 = count_1(new_bits1);//count how many 1 are in the board for each way of winning - the first mask
        int count2 = count_1(new_bits2);//count how many 1 are in the board for each way of winning - the second mask
        int count3 = count_1(new_bits3);//count how many 1 are in the board for each way of winning - the third mask
        int count4 = count_1(new_bits4);//count how many 1 are in the board for each way of winning - the fourth mask

        Long masks[] = {0b100000000000000000000000000000000000000000000000000000000000000L,
                0b000010000000000000000000000000000000000000000000000000000000000L,
                0b000000000001000000000000000000000000000000000000000000000000000L,
                0b000000000000010000000000000000000000000000000000000000000000000L};
        int start_indexex[] = {1, 5, 12, 14}; //index to start
        int middle_indexes[] = {28, 28, 21, 21}; //changed mini board index
        int counts[] = {count1, count2, count3, count4}; //the counts
        Long boards[] = {diag1Mask, diag2Mask, diag3Mask, diag4Mask}; //the mask
        Long ai_masks[] = {new_bits_ai1, new_bits_ai2, new_bits_ai3, new_bits_ai4}; //the ai mask
        int indexes_for_1[] = {3, 2, 3, 2}; // for loop
        int indexes_for_2[] = {2, 3, 2, 3}; //for loop
        int indexes_gap[] = {4, 4, 2, 2}; //the gap between the indexes
        int indexes_shift[] = {15, 15, 3, 3}; //the shift between the mini boards

        for (int i = 0; i < 4; i++) {
            if ((counts[i] > 2 && ai_masks[i] == 0)) { //need defending - mask 1
                mask = masks[i]; //find the next index to put the ball
                index = start_indexex[i];
//                System.out.println(count1);
                boolean found = false;
                for (int j = 0; j < indexes_for_1[i]; j++) {
                    if (found == false) {
//                        System.out.println(Long.toBinaryString(board));
//                        System.out.println(Long.toBinaryString(board&diag1Mask));
//                        System.out.println(Long.toBinaryString((mask & (board & diag1Mask))));
                        if ((mask & (board & boards[i])) == 0) { //found an index for defending
                            found = true;
                            mini_board_for_twist = calc_mini_board_diagonal(index); //the mini board to twist
                        } else index = index + indexes_gap[i];
                        mask = mask >> indexes_gap[i];
                    }
                }
                if (found == false) {
                    mask = mask >> indexes_shift[i];
                    index = middle_indexes[i];
                }
                for (int j = 0; j < indexes_for_2[i]; j++) {
                    if (found == false) {
                        if ((mask & (board & boards[i])) == 0) { //found an index for defending
                            found = true;
                            mini_board_for_twist = calc_mini_board_diagonal(index);//the mini board to twist
                        } else index = index + indexes_gap[i];
                        mask = mask >> indexes_gap[i];
                    }
                }

            }
        }
        System.out.println("diagonal");
        System.out.println(mini_board_for_twist + ", " + index);


        // Check for triple power play wins
        long theTripleWin = 0b010001000000000100000000000010001000000000000000000000000000000L;
        long theTripleWin2 = 0b000100010000000000001000000000100010000000000000000000000000000L;
        long theTripleWin3 = 0b000000001010100000010100000000000000000000000000000000000000000L;
        long theTripleWin4 = 0b000000000000001010000001010100000000000000000000000000000000000L;

        mask = 0;
        mini_board_for_twist = 0;
        index = 0;

        new_bits1 = Long.toBinaryString(board & theTripleWin);
        new_bits2 = Long.toBinaryString(board & theTripleWin2);
        new_bits3 = Long.toBinaryString(board & theTripleWin3);
        new_bits4 = Long.toBinaryString(board & theTripleWin4);

        new_bits_ai1 = ai & theTripleWin;//check if already defending
        new_bits_ai2 = ai & theTripleWin2;//check if already defending
        new_bits_ai3 = ai & theTripleWin3;//check if already defending
        new_bits_ai4 = ai & theTripleWin4;//check if already defending

        count1 = count_1(new_bits1);//count how many 1 are in the board for each way of winning - the first mask
        count2 = count_1(new_bits2);//count how many 1 are in the board for each way of winning - the second mask
        count3 = count_1(new_bits3);//count how many 1 are in the board for each way of winning - the third mask
        count4 = count_1(new_bits4);//count how many 1 are in the board for each way of winning - the fourth mask


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
                mask = masks2[i]; //find the next index to put the ball
                index = start_indexex2[i];
//                System.out.println(count1);
                boolean found = false;
                for (int j = 0; j < 2; j++) {
                    if (found == false) {
//                        System.out.println(Long.toBinaryString(board));
//                        System.out.println(Long.toBinaryString(board&diag1Mask));
//                        System.out.println(Long.toBinaryString((mask & (board & diag1Mask))));
                        if ((mask & (board & boards2[i])) == 0) { //found an index for defending
                            found = true;
                            mini_board_for_twist = calc_mini_board_diagonal_triple(index, i); //the mini board to twist
                        } else index = index + indexes_gap1[i];
                        mask = mask >> indexes_gap1[i];
                    }
                }


                if (found == false) {
                    mask = mask >> indexes_shift1[i];
                    index = middle_indexes2[i];
                }
                if (found == false) {
                    if ((mask & (board & boards2[i])) == 0) { //found an index for defending
                        found = true;
                        mini_board_for_twist = calc_mini_board_diagonal_triple(index, i);//the mini board to twist
                    } //else index = index + indexes_gap1[i];
//                    mask = mask >> indexes_gap1[i];
                }


                if (found == false) {
                    mask = mask >> indexes_shift2[i];
                    index = end_indexes2[i];
                }
                for (int j = 0; j < 2; j++) {
                    if (found == false) {
                        if ((mask & (board & boards2[i])) == 0) { //found an index for defending
                            found = true;
                            mini_board_for_twist = calc_mini_board_diagonal_triple(index, i);//the mini board to twist
                        } else index = index + indexes_gap1[i];
                        mask = mask >> indexes_gap1[i];
                    }
                }

            }
        }
        System.out.println("triple power play");
        System.out.println(mini_board_for_twist + ", " + index);


        setIndex(index);
        setMini_board_for_twist(mini_board_for_twist);
    }




    

    /**
     * move 1, putting the first ball
     *
     * @param playing_strategy- the strategy 1 or 2
     */
    private void player_move_1(int playing_strategy) {
        int index = random.nextInt(8);
        if (playing_strategy == 1) {
            AiPlayer.index = possible_moves_12[index];
            AiPlayer.mini_board_for_twist = this.mini_board_12_possible1[index];
            AiPlayer.direction_rotating = this.twist_board_12_possible1[index];
        } else {
            AiPlayer.index = possible_moves_34[index];
            AiPlayer.mini_board_for_twist = this.mini_board_34_possible1[index];
            AiPlayer.direction_rotating = this.twist_board_34_possible1[index];
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
        if(check_for_lose()==false & check_for_lose_sides()==false) {
            for (int i = 0; i < 8; i++) {
                if (check_finish_12 == false) {
                    long mask_possible = 0b100000000000000000000000000000000000000000000000000000000000000L; //follow each bit
                    if ((mask12 & ai) != 0) {
                        check_finish_12 = true; //not continue the for
                        if (((mask_possible >>> (indexes_move2_12_possible1[i] - 1)) & player1) == 0 &
                                ((mask_possible >>> (indexes_move2_12_possible1[i] - 1)) & ai) == 0) { //check opponent
                            AiPlayer.index = indexes_move2_12_possible1[i];
                            AiPlayer.mini_board_for_twist = mini_board_12_possible1[i];
                            AiPlayer.direction_rotating = twist_board_12_possible1[i];

                        } else if (((mask_possible >>> (indexes_move2_12_possible2[i] - 1)) & player1) == 0 &
                                ((mask_possible >>> (indexes_move2_12_possible2[i] - 1)) & ai) == 0) {
                            AiPlayer.index = indexes_move2_12_possible2[i];
                            AiPlayer.mini_board_for_twist = mini_board_12_possible2[i];
                            AiPlayer.direction_rotating = twist_board_12_possible2[i];
                        }
                    }
                    if (i == 3) { //changing mini board
                        mask12 = mask12 >>> 21;
                    } else mask12 = mask12 >>> 2;
                }
            }
            System.out.println("checkfinish12 " + check_finish_12);
            if (check_finish_12 == false) {
                for (int i = 0; i < 8; i++) {
                    if (check_finish_34 == false) {
                        long mask_possible = 0b100000000000000000000000000000000000000000000000000000000000000L; //follow each bit
                        if ((mask34 & ai) != 0) {
                            check_finish_34 = true;
                            System.out.println(Long.toBinaryString(mask_possible >>> (indexes_move2_34_possible1[i] - 1)));
                            if (((mask_possible >>> (indexes_move2_34_possible1[i] - 1)) & player1) == 0 &
                                    ((mask_possible >>> (indexes_move2_34_possible1[i] - 1)) & ai) == 0) { //check opponent
                                AiPlayer.index = indexes_move2_34_possible1[i];
                                AiPlayer.mini_board_for_twist = mini_board_34_possible1[i];
                                AiPlayer.direction_rotating = twist_board_34_possible1[i];

                            } else if (((mask_possible >>> (indexes_move2_34_possible2[i] - 1)) & player1) == 0 &
                                    ((mask_possible >>> (indexes_move2_34_possible2[i] - 1)) & ai) == 0) {
                                AiPlayer.index = indexes_move2_34_possible2[i];
                                AiPlayer.mini_board_for_twist = mini_board_34_possible2[i];
                                AiPlayer.direction_rotating = twist_board_34_possible2[i];
                            }
                        }
                        if (i == 3) { //changing mini board
                            mask34 = mask34 >>> 3;
                        } else mask34 = mask34 >>> 2;
                    }
                }
            }
        }
        else
            System.out.println("sofffffffffffffffffffffffffff");
    }

    /**
     * check for losing - 2 bits of opponent across each other
     *
     * @return
     */
    private boolean check_for_lose() {
        long mask_lose1 = 0b010000010000000000000000000000000000000000000000000000000000000L;//>>27, >>9, >>18
        long mask_lose2 = 0b000101000000000000000000000000000000000000000000000000000000000L;//>>27, >>18, >>9
        if (((mask_lose1 & player1) == mask_lose1) || (((mask_lose1 >> 27) & player1) == (mask_lose1 >> 27)) ||
                ((mask_lose2 & player1) == mask_lose2) || (((mask_lose2 >> 27) & player1) == (mask_lose2 >> 27))) {
            AiPlayer.player_move = 1; //go for the second strategy
            System.out.println("lose strategy 1");
            player_move_1(2);
            return true;
        } else if ((((mask_lose1 >> 9) & player1) == (mask_lose1 >> 9)) || (((mask_lose1 >> 18) & player1) == (mask_lose1 >> 18)) ||
                (((mask_lose2 >> 9) & player1) == (mask_lose2 >> 9)) || (((mask_lose2 >> 18) & player1) == (mask_lose2 >> 18))) {
            AiPlayer.player_move = 1; //go for the first strategy
            System.out.println("lose strategy 2");
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
    private boolean check_for_lose_sides() {
        long mask_lose = 0b101000101000000000000000000000000000000000000000000000000000000L;
        if ((((mask_lose >> 9) & player1) == (mask_lose >> 9)) || (((mask_lose >> 18) & player1) == (mask_lose >> 18))) {
            AiPlayer.player_move = 1; //go for the second strategy
            player_move_1(2);
            return true;
        } else if ((((mask_lose) & player1) == (mask_lose)) || (((mask_lose >> 27) & player1) == (mask_lose >> 27))) {
            AiPlayer.player_move = 1; //go for the first strategy
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
//            System.out.println(index);
            if (playing_strategy == 1) {
                AiPlayer.index = possible_moves_12[index];
//                System.out.println(index);
//                System.out.println(Long.toBinaryString(player1));
//                System.out.println(Long.toBinaryString(((mask12 >>> (((index * 2)))))));
                if (index < 4 & index > -1) { //first mini board
                    if ((player1 & (mask12 >>> (index * 2))) == 0) { //this place is not taken
                        already_taken = false;
                        AiPlayer.mini_board_for_twist = this.mini_board_12_possible1[index];
                        AiPlayer.direction_rotating = this.twist_board_12_possible1[index];
                    }
                } else {
                    if ((player1 & (mask12 >>> (27 + ((index % 4) * 2)))) == 0) { //this place in not taken
                        already_taken = false;
                        AiPlayer.mini_board_for_twist = this.mini_board_12_possible1[index];
                        AiPlayer.direction_rotating = this.twist_board_12_possible1[index];
                    }
                }

            } else {
                AiPlayer.index = possible_moves_34[index];
                if (index < 4 & index > -1) { //first mini board
                    if ((player1 & (mask34 >>> (index * 2))) == 0) { //this place in not taken
                        already_taken = false;
                        AiPlayer.mini_board_for_twist = this.mini_board_34_possible1[index];
                        AiPlayer.direction_rotating = this.twist_board_34_possible1[index];
                    }
                } else {
//                    System.out.println(index);
//                    System.out.println(Long.toBinaryString(player1));
//                    System.out.println(Long.toBinaryString((mask34 >>> (9 + ((index % 4) * 2)))));
//                    System.out.println(Long.toBinaryString((player1 & (mask34 >>> (index * 2)))));
                    if ((player1 & (mask34 >>> (9 + ((index % 4) * 2)))) == 0) { //this place in not taken
                        already_taken = false;
                        AiPlayer.mini_board_for_twist = this.mini_board_34_possible1[index];
                        AiPlayer.direction_rotating = this.twist_board_34_possible1[index];
                    }
                }
            }
        }
    }


    /**
     * move 3, putting the third ball
     */
    private void player_move_3() {
        if (check_for_lose() == false) {
            boolean play_board_12 = false;
            boolean play_board_34 = false;
            long mask12_1 = 0b010000000000000000000000000000000000000000000000000000000000000L; //mask for first mini board in
            // strategy 1
            long mask34_1 = 0b000000000010000000000000000000000000000000000000000000000000000L; //mask for first mini board in
            // strategy 2
            long mask12_2 = mask12_1 >>> 27;//mask for second mini board in strategy 1
            long mask34_2 = mask34_1 >>> 9;//mask for second mini board in strategy 2
            for (int i = 0; i < 4; i++) { //first mini board
                if (play_board_12 == false & play_board_34 == false) {
                    if ((mask12_1 & ai) != 0) { //already has a ball in there, so needs to take the second one
                        play_board_12 = true;
                    } else if ((mask34_1 & ai) != 0) { //already has a ball in there, so needs to take the second one
                        play_board_34 = true;
                    }
                }
                mask12_1 = mask12_1 >>> 2;
                mask34_1 = mask34_1 >>> 2;
            }
//            System.out.println(play_board_12 + " " + play_board_34);
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
                for (int i = 0; i < 4; i++) {
                    if (play_board_12 == false & play_board_34 == false) {
                        if ((mask12_2 & ai) != 0) { //already has a ball in there, so needs to take the first one
                            play_board_12 = true;
                        } else if ((mask34_2 & ai) != 0) { //already has a ball in there, so needs to take the first one
                            play_board_34 = true;
                        }
                    }
                    mask12_2 = mask12_2 >>> 2;
                    mask34_2 = mask34_2 >>> 2;
                }
//                System.out.println(play_board_12 + " " + play_board_34);

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
                        AiPlayer.mini_board_for_twist = mini_board_to_twist1[i - 1];
                        AiPlayer.direction_rotating = twist1[i - 1];
                    }
                }
            }
            if (check_second_mini_board == true) {
                check_twice = false;
                for (int i = 0; i < 4; i++) {
                    if (check_twice == false) {
                        if ((((mask >>> (places_win2[i][0] - 1)) & ai) != 0) & (((mask >>> (places_win2[i][1] - 1)) & ai) != 0)) {//both exists
                            check_twice = true;
                            AiPlayer.mini_board_for_twist = mini_board_to_twist2[i];
                            AiPlayer.direction_rotating = twist2[i];
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
                        AiPlayer.mini_board_for_twist = mini_board_to_twist1[i - 1];
                        AiPlayer.direction_rotating = twist1[i - 1];
                    }
                }
            }
            if (check_second_mini_board == true) {
                check_twice = false;
                for (int i = 0; i < 4; i++) {
                    if (check_twice == false) {
                        if ((((mask >>> (places_win2[i][0] - 1)) & ai) != 0) & (((mask >>> (places_win2[i][1] - 1)) & ai) != 0)) {//both exists
                            check_twice = true;
                            AiPlayer.mini_board_for_twist = mini_board_to_twist2[i];
                            AiPlayer.direction_rotating = twist2[i];
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
            int[] twisting1 = {1, random.nextInt(2) + 1, 2}; //twisting for the third mini board
            int[] places = {};
            int[] twist = {};
            int[] places2 = {21, 19, 27, 25}; //indexes of possible to the third mini board
            int[] twisting2 = {2, random.nextInt(2) + 1, 1}; //twisting for the third mini board
            int mini_board = 0;
//            System.out.println(count_1(Long.toBinaryString(mask_win_1 & ai)));
//            System.out.println(count_1(Long.toBinaryString(mask_win_2 & ai)));

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
                            AiPlayer.index = places[i];
                            AiPlayer.mini_board_for_twist = mini_board;
                            AiPlayer.direction_rotating = twist[i];
                        } else if (i == 0) {
                            AiPlayer.index = places[i];
                            find_rotate(mini_board);
                        }
                    } else if (((0b100000000000000000000000000000000000000000000000000000000000000L >>> (places[i] - 1)) & ai) != 0)//white
                    {
                        check = true;
                        if (i > 0) {
                            AiPlayer.index = find_index(mini_board);
                            AiPlayer.mini_board_for_twist = mini_board;
                            AiPlayer.direction_rotating = twist[i];
                        } else if (i == 0) {
                            AiPlayer.index = find_index(mini_board);
                            find_rotate(mini_board);
                        }
                    }
                }
            }
        } else { //strategy 2
            long mask_win_1 = 0b000000001010100000010100000000000000000000000000000000000000000L;
            long mask_win_2 = 0b000000000000001010000001010100000000000000000000000000000000000L;
            int[] places1 = {9, 3, 7, 1};
            int[] twisting1 = {random.nextInt(2) + 1, 2, 1};
            int[] places = {};
            int[] twist = {};
            int[] places2 = {28, 30, 34, 36};
            int[] twisting2 = {1, 2, random.nextInt(2) + 1};
            int mini_board = 0;
            System.out.println(count_1(Long.toBinaryString(mask_win_1 & ai)));
            System.out.println(count_1(Long.toBinaryString(mask_win_2 & ai)));

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
                            AiPlayer.index = places[i];
                            AiPlayer.mini_board_for_twist = mini_board;
                            AiPlayer.direction_rotating = twist[i];
                        } else if (i == 0) {
                            AiPlayer.index = places[i];
                            find_rotate(mini_board);
                        }
                    } else if (((0b100000000000000000000000000000000000000000000000000000000000000L >>> (places[i] - 1)) & ai) != 0)//white
                    {
                        check = true;
                        if (i > 0) {
                            AiPlayer.index = find_index(mini_board);
                            AiPlayer.mini_board_for_twist = mini_board;
                            AiPlayer.direction_rotating = twist[i];
                        } else if (i == 0) {
                            AiPlayer.index = find_index(mini_board);
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
        if (check_for_lose() == false & check_for_lose_sides() == false) {
//            System.out.println("chcking");
//            System.out.println(this.last_turn_playing_strategy);
//            System.out.println(this.last_turn_mini_board);
            if (this.last_turn_playing_strategy == 1) {
                long mask = 0b010000000000000000000000000000000000000000000000000000000000000L;
                boolean check = false;
                for (int i = 0; i < 4; i++) {
                    if (check == false) {
                        long mask_possible = 0b100000000000000000000000000000000000000000000000000000000000000L;
//                        System.out.println("mask & ai");
//                        System.out.println(i);
//                        System.out.println(Long.toBinaryString((mask & ai)));
                        if ((mask & ai) != 0) {
                            check = true;
                            if (((mask_possible >>> (indexes_move2_12_possible1[i] - 1)) & player1) != 0) //black ball - opponent
                            {
                                if (((mask_possible >>> (indexes_move2_12_possible2[i] - 1)) & ai) == 0) //empty
                                {
                                    AiPlayer.index = indexes_move2_12_possible2[i];
                                    AiPlayer.mini_board_for_twist = mini_board_12_possible2[i];
                                    AiPlayer.direction_rotating = twist_board_12_possible2[i];
                                } else if (((mask_possible >>> (indexes_move2_12_possible2[i] - 1)) & ai) != 0) //white ball
                                {
                                    my_color_ball();
                                }

                            } else if (((mask_possible >>> (indexes_move2_12_possible1[i] - 1)) & ai) != 0) //white ball
                            {
                                my_color_ball();
                            } else //empty
                            {
                                if ((((mask_possible >>> (indexes_move2_12_possible2[i] - 1)) & player1) != 0)
                                        || ((((mask_possible >>> (indexes_move2_12_possible2[i] - 1)) & ai) == 0))) //black ball - opponent or empty
                                {
                                    AiPlayer.index = indexes_move2_12_possible1[i];
                                    AiPlayer.mini_board_for_twist = mini_board_12_possible1[i];
                                    AiPlayer.direction_rotating = twist_board_12_possible1[i];
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
//                System.out.println(this.last_turn_playing_strategy);
//                System.out.println(this.last_turn_mini_board);
                for (int i = 0; i < 4; i++) {
                    if (check == false) {
                        long mask_possible = 0b100000000000000000000000000000000000000000000000000000000000000L;
//                        System.out.println("mask & ai");
//                        System.out.println(i);
//                        System.out.println(Long.toBinaryString((mask & ai)));
                        if ((mask & ai) != 0) {
                            check = true;
                            if (((mask_possible >>> (indexes_move2_34_possible1[i] - 1)) & player1) != 0) //black ball - opponent
                            {
                                if (((mask_possible >>> (indexes_move2_34_possible2[i] - 1)) & ai) == 0) //empty
                                {
                                    AiPlayer.index = indexes_move2_34_possible2[i];
                                    AiPlayer.mini_board_for_twist = mini_board_34_possible2[i];
                                    AiPlayer.direction_rotating = twist_board_34_possible2[i];
                                } else if (((mask_possible >>> (indexes_move2_34_possible2[i] - 1)) & ai) != 0) //white ball
                                {
                                    my_color_ball();
                                }

                            } else if (((mask_possible >>> (indexes_move2_34_possible1[i] - 1)) & ai) != 0) //white ball
                            {
                                my_color_ball();
                            } else //empty
                            {
                                if ((((mask_possible >>> (indexes_move2_34_possible2[i] - 1)) & player1) != 0)
                                        || ((((mask_possible >>> (indexes_move2_34_possible2[i] - 1)) & ai) == 0))) //black ball - opponent or empty
                                {
                                    AiPlayer.index = indexes_move2_34_possible1[i];
                                    AiPlayer.mini_board_for_twist = mini_board_34_possible1[i];
                                    AiPlayer.direction_rotating = twist_board_34_possible1[i];
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
        else {System.out.println("oopssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss, i lose");}
    }

    /**
     * the strategy with all the steps
     */
    public void triple_power_play() {
        if (player_move == 1) //first ball to put on board
        {
            int playing_strategy = random.nextInt(2) + 1; // generates either 1 or 2, 1 for winning 12, and 2 for winning 34
            player_move_1(playing_strategy);
        } else if (player_move == 2) //second ball to put on board
        {
            player_move_2();
        } else if (player_move == 3) { //third ball to put on board

            player_move_3();
        } else if (player_move > 3) {
            player_move_above_3(); //above third ball to put on board
        }


//        System.out.println("index: " + TheTriplePowerPlay.index);
//        System.out.println("mini board: " + TheTriplePowerPlay.mini_board_to_rotate);
//        if (TheTriplePowerPlay.direction_rotating == 1)
//            System.out.println("left");
//        else System.out.println("right");
    }





    public boolean repeat(boolean check, int i, long keep_ai, long keep_player1, int x, Board b) {
        long mask = 0b100000000000000000000000000000000000000000000000000000000000000L;
        for (int j = 0; j < 4; j++) {
            if (check_4 == false & ((mask >>> (indexes[i][j] - 1)) & keep_ai) == 0 & ((mask >>> (indexes[i][j] - 1)) & keep_player1) == 0) {
                index = this.indexes[i][j];
                System.out.println("index " +index);
                if(x>0)
                {
                    System.out.println("x " +x + " i "+ i);
                    index=b.rotate_whole_opp(index, mini_boards_to_twist_whole[x-1][0], mini_boards_to_twist_whole[x-1][1]);
                }
                System.out.println("x " +x + " i "+ i + " j " + j);
                mini_board_for_twist = this.mini_board[x][(i % 5)];
                if(x==2) {
                    direction_rotating = this.twist_mini_boards_34[(i % 5)];
                }
                else direction_rotating = this.twist_mini_boards_12[(i % 5)];
                check_4 = true;
            }
        }
        if (check == true)
            return true;
        return false;
    }


    public void straight_five() {
        for(int x=0; x<4; x++) {
            long keep_ai=ai;
            long keep_player1=player1;
            Board b= new Board();
            b.setPlayer1(player1);
            b.setPlayer2(ai);
            if(x>0)
            {
                long []players=b.rotate_whole(mini_boards_to_twist_whole[x-1][0], mini_boards_to_twist_whole[x-1][1]);
                keep_ai=players[1];
                keep_player1=players[0];
                System.out.println("x " +x);
                System.out.println(Long.toBinaryString(keep_ai));
            }
            for (int i = 0; i < 10; i++) {
                if (count_1(Long.toBinaryString(keep_ai & masks_boards_12[i])) == 4 & count_1(Long.toBinaryString(keep_player1 & masks_boards_12[i])) == 0) {
                    long mask = 0b100000000000000000000000000000000000000000000000000000000000000L;
                    for (int j = 0; j < 5; j++) {
                        if (check_4 == false & ((mask >>> (indexes[i][j] - 1)) & keep_ai) == 0) {
                            index = this.indexes[i][j];
                            System.out.println("index1 " +index);
                            if(x>0)
                            {
                                System.out.println("x " +x + " i "+ i);
                                index=b.rotate_whole_opp(index, mini_boards_to_twist_whole[x-1][0], mini_boards_to_twist_whole[x-1][1]);
                            }
                            mini_board_for_twist = this.mini_board[x][(i % 5)];
                            if(x==2) {
                                direction_rotating = this.twist_mini_boards_34[(i % 5)];
                            }
                            else direction_rotating = this.twist_mini_boards_12[(i % 5)];
                            check_4 = true;
                        }
                    }
                }
            }
        }


        if (check_4 == false) {
            for(int x=0; x<4; x++) {
                long keep_ai = ai;
                long keep_player1 = player1;
                Board b= new Board();
                b.setPlayer1(player1);
                b.setPlayer2(ai);
                if (x > 0) {
                    long[] players = b.rotate_whole(mini_boards_to_twist_whole[x - 1][0], mini_boards_to_twist_whole[x - 1][1]);
                    keep_ai = players[1];
                    keep_player1 = players[0];
                    System.out.println("x " + x);
                    System.out.println(Long.toBinaryString(keep_ai));
                }
                for (int i = 0; i < 10; i++) {
                    if (count_1(Long.toBinaryString(keep_ai & masks_boards_12[i])) == 3 & count_1(Long.toBinaryString(keep_player1 & masks_boards_12[i])) < 2) {
                        check_3_finish = repeat(check_3_finish, i, keep_ai, keep_player1, x, b);
                    }
                }
            }
        }

        if (check_4 == false & check_3_finish == false) {
            for(int x=0; x<4; x++) {
                long keep_ai = ai;
                long keep_player1 = player1;
                Board b= new Board();
                b.setPlayer1(player1);
                b.setPlayer2(ai);
                if (x > 0) {
                    long[] players = b.rotate_whole(mini_boards_to_twist_whole[x - 1][0], mini_boards_to_twist_whole[x - 1][1]);
                    keep_ai = players[1];
                    keep_player1 = players[0];
                    System.out.println("x " + x);
                    System.out.println(Long.toBinaryString(keep_ai));
                    System.out.println(Long.toBinaryString(keep_player1));

                }
                for (int i = 0; i < 10; i++) {
                    if (count_1(Long.toBinaryString(keep_ai & masks_boards_12[i])) == 2 & count_1(Long.toBinaryString(keep_player1 & masks_boards_12[i])) < 2) {
                        check_2_finish = repeat(check_2_finish, i, keep_ai, keep_player1, x, b);
                    }
                }
            }
        }

        if (check_4 == false & check_3_finish == false & check_2_finish == false) {
            for (int x = 0; x < 4; x++) {
                long keep_ai = ai;
                long keep_player1 = player1;
                Board b= new Board();
                b.setPlayer1(player1);
                b.setPlayer2(ai);
                if (x > 0) {
                    long[] players = b.rotate_whole(mini_boards_to_twist_whole[x - 1][0], mini_boards_to_twist_whole[x - 1][1]);
                    keep_ai = players[1];
                    keep_player1 = players[0];
                    System.out.println("x " + x);
                    System.out.println(Long.toBinaryString(keep_ai));
                    System.out.println(Long.toBinaryString(keep_player1));
                }
                for (int i = 0; i < 10; i++) {
                    if (count_1(Long.toBinaryString(keep_ai & masks_boards_12[i])) == 1 & count_1(Long.toBinaryString(keep_player1 & masks_boards_12[i])) < 2) {
                        check_1_finish = repeat(check_1_finish, i, keep_ai, keep_player1, x, b);
                    }
                }
            }
        }


        if (check_4 == false & check_3_finish == false & check_2_finish == false & check_1_finish == false) {
            for (int x = 0; x < 4; x++) {
                long keep_ai = ai;
                long keep_player1 = player1;
                Board b= new Board();
                b.setPlayer1(player1);
                b.setPlayer2(ai);
                if (x > 0) {
                    long[] players = b.rotate_whole(mini_boards_to_twist_whole[x - 1][0], mini_boards_to_twist_whole[x - 1][1]);
                    keep_ai = players[1];
                    keep_player1 = players[0];
                    System.out.println("x " + x);
                    System.out.println(Long.toBinaryString(keep_ai));
                    System.out.println(Long.toBinaryString(keep_player1));
                }
                for (int i = 0; i < 10; i++) {
                    if (count_1(Long.toBinaryString(keep_ai & masks_boards_12[i])) == 0 & count_1(Long.toBinaryString(keep_player1 & masks_boards_12[i])) < 2) {
                        long mask = 0b100000000000000000000000000000000000000000000000000000000000000L;
                        for (int j = 0; j < 5; j++) {
                            if (check_0_finish == false & ((mask >>> (indexes[i][j] - 1)) & keep_ai) == 0 & ((mask >>> (indexes[i][j] - 1)) & keep_player1) == 0) {
                                System.out.println(i + " " + j);
                                index = this.indexes[i][j];
                                mini_board_for_twist = this.mini_board_12[(i % 5)];
                                if(x==2) {
                                    direction_rotating = this.twist_mini_boards_34[(i % 5)];
                                }
                                else direction_rotating = this.twist_mini_boards_12[(i % 5)];
                                check_0_finish = true;
                            }
                        }
                    }
                }
            }
        }
        System.out.println((index));
        System.out.println((mini_board_for_twist));
        System.out.println((direction_rotating));

    }


    public static void main(String[] args) {
//        AiPlayer c= new AiPlayer();
//        c.defence(0b000000001010100000010000000000000000000000000000000000000000000L,
//                0b000000000000000000000000000000000000000000000000000000000000000L);
//
    }
}
