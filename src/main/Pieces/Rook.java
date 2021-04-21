package main.Pieces;

import main.Board;
import main.Piece;
import org.w3c.dom.ranges.Range;

import java.awt.*;
import java.util.ArrayList;

import static main.Board.BOARD_SIZE;

public class Rook extends Piece {

    public Rook(Integer position, Board board, Boolean black) {
        super(position, board, "rook", black);
    }

    @Override
    public void scan() {
        for (int pos = 0; pos < BOARD_SIZE * BOARD_SIZE; pos++) {

            int xPos = (pos % BOARD_SIZE);
            int yPos = (pos / BOARD_SIZE);

            if (board.existPieceAt(pos) && (this.xPos == xPos || this.yPos == yPos)) {
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

        if ((this.xPos == xPos || this.yPos == yPos) && !newBlocked.contains(position)) {
            return true;
        }

        return false;
    }

    private ArrayList<Integer> generateBlocked(ArrayList<Integer> blocked) {

        for (int p : piecesInPath) {
            int xPos = (p % BOARD_SIZE);
            int yPos = (p / BOARD_SIZE);

            if (xPos > this.xPos) {
                for (int i = 0; i < BOARD_SIZE - xPos; i++) {
                    blocked.add(p + i + 1);
                }
            } else if (xPos < this.xPos) {
                for (int i = 0; i < xPos; i++) {
                    blocked.add(p - i - 1);
                }
            } else if (yPos > this.yPos) {
                for (int i = 1; i < BOARD_SIZE - yPos; i++) {
                    blocked.add(p + i * BOARD_SIZE);
                }
            } else if (yPos < this.yPos) {
                for (int i = 1; i < yPos + 1; i++) {
                    blocked.add(p - i * BOARD_SIZE);
                }
            }
        }
        return blocked;
    }
}
