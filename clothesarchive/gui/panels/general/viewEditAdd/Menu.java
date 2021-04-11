package clothesarchive.gui.panels.general.viewEditAdd;

import clothesarchive.exceptions.CAException;
import clothesarchive.gui.MyJFrame;
import clothesarchive.gui.panels.buttons.MenuButtons;
import clothesarchive.gui.panels.headings.Heading;
import clothesarchive.gui.panels.mainContent.MenuContent;
import clothesarchive.models.RecordDTO;
import clothesarchive.services.CRUD.CrudService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class Menu extends JPanel{
    Heading heading;
    MenuContent content;
    MenuButtons buttons;
    CrudService service;
    MyJFrame parent;


    public Menu(String headingMsg, CrudService service, MyJFrame parent){
        this.parent = parent;
        this.service = service;
        this.heading = new Heading(headingMsg); //Creating new Panel for the heading
        this.buttons = new MenuButtons(this); // Creating new Panel for the control buttons
        this.content = new MenuContent(); //Creating new Panel for the text fields

        this.setLayout(new BorderLayout()); //Setting the AddMenu to BorderLayout so that the above panels can go top,center and bottom

        this.add(heading,BorderLayout.NORTH); //Setting the heading position on top side
        this.add(content, BorderLayout.CENTER); //Setting the text fields in the center
        this.add(buttons,BorderLayout.SOUTH); //Setting the buttons on the bottom side
        this.setVisible(false);
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

    public void cancelPerformed() {
        this.setVisible(false);
        this.content.clearAllFields();
    }
    abstract public int save();

    abstract public String getClassName();

    public final MyJFrame getMenuParent(){
        return parent;
    }
}
