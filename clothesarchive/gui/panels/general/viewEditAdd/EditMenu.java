package clothesarchive.gui.panels.general.viewEditAdd;

import clothesarchive.exceptions.CAException;
import clothesarchive.gui.MyJFrame;
import clothesarchive.gui.panels.general.Menu;
import clothesarchive.services.CRUD.CrudService;

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
