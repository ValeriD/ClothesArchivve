package clothesarchive.gui.panels.general.viewEditAdd;

import clothesarchive.gui.MyJFrame;
import clothesarchive.gui.panels.general.Menu;
import clothesarchive.services.CRUD.CrudService;

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
