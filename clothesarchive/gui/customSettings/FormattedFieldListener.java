package clothesarchive.gui.customSettings;

import javax.swing.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class FormattedFieldListener implements FocusListener {
    JTextField field;
    public FormattedFieldListener(JTextField field){
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
}
