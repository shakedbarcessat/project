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

    public int count_1(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1')
                count++;
        }
        return count;
    }
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

    private boolean check_for_lose(long player1)
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

    private boolean check_for_lose_sides(long player1)
    {
        long mask_lose= 0b101000101000000000000000000000000000000000000000000000000000000L;
        if((((mask_lose>>9)&player1)==(mask_lose>>9)) || (((mask_lose>>18)&player1)==(mask_lose>>18)))
        {
            TheTriplePowerPlay.player_move=1;
            player_move_1(2);
            return true;
        }
        else if((((mask_lose)&player1)==(mask_lose))|| (((mask_lose>>27)&player1)==(mask_lose>>27)))
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
        if(check_for_lose(player1)==false)
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
                this.last_turn_mini_board=4;
                this.last_turn_playing_strategy=1;
            }
            else if(play_board_34==true)
            {
                start_next_mini_board(2, 4, 7, player1);
                this.last_turn_playing_strategy=2;
                this.last_turn_mini_board=3;
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
                    this.last_turn_mini_board=1;
                }
                else if(play_board_34==true)
                {
                    start_next_mini_board(2, 0, 3, player1);
                    this.last_turn_playing_strategy=2;
                    this.last_turn_mini_board=2;
                }
            }
        }
    }

    private int find_index(long ai, long player1, int mini_board)
    {
        int [] places_win= {};
        int [] places1= {2, 6, 16, 29, 33};
        int [] places2= {4, 8, 21, 31, 35};
        long mask= 0b100000000000000000000000000000000000000000000000000000000000000L;
        if(mini_board==2)
        {
            places_win=places1;
        }
        else if(mini_board==3)
        {
            places_win=places2;
        }
        for(int i=0; i<5; i++)
        {
            if(((mask>>>(places_win[i]-1))&ai)==0 & ((mask>>>(places_win[i]-1))&player1)==0 )//empty
            {
                return places_win[i];
            }
        }
        for(int i=1; i<37; i++)
        {
            if(((mask>>>(i-1))&ai)==0 & ((mask>>>(i-1))&player1)==0)//empty
            {
                return i;
            }
        }
        return -1;
    }

    private void find_rotate(long ai, long player1, int mini_board)
    {
        if(this.last_turn_playing_strategy==1) {
            int[][] places_win1 = {};
            int[][] places_win2 = {};
            int[] mini_board_to_twist1 = {};
            int[] mini_board_to_twist2 = {};
            int[] twist1 = {};
            int[] twist2 = {};

            int[] mini_board11 = {1, 1, 1};
            int[] mini_board12 = {3, 4, 4, 4};
            int[] twisting11 = {2, 1, random.nextInt(2) + 1};
            int[] twisting12 = {random.nextInt(2) + 1, 2, 1, random.nextInt(2) + 1};
            int[] mini_board21 = {1, 1, 1};
            int[] mini_board22 = {2, 4, 4, 4};
            int[] twisting21 = {1, 2, random.nextInt(2) + 1};
            int[] twisting22 = {random.nextInt(2) + 1, 1, 2, random.nextInt(2) + 1};
            int[][] places11 = {{2, 6}, {2, 4}, {6, 8}, {8, 4}};
            int[][] places12 = {{29, 33}, {29, 31}, {33, 35}, {31, 35}};
            int[][] places21 = {{4, 8}, {2, 4}, {6, 8}, {2, 6}};
            int[][] places22 = {{31, 35}, {29, 31}, {33, 35}, {29, 33}};

            long mask = 0b100000000000000000000000000000000000000000000000000000000000000L;
            if (mini_board == 2) {
                places_win1 = places11;
                places_win2 = places12;
                mini_board_to_twist1 = mini_board11;
                mini_board_to_twist2 = mini_board12;
                twist1 = twisting11;
                twist2 = twisting12;
            } else if (mini_board == 3) {
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
                    if ((((mask >>> (places_win1[0][0] - 1)) & ai) != 0) & (((mask >>> (places_win1[0][1] - 1)) & ai) != 0)) {
                        check_twice = true;
                        check_second_mini_board = true;
                    } else if ((((mask >>> (places_win1[i][0] - 1)) & ai) != 0) & (((mask >>> (places_win1[i][1] - 1)) & ai) != 0)) {
                        check_twice = true;
                        TheTriplePowerPlay.mini_board_to_rotate = mini_board_to_twist1[i - 1];
                        TheTriplePowerPlay.direction_rotating = twist1[i - 1];
                    }
                }
            }
            if (check_second_mini_board == true) {
                check_twice = false;
                for (int i = 0; i < 4; i++) {
                    if (check_twice == false) {
                        if ((((mask >>> (places_win2[i][0] - 1)) & ai) != 0) & (((mask >>> (places_win2[i][1] - 1)) & ai) != 0)) {
                            check_twice = true;
                            TheTriplePowerPlay.mini_board_to_rotate = mini_board_to_twist2[i];
                            TheTriplePowerPlay.direction_rotating = twist2[i];
                        }
                    }
                }
            }
        }
        else {
            int[][] places_win1 = {};
            int[][] places_win2 = {};
            int[] mini_board_to_twist1 = {};
            int[] mini_board_to_twist2 = {};
            int[] twist1 = {};
            int[] twist2 = {};

            int[] mini_board11 = {1, 1, 1};
            int[] mini_board12 = {3, 4, 4, 4};
            int[] twisting11 = {2, 1, random.nextInt(2) + 1};
            int[] twisting12 = {random.nextInt(2) + 1, 2, 1, random.nextInt(2) + 1};
            int[] mini_board21 = {1, 1, 1};
            int[] mini_board22 = {2, 4, 4, 4};
            int[] twisting21 = {1, 2, random.nextInt(2) + 1};
            int[] twisting22 = {random.nextInt(2) + 1, 1, 2, random.nextInt(2) + 1};
            int[][] places11 = {{2, 6}, {2, 4}, {6, 8}, {8, 4}};
            int[][] places12 = {{29, 33}, {29, 31}, {33, 35}, {31, 35}};
            int[][] places21 = {{4, 8}, {2, 4}, {6, 8}, {2, 6}};
            int[][] places22 = {{31, 35}, {29, 31}, {33, 35}, {29, 33}};

            long mask = 0b100000000000000000000000000000000000000000000000000000000000000L;
            if (mini_board == 2) {
                places_win1 = places11;
                places_win2 = places12;
                mini_board_to_twist1 = mini_board11;
                mini_board_to_twist2 = mini_board12;
                twist1 = twisting11;
                twist2 = twisting12;
            } else if (mini_board == 3) {
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
                    if ((((mask >>> (places_win1[0][0] - 1)) & ai) != 0) & (((mask >>> (places_win1[0][1] - 1)) & ai) != 0)) {
                        check_twice = true;
                        check_second_mini_board = true;
                    } else if ((((mask >>> (places_win1[i][0] - 1)) & ai) != 0) & (((mask >>> (places_win1[i][1] - 1)) & ai) != 0)) {
                        check_twice = true;
                        TheTriplePowerPlay.mini_board_to_rotate = mini_board_to_twist1[i - 1];
                        TheTriplePowerPlay.direction_rotating = twist1[i - 1];
                    }
                }
            }
            if (check_second_mini_board == true) {
                check_twice = false;
                for (int i = 0; i < 4; i++) {
                    if (check_twice == false) {
                        if ((((mask >>> (places_win2[i][0] - 1)) & ai) != 0) & (((mask >>> (places_win2[i][1] - 1)) & ai) != 0)) {
                            check_twice = true;
                            TheTriplePowerPlay.mini_board_to_rotate = mini_board_to_twist2[i];
                            TheTriplePowerPlay.direction_rotating = twist2[i];
                        }
                    }
                }
            }
        }
    }


    private void my_color_ball(long ai, long player1)
    {
        if(this.last_turn_playing_strategy==1)
        {
            long mask_win_1= 0b010001000000000100000000000010001000000000000000000000000000000L;
            long mask_win_2= 0b000100010000000000001000000000100010000000000000000000000000000L;
            int [] places1= {16, 10, 12, 18};
            int [] twisting1 = {1, random.nextInt(2) +1, 2};
            int [] places= {};
            int [] twist= {};
            int [] places2 = {21, 19, 25, 27};
            int [] twisting2 = {2, random.nextInt(2) +1, 1};
            int mini_board=0;
            System.out.println(count_1(Long.toBinaryString(mask_win_1&ai)));
            System.out.println(count_1(Long.toBinaryString(mask_win_2&ai)));

            if((count_1(Long.toBinaryString(mask_win_1&ai))>count_1(Long.toBinaryString(mask_win_2&ai)))
            || ((count_1(Long.toBinaryString(mask_win_1&ai))==count_1(Long.toBinaryString(mask_win_2&ai)))&
                    (((0b100000000000000000000000000000000000000000000000000000000000000L>>>15)&ai)!=0)))
            {
                places=places1;
                twist=twisting1;
                mini_board= 2;
            }
            else if((count_1(Long.toBinaryString(mask_win_1&ai))<count_1(Long.toBinaryString(mask_win_2&ai)))
                    || ((count_1(Long.toBinaryString(mask_win_1&ai))==count_1(Long.toBinaryString(mask_win_2&ai)))&
                    (((0b100000000000000000000000000000000000000000000000000000000000000L>>>20)&ai)!=0)))
            {
                places=places2;
                twist=twisting2;
                mini_board= 3;
            }
            else
            {
                places=places1;
                twist=twisting1;
                mini_board= 2;
            }

            boolean check=false;
            for(int i=0; i<4; i++)
            {
                if(check==false)
                {
                    if(((0b100000000000000000000000000000000000000000000000000000000000000L>>>(places[i]-1))&ai)==0
                     & ((0b100000000000000000000000000000000000000000000000000000000000000L>>>(places[i]-1))&player1)==0)//empty
                    {
                        check=true;
                        if(i>0)
                        {
                            TheTriplePowerPlay.index=places[i];
                            TheTriplePowerPlay.mini_board_to_rotate= mini_board;
                            TheTriplePowerPlay.direction_rotating = twist[i];
                        }
                        else if(i==0)
                        {
                            TheTriplePowerPlay.index=places[i];
                            find_rotate(ai, player1, mini_board);
                        }
                    }
                    else if(((0b100000000000000000000000000000000000000000000000000000000000000L>>>(places[i]-1))&ai)!=0)//white
                    {
                        check=true;
                        if(i>0)
                        {
                            TheTriplePowerPlay.index=find_index(ai, player1, mini_board);
                            TheTriplePowerPlay.mini_board_to_rotate= mini_board;
                            TheTriplePowerPlay.direction_rotating= twist[i];
                        } else if (i==0)
                        {
                            TheTriplePowerPlay.index=find_index(ai, player1, mini_board);
                            find_rotate(ai, player1, mini_board);
                        }
                    }
                }
            }
        }

        else { //strategy 2
            long mask_win_1= 0b000000001010100000010100000000000000000000000000000000000000000L;
            long mask_win_2= 0b000000000000001010000001010100000000000000000000000000000000000L;
            int [] places1= {9, 1, 2, 7};
            int [] twisting1 = {random.nextInt(2) +1, 2 ,1};
            int [] places= {};
            int [] twist= {};
            int [] places2 = {28, 30, 34, 36};
            int [] twisting2 = {1 ,2 , random.nextInt(2) +1};
            int mini_board=0;
            System.out.println(count_1(Long.toBinaryString(mask_win_1&ai)));
            System.out.println(count_1(Long.toBinaryString(mask_win_2&ai)));

            if((count_1(Long.toBinaryString(mask_win_1&ai))>count_1(Long.toBinaryString(mask_win_2&ai)))
                    || ((count_1(Long.toBinaryString(mask_win_1&ai))==count_1(Long.toBinaryString(mask_win_2&ai)))&
                    (((0b100000000000000000000000000000000000000000000000000000000000000L>>>8)&ai)!=0)))
            {
                places=places1;
                twist=twisting1;
                mini_board= 1;
            }
            else if((count_1(Long.toBinaryString(mask_win_1&ai))<count_1(Long.toBinaryString(mask_win_2&ai)))
                    || ((count_1(Long.toBinaryString(mask_win_1&ai))==count_1(Long.toBinaryString(mask_win_2&ai)))&
                    (((0b100000000000000000000000000000000000000000000000000000000000000L>>>27)&ai)!=0)))
            {
                places=places2;
                twist=twisting2;
                mini_board= 4;
            }
            else
            {
                places=places1;
                twist=twisting1;
                mini_board= 1;
            }

            boolean check=false;
            for(int i=0; i<4; i++)
            {
                if(check==false)
                {
                    if(((0b100000000000000000000000000000000000000000000000000000000000000L>>>(places[i]-1))&ai)==0
                            & ((0b100000000000000000000000000000000000000000000000000000000000000L>>>(places[i]-1))&player1)==0)//empty
                    {
                        check=true;
                        if(i>0)
                        {
                            TheTriplePowerPlay.index=places[i];
                            TheTriplePowerPlay.mini_board_to_rotate= mini_board;
                            TheTriplePowerPlay.direction_rotating = twist[i];
                        }
                        else if(i==0)
                        {
                            TheTriplePowerPlay.index=places[i];
                            find_rotate(ai, player1, mini_board);
                        }
                    }
                    else if(((0b100000000000000000000000000000000000000000000000000000000000000L>>>(places[i]-1))&ai)!=0)//white
                    {
                        check=true;
                        if(i>0)
                        {
                            TheTriplePowerPlay.index=find_index(ai, player1, mini_board);
                            TheTriplePowerPlay.mini_board_to_rotate= mini_board;
                            TheTriplePowerPlay.direction_rotating= twist[i];
                        } else if (i==0)
                        {
                            TheTriplePowerPlay.index=find_index(ai, player1, mini_board);
                            find_rotate(ai, player1, mini_board);
                        }
                    }
                }
            }
        }

    }

    private void player_move_above_3(long ai, long player1)
    {
        if(check_for_lose(player1)==false & check_for_lose_sides(player1)==false)
        {
            if(this.last_turn_playing_strategy==1)
            {
                long mask= 0b010000000000000000000000000000000000000000000000000000000000000L;
                boolean check= false;
//                System.out.println(this.last_turn_playing_strategy);
//                System.out.println(this.last_turn_mini_board);
                for(int i=0; i<4; i++) {
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
                                    TheTriplePowerPlay.index = indexes_move2_12_possible2[i];
                                    TheTriplePowerPlay.mini_board_to_rotate = mini_board_12_possible2[i];
                                    TheTriplePowerPlay.direction_rotating = twist_board_12_possible2[i];
                                } else if (((mask_possible >>> (indexes_move2_12_possible2[i] - 1)) & ai) != 0) //white ball
                                {
                                    my_color_ball(ai, player1);
                                }

                            }
                            else if (((mask_possible >>> (indexes_move2_12_possible1[i] - 1)) & ai) != 0) //white ball
                            {
                                my_color_ball(ai, player1);
                            }
                            else //empty
                            {
                                if ((((mask_possible >>> (indexes_move2_12_possible2[i] - 1)) & player1) != 0)
                                        || ((((mask_possible >>> (indexes_move2_12_possible2[i] - 1)) & ai) == 0))) //black ball - opponent or empty
                                {
                                    TheTriplePowerPlay.index = indexes_move2_12_possible1[i];
                                    TheTriplePowerPlay.mini_board_to_rotate = mini_board_12_possible1[i];
                                    TheTriplePowerPlay.direction_rotating = twist_board_12_possible1[i];
                                }
                                else //white ball
                                {
                                    my_color_ball(ai, player1);
                                }
                            }
                        }
                    }
                    mask=mask>>>2;
                }
            }

            else
            {
                long mask= 0b000000000010000000000000000000000000000000000000000000000000000L;
                boolean check= false;
//                System.out.println(this.last_turn_playing_strategy);
//                System.out.println(this.last_turn_mini_board);
                for(int i=0; i<4; i++) {
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
                                    TheTriplePowerPlay.index = indexes_move2_34_possible2[i];
                                    TheTriplePowerPlay.mini_board_to_rotate = mini_board_34_possible2[i];
                                    TheTriplePowerPlay.direction_rotating = twist_board_34_possible2[i];
                                } else if (((mask_possible >>> (indexes_move2_34_possible2[i] - 1)) & ai) != 0) //white ball
                                {
                                    my_color_ball(ai, player1);
                                }

                            }
                            else if (((mask_possible >>> (indexes_move2_34_possible1[i] - 1)) & ai) != 0) //white ball
                            {
                                my_color_ball(ai, player1);
                            }
                            else //empty
                            {
                                if ((((mask_possible >>> (indexes_move2_34_possible2[i] - 1)) & player1) != 0)
                                        || ((((mask_possible >>> (indexes_move2_34_possible2[i] - 1)) & ai) == 0))) //black ball - opponent or empty
                                {
                                    TheTriplePowerPlay.index = indexes_move2_34_possible1[i];
                                    TheTriplePowerPlay.mini_board_to_rotate = mini_board_34_possible1[i];
                                    TheTriplePowerPlay.direction_rotating = twist_board_34_possible1[i];
                                }
                                else //white ball
                                {
                                    my_color_ball(ai, player1);
                                }
                            }
                        }
                    }
                    mask=mask>>>2;
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


        else if (player_move == 2) //second ball to put on board
        {
            player_move_2(ai, player1);
        }

        else if(player_move==3)
        {
            player_move_3(ai, player1);
        }

        else if(player_move>3)
        {
            player_move_above_3(ai, player1);
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
        t.triple_power_play(0b000000000000000000010100000000000000000000000000000000000000000L,
                        0b000000000000000000000000000000000000000000000000000000000000000L);
        TheTriplePowerPlay.player_move++;
        t.triple_power_play(0b000000000010100000000000000000000000000000000000000000000000000L,
                        0b000000000000000000000000000000000000000000000000000000000000000L);
//        TheTriplePowerPlay.player_move++;
//        t.triple_power_play(0b000100010000000100001000000110100000000000000000000000000000000L,
//                        0b000000000000000000000000000000000010000000000000000000000000000L);

        System.out.println(TheTriplePowerPlay.player_move);
    }
}
