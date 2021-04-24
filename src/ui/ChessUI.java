package ui;

import main.Board;
import main.ChessGame;

import javax.swing.*;
import java.awt.*;

public class ChessUI extends JFrame {

    private ChessGame chessGame;
    GamePanel gp;

    public ChessUI() {
        super("Chess");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        chessGame = new ChessGame();
        gp = new GamePanel(chessGame);

        add(gp);

        pack();
        centreOnScreen();
        setVisible(true);

    }

    private void centreOnScreen() {
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screen.width - getWidth()) / 2, (screen.height - getHeight()) / 2);
    }

    public static void main(String[] args) {
        new ChessUI();
    }

}
