package main;

public class ChessGame {

    private Board board;
    private boolean blackTurn;

    public ChessGame() {
        board = new Board();
        blackTurn = false;
    }

    public Board getBoard() {
        return board;
    }

    public boolean isBlackTurn() {
        return blackTurn;
    }

    public void movePiece(Piece piece, Integer position) {
        if (blackTurn && piece.isBlack()) {
            board.movePiece(piece, position);
            if (piece.getPosition().equals(position)) {
                blackTurn = false;
            }
        } else if (!blackTurn && !piece.isBlack()) {
            board.movePiece(piece, position);
            if (piece.getPosition().equals(position)) {
                blackTurn = true;
            }
        }
    }
}
