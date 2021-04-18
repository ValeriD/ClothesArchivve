package clothesarchive.gui.custom.customFields;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class CAFormattedFieldListener implements FocusListener, DocumentListener {
    JTextField field;
    public CAFormattedFieldListener(JTextField field){
        this.field = field;
    }

    @Override
    public void focusGained(FocusEvent e) {
        if(this.field instanceof JFormattedTextField) {
            this.field.setText("");
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if(this.field instanceof JFormattedTextField){
            String s = "BGN"+this.field.getText();
            this.field.setText(s);
        }
    }
    @Override
    public void removeUpdate(DocumentEvent e) {
        this.field.setText("BGN0.00");
    }

    @Override
    public void insertUpdate(DocumentEvent e) {

    }
    @Override
    public void changedUpdate(DocumentEvent e) {

    }
}
