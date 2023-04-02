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

    private Long board_player1;
    private Long board_ai;

    private Board b;

    public static int mini_board_for_twist; //mini board for twisting
    public static int index; //index of putting the ball

    public AiPlayer()
    {
        ControllerClass c= new ControllerClass();
        this.b= c.getB();
        setBoard_player1(this.b.getPlayer1());
        setBoard_ai(this.b.getAiPlayer());
    }



    public void setBoard_ai(Long board_ai) {
        this.board_ai = board_ai;
    }

    public void setBoard_player1(Long board_player1) {
        this.board_player1 = board_player1;
    }

    /**
     * setting the mini board for twist
     * @param num- the mini board twisting
     */
    public void setMini_board_for_twist(int num)
    {
        mini_board_for_twist=num;
    }

    /**
     * setting the index to put the ball
     * @param num- the ball to put the ball
     */
    public void setIndex(int num)
    {
        index=num;
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
        }

        else if (strategy_mask == 1) //mini boards 1, 3, 4
        {
            if (index < START_MINI_2 & index > START_MINI_1) {
                return rand.nextInt(2) + 3;// 3 or 4
            } else if (index > END_MINI_2 & index < START_MINI_4) {
                return rand.nextInt(2) * 3 + 1;// 1 or 4
            } else
                return rand.nextInt(2) * 2 + 1;// 1 or 3
        }

        else if (strategy_mask == 2) //mini boards 1, 2, 3
        {
            if (index < START_MINI_2 & index > START_MINI_1) {
                return  rand.nextInt(2) + 2;// 2 or 3
            } else if (index > END_MINI_2 & index < START_MINI_4) {
                return rand.nextInt(2) + 1;// 1 or 2
            } else
                return rand.nextInt(2) * 2 + 1;// 1 or 3
        }

        else //mini boards 2, 3, 4
        {
            if (index > END_MINI_1 & index < START_MINI_3){
                return  rand.nextInt(2) + 3;// 3 or 4
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
     *
     * @param player1- the board from the human player point of view
     * @param ai-      the board from the ai player point of view
     */
    public void defence(long player1, long ai) {
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

    public static void main(String[] args) {
//        AiPlayer c= new AiPlayer();
//        c.defence(0b000000001010100000010000000000000000000000000000000000000000000L,
//                0b000000000000000000000000000000000000000000000000000000000000000L);
//
    }
}
