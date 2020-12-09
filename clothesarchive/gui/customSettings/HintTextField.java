package clothesarchive.gui.customSettings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class HintTextField extends JFormattedTextField implements FocusListener {
    private String hint;

    public HintTextField(){
        super();
        super.addFocusListener(this);

    }


    @Override
    public void focusGained(FocusEvent e) {
        super.setFont(CustomFonts.TextBoxTextFont());
        if(this.getText().contains(this.hint)) {
            this.setText("");
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if(this.getText().isEmpty()){
            super.setText(hint);
            super.setFont(CustomFonts.HintFont());
        }
    }
    public void setHint(String hint){
        this.hint = hint;
        super.setFont(CustomFonts.HintFont());
        this.setText(hint);
    }
}
