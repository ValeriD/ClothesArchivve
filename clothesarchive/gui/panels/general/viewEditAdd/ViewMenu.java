package clothesarchive.gui.panels.general.viewEditAdd;

import clothesarchive.services.CRUD.CrudService;

public class ViewMenu extends Menu {
    public ViewMenu( CrudService service) {
        super("Преглед на запис", service);
    }
}
