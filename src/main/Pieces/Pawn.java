package main.Pieces;

import main.Board;
import main.Piece;

import java.awt.*;

public class Pawn extends Piece {

    public Pawn(Integer position, Board board, Boolean black) {
        super(position, board, "pawn", black);
    }

    @Override
    public boolean validMove(Integer position) {
        if (black) {
            return  ((board.existPieceAt(position) && getImmediateDiag(position))
                    || position.equals(this.position - Board.BOARD_SIZE) && noPieceAt(this.position - Board.BOARD_SIZE));
        } else {
            return ((board.existPieceAt(position) && getImmediateDiag(position))
                    || position.equals(this.position + Board.BOARD_SIZE) && noPieceAt(this.position + Board.BOARD_SIZE));
        }
    }

    private boolean noPieceAt(int position) {
        return board.getPieceAt(position) == null;
    }

    private boolean getImmediateDiag(Integer position) {
        if (black) {
            return position.equals(this.position - Board.BOARD_SIZE + 1)
                    || position.equals(this.position - Board.BOARD_SIZE - 1);
        } else {
            return position.equals(this.position + Board.BOARD_SIZE + 1)
                    || position.equals(this.position + Board.BOARD_SIZE - 1);
        }
    }
}
