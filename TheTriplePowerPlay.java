import java.util.Random;

public class TheTriplePowerPlay {


    Random random = new Random();
    private int[] possible_moves_12 = {2, 4, 6, 8, 29, 31, 33, 35};
    private int[] mini_board_12_possible1 = {random.nextInt(2) + 2, random.nextInt(2) + 2, 1, 1, 4, 4,
            random.nextInt(2) + 2, random.nextInt(2) + 2};
    private int[] twist_board_12_possible1 = {random.nextInt(2) + 1, random.nextInt(2) + 1, 1, 2, 2, 1,
            random.nextInt(2) + 1, random.nextInt(2) + 1};
    private int [] indexes_move2_12_possible1=  {4, 2, 2, 4, 33, 35, 35, 33};
    private int [] indexes_move2_12_possible2=  {6, 8, 8, 6, 31, 29, 29, 31};

    private int[] mini_board_12_possible2 = {1, 1, 1, 1, 4, 4, 4, 4};
    private int[] twist_board_12_possible2 ={1, 2, 2, 2, 1, 1, 2, 1};



    private int[] possible_moves_34 = {11, 13, 15, 17, 20, 22, 24, 26};
    private int[] mini_board_34_possible1 = {random.nextInt(2) * 3 + 1, 2, random.nextInt(2) * 3 + 1,
                                                2, 3, random.nextInt(2) * 3 + 1, 3, random.nextInt(2) * 3 + 1};
    private int[] twist_board_34_possible1 = {random.nextInt(2) + 1, 2, random.nextInt(2) + 1,
                                                1, 1, random.nextInt(2) + 1, 2, random.nextInt(2) + 1};
    private int [] indexes_move2_34_possible1=  {15, 11, 11, 15, 22, 26, 26, 22};
    private int [] indexes_move2_34_possible2=  {13, 17, 17, 13, 24, 20, 20, 24};

    private int[] mini_board_34_possible2 = {2, 2, 2, 2, 3, 3, 3, 3};
    private int[] twist_board_34_possible2 ={2, 1, 1, 1, 2, 1, 2, 2};



    public static int index;
    public static int mini_board_to_rotate;
    public static int direction_rotating; // 1 for left, 2 for right
    public static int player_move = 0; //number of stage to put the ball - the first ball to put is on move 1;

    private int last_turn_mini_board;
    private int last_turn_playing_strategy;


    private void player_move_1(int playing_strategy)
    {
        int index = random.nextInt(8);
        if (playing_strategy == 1) {
            TheTriplePowerPlay.index = possible_moves_12[index];
            TheTriplePowerPlay.mini_board_to_rotate = this.mini_board_12_possible1[index];
            TheTriplePowerPlay.direction_rotating = this.twist_board_12_possible1[index];
        } else {
            TheTriplePowerPlay.index = possible_moves_34[index];
            TheTriplePowerPlay.mini_board_to_rotate = this.mini_board_34_possible1[index];
            TheTriplePowerPlay.direction_rotating = this.twist_board_34_possible1[index];
        }
    }

