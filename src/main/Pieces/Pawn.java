package main.Pieces;

import main.Board;
import main.Piece;

public class Pawn extends Piece {

    private boolean firstMove = true;
    private Integer initPos;

    public Pawn(Integer position, Board board, Boolean black) {
        super(position, board, "pawn", black);
        initPos = position;
    }

    @Override
    public boolean moveIsValid(Integer position) {
        return  (canCapture(position) || canMoveForward(position));
    }

    private boolean canMoveForward(Integer position) {
        boolean blackForward = black
                && validPosition(position) && noPieceAt(this.position - Board.BOARD_SIZE);
        boolean whiteForward = !black
                && validPosition(position) && noPieceAt(this.position + Board.BOARD_SIZE);
        return blackForward || whiteForward;
    }

    private boolean validPosition(Integer position) {
        if (black) {
            return  (position.equals(this.position - Board.BOARD_SIZE)
                    || (firstMove && position.equals(this.position - Board.BOARD_SIZE * 2)));
        } else {
            return (position.equals(this.position + Board.BOARD_SIZE)
                    || (firstMove && position.equals(this.position + Board.BOARD_SIZE * 2)));
        }
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

    @Override
    public void doneFirstMove() {
        if (!position.equals(initPos)) {
            firstMove = false;
        }
    }
}
