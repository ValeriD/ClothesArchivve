package clothesarchive.gui.panels.content;

import clothesarchive.gui.custom.customSettings.CAFonts;
import clothesarchive.models.RecordDTO;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;

public class MainContentListRenderer extends JPanel implements ListCellRenderer<RecordDTO> {

    JLabel name;
    JLabel date;
    JLabel company;

    public MainContentListRenderer(){
        this.setLayout(new GridLayout(0,3));

        this.name = new JLabel("", JLabel.CENTER);
        name.setFont(CAFonts.TextBoxTextFont());
        name.setBorder(BorderFactory.createMatteBorder(0,0,0,1,Color.GRAY));


        this.company = new JLabel("", JLabel.CENTER);
        company.setFont(CAFonts.TextBoxTextFont());
        company.setBorder(BorderFactory.createMatteBorder(0,0,0,1,Color.GRAY));

        this.date = new JLabel("", JLabel.CENTER);
        this.date.setFont(CAFonts.TextBoxTextFont());

        this.add(name );
        this.add(company);
        this.add(date);

    }
    @Override
    public Component getListCellRendererComponent(JList<? extends RecordDTO> list, RecordDTO value, int index, boolean isSelected, boolean cellHasFocus) {

        name.setText(value.getName());
        company.setText(value.getCompany());
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        date.setText(outputFormat.format(value.getDate()));

        if(isSelected){
            changeColor(list.getSelectionBackground());
        }else{
            if(index%2==0){
                changeColor(Color.decode("#d7d9d7"));
            }else{
                changeColor(Color.WHITE);
            }

        }
        return this;
    }

    private void changeColor(Color c){
        name.setBackground(c);
        date.setBackground(c);
        company.setBackground(c);
        this.setBackground(c);
    }
}
