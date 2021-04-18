package clothesarchive.gui.panels.headings;

import clothesarchive.gui.custom.customSettings.CAFonts;

import javax.swing.*;
import java.awt.*;

public class Heading extends JPanel {


    public Heading(String msg){
        this.setPreferredSize(new Dimension(100,50));//Setting the size of the panel
        JLabel heading = new JLabel();  //Creating a new label
        heading.setText(msg); //Setting the text
        heading.setFont(CAFonts.HeadingFont()); //Setting the font for the label

        this.setLayout(new FlowLayout()); //Setting the panel to Flow
        this.add(heading);  //Adding the heading label

        this.setVisible(true);

    }
}
