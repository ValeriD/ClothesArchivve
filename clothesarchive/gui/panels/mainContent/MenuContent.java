package clothesarchive.gui.panels.mainContent;

import clothesarchive.MainScreen;
import clothesarchive.gui.customSettings.CustomFonts;
import clothesarchive.gui.customSettings.HintTextField;
import clothesarchive.gui.customSettings.FormattedFieldListener;
import customFilters.CustomPictureFilter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MenuContent extends JPanel implements ActionListener {
    HintTextField name;
    HintTextField description;
    HintTextField company;
    JFormattedTextField price;
    byte[] file;
    JFileChooser fileChooser;
    JTextField filePath;

    public MenuContent(){
        this.setPreferredSize(new Dimension(100,100));
        this.setLayout(new GridLayout(7,0));

        this.add(this.createFields(1)); //Creating name field
        this.add(this.createFields(2)); //Creating description field
        this.add(this.createFields(3)); //Creating company name field
        this.add(this.createPriceField());  //Creating price field
        this.add(this.createFileUpload());  //Creating file upload button


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

        return setupContentLayout(textField, null);
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
        this.price.setFont(CustomFonts.TextBoxTextFont());

        this.price.setPreferredSize(new Dimension(100,50));
        this.price.addFocusListener(new FormattedFieldListener(this.price));

        return this.setupContentLayout(this.price, null);
    }

    /**
     * Method for creating 2 containers so that the fields can be aligned easier
     * @param jTextField :
     *            Field to be added to the 2 containers
     * @param button:
     *              Button to be added
     * @return JPanel
     *
     */
    private JPanel setupContentLayout(JTextField jTextField,JButton button){
        JPanel borderContainer = new JPanel(new BorderLayout()); //Creating border panel that will contain fieldContainer
        JPanel flowContainer = new JPanel(new FlowLayout(FlowLayout.CENTER)); //Creating the field container

        flowContainer.add(jTextField); //Adding the text field to the flow container
        if(button!=null) {
            flowContainer.add(button); //Adding button if provided
        }
        borderContainer.add(flowContainer, BorderLayout.SOUTH); //Adding the the flow container in the bottom of the border container

        return borderContainer;
    }

    /**
     * Method for creating the upload files field an button
     *
     * @return JPanel
     */
    private JPanel createFileUpload(){
        JButton selectButton = new JButton("Избор на файл...");
        this.filePath = new JTextField();

        this.filePath.setPreferredSize(new Dimension(400,50));
        this.filePath.setAutoscrolls(true);


        selectButton.setPreferredSize(new Dimension(100,45));

        selectButton.addActionListener(this);

        this.fileChooser=new JFileChooser();
        this.fileChooser.setFileFilter(new CustomPictureFilter());
        this.fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

        return this.setupContentLayout(filePath, selectButton);
    }

    /**
     * Method handling the chooseFile button
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        this.file = null; //setting the file bytes to null
        int returnVal = fileChooser.showOpenDialog(this); //Setting the opening dialog
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();  //Getting the selected file
            this.filePath.setText(file.getAbsolutePath());  //Setting the file path to the text field

            try {
                this.file = Files.readAllBytes(file.toPath()); //Converting the file to bytes[]
            } catch (IOException ex) {
                Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("File access cancelled by user.");
        }
    }
    /**
     * Method for clearing all the fields
     */
    public void clearAllFields(){
        this.filePath.setText("");
        this.price.setValue(0);
        this.company.showHint();
        this.description.showHint();
        this.name.showHint();
        this.file=null;
    }

    /**
     * Getters for the fields that are needed for the backend
     */
    public String getNameFromField() {
        if(!name.getText().contains("Име на продукта...")){
            return name.getText();
        }
        return "";
    }

    public String getDescription() {
        return description.getText();
    }

    public String getCompany() {
        if(!company.getText().contains("Име на поръчителя...")) {
            return company.getText();
        }
        return "";
    }

    public Double getPrice() {
        String price = this.price.getText().split("N")[1];
        return Double.parseDouble(price);
    }

    public byte[] getFile() {
        return file;
    }


    /**
     * Setters for the fields
     */
    public void setName(String name) {
        this.name.setText(name);
    }

    public void setDescription(String description) {
        this.description.setText(description);
    }

    public void setCompany(String company) {
        this.company.setText(company);
    }

    public void setPrice(Double price) {
        this.price.setValue(price);
    }

    public void setFile(byte[] file) {
        this.file = file;
    }
}
