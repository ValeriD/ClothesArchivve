package clothesarchive.gui.panels.general.viewEditAdd;

import clothesarchive.exceptions.CAException;
import clothesarchive.gui.panels.mainContent.MenuContent;
import clothesarchive.services.CRUD.CrudService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AddMenu extends Menu {

    public AddMenu(CrudService service){
        super("Добавяне на елемент",service);
    }

    @Override
    protected boolean areFieldsFocusable() {
        return true;
    }

    @Override
    void checkSave(ActionEvent e) {
        //Checks which button is pressed
        if(e.getSource().toString().contains("Добави")) {
            try {
                this.service.saveRecord(this.content.getNameFromField(), this.content.getDescription(),
                        this.content.getCompany(),
                        this.content.getPrice(),
                        this.content.getFile(), 1);
                this.content.clearAllFields();
                this.setVisible(false);
            } catch (CAException caException) {
                caException.show(this);
            }
        }else if(e.getSource().toString().equals("Откажи")){
            this.content.clearAllFields();
            this.setVisible(false);
        }
    }
}
