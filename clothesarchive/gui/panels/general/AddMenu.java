package clothesarchive.gui.panels.general;

import clothesarchive.exceptions.CAException;
import clothesarchive.gui.panels.buttons.AddMenuButtons;
import clothesarchive.gui.panels.headings.Heading;
import clothesarchive.gui.panels.mainContent.AddMenuContent;
import clothesarchive.services.CRUD.CrudService;
import clothesarchive.services.CRUD.CrudServiceImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddMenu extends JPanel implements ActionListener {
    Heading heading;
    AddMenuContent textBoxes;
    AddMenuButtons buttons;
    CrudService service;
    public AddMenu(){

        this.heading = new Heading("Добавяне на запис"); //Creating new Panel for the heading
        this.textBoxes = new AddMenuContent(); //Creating new Panel for the text fields
        this.buttons = new AddMenuButtons(this); // Creating new Panel for the control buttons


        this.setLayout(new BorderLayout()); //Setting the AddMenu to BorderLayout so that the above panels can go top,center and bottom

        this.add(heading,BorderLayout.NORTH); //Setting the heading position on top side
        this.add(textBoxes, BorderLayout.CENTER); //Setting the text fields in the center
        this.add(buttons,BorderLayout.SOUTH); //Setting the buttons on the bottom side

        this.setVisible(true);

        try {
            this.service = new CrudServiceImpl();
        } catch (CAException e) {
            e.show(this);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource().toString().contains("Добави")) {
            boolean record_exists = false;
            try {
                this.service.saveRecord(this.textBoxes.getNameFromField(), this.textBoxes.getDescription(),
                        this.textBoxes.getCompany(),
                        this.textBoxes.getPrice(),
                        this.textBoxes.getFile());
            } catch (CAException caException) {
                caException.show(this);
                record_exists = true;
            }
            if (!record_exists) {
                this.textBoxes.clearAllFields();
                this.setVisible(false);
            }
        }else if(e.getSource().toString().contains("Откажи")){
            this.textBoxes.clearAllFields();
            this.setVisible(false);
        }


    }
}
