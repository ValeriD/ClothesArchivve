package clothesarchive.gui;

import clothesarchive.exceptions.CAException;
import clothesarchive.gui.panels.general.viewEditAdd.Menu;
import clothesarchive.services.CRUD.CrudService;
import clothesarchive.services.CRUD.CrudServiceImpl;

import javax.swing.*;

/**
 *
 * @author Valeri Dobrev
 */
public class MyJFrame extends JFrame {

    public MyJFrame(){
        this.setSize(960,720);

        this.setResizable(true);
        this.setTitle("ClothesArchive");
        this.setIconImage(new ImageIcon("static/icons/img.png").getImage());
        CrudService service = null;
        try {
            service = new CrudServiceImpl();
        } catch (CAException e) {
            e.show(this);
            this.setVisible(false);
            this.dispose();
            System.exit(0);
        }

        Menu addMenu = new AddMenu(service);
        this.add(addMenu);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
