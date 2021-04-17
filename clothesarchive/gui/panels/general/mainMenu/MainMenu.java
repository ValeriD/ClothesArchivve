package clothesarchive.gui.panels.general.mainMenu;

import clothesarchive.exceptions.CAException;
import clothesarchive.gui.MyJFrame;
import clothesarchive.gui.panels.general.Menu;
import clothesarchive.gui.panels.mainContent.MainMenuFields;
import clothesarchive.services.CRUD.CrudService;

import java.awt.*;

public class MainMenu extends Menu {
    MainMenuFields mainContent;

    public MainMenu(CrudService service, MyJFrame parent){
        super("Всички записи", service,parent);
        this.remove(this.content);
        mainContent=null;
        showMenu(null);
    }

    public void deleteRecord(String name){
        try{
            service.deleteRecord(name);
        }catch (CAException e){
            e.show(this);
        }
    }

    public String getSelectedItem(){
        return mainContent.getSelectedItem().getName();
    }

    @Override
    public void showMenu(String name) {
        if(mainContent!=null){
            remove(mainContent);
        }
        try {
            this.mainContent = new MainMenuFields(service.getRecords());
        } catch (CAException e) {
            e.show(this);
        }
        this.add(this.mainContent, BorderLayout.CENTER);
        this.setVisible(true);
    }

    @Override
    protected boolean areFieldsFocusable() {
        return true;
    }

    @Override
    public int save() {
        return 0;
    }

    @Override
    public String getClassName() {
        return "MainMenu";
    }

}
