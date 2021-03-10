package clothesarchive.gui.panels.general.viewEditAdd;

import clothesarchive.services.CRUD.CrudService;

public class EditMenu extends Menu {
    public EditMenu( CrudService service) {
        super("Редактиране на запис", service);
    }
}
