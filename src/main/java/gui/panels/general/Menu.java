package src.main.java.gui.panels.general;

import src.main.java.exceptions.CAException;
import src.main.java.gui.MyJFrame;
import src.main.java.gui.panels.buttons.MenuButtons;
import src.main.java.gui.panels.headings.Heading;
import src.main.java.gui.panels.content.CreateEditViewContent;
import src.main.java.models.RecordDTO;
import src.main.java.services.CRUD.CrudService;

import javax.swing.*;
import java.awt.*;


public abstract class Menu extends JPanel{
    protected Heading heading;
    protected CreateEditViewContent content;
    protected MenuButtons buttons;
    protected CrudService service;
    protected MyJFrame parent;


    public Menu(String headingMsg, CrudService service, MyJFrame parent){
        this.parent = parent;
        this.service = service;
        this.heading = new Heading(headingMsg); //Creating new Panel for the heading
        this.buttons = new MenuButtons(this); // Creating new Panel for the control buttons
        this.content = new CreateEditViewContent(); //Creating new Panel for the text fields

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
    abstract public void showMenu(String name);

    /**
     * Setting values to the fields
     * @param name
     */
    protected void fillFields(String name){
        RecordDTO record;
        try {
            record = service.getRecordByName(name);
            this.content.setFields(record.getName(), record.getDescription(), record.getCompany(), record.getPrice(), record.getFile(), areFieldsFocusable());
        } catch (CAException e) {
            e.show(this);
            return;
        }
    }

    protected abstract boolean areFieldsFocusable();

    public void cancelPerformed() {
        this.setVisible(false);
        this.content.clearAllFields();
    }

    /**
     * Method for saving a record
     * @return 0 if successful
     */
    abstract public int save();

    abstract public String getClassName();

    public final MyJFrame getMenuParent(){
        return parent;
    }
}
