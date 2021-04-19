package main.Pieces;

import main.Board;
import main.Piece;

import java.awt.*;
import java.util.ArrayList;

public class King extends Piece {

    public King(Integer position, Board board, Boolean black) {
        super(position, board, "king", black);

    }

    @Override
    public boolean moveIsValid(Integer position) {
        return false;
    }
}
