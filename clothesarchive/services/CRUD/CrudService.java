/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clothesarchive.services.CRUD;

import clothesarchive.exceptions.CAException;
import clothesarchive.models.RecordDTO;
import java.io.File;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Valeri Dobrev
 */
public interface CrudService {
     /**
     * Method for handling the save action
     * @param name 
     * @param description
     * @param company
     * @param price
     * @param file
     * @throws CAException
     */
    public void saveRecord(String name, String description, String company, Double price, byte[] file) throws CAException;
    
     /**
     * Method for serializing the data from the add menu
     * @param name 
     * @param description
     * @param company
     * @param price
     * @param file
     * @param record
     * @throws CAException
     */
    public void serializeRecord(String name, String description, String company, Double price, byte[] file, RecordDTO record)throws Exception;
    
    /**
      * Method for serializing the data from the add menu
      * @param name
      * @throws CAException
     */
    public void deleteRecord(String name) throws CAException;
    
    public RecordDTO getRecordByName(String name);

}
