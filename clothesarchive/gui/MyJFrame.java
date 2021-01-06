package clothesarchive.gui;

import clothesarchive.gui.panels.general.AddMenu;

import javax.swing.*;
/**
 *
 * @author Valeri Dobrev
 */
public class MyJFrame extends JFrame {

    public MyJFrame(){
        this.setSize(960,720);

        this.setResizable(true);
        this.setTitle("ClothesArchive");
        this.setIconImage(new ImageIcon("static/icons/img.png").getImage());
        this.add(new AddMenu());


        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
