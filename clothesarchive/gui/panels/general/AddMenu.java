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

        this.heading = new Heading("Добавяне на запис"); //Creating new Panel for the heading
        this.textBoxes = new AddMenuContent(); //Creating new Panel for the text fields
        this.buttons = new AddMenuButtons(this); // Creating new Panel for the control buttons


        this.setLayout(new BorderLayout()); //Setting the AddMenu to BorderLayout so that the above panels can go top,center and bottom

        this.add(heading,BorderLayout.NORTH); //Setting the heading position on top side
        this.add(textBoxes, BorderLayout.CENTER); //Setting the text fields in the center
        this.add(buttons,BorderLayout.SOUTH); //Setting the buttons on the bottom side

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
