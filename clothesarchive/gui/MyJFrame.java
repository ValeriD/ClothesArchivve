package clothesarchive.gui;

import clothesarchive.gui.panels.general.AddMenu;

import javax.swing.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/**
 *
 * @author Valeri Dobrev
 */
public class MyJFrame extends JFrame {

    AddMenu addMenu;
    public MyJFrame(){
        this.setSize(960,720);

        this.setResizable(true);
        this.setTitle("ClothesArchive");
        this.setIconImage(new ImageIcon("static/icons/img.png").getImage());
        this.addMenu = new AddMenu();
        this.add(addMenu);
        addMenu.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                //Hide the other windows
            }

            @Override
            public void componentHidden(ComponentEvent e) {
                //Show the List menu
            }
        });


        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
