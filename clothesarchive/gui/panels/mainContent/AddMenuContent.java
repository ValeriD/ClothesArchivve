package clothesarchive.gui.panels.mainContent;

import clothesarchive.gui.customSettings.HintTextField;
import clothesarchive.gui.customSettings.FormattedFieldListener;

import javax.swing.*;
import java.awt.*;
import java.text.NumberFormat;
import java.util.Currency;

public class AddMenuContent extends JPanel {
    HintTextField name;
    HintTextField description;
    HintTextField company;
    JFormattedTextField price;

    public AddMenuContent(){
        this.setPreferredSize(new Dimension(100,100));

        this.setLayout(new GridLayout(5,0));

        this.add(this.createFields(1)); //Creating name field
        this.add(this.createFields(2)); //Creating description field
        this.add(this.createFields(3)); //Creating company name field
        this.add(this.createPriceField());  //Creating price field


        this.setVisible(true);
    }
    /**
     * Method for creating text fields
     * @param flag :
     *             1 if the text field is for name
     *             2 if the text field is for description
     *             3 if the text field is for company name
     * @return JPanel
     */
    private JPanel createFields(int flag){
        HintTextField textField = new HintTextField(); //Creating the text field
        textField.setPreferredSize(new Dimension(300,50)); //Setting the size of the boxes

        //Check which field is going to be created
        if(flag==1){
            textField.setHint("Име на продукта...");
            this.name=textField;
        }else if(flag==2){
            textField.setHint("Описание на продукта...");
            this.description=textField;
        }else if(flag==3){
            textField.setHint("Име на поръчителя...");
            this.company=textField;
        }

        return setupContentLayout(textField);
    }
    /**
     * Method for creating the price field
     *
     * @return JPanel
     */
    private JPanel createPriceField(){
        NumberFormat nf = NumberFormat.getCurrencyInstance();
        nf.setCurrency(Currency.getInstance("BGN"));

        this.price = new JFormattedTextField(nf);
        this.price.setValue(0);
        this.price.setColumns(10);

        this.price.setPreferredSize(new Dimension(100,50));
        this.price.addFocusListener(new FormattedFieldListener(this.price));

        return this.setupContentLayout(this.price);
    }

    /**
     * Method for creating 2 containers so that the fields can be aligned easier
     * @param jTextField :
     *            Field to be added to the 2 containers
     * @return JPanel
     *
     */
    private JPanel setupContentLayout(JTextField jTextField){
        JPanel borderContainer = new JPanel(new BorderLayout()); //Creating border panel that will contain fieldContainer

        JPanel flowContainer = new JPanel(new FlowLayout(FlowLayout.CENTER)); //Creating the field container

        flowContainer.add(jTextField); //Adding the text field to the flow container
        borderContainer.add(flowContainer, BorderLayout.SOUTH); //Adding the the flow container in the bottom of the border container

        return borderContainer;
    }
    private JPanel createFileUpload(){

        return null;
    }


}
