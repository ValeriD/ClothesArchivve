/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clothesarchive.exceptions;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author valeri
 */
public class CAException extends Exception{
    private int flag;
    
    public CAException(String msg, int flag){
        super(msg);
        this.flag = flag;
    }
    
    public int getFlag(){
        return flag;
    }

    public void show(JPanel panel){
        if(this.flag==0){
            JOptionPane.showMessageDialog (panel, this.getMessage(), "Information", JOptionPane.INFORMATION_MESSAGE);
            System.out.println(this.getMessage());
        }else if(this.flag==1){
            JOptionPane.showMessageDialog(panel, this.getMessage(), "Warning!", JOptionPane.WARNING_MESSAGE);
        }else if(this.flag==2){
            JOptionPane.showMessageDialog(panel, this.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
