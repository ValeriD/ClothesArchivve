package clothesarchive.gui.custom.customFields;

import clothesarchive.gui.custom.customSettings.CAFonts;

import javax.swing.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class CAHintTextField extends JFormattedTextField implements FocusListener {
    private String hint;

    public CAHintTextField(){
        super();
        super.addFocusListener(this);
    }

    @Override
    public void focusGained(FocusEvent e) {
        super.setFont(CAFonts.TextBoxTextFont());
        if(this.getText().contains(this.hint)) {
            this.setText("");
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if(this.getText().isEmpty()){
            this.showHint();
        }
    }
    public void setHint(String hint){
        this.hint = hint;
        this.showHint();

    }
    public void showHint(){
        super.setFont(CAFonts.HintFont());
        this.setText(hint);
    }

}