    private void player_move_2(long ai, long player1)
    {
        long mask12= 0b010000000000000000000000000000000000000000000000000000000000000L;
        long mask34= 0b000000000010000000000000000000000000000000000000000000000000000L;
        boolean check_finish_12=false;
        boolean check_finish_34=false;
        for(int i=0; i<8; i++)
        {
            if(check_finish_12==false) {
                long mask_possible = 0b100000000000000000000000000000000000000000000000000000000000000L;
                if ((mask12 & ai) != 0) {
                    check_finish_12 = true;
//                    System.out.println(Long.toBinaryString(mask_possible >>> (indexes_move2_12_possible1[i] - 1)));
                    if (((mask_possible >>> (indexes_move2_12_possible1[i] - 1)) & player1) == 0) {
                        TheTriplePowerPlay.index = indexes_move2_12_possible1[i];
                        TheTriplePowerPlay.mini_board_to_rotate = mini_board_12_possible1[i];
                        TheTriplePowerPlay.direction_rotating = twist_board_12_possible1[i];

                    } else {
                        TheTriplePowerPlay.index = indexes_move2_12_possible2[i];
                        TheTriplePowerPlay.mini_board_to_rotate = mini_board_12_possible2[i];
                        TheTriplePowerPlay.direction_rotating = twist_board_12_possible2[i];
                    }
                }
                if (i == 3) {
                    mask12 = mask12 >>> 21;
                } else mask12 = mask12 >>> 2;
            }
        }
        if(check_finish_12==false) {
            for (int i = 0; i < 8; i++) {
                if (check_finish_34 == false) {
                    long mask_possible = 0b100000000000000000000000000000000000000000000000000000000000000L;
                    if ((mask34 & ai) != 0) {
                        check_finish_34 = true;
                        System.out.println(Long.toBinaryString(mask_possible >>> (indexes_move2_34_possible1[i] - 1)));
                        if (((mask_possible >>> (indexes_move2_34_possible1[i] - 1)) & player1) == 0) {
                            TheTriplePowerPlay.index = indexes_move2_34_possible1[i];
                            TheTriplePowerPlay.mini_board_to_rotate = mini_board_34_possible1[i];
                            TheTriplePowerPlay.direction_rotating = twist_board_34_possible1[i];

                        } else {
                            TheTriplePowerPlay.index = indexes_move2_34_possible2[i];
                            TheTriplePowerPlay.mini_board_to_rotate = mini_board_34_possible2[i];
                            TheTriplePowerPlay.direction_rotating = twist_board_34_possible2[i];
                        }
                    }
                    if (i == 3) {
                        mask34 = mask34 >>> 3;
                    } else mask34 = mask34 >>> 2;
                }
            }
        }
    }

    private boolean check_for_lose(long ai, long player1)
    {
        long mask_lose1= 0b010000010000000000000000000000000000000000000000000000000000000L;//>>27, >>9, >>18
        long mask_lose2= 0b000101000000000000000000000000000000000000000000000000000000000L;//>>27, >>18, >>9
        if(((mask_lose1&player1)==mask_lose1)|| (((mask_lose1>>27)&player1)==(mask_lose1>>27)) ||
                ((mask_lose2&player1)==mask_lose2)|| (((mask_lose2>>27)&player1)==(mask_lose2>>27)))
        {
            TheTriplePowerPlay.player_move=1;
            player_move_1(2);
            return true;
        }
        else if((((mask_lose1>>9)&player1)==(mask_lose1>>9))|| (((mask_lose1>>18)&player1)==(mask_lose1>>18)) ||
                (((mask_lose2>>9)&player1)==(mask_lose2>>9)) || (((mask_lose2>>18)&player1)==(mask_lose2>>18)))
        {
            TheTriplePowerPlay.player_move=1;
            player_move_1(1);
            return true;
        }
        return false;
    }

    private void start_next_mini_board(int playing_strategy, int start_index, int end_index, long player1)
    {
        boolean already_taken=true;

        while(already_taken==true) {
            long mask12= 0b010000000000000000000000000000000000000000000000000000000000000L;
            long mask34= 0b000000000010000000000000000000000000000000000000000000000000000L;
            int index = random.nextInt(start_index, end_index + 1);
            System.out.println(index);
            if (playing_strategy == 1) {
                TheTriplePowerPlay.index = possible_moves_12[index];
                System.out.println(index);
                System.out.println(Long.toBinaryString(player1));
                System.out.println(Long.toBinaryString(((mask12>>>(((index*2)))))));
                if(index<4 & index>-1) {
                    if ((player1 & (mask12 >>> (index * 2))) == 0) {
                        already_taken = false;
                        TheTriplePowerPlay.mini_board_to_rotate = this.mini_board_12_possible1[index];
                        TheTriplePowerPlay.direction_rotating = this.twist_board_12_possible1[index];
                    }
                }
                else {
                    if ((player1 & (mask12 >>> (27 + ((index % 4) * 2)))) == 0) {
                        already_taken = false;
                        TheTriplePowerPlay.mini_board_to_rotate = this.mini_board_12_possible1[index];
                        TheTriplePowerPlay.direction_rotating = this.twist_board_12_possible1[index];
                    }
                }

            } else {
                TheTriplePowerPlay.index = possible_moves_34[index];
                if(index<4 & index>-1) {
                    if ((player1 & (mask34 >>> (index * 2))) == 0) {
                        already_taken = false;
                        TheTriplePowerPlay.mini_board_to_rotate = this.mini_board_34_possible1[index];
                        TheTriplePowerPlay.direction_rotating = this.twist_board_34_possible1[index];
                    }
                }
                else {
                    System.out.println(index);
                    System.out.println(Long.toBinaryString(player1));
                    System.out.println(Long.toBinaryString((mask34 >>> (9 + ((index % 4) * 2)))));
                    System.out.println(Long.toBinaryString((player1 & (mask34 >>> (index * 2)))));
                    if ((player1 & (mask34 >>> (9 + ((index % 4) * 2)))) == 0) {
                        already_taken = false;
                        TheTriplePowerPlay.mini_board_to_rotate = this.mini_board_34_possible1[index];
                        TheTriplePowerPlay.direction_rotating = this.twist_board_34_possible1[index];
                    }
                }
                }
            }
        }


