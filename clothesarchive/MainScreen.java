package clothesarchive;

import clothesarchive.gui.MyJFrame;
import com.sun.tools.javac.Main;

import javax.swing.*;
/**
 *
 * @author Valeri Dobrev
 */
public class MainScreen {

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            new MyJFrame().setVisible(true);
        });
    }
}
