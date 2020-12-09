package clothesarchive.gui.panels.buttons;

import clothesarchive.exceptions.CAException;
import clothesarchive.gui.panels.general.AddMenu;

import javax.swing.*;
import java.awt.*;

public class AddMenuButtons extends JPanel {
    AddMenu addMenu; //Instance of the AddMenu so that a listener could be connected

    public AddMenuButtons(AddMenu addMenu){
        this.addMenu=addMenu;
        this.setPreferredSize(new Dimension(100,55)); //Setting the size of the panel



        this.setLayout(new GridLayout(0,2)); //Setting the bottom layout to Grid so that 2 flow panels can fit

        this.add(createButton(1)); //Adding the left panel
        this.add(createButton(0));//Adding the right panel

        this.setVisible(true);

    }
    /**
     * Method for creating aligned buttons
     * @param flag :
     *             1 if the button is for adding
     *             0 if the button is for canceling
     * @return JPanel
     */
    private JPanel createButton(int flag){
        JButton button= new JButton(); //Creating new button
        button.setPreferredSize(new Dimension(200,45)); //Setting the size
        button.addActionListener(this.addMenu); //Adding a listener for the button

        JPanel buttonContainer = new JPanel(); //Creating a panel for the button

        //Checks if which button is wanted to be created
        if(flag==1){
            button.setText("Добави");//Setting the text for the button
            buttonContainer.setLayout(new FlowLayout(FlowLayout.LEADING)); //Creating FlowLayout so that the button is aligned left
        }else{
            button.setText("Откажи"); //Setting the text for the button
            buttonContainer.setLayout(new FlowLayout(FlowLayout.TRAILING)); //Creating FlowLayout so that the button is aligned right
        }
        buttonContainer.add(button); //Adding the button to the panel
        return buttonContainer;
    }




}