    private void player_move_3(long ai, long player1)
    {
        if(check_for_lose(ai, player1)==false)
        {
            boolean play_board_12 = false;
            boolean play_board_34 = false;
            long mask12_1= 0b010000000000000000000000000000000000000000000000000000000000000L;
            long mask34_1= 0b000000000010000000000000000000000000000000000000000000000000000L;
            long mask12_2= mask12_1>>>27;
            long mask34_2= mask34_1>>>9;
            for(int i=0; i<4; i++)
            {
                if(play_board_12 == false & play_board_34 == false) {
                    if ((mask12_1 & ai) != 0) {
                        play_board_12 = true;
                    } else if ((mask34_1 & ai) != 0) {
                        play_board_34 = true;
                    }
                }
                mask12_1= mask12_1>>>2;
                mask34_1= mask34_1>>>2;
            }
            System.out.println(play_board_12 + " " +  play_board_34);
            if(play_board_12==true)
            {
                start_next_mini_board(1, 4, 7, player1);
                this.last_turn_mini_board=1;
                this.last_turn_playing_strategy=1;
            }
            else if(play_board_34==true)
            {
                start_next_mini_board(2, 4, 7, player1);
                this.last_turn_playing_strategy=2;
                this.last_turn_mini_board=2;
            }
            if(play_board_12==false & play_board_34==false)
            {
                for(int i=0; i<4; i++)
                {
                    if(play_board_12 == false & play_board_34 == false) {
                        if ((mask12_2 & ai) != 0) {
                            play_board_12 = true;
                        } else if ((mask34_2 & ai) != 0) {
                            play_board_34 = true;
                        }
                    }
                    mask12_2= mask12_2>>>2;
                    mask34_2= mask34_2>>>2;
                }
                System.out.println(play_board_12 + " " +  play_board_34);

                if(play_board_12==true)
                {
                    start_next_mini_board(1, 0, 3, player1);
                    this.last_turn_playing_strategy=1;
                    this.last_turn_mini_board=4;
                }
                else if(play_board_34==true)
                {
                    start_next_mini_board(2, 0, 3, player1);
                    this.last_turn_playing_strategy=2;
                    this.last_turn_mini_board=3;
                }
            }
        }
    }

    public void triple_power_play(long ai, long player1) {
        if (player_move == 1) //first ball to put on board
        {
            int playing_strategy = random.nextInt(2) + 1; // generates either 1 or 2, 1 for winning 12, and 2 for winning 34
            player_move_1(playing_strategy);
        }


        else if (player_move == 2) { //second ball to put on board
            player_move_2(ai, player1);
        }


        else if(player_move==3)
        {
            player_move_3(ai, player1);
        }

        else if(player_move==4)
        {

        }



        System.out.println("index: " + TheTriplePowerPlay.index);
        System.out.println("mini board: " + TheTriplePowerPlay.mini_board_to_rotate);
        if(TheTriplePowerPlay.direction_rotating==1)
            System.out.println("left");
        else System.out.println("right");
    }



    public static void main(String[] args) {
        TheTriplePowerPlay t = new TheTriplePowerPlay();
        TheTriplePowerPlay.player_move++;
//        t.triple_power_play(0, 0);
        TheTriplePowerPlay.player_move++;
//        t.triple_power_play(0b010000000000000000000000000000000000000000000000000000000000000L,
//                        0b000100000000000000000100000000000000000000000000000000000000000L);
        TheTriplePowerPlay.player_move++;
        t.triple_power_play(0b000000000000000000000000000000000000000000000000000000000000000L,
                        0b000000000000000000000000000000000000000000000000000000000000000L);

//        System.out.println(TheTriplePowerPlay.player_move);
    }
}
