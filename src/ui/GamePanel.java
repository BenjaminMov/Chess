package ui;

import main.Board;
import main.Piece;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static main.Board.BOARD_SIZE;
import static main.Board.CELL_SIZE;

public class GamePanel extends JPanel {

    private static final Integer Y_PADDING = 15;
    private static final Integer X_PADDING = 25;

    private static final Color WHITE_TILE_COLOUR = new Color(255,245,196);
    private static final Color BLACK_TILE_COLOUR = new Color(101, 67, 33);

    private static final Integer CIRCLE_SHADOW_DIAMETER = 40;
    private static final Integer CIRCLE_SHADOW_OPACITY = 100;

    private Board board;
    private Piece dragging;

    private Integer mouseX;
    private Integer mouseY;

    public GamePanel(Board board) {
        this.board = board;

        setPreferredSize(new Dimension(CELL_SIZE * BOARD_SIZE, CELL_SIZE * BOARD_SIZE));
        setOpaque(false);
        setLayout(new FlowLayout(FlowLayout.LEADING));
        setupInteraction();
    }

    private void setupInteraction() {
        GameMouseListener eml = new GameMouseListener();
        addMouseListener(eml);
        addMouseMotionListener(eml);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        makeColours(g);

        for (Piece p : board) {
            drawPiece(g, p);
        }

        if (dragging != null) {
            drawValidMoves(g);
        }

        repaint();
    }

    private void drawValidMoves(Graphics g) {
        for (int pos = 0; pos < BOARD_SIZE * BOARD_SIZE; pos++) {
            if (dragging.moveIsValid(pos)) {
                int xPos = (pos % BOARD_SIZE) * CELL_SIZE + (CELL_SIZE - CIRCLE_SHADOW_DIAMETER) / 2;
                int yPos = (pos / BOARD_SIZE) * CELL_SIZE + (CELL_SIZE - CIRCLE_SHADOW_DIAMETER) / 2;
                g.setColor(new Color(0,0,0, CIRCLE_SHADOW_OPACITY));
                g.fillOval(xPos, yPos, CIRCLE_SHADOW_DIAMETER, CIRCLE_SHADOW_DIAMETER);
            }
        }
    }

    private void drawPiece(Graphics g, Piece p) {
        Image img = getImage(p);

        int xPos = (p.getPosition() % BOARD_SIZE) * CELL_SIZE;
        int yPos = (p.getPosition() / BOARD_SIZE) * CELL_SIZE;

        if (dragging == null || !dragging.equals(p)) {
            g.drawImage(img, xPos + X_PADDING / 2,
                    yPos + Y_PADDING / 2,
                    CELL_SIZE - X_PADDING,
                    CELL_SIZE - Y_PADDING, null);
        } else {
            g.drawImage(img, mouseX - (CELL_SIZE - X_PADDING) / 2,
                    mouseY - (CELL_SIZE - Y_PADDING) / 2,
                    CELL_SIZE - X_PADDING,
                    CELL_SIZE - Y_PADDING, null);
        }
    }

    private Image getImage(Piece p) {
        Image image = null;
        switch (p.getType()) {
            case "king":
                if (p.isBlack()) {
                    image = Toolkit.getDefaultToolkit().getImage("./data/BKing.png");
                } else {
                    image = Toolkit.getDefaultToolkit().getImage("./data/WKing.png");
                }
                break;
            case "queen":
                if (p.isBlack()) {
                    image = Toolkit.getDefaultToolkit().getImage("./data/BQueen.png");
                } else {
                    image = Toolkit.getDefaultToolkit().getImage("./data/WQueen.png");
                }
                break;
            case "bishop":
                if (p.isBlack()) {
                    image = Toolkit.getDefaultToolkit().getImage("./data/BBishop.png");
                } else {
                    image = Toolkit.getDefaultToolkit().getImage("./data/WBishop.png");
                }
                break;
            case "knight":
                if (p.isBlack()) {
                    image = Toolkit.getDefaultToolkit().getImage("./data/BKnight.png");
                } else {
                    image = Toolkit.getDefaultToolkit().getImage("./data/WKnight.png");
                }
                break;
            case "rook":
                if (p.isBlack()) {
                    image = Toolkit.getDefaultToolkit().getImage("./data/BRook.png");
                } else {
                    image = Toolkit.getDefaultToolkit().getImage("./data/WRook.png");
                }
                break;
            case "pawn":
                if (p.isBlack()) {
                    image = Toolkit.getDefaultToolkit().getImage("./data/BPawn.png");
                } else {
                    image = Toolkit.getDefaultToolkit().getImage("./data/WPawn.png");
                }

        }
        return image;
    }

    private void makeColours(Graphics g) {
        for (int w = 0; w <= BOARD_SIZE; w++) {
            for (int h = 0; h <= BOARD_SIZE; h++) {
                if ((w + h) % 2 == 0) {
                    g.setColor(WHITE_TILE_COLOUR);
                    g.fillRect(w * CELL_SIZE, h * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                } else {
                    g.setColor(BLACK_TILE_COLOUR);
                    g.fillRect(w * CELL_SIZE, h * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                }
            }
        }
    }

    private void handleMousePressed(MouseEvent me) {
        Integer position = (translateY(me.getY()) * BOARD_SIZE) + translateX(me.getX());
        Piece myPiece = board.getPieceAt(position);

        mouseX = me.getX();
        mouseY = me.getY();

        if (myPiece != null) {
            dragging = myPiece;
            dragging.scan();
            repaint();
        }
    }

    private void handleMouseDragged(MouseEvent me) {
        mouseX = me.getX();
        mouseY = me.getY();
    }

    private void handleMouseReleased(MouseEvent me) {
        Integer position = (translateY(me.getY()) * BOARD_SIZE) + translateX(me.getX());
        if (dragging != null) {
            dragging.clearBlocked();
            if (dragging.moveIsValid(position))
                dragging.setPosition(position);
                board.update(dragging);
        }
        dragging = null;
    }

    private Integer translateX(int x) {
        return x / CELL_SIZE;
    }

    private Integer translateY(int y) {
        return y / CELL_SIZE;
    }


    //Class to represent a MouseListener in the Editor Panel
    private class GameMouseListener extends MouseAdapter {

        @Override
        public void mousePressed(MouseEvent e) {
            handleMousePressed(translateEvent(e));
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            handleMouseDragged(translateEvent(e));
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            handleMouseReleased(translateEvent(e));
        }
    }


    private MouseEvent translateEvent(MouseEvent e) {
        return SwingUtilities.convertMouseEvent(e.getComponent(), e, this);
    }
}
