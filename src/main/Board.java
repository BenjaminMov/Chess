package main;

import main.Pieces.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Board implements Iterable<Piece> {

    private List<Piece> pieces;

    public static final Integer BOARD_SIZE = 8;
    public static final Integer CELL_SIZE = 100;

    public Board() {
        pieces = new ArrayList<>();
        setup();
    }

    public void update(Piece movedPiece) {
        Piece capturedPiece = null;
        for (Piece p : pieces) {
            if (p != movedPiece && p.getPosition().equals(movedPiece.getPosition())) {
                capturedPiece = p;
            }
            p.setxPos(p.getPosition() % BOARD_SIZE);
            p.setyPos(p.getPosition() / BOARD_SIZE);
            p.clearBlocked();
        }

        if (capturedPiece != null) {
            Piece finalCapturedPiece = capturedPiece;
            pieces.removeIf(n -> n.equals(finalCapturedPiece));
        }
    }

    private void setup() {
        pieces.add(new King(4, this, false));
        pieces.add(new King(60, this, true));

        pieces.add(new Queen(3, this, false));
        pieces.add(new Queen(59, this, true));

        pieces.add(new Bishop(2, this, false));
        pieces.add(new Bishop(5, this, false));
        pieces.add(new Bishop(58, this, true));
        pieces.add(new Bishop(61, this, true));

        pieces.add(new Knight(1, this, false));
        pieces.add(new Knight(6, this, false));
        pieces.add(new Knight(57, this, true));
        pieces.add(new Knight(62, this, true));

        pieces.add(new Rook(0, this, false));
        pieces.add(new Rook(7, this, false));
        pieces.add(new Rook(56, this, true));
        pieces.add(new Rook(63, this, true));

        for (int pos = 8; pos < 16; pos++) {
            pieces.add(new Pawn(pos, this, false));
        }

        for (int pos = 48; pos < 56; pos++) {
            pieces.add(new Pawn(pos, this, true));
        }
    }

    public boolean existPieceAt(Integer position) {
        boolean exists = false;
        for (Piece p : pieces) {
            if (p.getPosition().equals(position)) {
                exists = true;
                break;
            }
        }
        return exists;
    }

    public Piece getPieceAt(Integer position) {
        Piece piece = null;
        for (Piece p : pieces) {
            if (p.getPosition().equals(position)) {
                piece = p;
                break;
            }
        }
        return piece;
    }

    @Override
    public Iterator<Piece> iterator() {
        return pieces.iterator();
    }
}
