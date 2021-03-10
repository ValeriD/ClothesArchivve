package clothesarchive.gui.panels.general.viewEditAdd;

import clothesarchive.exceptions.CAException;
import clothesarchive.gui.panels.buttons.MenuButtons;
import clothesarchive.gui.panels.headings.Heading;
import clothesarchive.gui.panels.mainContent.MenuContent;
import clothesarchive.services.CRUD.CrudService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class Menu extends JPanel implements ActionListener {
    Heading heading;
    MenuContent content;
    MenuButtons buttons;
    CrudService service;


    public Menu(String headingMsg, CrudService service){
        this.service = service;
        this.heading = new Heading(headingMsg); //Creating new Panel for the heading
        this.content = new MenuContent(); //Creating new Panel for the text fields
        this.buttons = new MenuButtons(this); // Creating new Panel for the control buttons


        this.setLayout(new BorderLayout()); //Setting the AddMenu to BorderLayout so that the above panels can go top,center and bottom

        this.add(heading,BorderLayout.NORTH); //Setting the heading position on top side
        this.add(content, BorderLayout.CENTER); //Setting the text fields in the center
        this.add(buttons,BorderLayout.SOUTH); //Setting the buttons on the bottom side

        this.setVisible(true);


    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource().toString().contains("Добави")) {
            boolean record_exists = false;
            try {
                this.service.saveRecord(this.content.getNameFromField(), this.content.getDescription(),
                        this.content.getCompany(),
                        this.content.getPrice(),
                        this.content.getFile());
            } catch (CAException caException) {
                caException.show(this);
                record_exists = true;
            }
            if (!record_exists) {
                this.content.clearAllFields();
                this.setVisible(false);
            }
        }else if(e.getSource().toString().contains("Откажи")){
            this.content.clearAllFields();
            this.setVisible(false);
        }


    }
}
