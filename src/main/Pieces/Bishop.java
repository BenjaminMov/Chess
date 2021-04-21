package main.Pieces;

import main.Board;
import main.Piece;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Math.abs;
import static main.Board.BOARD_SIZE;

public class Bishop extends Piece {

    public Bishop(Integer position, Board board, Boolean black) {
        super(position, board, "bishop", black);
    }

    @Override
    public void scan() {
        for (int pos = 0; pos < BOARD_SIZE * BOARD_SIZE; pos++) {

            int xPos = (pos % BOARD_SIZE);
            int yPos = (pos / BOARD_SIZE);

            if (board.existPieceAt(pos)
                    && ((this.xPos - xPos) != 0)
                    && (abs(this.yPos - yPos) == abs(this.xPos - xPos))) {
                piecesInPath.add(pos);
            }
        }
    }

    @Override
    public boolean moveIsValid(Integer position) {

        int xPos = (position % BOARD_SIZE);
        int yPos = (position / BOARD_SIZE);

        ArrayList<Integer> blocked = new ArrayList<>();

        if (piecesInPath.contains(position) && board.getPieceAt(position).isBlack() == black) {
            return false;
        }

        ArrayList<Integer> newBlocked = generateBlocked(blocked);

        if (((this.xPos != xPos) && (abs(this.yPos - yPos) == abs(this.xPos - xPos))
                && !newBlocked.contains(position))) {
            return true;
        }

        return false;
    }

    private ArrayList<Integer> generateBlocked(ArrayList<Integer> blocked) {

        for (int p : piecesInPath) {
            int xPos = (p % BOARD_SIZE);
            int yPos = (p / BOARD_SIZE);

            if (xPos > this.xPos && yPos < this.yPos) {
                for (int i = 1; i < BOARD_SIZE - xPos; i++) {
                    blocked.add(p - (i * BOARD_SIZE) + i);
                }
            } else if (xPos < this.xPos && yPos < this.yPos) {
                for (int i = 1; i < xPos + 1; i++) {
                    blocked.add(p - (i * BOARD_SIZE) - i);
                }
            } else if (xPos < this.xPos && yPos > this.yPos) {
                for (int i = 1; i < xPos + 1; i++) {
                    blocked.add(p + (i * BOARD_SIZE) - i);
                }
            } else if (xPos > this.xPos && yPos > this.yPos) {
                for (int i = 1; i < BOARD_SIZE - xPos; i++) {
                    blocked.add(p + (i * BOARD_SIZE) + i);
                }
            }
        }
        return blocked;
    }
}
