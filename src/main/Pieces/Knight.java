package main.Pieces;

import main.Board;
import main.Piece;

import java.awt.*;

public class Knight extends Piece {

    public Knight(Integer position, Board board, Boolean black) {
        super(position, board, "knight", black);
    }

    @Override
    public boolean validMove(Integer position) {
        return false;
    }
}
