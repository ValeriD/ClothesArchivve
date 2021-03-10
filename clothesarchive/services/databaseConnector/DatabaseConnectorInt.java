/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clothesarchive.services.databaseConnector;



import clothesarchive.exceptions.CAException;
import clothesarchive.models.RecordDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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
     int addRecord(RecordDTO record);
    
     /**
     * Method for adding a Record into the DB
     * @param record
     * @return index of the newly added record
     */
     int insertRecord(RecordDTO record);
    /**
     * Updates a record in the DB
     * @param record
     * @return index of the updated record
     */
    int updateRecord(RecordDTO record);
    
     /**
     * Deletes record from the DB
     * @param index
     * @return 1 if successfully deleted and 0 if not
     */
     int deleteRecord(long index);
    
     /**
     *  Method for retrieving record from the DB
     * @param index
     * @return the record from the DB
     */
     RecordDTO getRecord(long index);
    
     /**
     *  Method for retrieving record from the DB by name
     * @param name
     * @return the record from the DB
     */
     RecordDTO getRecordByName(String name);
    /**
     * Method for retrieving all records form the DB
     * @return List of Records
     */
    List<RecordDTO> getRecords();
   
    /**
     * Used for creating RecordDTO object from ResultSet
     * @param resultSet
     * @return Record
     * @throws SQLException
     */
    RecordDTO serializeRecord(ResultSet resultSet)throws SQLException;
    
    /**
     * Method for checking if the record with given name already exists
     * @param name
     * @return true if yes, false - if not
     */
    boolean exists(String name);
    
    /**
     * Method for checking if the record with given index already exists
     * @param index
     * @return true if yes, false - if not
     */
    boolean exists(long index);
    
    long getIdByName(String name);
}
