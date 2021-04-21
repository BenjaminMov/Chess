package main.Pieces;

import main.Board;
import main.Piece;

import java.awt.*;

public class Queen extends Piece {

    Bishop tempBish;
    Rook tempRook;

    public Queen(Integer position, Board board, Boolean black) {
        super(position, board, "queen", black);
        tempBish = new Bishop(position, board, black);
        tempRook = new Rook(position, board, black);
    }

    @Override
    public void scan() {
        tempBish.scan();
        tempRook.scan();
    }

    @Override
    public boolean moveIsValid(Integer position) {
        return tempBish.moveIsValid(position) || tempRook.moveIsValid(position);
    }

    @Override
    public void clearBlocked() {
        tempBish = new Bishop(position, board, black);
        tempRook = new Rook(position, board, black);
    }
}
