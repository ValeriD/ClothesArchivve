package clothesarchive.gui.panels.mainContent;

import clothesarchive.gui.customSettings.CustomFonts;
import clothesarchive.models.RecordDTO;

import javax.swing.*;
import java.awt.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class MainContentListRenderer extends JPanel implements ListCellRenderer<RecordDTO> {

    JLabel name;
    JLabel date;

    public MainContentListRenderer(){
        this.setLayout(new GridLayout(0,3));

        this.name = new JLabel();
        name.setFont(CustomFonts.TextBoxTextFont());

        name.setHorizontalTextPosition(SwingConstants.CENTER);
        this.date = new JLabel();
        this.date.setFont(CustomFonts.TextBoxTextFont());



        this.add(name );
        this.add(new JSeparator(SwingConstants.VERTICAL));
        this.add(date);

    }
    @Override
    public Component getListCellRendererComponent(JList<? extends RecordDTO> list, RecordDTO value, int index, boolean isSelected, boolean cellHasFocus) {

        name.setText(value.getName());


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
