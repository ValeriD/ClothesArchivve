/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clothesarchive.services;

import clothesarchive.exceptions.CAException;
import clothesarchive.models.RecordDTO;
import java.util.List;
import java.sql.*; 

/**
 *
 * @author Valeri Dobrev
 */
public interface DatabaseConnectorInt {
    
     /**
     * Method for deciding whether record should be inserted or updated
     * @param record
     * @return index of the newly added record
     */
    public int addRecord(RecordDTO record);
    
     /**
     * Method for adding a Record into the DB
     * @param record
     * @return index of the newly added record
     */
    public int insertRecord(RecordDTO record);
    /**
     * Updates a record in the DB
     * @param record
     * @return index of the updated record
     */
    public int updateRecord(RecordDTO record);
    
     /**
     * Deletes record from the DB
     * @param index
     * @return 1 if successfully deleted and 0 if not
     */
    public int deleteRecord(long index);
    
     /**
     *  Method for retrieving record from the DB
     * @param index
     * @return the record from the DB
     */
    public RecordDTO getRecord(long index);
    
     /**
     *  Method for retrieving record from the DB by name
     * @param name
     * @return the record from the DB
     */
    public RecordDTO getRecordByName(String name);
    /**
     * Method for retrieving all records form the DB
     * @return List of Records
     */
    public List<RecordDTO> getRecords();
   
    /**
     * Used for creating RecordDTO object from ResultSet
     * @param resultSet
     * @return Record
     * @throws SQLException
     */    
    public RecordDTO serializeRecord(ResultSet resultSet)throws SQLException;
    
    /**
     * Method for checking if the record with given name already exists
     * @param name
     * @return true if yes, false - if not
     */
    public boolean exists(String name);
    
    /**
     * Method for checking if the record with given index already exists
     * @param index
     * @return true if yes, false - if not
     */
    public boolean exists(long index);
    
    public void setup()throws CAException;
    
}
