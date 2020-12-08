package clothesarchive.gui;

import clothesarchive.gui.panels.general.AddMenu;

import javax.swing.*;

public class MyJFrame extends JFrame {

    public MyJFrame(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(420,420);

        this.setResizable(false);
        this.setTitle("ClothesArchive");
        this.setIconImage(new ImageIcon("static/icons/img.png").getImage());

        this.add(new AddMenu());


        this.setVisible(true);


    }
}
