package clothesarchive.gui.panels.general.viewEditAdd;

import clothesarchive.gui.MyJFrame;
import clothesarchive.services.CRUD.CrudService;
import java.awt.event.ActionEvent;

public class ViewMenu extends Menu {
    public ViewMenu(CrudService service, MyJFrame parent) {
        super("Преглед на запис", service, parent);
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
