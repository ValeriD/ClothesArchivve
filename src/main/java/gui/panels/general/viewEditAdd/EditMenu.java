package src.main.java.gui.panels.general.viewEditAdd;

import src.main.java.exceptions.CAException;
import src.main.java.gui.MyJFrame;
import src.main.java.gui.panels.general.Menu;
import src.main.java.services.CRUD.CrudService;

public class EditMenu extends Menu {
    String oldName;

    public EditMenu(CrudService service, MyJFrame parent) {
        super("Редактиране на запис", service, parent);
    }

    @Override
    public void showMenu(String name) {
        oldName = name;
        fillFields(name);
        this.setVisible(true);
        parent.add(this);
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
                    this.content.getFile(), 0, oldName);
        } catch (CAException caException) {
            caException.show(this);
            return -1;
        }
        this.content.clearAllFields();
        this.setVisible(false);
        return 0;
    }

    @Override
    public String getClassName() {
        return "EditMenu";
    }
}
