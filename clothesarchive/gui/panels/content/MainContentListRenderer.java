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
        this.setLayout(new GridLayout(0,5));

        this.name = new JLabel("", JLabel.CENTER);
        name.setFont(CAFonts.TextBoxTextFont());

        this.company = new JLabel("", JLabel.CENTER);
        company.setFont(CAFonts.TextBoxTextFont());

        this.date = new JLabel("", JLabel.CENTER);
        this.date.setFont(CAFonts.TextBoxTextFont());



        this.add(name );
        this.add(new JSeparator(SwingConstants.VERTICAL));
        this.add(company);
        this.add(new JSeparator(SwingConstants.VERTICAL));
        this.add(date);

    }
    @Override
    public Component getListCellRendererComponent(JList<? extends RecordDTO> list, RecordDTO value, int index, boolean isSelected, boolean cellHasFocus) {

        name.setText(value.getName());
        company.setText(value.getCompany());
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        date.setText(outputFormat.format(value.getDate()));

        if(isSelected){
            name.setBackground(list.getSelectionBackground());
            date.setBackground(list.getSelectionBackground());
            setBackground(list.getSelectionBackground());
        }else{
            name.setBackground(list.getBackground());
            date.setBackground(list.getBackground());
            setBackground(list.getBackground());
        }
        return this;
    }
}
