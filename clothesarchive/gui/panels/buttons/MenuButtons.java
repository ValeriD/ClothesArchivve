package clothesarchive.gui.panels.buttons;

import clothesarchive.gui.panels.general.viewEditAdd.AddMenu;
import clothesarchive.gui.panels.general.viewEditAdd.EditMenu;
import clothesarchive.gui.panels.general.viewEditAdd.Menu;
import clothesarchive.gui.panels.general.viewEditAdd.ViewMenu;

import javax.swing.*;
import java.awt.*;

public class MenuButtons extends JPanel {
    Menu menu; //Instance of the Menu so that a listener could be connected

    public MenuButtons(Menu menu){
        this.menu = menu;
        this.setPreferredSize(new Dimension(100,55)); //Setting the size of the panel

        this.setLayout(new GridLayout(0,2)); //Setting the bottom layout to Grid so that 2 flow panels can fit

        //Check buttons for which menu to generate
        if(menu instanceof AddMenu) {
            this.add(createButton(1,"Добави")); //Adding the left panel
            this.add(createButton(0,"Откажи"));//Adding the right panel
        }else if(menu instanceof EditMenu){
            this.add(createButton(1,"Запази"));
            this.add(createButton(0,"Откажи"));
        }else if(menu instanceof ViewMenu){
            this.add(createButton(0,"Назад"));
        }

        this.setVisible(true);

    }
    /**
     * Method for creating aligned buttons
     * @param flag :
     *             1 if the button is for leading
     *             0 if the button is for trailing
     * @param text: the text of the button
     * @return JPanel
     */
    private JPanel createButton(int flag, String text){
        JButton button= new JButton(); //Creating new button
        button.setPreferredSize(new Dimension(200,45)); //Setting the size
        button.addActionListener(this.menu); //Adding a listener for the button

        JPanel buttonContainer = new JPanel(); //Creating a panel for the button
        button.setText(text);//Setting the text for the button

        //Checks if which button is wanted to be created
        if(flag==1){
            buttonContainer.setLayout(new FlowLayout(FlowLayout.LEADING)); //Creating FlowLayout so that the button is aligned left
        }else{
            buttonContainer.setLayout(new FlowLayout(FlowLayout.TRAILING)); //Creating FlowLayout so that the button is aligned right
        }

        buttonContainer.add(button); //Adding the button to the panel
        return buttonContainer;
    }
}
