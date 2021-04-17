package clothesarchive.gui.panels.mainContent;

import clothesarchive.gui.panels.headings.Heading;
import clothesarchive.models.RecordDTO;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MainMenuFields extends JPanel {

    JList<RecordDTO> jList;
    public MainMenuFields(List<RecordDTO> records){
        this.setLayout(new BorderLayout());
        DefaultListModel<RecordDTO> listModel = new DefaultListModel<>();

        for(RecordDTO record: records){
            listModel.addElement(record);
        }

        jList = new JList<RecordDTO>(listModel);

        jList.setCellRenderer(new MainContentListRenderer());
        jList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        jList.setFixedCellHeight(40);

        jList.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        JPanel listContainer= new JPanel(new BorderLayout(10,10));
        listContainer.add(new JScrollPane(jList), BorderLayout.CENTER);
        this.add(listContainer);
        this.setVisible(true);
    }

    public RecordDTO getSelectedItem(){
        return jList.getSelectedValue();
    }
}
