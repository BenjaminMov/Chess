package main.Pieces;

import main.Board;
import main.Piece;

import java.awt.*;
import java.util.ArrayList;

import static java.lang.Math.abs;

public class King extends Piece {

    public King(Integer position, Board board, Boolean black) {
        super(position, board, "king", black);

    }

    @Override
    public boolean moveIsValid(Integer position) {

        if (board.existPieceAt(position) && board.getPieceAt(position).isBlack() == black) {
            return false;
        }

        Integer xPos = position % Board.BOARD_SIZE;
        Integer yPos = position / Board.BOARD_SIZE;

        return (abs(this.yPos - yPos) <= 1 && abs(this.xPos - xPos) <= 1);
    }
}
