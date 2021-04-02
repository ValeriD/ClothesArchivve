package clothesarchive.gui.panels.general.viewEditAdd;

import clothesarchive.exceptions.CAException;
import clothesarchive.gui.panels.buttons.MenuButtons;
import clothesarchive.gui.panels.headings.Heading;
import clothesarchive.gui.panels.mainContent.MenuContent;
import clothesarchive.models.RecordDTO;
import clothesarchive.services.CRUD.CrudService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//TODO abstract method save
public abstract class Menu extends JPanel implements ActionListener {
    Heading heading;
    MenuContent content;
    MenuButtons buttons;
    CrudService service;


    public Menu(String headingMsg, CrudService service){
        this.service = service;
        this.heading = new Heading(headingMsg); //Creating new Panel for the heading
        this.buttons = new MenuButtons(this); // Creating new Panel for the control buttons
        this.content = new MenuContent(); //Creating new Panel for the text fields

        this.setLayout(new BorderLayout()); //Setting the AddMenu to BorderLayout so that the above panels can go top,center and bottom

        this.add(heading,BorderLayout.NORTH); //Setting the heading position on top side
        this.add(content, BorderLayout.CENTER); //Setting the text fields in the center
        this.add(buttons,BorderLayout.SOUTH); //Setting the buttons on the bottom side

        this.setVisible(true);
    }

    /**
     * Method that is called when we want to show the different menus like Edit, View or Add
     * @param name is null when Add is called, otherwise we make a call to the database to get the record with the given name
     */
    public void showRecord(String name) {
        if(!(this instanceof AddMenu)){
            RecordDTO record;
            try {
                record = service.getRecordByName(name);
            } catch (CAException e) {
                e.show(this);
                return;
            }
            this.content.setFields(record.getName(),record.getDescription(),record.getCompany(),record.getPrice(),record.getFile(), areFieldsFocusable());
        }
        this.setVisible(true);
    }

    protected abstract boolean areFieldsFocusable();

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource().toString().equals("Откажи") || e.getSource().toString().equals("Назад")){
            this.content.clearAllFields();
            this.setVisible(false);
        }else{
            checkSave(e);
        }


    }
    abstract void checkSave(ActionEvent event);
}
