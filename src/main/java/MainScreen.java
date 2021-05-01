package src.main.java;

import src.main.java.gui.MyJFrame;

/**
 *
 * @author Valeri Dobrev
 */
public class MainScreen{

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            new MyJFrame().setVisible(true);
        });
    }
}
