package main.Pieces;

import main.Board;
import main.Piece;

public class Pawn extends Piece {

    public Pawn(Integer position, Board board, Boolean black) {
        super(position, board, "pawn", black);
    }

    @Override
    public boolean moveIsValid(Integer position) {
        return  (canCapture(position) || canMoveForward(position));
    }

    private boolean canMoveForward(Integer position) {
        boolean blackForward = black
                && position.equals(this.position - Board.BOARD_SIZE) && noPieceAt(this.position - Board.BOARD_SIZE);
        boolean whiteForward = !black
                && position.equals(this.position + Board.BOARD_SIZE) && noPieceAt(this.position + Board.BOARD_SIZE);
        return blackForward || whiteForward;
    }

    private boolean canCapture(Integer position) {
       return (board.existPieceAt(position)
               && isImmediateDiag(position)
               && (board.getPieceAt(position)).isBlack() != black);
    }

    private boolean noPieceAt(int position) {
        return board.getPieceAt(position) == null;
    }

    private boolean isImmediateDiag(Integer position) {
        if (black) {
            return position.equals(this.position - Board.BOARD_SIZE + 1)
                    || position.equals(this.position - Board.BOARD_SIZE - 1)
                    && (position / Board.BOARD_SIZE == this.yPos - 1);
        } else {
            return position.equals(this.position + Board.BOARD_SIZE + 1)
                    || position.equals(this.position + Board.BOARD_SIZE - 1)
                    && (position / Board.BOARD_SIZE == this.yPos + 1);
        }
    }
}
