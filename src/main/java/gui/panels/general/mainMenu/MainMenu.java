package src.main.java.gui.panels.general.mainMenu;

import src.main.java.exceptions.CAException;
import src.main.java.gui.MyJFrame;
import src.main.java.gui.panels.general.Menu;
import src.main.java.gui.panels.content.MainMenuContent;
import src.main.java.services.CRUD.CrudService;

import java.awt.*;

public class MainMenu extends Menu {
    MainMenuContent mainContent;

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

    public String getSelectedItem() throws CAException {
       return mainContent.getSelectedItem().getName();
    }

    @Override
    public void showMenu(String name) {
        if(mainContent!=null){
            remove(mainContent);
        }
        try {
            this.mainContent = new MainMenuContent(service.getRecords());
        } catch (CAException e) {
            e.show(this);
        }
        this.setVisible(true);
        this.requestFocus();
        this.add(this.mainContent, BorderLayout.CENTER);
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
