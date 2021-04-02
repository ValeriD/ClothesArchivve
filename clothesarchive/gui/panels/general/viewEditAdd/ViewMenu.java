package clothesarchive.gui.panels.general.viewEditAdd;

import clothesarchive.gui.panels.mainContent.MenuContent;
import clothesarchive.services.CRUD.CrudService;

import java.awt.event.ActionEvent;

public class ViewMenu extends Menu {
    public ViewMenu( CrudService service) {
        super("Преглед на запис", service);
    }

    protected boolean areFieldsFocusable(){
        return false;
    }

    @Override
    void checkSave(ActionEvent event) {
        return;
    }
}
