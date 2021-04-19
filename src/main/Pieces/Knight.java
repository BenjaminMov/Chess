package main.Pieces;

import main.Board;
import main.Piece;

import java.awt.*;
import java.util.ArrayList;

import static java.lang.Math.abs;
import static main.Board.BOARD_SIZE;
import static main.Board.CELL_SIZE;

public class Knight extends Piece {

    public Knight(Integer position, Board board, Boolean black) {
        super(position, board, "knight", black);
    }


    @Override
    public boolean moveIsValid(Integer position) {
        boolean valid = false;

        int xPos = (position % BOARD_SIZE);
        int yPos = (position / BOARD_SIZE);

        if (abs(this.yPos - yPos) == 2) {
            valid = abs(this.xPos - xPos) == 1;
        } else if (abs(this.xPos - xPos) == 2) {
            valid = abs(this.yPos - yPos) == 1;
        }


        return valid;
    }
}
