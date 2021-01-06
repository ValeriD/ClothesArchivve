package clothesarchive;

import clothesarchive.gui.MyJFrame;
import com.sun.tools.javac.Main;

import javax.swing.*;
/**
 *
 * @author Valeri Dobrev
 */
public class MainScreen {
    private JPanel mainPanel;

    public MainScreen(){
        java.awt.EventQueue.invokeLater(() -> {
           new MyJFrame().setVisible(true);
       });
    }


    public static void main(String[] args) {
       new MainScreen();
    }
}
