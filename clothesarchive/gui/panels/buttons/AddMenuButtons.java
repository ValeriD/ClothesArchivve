package clothesarchive.gui.panels.buttons;

import javax.swing.*;
import java.awt.*;

public class AddMenuButtons extends JPanel {
    JButton addButton;
    JButton cancelButton;
    public AddMenuButtons(){
        this.setLayout(new GridLayout(1,2)); //Setting the bottom layout to Grid so that 2 flow panels can fit

        this.add(createAddButton()); //Adding the left panel
        this.add(createCancelButton());//Adding the right panel

        this.setVisible(true);

    }

    private JPanel createAddButton(){
        this.addButton = new JButton(); //Creating new button
        this.addButton.setText("Добави"); //Setting the text for the button
        this.addButton.setPreferredSize(new Dimension(200,45));//Setting the size of the button

        JPanel leftButton = new JPanel();//Creating new panel for my AddButton
        leftButton.setLayout(new FlowLayout(FlowLayout.LEADING));//Setting the FlowLayout so the button goes right
        leftButton.add(addButton);//Adding the button in my new panel

        return leftButton;
    }

    private JPanel createCancelButton(){

        this.cancelButton = new JButton();  //Creating new button
        this.cancelButton.setText("Откажи"); //Setting the text for the button
        this.cancelButton.setPreferredSize(new Dimension(200,45)); //Setting the size of the button

        JPanel rightButton = new JPanel(); //Creating new panel for my CancelButton
        rightButton.setLayout(new FlowLayout(FlowLayout.TRAILING));//Setting the FlowLayout so the button goes right
        rightButton.add(this.cancelButton); //Adding the button in my new panel

        return rightButton;
    }
}
