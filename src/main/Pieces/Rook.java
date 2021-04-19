package main.Pieces;

import main.Board;
import main.Piece;

import java.awt.*;

public class Rook extends Piece {

    public Rook(Integer position, Board board, Boolean black) {
        super(position, board, "rook", black);
    }

    @Override
    public boolean moveIsValid(Integer position) {
        return false;
    }
}
