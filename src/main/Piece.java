package main;

import java.util.ArrayList;
import java.util.List;

import static main.Board.BOARD_SIZE;

public abstract class Piece {

    protected Integer position; //from 0 - 63
    protected Board board;
    protected String type;
    protected boolean black;

    protected List<Integer> piecesInPath;

    protected Integer yPos;
    protected Integer xPos;

    public Piece(Integer position, Board board, String type, Boolean black) {
        this.position = position;
        this.board = board;
        this.type = type;
        this.black = black;

        xPos = position % BOARD_SIZE;
        yPos = position / BOARD_SIZE;

        piecesInPath = new ArrayList<>();
    }

    protected void move(Integer position) {
        if (moveIsValid(position)) {
            this.position = position;
        }
    }

    public abstract boolean moveIsValid(Integer position);

    public void scan() {

    }

    public Integer getPosition() {
        return position;
    }

    public boolean isBlack() {
        return black;
    }

    public String getType() {
        return type;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public void setxPos(Integer xPos) {
        this.xPos = xPos;
    }

    public void setyPos(Integer yPos) {
        this.yPos = yPos;
    }

    public void clearBlocked() {
        piecesInPath.clear();
    }

    public List<Integer> getPiecesInPath() {
        return piecesInPath;
    }
}
