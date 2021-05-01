/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.main.java.services.databaseConnector;



import src.main.java.models.RecordDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Valeri Dobrev
 */
public interface DatabaseConnector {

    /**
     * Method for adding a Record into the DB
     * @param record to be added
     * @return index of the newly added record
     * @throws SQLException when there is record with this name or null name used
    */
    int insertRecord(RecordDTO record) throws SQLException;

    /**
     * Updates a record in the DB
     * @param record to be saved
     * @return index of the updated record
     * @throws SQLException when there is record with this name or null name used
    */
    int updateRecord(RecordDTO record) throws SQLException;

    /**
     * Deletes record from the DB
     * @param index if the record to be deleted
     * @return 1 if successfully deleted and 0 if not found
     * @throws SQLException when used invalid index
    */
    int deleteRecord(long index) throws SQLException;

    /**
     * Deletes record from the DB by name
     * @param name of the record to be deleted
     * @return 1 if successfully deleted, 0 if not found
     * @throws SQLException when used null name
    */
     int deleteRecordByName(String name) throws SQLException;

    /**
     * Method that deletes all the records in the database
     * @return 1 if successful
     * @throws SQLException if error occurs
    */
    int deleteAll() throws SQLException;

     /**
      *  Method for retrieving record from the DB
      * @param index of the record to be retrieved
      * @return the record from the DB
      * @throws SQLException when used null name or record not found
     */
     RecordDTO getRecord(long index) throws SQLException;
    
     /**
      *  Method for retrieving record from the DB by name
      * @param name of the record to be retrieved
      * @return the record from the DB, null if not found
      * @throws SQLException when used null name
     */
     RecordDTO getRecordByName(String name) throws SQLException;

     /**
      * Method for retrieving all records form the DB
      * @return List of Records
      * @throws SQLException when error occurs
     */
    List<RecordDTO> getRecords() throws SQLException;

    /**
     *Method that retrieves the names of all the records
     * @return List of names
     * @throws SQLException when error occurs
    */
    List<String> getRecordsNames() throws SQLException;

    /**
     * Used for creating RecordDTO object from ResultSet
     * @param resultSet of the sql query
     * @return Record
     * @throws SQLException when failed to get field
    */
    RecordDTO serializeRecord(ResultSet resultSet)throws SQLException;

    /**
     * Method that accepts result set of the names of all records and converts it to List
     * @param set of the names
     * @return List of the names, null when couldn't access the name field
    */
    List<String> serializeNames(ResultSet set);


    /**
     * Method that closes the connection to the database
     * @throws SQLException when an error from the database occurs
    */
    void closeConnection() throws SQLException;
}
