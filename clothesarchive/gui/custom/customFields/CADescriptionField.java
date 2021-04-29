package clothesarchive.gui.custom.customFields;

import clothesarchive.gui.custom.customSettings.CAFonts;

import javax.swing.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class CADescriptionField extends JTextArea implements FocusListener {
    String hint;

    public CADescriptionField() {
        super();
        super.addFocusListener(this);
    }

    @Override
    public String getText() {
        if(super.getText().contains(this.hint)){
            return "";
        }
        return super.getText();
    }

    @Override
    public void focusGained(FocusEvent e) {
        super.setFont(CAFonts.TextBoxTextFont());
        if(super.getText().contains(this.hint)) {
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
