/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clothesarchive.services.databaseConnector;



import clothesarchive.models.RecordDTO;

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
     * @return index of the newly added record, -1 if error occurs or record with that name already exists
     * @throws SQLException when there is record with this name or null name used
    */
    int insertRecord(RecordDTO record) throws SQLException;

    /**
     * Updates a record in the DB
     * @param record to be saved
     * @return index of the updated record, -1 if error occurs
     * @throws SQLException when there is record with this name or null name used
    */
    int updateRecord(RecordDTO record) throws SQLException; //FIXME might need to pass the index as parameter

    /**
     * Deletes record from the DB
     * @param index if the record to be deleted
     * @return 1 if successfully deleted and 0 if not found, -1 if not valid index or error occurs
     * @throws SQLException when used invalid index
    */
    int deleteRecord(long index) throws SQLException;

    /**
     * Deletes record from the DB by name
     * @param name of the record to be deleted
     * @return 1 if successfully deleted, 0 if not found, -1 if the name is invalid
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
      * @return the record from the DB, null if not found or error occurs
      * @throws SQLException when used null name or record not found
     */
     RecordDTO getRecordByName(String name) throws SQLException;

     /**
      * Method for retrieving all records form the DB
      * @return List of Records
      * @throws SQLException when error occures
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
     * @return List of the names
     * @throws SQLException when couldn't access the name field
    */
    List<String> serializeNames(ResultSet set) throws SQLException;



    /**
     * Method that closes the connection to the database
     * @throws SQLException when an error from the database occurs
    */
    void closeConnection() throws SQLException;
}
