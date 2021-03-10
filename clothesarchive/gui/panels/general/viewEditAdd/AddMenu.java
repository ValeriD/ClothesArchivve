package clothesarchive.gui.panels.general.viewEditAdd;

import clothesarchive.services.CRUD.CrudService;

public class AddMenu extends Menu {

    public AddMenu(CrudService service){
        super("Добавяне на елемент",service);
    }
}
