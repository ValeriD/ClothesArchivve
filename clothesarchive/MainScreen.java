package clothesarchive;

import clothesarchive.gui.MyJFrame;
import com.sun.tools.javac.Main;

import javax.swing.*;

public class MainScreen {
    private JPanel mainPanel;

    public MainScreen(){

        new MyJFrame();
    }


    public static void main(String[] args) {
       new MainScreen();
    }
}
