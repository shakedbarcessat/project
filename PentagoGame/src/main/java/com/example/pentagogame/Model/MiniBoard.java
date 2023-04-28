package com.example.pentagogame.Model;
public class MiniBoard {
    private short board_mini;

    /**
     * initialize mini board
     *
     * @param board_mini
     */
    public MiniBoard(short board_mini) {
        this.board_mini = board_mini;
    }

    /**
     * rotates the board left (the variable)
     *
     * @return- returns the board after rotating left
     */
    public short rotate_left() {

        final int START_1 = 6;
        final int END_1 = -3;
        final int GAP_123 = 4;
        final int START_2 = 4;
        final int END_2 = -5;
        final int START_3 = -2;
        final int END_3 = 7;
        final short MISUH_1 = 0b100000000000000;
        final short MISUH_3 = 0b000000100000000;
        final short MISUH_2 = 0b000100000000000;
        final int STRAT_INDEX1 = 8;
        final int START_INDEX2 = 7;
        final int START_INDEX3 = 6;
        final int LEN_ROW = 3;


        short x = this.board_mini;
        short save = x;
        short newx = x;
        short num = MISUH_1;
        short index = 0;
        for (int i = START_1; i > END_1; i = i - GAP_123) {
            newx = (short) (x & num);
            if (i == -2) {
                newx = (short) (newx << 2);
            } else
                newx = (short) (newx >> i);
            if (newx == 0) {
                save = (short) (save & ~(1 << STRAT_INDEX1 + index * LEN_ROW));
            } else {
                save = (short) (save | (newx | save));
            }
            num = (short) (num >>> 1);
            index++;
        }
        short level1 = save; //rotates the first row


        x = this.board_mini;
        save = x;
        newx = x;
        num = MISUH_2;
        index = 0;
        for (int i = START_2; i > END_2; i = i - GAP_123) {
            newx = (short) (x & num);
            if (i == -4) {
                newx = (short) (newx << 4);
            } else
                newx = (short) (newx >>> i);
            if (newx == 0) {
                save = (short) (save & ~(1 << START_INDEX2 + index * LEN_ROW));
            } else {
                save = (short) (save | (newx | save));
            }
            num = (short) (num >>> 1);
            index++;

        }

        short level2 = save;//rotates the second row


        x = this.board_mini;
        save = x;
        newx = x;
        num = MISUH_3;
        index = 0;

        for (int i = START_3; i < END_3; i = i + GAP_123) {
            newx = (short) (x & num);
            if (i == -2) {
                newx = (short) (newx >>> 2);
            } else {
                newx = (short) (newx << i);
            }
            if (newx == 0) {
                save = (short) (save & ~(1 << START_INDEX3 + index * LEN_ROW));
            } else {
                save = (short) (save | (newx | save));
            }
            num = (short) (num >>> 1);
            index++;

        }
        short level3 = save;//rotates the third row


        short last = 0;
        short keep = 0;
        short move = MISUH_1;

        for (int i = 0; i < LEN_ROW; i++) {
            keep = (short) (level1 & move);
            last = (short) (keep | last);
            move = (short) (move >>> 1);

            keep = (short) (level2 & move);
            last = (short) (keep | last);
            move = (short) (move >>> 1);

            keep = (short) (level3 & move);
            last = (short) (keep | last);
            move = (short) (move >>> 1);
        }


        return last; //matching the 3 rows
    }

    /**
     * rotates the board right (the variable)
     *
     * @return- returns the board after rotating right
     */
    public short rotate_right() {
        final int SHORT_SIZE = 16;
        final int LEN_ROW = 3;
        final int INDEX_START_ROW1 = 2;
        final int INDEX_END_ROW1 = 6;
        final int DIFFERENCE_ROWS = 2;
        final int START_INDEX2 = 7;
        final int STRAT_INDEX3 = 8;
        final int START_ROW2 = -2;
        final short MISUH_1 = 0b100000000000000;
        final short MISUH_3 = 0b000000100000000;
        final short MISUH_2 = 0b000100000000000;


        short x = board_mini;
        short save = x;
        short newx = x;
        short save2;
        short num = MISUH_1;
        short index = 2;
        for (int i = INDEX_START_ROW1; i < INDEX_END_ROW1 + 1; i = i + DIFFERENCE_ROWS) {
            newx = (short) (x & num);
            newx = (short) (newx >>> i);
            if (newx == 0) {
                save = (short) (save & ~(1 << SHORT_SIZE - i - index));
            } else {
                save = (short) (save | (newx | save));
            }
            num = (short) (num >>> 1);
            index++;
        }

        short level1 = save;//rotates the first row


        x = board_mini;
        save = x;
        newx = x;
        num = MISUH_2;
        index = 2;
        for (int i = START_ROW2; i < DIFFERENCE_ROWS + 1; i = i + DIFFERENCE_ROWS) {
            newx = (short) (x & num);

            if (i == -2) {
                newx = (short) (newx << 2);
            } else {
                newx = (short) (newx >>> i);
            }
            if (newx == 0) {
                save = (short) (save & ~(1 << START_INDEX2 + index * LEN_ROW));
            } else {
                save = (short) (save | (newx | save));
            }
            num = (short) (num >>> 1);
            index--;

        }

        short level2 = save;//rotates the second row


        x = board_mini;
        save = x;
        newx = x;
        num = MISUH_3;
        index = 2;
        for (int i = INDEX_END_ROW1; i > 1; i = i - DIFFERENCE_ROWS) {
            newx = (short) (x & num);
            newx = (short) (newx << i);
            if (newx == 0) {
                save = (short) (save & ~(1 << STRAT_INDEX3 + index * LEN_ROW));
            } else {
                save = (short) (save | (newx | save));
            }
            num = (short) (num >>> 1);
            index--;
        }
        short level3 = save;//rotates the third row



        short last = 0;
        short keep = 0;
        short move = 0b100000000000000;

        for (int i = 0; i < LEN_ROW; i++) {
            keep = (short) (level3 & move);
            last = (short) (keep | last);
            move = (short) (move >>> 1);

            keep = (short) (level2 & move);
            last = (short) (keep | last);
            move = (short) (move >>> 1);

            keep = (short) (level1 & move);
            last = (short) (keep | last);
            move = (short) (move >>> 1);
        }
        return last; //matching the 3 rows
    }

}
