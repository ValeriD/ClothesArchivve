/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clothesarchive.services.CRUD;

import clothesarchive.exceptions.CAException;
import clothesarchive.models.RecordDTO;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


/**
 *
 * @author Valeri Dobrev
 */
public interface CrudService {

     /**
     * Method for handling the save action
     * @param name: the unique name of the product
     * @param description: description of the product
     * @param company: the name of the sponsor
     * @param price: the price of the product
     * @param file:file to be saved
     * @param flag: 1 for insert, 0 for update
     * @throws CAException if flag==1:
      *                         if record with that name exists
      *                    if flag==0:
      *                         if the name of the record is changed to already existing name
      *                         if record with the given id is not found
     */
     void saveRecord(String name, String description, String company, Double price, byte[] file, int flag, String... oldName) throws CAException;

     /**
     * Method for serializing the data from the add menu
      * @param name: the unique name of the product
      * @param description: description of the product
      * @param company: the name of the sponsor
      * @param price: the price of the product
      * @param file:file to be saved
      * @throws Exception when one of the parameters are invalid
     */
     RecordDTO serializeRecord(String name, String description, String company, Double price, byte[] file)throws Exception;
    
    /**
      * Method for serializing the data from the add menu
      * @param name of the record to be deleted
      * @return int 1 if successfully deleted, 0 if record with this name is not found
      * @throws CAException when null name is used
     */
    int deleteRecord(String name) throws CAException;

    /**
     * Method that deletes all the records in the database
     * @throws SQLException when error occurs
     */
    void deleteAllRecords() throws SQLException;


    /**
     * Method that checks if the record already exists in the database by given name
     * @param name of the record
     * @return true if exists, false otherwise
     * @throws CAException when the name is null
     */
    boolean exists(String name) throws CAException;

    /**
     * Method that gets the record by given name
     * @param name of the record
     * @return RecordDTO if found, null otherwise
     * @throws CAException when null name is used
     */
    RecordDTO getRecordByName(String name) throws CAException;

    /**
     * Method that gets all the records from the database
     * @return list of the records
     * @throws CAException when error with the database occurs
     */
    List<RecordDTO> getRecords() throws CAException;

    /**
     * Method that calls the DatabaseConnector's method closeConnection()
     * @throws CAException when there is error from the connector
     */
    void closeDatabaseConnection() throws CAException;

}
