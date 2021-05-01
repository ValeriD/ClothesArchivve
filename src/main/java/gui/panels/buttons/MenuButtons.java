package src.main.java.gui.panels.buttons;

import src.main.java.gui.panels.general.mainMenu.MainMenu;
import src.main.java.gui.panels.general.viewEditAdd.AddMenu;
import src.main.java.gui.panels.general.viewEditAdd.EditMenu;
import src.main.java.gui.panels.general.Menu;
import src.main.java.gui.panels.general.viewEditAdd.ViewMenu;

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
            this.add(createButton(1,"Добави", menu)); //Adding the left panel
            this.add(createButton(2,"Откажи", menu));//Adding the right panel
        }else if(menu instanceof EditMenu){
            this.add(createButton(1,"Запази", menu));
            this.add(createButton(2,"Откажи", menu));
        }else if(menu instanceof ViewMenu){
            this.add(createButton(0,"Hidden", menu));
            this.add(createButton(2,"Назад", menu));
        }else if(menu instanceof MainMenu){
            this.setLayout(new GridLayout(0,4));
            this.add(createButton(1, "Добави", menu));
            this.add(createButton(1, "Редактирай", menu));
            this.add(createButton(2, "Виж", menu));
            this.add(createButton(2, "Изтрий", menu));
        }

        this.setVisible(true);

    }
    /**
     * Method for creating aligned buttons
     * @param flag :
     *             1 if the button is for leading
     *             0 if the button is for trailing
     * @param text: the text of the button
     * @param menu : instance of the parent so that actionCommand can be generated
     * @return JPanel
     */
    private JPanel createButton(int flag, String text,Menu menu){
        JButton button= new JButton(); //Creating new button
        button.setPreferredSize(new Dimension(200,45)); //Setting the size
        button.addActionListener(this.menu.getMenuParent()); //Adding a listener for the button

        JPanel buttonContainer = new JPanel(); //Creating a panel for the button
        button.setText(text);//Setting the text for the button

        button.setActionCommand(menu.getClassName() + " " + text);

        //Checks if which button is wanted to be created
        if(flag==1){
            buttonContainer.setLayout(new FlowLayout(FlowLayout.LEADING)); //Creating FlowLayout so that the button is aligned left
        }else if(flag==2){
            buttonContainer.setLayout(new FlowLayout(FlowLayout.TRAILING)); //Creating FlowLayout so that the button is aligned right
        }else if(flag ==0 ){
            button.setVisible(false);
            buttonContainer.setLayout(new FlowLayout(FlowLayout.LEADING)); //Creating FlowLayout so that the button is aligned right
        }
        buttonContainer.add(button); //Adding the button to the panel
        return buttonContainer;
    }
}
