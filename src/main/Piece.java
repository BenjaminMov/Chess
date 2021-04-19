package main;

import java.awt.*;

import static main.Board.BOARD_SIZE;

public abstract class Piece {

    protected Integer position; //from 0 - 63
    protected Board board;
    protected String type;
    protected boolean black;

    protected Integer yPos;
    protected Integer xPos;

    public Piece(Integer position, Board board, String type, Boolean black) {
        this.position = position;
        this.board = board;
        this.type = type;
        this.black = black;

        xPos = position % BOARD_SIZE;
        yPos = position / BOARD_SIZE;
    }

    protected void move(Integer position) {
        if (validMove(position)) {
            this.position = position;
        }
    }

    public abstract boolean validMove(Integer position);

    public Integer getPosition() {
        return position;
    }

    public boolean isBlack() {
        return black;
    }

    public String getType() {
        return type;
    }

    public void setxPos(Integer xPos) {
        this.xPos = xPos;
    }

    public void setyPos(Integer yPos) {
        this.yPos = yPos;
    }

    public Integer getxPos() {
        return xPos;
    }

    public Integer getyPos() {
        return yPos;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }
}
