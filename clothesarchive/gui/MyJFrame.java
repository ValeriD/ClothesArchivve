package clothesarchive.gui;

import clothesarchive.exceptions.CAException;
import clothesarchive.gui.panels.general.mainMenu.MainMenu;
import clothesarchive.gui.panels.general.viewEditAdd.AddMenu;
import clothesarchive.gui.panels.general.viewEditAdd.EditMenu;
import clothesarchive.gui.panels.general.Menu;
import clothesarchive.gui.panels.general.viewEditAdd.ViewMenu;
import clothesarchive.services.CRUD.CrudService;
import clothesarchive.services.CRUD.CrudServiceImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Valeri Dobrev
 */
public class MyJFrame extends JFrame implements ActionListener {
    Menu addMenu;
    Menu editMenu;
    Menu viewMenu;
    MainMenu mainMenu;
    JLayeredPane layeredPanel;

    public MyJFrame(){
        this.setSize(960,720);
        this.setResizable(true);
        this.setTitle("ClothesArchive");
        this.setIconImage(new ImageIcon("static/icons/img.png").getImage());
        CrudService service;

        try {
            service = new CrudServiceImpl();
        } catch (CAException e) {
            e.show(this);
            this.setVisible(false);
            this.dispose();
            System.exit(0);
            return;
        }

        this.addMenu = new AddMenu(service, this);
        this.editMenu = new EditMenu(service, this);
        this.viewMenu = new ViewMenu(service, this);
        this.mainMenu = new MainMenu(service,this);
        mainMenu.setVisible(true);

        this.add(mainMenu);


        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Method that handles all button clicks events
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        String cl = button.getActionCommand().split(" ")[0];
        String command = button.getActionCommand().split(" ")[1];

        if(cl.equals("AddMenu")){
            if(command.contains("Откажи")){
                this.addMenu.cancelPerformed();
                this.hideMenu(addMenu);
                this.showMenu(mainMenu,null);
            }else if(command.contains("Добави")){
                if(this.addMenu.save()==0) {
                    this.hideMenu(addMenu);
                    this.showMenu(mainMenu, null);
                }
            }
        }else if(cl.equals("EditMenu")){
            if(command.contains("Откажи")){
                this.editMenu.cancelPerformed();
                this.hideMenu(editMenu);
                this.showMenu(mainMenu, null);
            }else if(command.contains("Запази")){
                if(this.editMenu.save()==0){
                    this.hideMenu(editMenu);
                    this.showMenu(mainMenu, null);
                }
            }
        }else if(cl.equals("ViewMenu")){
            if(command.contains("Назад")){
                this.viewMenu.cancelPerformed();
                this.hideMenu(viewMenu);
                this.showMenu(mainMenu, null);
            }
        }else if(cl.equals("MainMenu")){
            try {
                if (command.contains("Добави")) {
                    this.hideMenu(mainMenu);
                    this.showMenu(addMenu, null);
                } else if (command.contains("Редактирай")) {
                    editMenu.showMenu(mainMenu.getSelectedItem());
                    this.hideMenu(mainMenu);
                } else if (command.contains("Виж")) {
                    viewMenu.showMenu(mainMenu.getSelectedItem());
                    this.hideMenu(mainMenu);
                } else {
                        mainMenu.deleteRecord(mainMenu.getSelectedItem());
                        this.hideMenu(mainMenu);
                        this.showMenu(mainMenu,null);
                }

            }catch (CAException caException){
                caException.show(this);
                this.remove(mainMenu);
                mainMenu.updateUI();
                this.showMenu(mainMenu, null);
            }
        }
    }
    private void hideMenu(Menu menu){
        menu.setVisible(false);
        this.remove(menu);

    }
    private void showMenu(Menu menu, String recordName){
        menu.showMenu(recordName);
        this.add(menu);
        validate();
        repaint();
    }
}
