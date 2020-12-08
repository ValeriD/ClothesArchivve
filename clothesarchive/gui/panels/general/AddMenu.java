package clothesarchive.gui.panels.general;

import clothesarchive.gui.panels.buttons.AddMenuButtons;
import clothesarchive.gui.panels.headings.AddMenuHeading;
import clothesarchive.gui.panels.mainContent.AddMenuContent;

import javax.swing.*;
import java.awt.*;

public class AddMenu extends JPanel {


    public AddMenu(){

        JPanel heading = new AddMenuHeading();
        JPanel textBoxes = new AddMenuContent();
        JPanel buttons = new AddMenuButtons();
        this.setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));

        this.add(heading,BorderLayout.NORTH);
        this.add(textBoxes, BorderLayout.CENTER);
        this.add(buttons,BorderLayout.SOUTH);



        this.setVisible(true);


    }

}
