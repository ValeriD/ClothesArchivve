package clothesarchive.gui.panels.general;

import clothesarchive.gui.panels.buttons.AddMenuButtons;
import clothesarchive.gui.panels.headings.Heading;
import clothesarchive.gui.panels.mainContent.AddMenuContent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddMenu extends JPanel implements ActionListener {
    JPanel heading;
    JPanel textBoxes;
    JPanel buttons;
    public AddMenu(){

        this.heading = new Heading("Добавяне на запис");

        this.textBoxes = new AddMenuContent();

        this.buttons = new AddMenuButtons(this);

        this.setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));

        this.add(heading,BorderLayout.NORTH);
        this.add(textBoxes, BorderLayout.CENTER);
        this.add(buttons,BorderLayout.SOUTH);

        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().toString().contains("Добави")){
            System.out.println("Ok");
        }else{
            System.out.println("not ok");
        }
    }
}
