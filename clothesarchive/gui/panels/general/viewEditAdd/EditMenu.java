package clothesarchive.gui.panels.general.viewEditAdd;

import clothesarchive.exceptions.CAException;
import clothesarchive.gui.MyJFrame;
import clothesarchive.services.CRUD.CrudService;

import java.awt.event.ActionEvent;

public class EditMenu extends Menu {
    public EditMenu(CrudService service, MyJFrame parent) {
        super("Редактиране на запис", service, parent);
    }

    @Override
    protected boolean areFieldsFocusable(){
        return true;
    }


    @Override
    public int save() {
        try {
            this.service.saveRecord(this.content.getNameFromField(), this.content.getDescription(),
                    this.content.getCompany(),
                    this.content.getPrice(),
                    this.content.getFile(), 0);
        } catch (CAException caException) {
            caException.show(this);
            return -1;
        }
        this.content.clearAllFields();
        return 0;
    }

    @Override
    public String getClassName() {
        return "EditMenu";
    }
}
