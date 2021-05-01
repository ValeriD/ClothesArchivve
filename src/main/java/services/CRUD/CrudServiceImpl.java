/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.main.java.services.CRUD;

import src.main.java.exceptions.CAException;
import src.main.java.models.RecordDTO;
import src.main.java.services.databaseConnector.DatabaseConnectorImpl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Valeri Dobrev
 */
public class CrudServiceImpl implements CrudService{
    private final DatabaseConnectorImpl connector;

    //------------------------------------------------------------------------------------------------------------------
    public CrudServiceImpl() throws CAException{
        this.connector = new DatabaseConnectorImpl();
    }

    //------------------------------------------------------------------------------------------------------------------
    @Override
    public void saveRecord(String name, String description, String company, Double price, byte[] file, int flag, String... oldName) throws CAException {
        RecordDTO record;
        try{
            //Both validating and creating the record based on the given information
            record = this.serializeRecord(name, description, company, price,file);
        }catch(Exception e){
            e.printStackTrace();
            throw new CAException(e.getMessage(), 0);
        }

        try {
            if (flag == 1) {
                if(exists(name)){
                    throw new CAException("Запис с такова име вече съществува!",1);
                }
                this.connector.insertRecord(record);

            } else if(oldName.length==1){                                       //Checking if the oldName parameter is passed
                RecordDTO oldRecord = connector.getRecordByName(oldName[0]);    //Get the record that we want to update
                if(oldRecord==null){
                    throw new CAException("Запис с такова име не съществува!",2);
                }

                if(!oldRecord.getName().equals(name)){                             //Checks if the name is changed
                    if(exists(name)){                                           //If changed check if the new name already exists
                        throw new CAException("Запис с такова име вече съществува!",1);
                    }
                }

                record.setId(oldRecord.getId());
                this.connector.updateRecord(record);                    //Else we just update the record
            }
        }catch (SQLException e){
            throw new CAException("Грешка в базата от данни при запазване на данните!", 2);
        }

    }
    //------------------------------------------------------------------------------------------------------------------
    @Override
    public RecordDTO serializeRecord(String name, String description, String company, Double price, byte[] file) throws Exception{

        RecordDTO record = new RecordDTO();
        if (!name.equals("")) {
            record.setName(name);
        } else {
            throw new Exception("Моля въведете наименование!");
        }
        record.setDescription(description);
        if (!company.equals("")) {
            record.setCompany(company);
        } else {
            throw new Exception("Моля въведете поръчител!");
        }
        if (price>0.0) {
            record.setPrice(price);
        } else {
            throw new Exception("Моля въведете цена!");
        }
        record.setDate(new Timestamp(System.currentTimeMillis()));
        record.setFile(file);
        return record;
    }

    //------------------------------------------------------------------------------------------------------------------
    @Override
    public int deleteRecord(String name) throws CAException {
        int isDeleted;
        try {
            isDeleted = this.connector.deleteRecordByName(name);
        } catch (SQLException sqlException) {
            throw new CAException("Имаето на записът има стойност NULL",2);
        }
        return isDeleted;
    }

    //------------------------------------------------------------------------------------------------------------------
    public void deleteAllRecords() throws SQLException {
        connector.deleteAll();
    }

    //------------------------------------------------------------------------------------------------------------------
    @Override
    public RecordDTO getRecordByName(String name) throws CAException {
        RecordDTO record;

        try {
            record = connector.getRecordByName(name);
        } catch (SQLException sqlException) {
            throw new CAException("Стойността за име е NULL!",2);
        }
        return record;
    }

    //------------------------------------------------------------------------------------------------------------------
    @Override
    public List<RecordDTO> getRecords() throws CAException {
        ArrayList<RecordDTO> records;
        try{
           records = (ArrayList<RecordDTO>) this.connector.getRecords();
        }catch (SQLException e){
            throw new CAException("Грешка в базата при вземането на всички записи!",2);
        }
        return records;
    }

    //------------------------------------------------------------------------------------------------------------------
    @Override
    public boolean exists(String name) throws CAException {
        try {
            if(this.connector.getRecordByName(name)!=null){
                return true;
            }
        } catch (SQLException sqlException) {
            throw new CAException("Грешка в базата от данни или Име със стойност NULL!",2);
        }
        return false;
    }

    //------------------------------------------------------------------------------------------------------------------
    public void closeDatabaseConnection() throws CAException {
        try {
            this.connector.closeConnection();
        } catch (SQLException sqlException) {
            throw new CAException("Unable to close the connection!", 1);
        }
    }
}
