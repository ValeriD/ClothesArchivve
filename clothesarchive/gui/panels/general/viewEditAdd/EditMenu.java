package clothesarchive.gui.panels.general.viewEditAdd;

import clothesarchive.exceptions.CAException;
import clothesarchive.gui.panels.mainContent.MenuContent;
import clothesarchive.models.RecordDTO;
import clothesarchive.services.CRUD.CrudService;

import java.awt.event.ActionEvent;

public class EditMenu extends Menu {
    public EditMenu( CrudService service) {
        super("Редактиране на запис", service);
    }

    protected boolean areFieldsFocusable(){
        return true;
    }


    @Override
    void checkSave(ActionEvent e) {
        if(e.getSource().toString().contains("Запази")) {
            boolean record_exists = false;
            try {
                this.service.saveRecord(this.content.getNameFromField(), this.content.getDescription(),
                        this.content.getCompany(),
                        this.content.getPrice(),
                        this.content.getFile(),0);
            } catch (CAException caException) {
                caException.show(this);
                record_exists = true;
            }
            if (!record_exists) {
                this.content.clearAllFields();
                this.setVisible(false);
            }
        }
    }
}
