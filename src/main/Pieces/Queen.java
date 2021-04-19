package main.Pieces;

import main.Board;
import main.Piece;

import java.awt.*;

public class Queen extends Piece {

    public Queen(Integer position, Board board, Boolean black) {
        super(position, board, "queen", black);
    }

    @Override
    public boolean moveIsValid(Integer position) {
        return false;
    }
}
