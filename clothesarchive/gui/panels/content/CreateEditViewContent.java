package clothesarchive.gui.panels.content;

import clothesarchive.exceptions.CAException;
import clothesarchive.gui.custom.customFields.CADescriptionField;
import clothesarchive.gui.custom.customFields.CAHintTextField;
import clothesarchive.gui.custom.customFields.CAFormattedFieldListener;
import clothesarchive.gui.custom.customSettings.*;
import clothesarchive.gui.custom.pictures.CAImageResizer;
import clothesarchive.gui.custom.pictures.CAPictureFilter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.NumberFormat;
import java.util.Currency;


public class CreateEditViewContent extends JPanel implements ActionListener {
    CAHintTextField name;
    CADescriptionField description;
    CAHintTextField company;
    JFormattedTextField price;
    File file = null;
    JButton selectFileButton;

    JLabel image;
    JFileChooser fileChooser;
    JTextField filePath;


    public CreateEditViewContent(){
        this.setLayout(new GridLayout(0,2));
        this.add(generateFields());
        this.add(generateImageField());
        this.setVisible(true);
    }

    /**
     * Method that generates the Image component
     * @return
     */
    private JPanel generateImageField() {
        JPanel imageContainer = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        this.image = new JLabel();
        try {
            this.setImage();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.addObjectToGridBag(this.image, imageContainer, gbc, 1,1);
        return imageContainer;
    }

    /**
     * Method that generates the fields
     * @return JPanel with the fields
     */
    private JPanel generateFields(){
        GridBagConstraints gbc = new GridBagConstraints();
        JPanel fieldsContainer = new JPanel(new GridBagLayout());

        gbc.insets = new Insets(10,10,10,10);

        this.addObjectToGridBag(this.createFields(1), fieldsContainer, gbc, 1,1 );
        this.addObjectToGridBag(this.createFields(2), fieldsContainer, gbc, 1,2 );
        this.addObjectToGridBag(this.createFields(3), fieldsContainer, gbc, 1,3 );
        this.addObjectToGridBag(this.createPriceField(), fieldsContainer, gbc, 1,4 );
        this.addObjectToGridBag(this.createFileUpload(), fieldsContainer, gbc, 1,5 );
        return fieldsContainer;
    }

    /**
     * Methods that adds component to GridBag panel
     * @param component to be added
     * @param container to be added to
     * @param gbc instance of GridBagConstraints that is used to set the position of the element
     * @param gridx x position
     * @param gridy y posiotion
     * @param gridwidth how wide the element to be
     * @param gridheight how high the element to be
     */
    private void addObjectToGridBag(Component component, Container container, GridBagConstraints gbc, int gridx, int gridy, int gridwidth, int gridheight){
        gbc.gridx = gridx;
        gbc.gridy = gridy;

        gbc.gridheight = gridheight;
        gbc.gridwidth = gridwidth;

        container.add(component, gbc);

    }
    private void addObjectToGridBag(Component component, Container container, GridBagConstraints gbc, int gridx, int gridy) {
        this.addObjectToGridBag(component,container, gbc,gridx,gridy, 1,1);
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
        CAHintTextField textField = new CAHintTextField(); //Creating the text field
        textField.setPreferredSize(new Dimension(300,35)); //Setting the size of the boxes

        //Check which field is going to be created
        if(flag==1){
            textField.setHint("Име на продукта...");
            this.name=textField;
        }else if(flag==2){
            CADescriptionField descriptionField = new CADescriptionField();
            descriptionField.setPreferredSize(new Dimension(300, 105));
            descriptionField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            descriptionField.setHint("Описание на продукта...");
            this.description=descriptionField;
            return setupContentLayout(new JScrollPane(descriptionField,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER),null);
        }else if(flag==3){
            textField.setHint("Име на поръчителя...");
            this.company=textField;
        }

        return setupContentLayout(textField, null);
    }

    /**
     * Method for creating 2 containers so that the fields and button can be aligned one next to another
     * @param jTextField :
     *            Field to be added to the 2 containers
     * @param button:
     *              Button to be added
     * @return JPanel
     *
     */
    private JPanel setupContentLayout(Component jTextField,JButton button){
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
        this.price.setFont(CAFonts.TextBoxTextFont());

        this.price.setPreferredSize(new Dimension(100,35));
        this.price.addFocusListener(new CAFormattedFieldListener(this.price));

        return this.setupContentLayout(this.price, null);
    }


    /**
     * Method for creating the upload files field an button
     *
     * @return JPanel
     */
    private JPanel createFileUpload(){
        this.selectFileButton = new JButton("Избор на файл...");
        this.filePath = new JTextField();

        this.filePath.setPreferredSize(new Dimension(200,35));
        this.filePath.setAutoscrolls(true);
        this.filePath.setFocusable(false);


        selectFileButton.setPreferredSize(new Dimension(100,35));

        selectFileButton.addActionListener(this);

        this.fileChooser=new JFileChooser();
        this.fileChooser.setFileFilter(new CAPictureFilter());
        this.fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

        return this.setupContentLayout(filePath, selectFileButton);
    }

    /**
     * Method handling the chooseFile button
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        this.file = null; //setting the file bytes to null
        int returnVal = fileChooser.showOpenDialog(this); //Setting the opening dialog
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            this.file = fileChooser.getSelectedFile();  //Getting the selected file
            this.filePath.setText(file.getAbsolutePath());  //Setting the file path to the text field
            try {
                this.setImage();
            } catch (IOException ioException) {
                new CAException("Проблем при преоразмеряването на снимката!", 2).show(this);
            }
        } else {
            System.out.println("File access cancelled by user.");
        }
    }

    /**
     * Method called when we want to visualize the record in the UI
     * @param name
     * @param description
     * @param company
     * @param price
     * @param file
     * @param focusable true if Edit menu, false if in View menu
     */
    public void setFields(String name, String description, String company, double price, byte[] file, boolean focusable){
        this.name.setFont(CAFonts.TextBoxTextFont());
        this.company.setFont(CAFonts.TextBoxTextFont());
        this.description.setFont(CAFonts.TextBoxTextFont());
        this.price.setFont(CAFonts.TextBoxTextFont());

        setName(name);
        setDescription(description);
        setCompany(company);
        setPrice(price);
        try {
            setFile(file);

        } catch (CAException e) {
            e.show(this);
        }
        try {
            setImage();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.name.setFocusable(focusable);
        this.description.setFocusable(focusable);
        this.company.setFocusable(focusable);
        this.price.setFocusable(focusable);
        this.selectFileButton.setEnabled(focusable);
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

    public byte[] getFile() throws CAException {
        if(this.file == null){
            return null;
        }
        try {
            System.out.println(this.file.toPath());
            return Files.readAllBytes(this.file.toPath());
        } catch (IOException e) {
            throw new CAException("Неуспешно запазване на снимката!", 1);
        }
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

    /**
     * Visualizing the record image
     * @throws IOException
     */
    public void setImage() throws IOException {
        if(file!=null){
            this.image.setText("");
            this.image.setIcon(CAImageResizer.resize(file));
        }else{
            this.image.setText("No image");
        }
    }

    /**
     * This method is used when we open a menu and we want to set the blob file from the database
     * @param file
     * @throws CAException if the program fails to read the image
     */
    public void setFile(byte[] file) throws CAException {
        if(file!=null) {
            try {
                this.file = new File("static/icons/temp.png");
                OutputStream os = new FileOutputStream(this.file);
                os.write(file);
                os.close();
            } catch (IOException | NullPointerException exception) {
                this.file = null;
                throw new CAException("Неуспешно зареждане на снимката", 1);
            }
        }else{
            this.file = null;
        }

    }
}
