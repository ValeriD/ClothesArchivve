package clothesarchive.gui;

import clothesarchive.exceptions.CAException;
import clothesarchive.gui.panels.general.viewEditAdd.AddMenu;
import clothesarchive.gui.panels.general.viewEditAdd.EditMenu;
import clothesarchive.gui.panels.general.viewEditAdd.Menu;
import clothesarchive.gui.panels.general.viewEditAdd.ViewMenu;
import clothesarchive.services.CRUD.CrudService;
import clothesarchive.services.CRUD.CrudServiceImpl;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

/**
 *
 * @author Valeri Dobrev
 */
public class MyJFrame extends JFrame implements ActionListener {
    Menu addMenu;
    Menu editMenu;
    Menu viewMenu;

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
        addMenu.setVisible(true);
        this.add(addMenu);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        String cl = button.getActionCommand().split(" ")[0];
        String command = button.getActionCommand().split(" ")[1];

        if(cl.equals("AddMenu")){
            if(command.contains("Откажи")){
                this.addMenu.cancelPerformed();
                this.remove(addMenu);
            }else if(command.contains("Добави")){
                if(this.addMenu.save()==0) {
                    this.remove(this.addMenu);
                }
            }
        }else if(cl.equals("EditMenu")){
            if(command.contains("Откажи")){
                this.addMenu.cancelPerformed();
                this.remove(addMenu);
            }else if(command.contains("Добави")){
                if(this.addMenu.save()==0){
                    this.remove(this.addMenu);
                }
            }
        }else if(cl.equals("ViewMenu")){
            if(command.contains("Назад")){
                this.viewMenu.cancelPerformed();
                this.remove(viewMenu);
            }
        }
    }
}
