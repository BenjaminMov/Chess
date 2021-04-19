package main.Pieces;

import main.Board;
import main.Piece;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static main.Board.BOARD_SIZE;

public class Bishop extends Piece {

    private List<Integer> piecesInPath = new ArrayList<>();

    public Bishop(Integer position, Board board, Boolean black) {
        super(position, board, "bishop", black);
    }

    @Override
    public boolean validMove(Integer position) {
        boolean valid = false;

        Integer xPos = position % BOARD_SIZE;
        Integer yPos = position / BOARD_SIZE;

        List<Integer> allPossibleMoves = new ArrayList<>();

        boolean onRightDiag = (this.yPos - yPos) / (this.xPos - xPos) == 1;
        boolean onLeftDiag = (this.yPos - yPos) / (this.xPos - xPos) == -1;

        for (int pos = 0; pos < BOARD_SIZE * BOARD_SIZE; pos++) {
            if ((this.position != pos) && (onLeftDiag || onRightDiag)) {
                allPossibleMoves.add(pos);
                if (board.existPieceAt(pos)) {
                    piecesInPath.add(pos);
                }
            }
        }

        for (int piece : piecesInPath) {
            int quad = getQuadrant(piece);

            switch (quad) {
                case 1:
                    for (int i = 0; i < BOARD_SIZE; i++) {
                        allPossibleMoves.removeIf(p -> p.equals(piece - BOARD_SIZE + 1));
                    }
                    break;
                case 2:
                    for (int i = 0; i < BOARD_SIZE; i++) {
                        allPossibleMoves.removeIf(p -> p.equals(piece - BOARD_SIZE - 1));
                    }
                    break;
                case 3:
                    for (int i = 0; i < BOARD_SIZE; i++) {
                        allPossibleMoves.removeIf(p -> p.equals(piece + BOARD_SIZE - 1));
                    }
                    break;
                case 4:
                    for (int i = 0; i < BOARD_SIZE; i++) {
                        allPossibleMoves.removeIf(p -> p.equals(piece + BOARD_SIZE + 1));
                    }
                    break;
            }
        }

        List<Integer> validMoves = allPossibleMoves.stream().filter(this::valid).collect(Collectors.toList());

        return validMoves.contains(position);
    }

    private boolean valid(Integer move) {
        if (board.existPieceAt(move)) {
            return (black != board.getPieceAt(move).isBlack());
        } else {
            return true;
        }
    }


    private int getQuadrant(Integer next) {
        int quadrant = 0;
        Integer xPos = next % BOARD_SIZE;
        Integer yPos = next / BOARD_SIZE;

        if (xPos > this.xPos && yPos > this.yPos) {
            quadrant = 1;
        } else if (xPos < this.xPos && yPos > this.yPos) {
            quadrant = 2;
        } else if (xPos < this.xPos && yPos < this.yPos) {
            quadrant = 3;
        } else if (xPos > this.xPos && yPos < this.yPos) {
            quadrant = 4;
        }

        return quadrant;
    }



}
