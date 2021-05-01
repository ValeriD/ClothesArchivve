package src.main.java.gui.panels.content;

import src.main.java.exceptions.CAException;
import src.main.java.models.DateComparator;
import src.main.java.models.RecordDTO;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.util.List;

public class MainMenuContent extends JPanel {

    JList<RecordDTO> jList;

    public MainMenuContent(List<RecordDTO> records){
        this.setLayout(new BorderLayout());
        DefaultListModel<RecordDTO> listModel = new DefaultListModel<>();
        records.sort(new DateComparator());
        listModel.addAll(records);

        jList = new JList<>(listModel);

        jList.setCellRenderer(new MainContentListRenderer());
        jList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        jList.setFixedCellHeight(40);

        JScrollPane scrollPane = new JScrollPane(jList);
        scrollPane.setColumnHeaderView(createHeadings());

        this.setBorder(BorderFactory.createEmptyBorder(10,0,10,0));

        this.add(scrollPane);
        this.requestFocus();
        this.setVisible(true);
    }

    public RecordDTO getSelectedItem() throws CAException {
        if(jList.isSelectionEmpty()){
            throw new CAException("Моля изберете запис",0);
        }
        return jList.getSelectedValue();
    }

    private JPanel createHeadings(){
        JPanel headings = new JPanel(new GridLayout(0,3));

        JLabel name = new JLabel("Име на записа", JLabel.CENTER);
        name.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

        JLabel company = new JLabel("Поръчител", JLabel.CENTER);
        company.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

        JLabel date = new JLabel("Последна промяна", JLabel.CENTER);
        date.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

        headings.add(name);
        headings.add(company);
        headings.add(date);

        return headings;
    }
}
