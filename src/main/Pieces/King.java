package main.Pieces;

import main.Board;
import main.Piece;

import java.awt.*;

public class King extends Piece {

    public King(Integer position, Board board, Boolean black) {
        super(position, board, "king", black);

    }

    @Override
    public boolean validMove(Integer position) {
        return false;
    }
}
