/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clothesarchive.exceptions;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.*;

/**
 *
 * @author valeri
 */
public class CAException extends Exception{
    private final int flag;
    
    public CAException(String msg, int flag){
        super(msg);
        this.flag = flag;
    }
    
    public int getFlag(){
        return flag;
    }

    public void show(Component parent){
        if(this.flag==0){
            JOptionPane.showMessageDialog (parent, this.getMessage(), "Information", JOptionPane.INFORMATION_MESSAGE);
        }else if(this.flag==1){
            JOptionPane.showMessageDialog(parent, this.getMessage(), "Warning!", JOptionPane.WARNING_MESSAGE);
        }else if(this.flag==2){
            JOptionPane.showMessageDialog(parent, this.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
