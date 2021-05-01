package src.main.java.gui.panels.general.viewEditAdd;

import src.main.java.gui.MyJFrame;
import src.main.java.gui.panels.general.Menu;
import src.main.java.services.CRUD.CrudService;

public class ViewMenu extends Menu {
    public ViewMenu(CrudService service, MyJFrame parent) {
        super("Преглед на запис", service, parent);
    }

    public void showMenu(String name) {
        fillFields(name);
        this.setVisible(true);
        parent.add(this);
    }

    @Override
    protected boolean areFieldsFocusable(){
        return false;
    }

    @Override
    public int save() {
        return 0;
    }

    @Override
    public String getClassName() {
        return "ViewMenu";
    }
}
