/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clothesarchive.services.CRUD;

import clothesarchive.exceptions.CAException;
import clothesarchive.models.RecordDTO;
import clothesarchive.services.databaseConnector.DatabaseConnector;

import java.sql.Timestamp;

/**
 *
 * @author Valeri Dobrev
 */
public class CrudServiceImpl implements CrudService{
    
    private DatabaseConnector connector;
    
    
    public CrudServiceImpl() throws CAException{
        this.connector = new DatabaseConnector();
    }

     /**
     * Method for handling the save action
     * @throws CAException
     */
    @Override
    public void saveRecord(String name, String description, String company, Double price, byte[] file) throws CAException{
        RecordDTO record = new RecordDTO();
        try{
            this.serializeRecord(name, description, company, price,file, record);
        }catch(Exception e){
            throw new CAException(e.getMessage(), 0);            
        }
        
        int flag = this.connector.addRecord(record);
        //Handling if the record is successfully inserted into the databse
        if (flag < 0) {
            throw new CAException("Couldn't save the record in the database", 2);            
        } else if (flag == 0) {
            throw new CAException("Моля сменете наименованието на записа!", 1);
        } 

    }

     /**
      * Method for serializing the data from the add menu
      * @throws CAException
     */
    @Override
    public void serializeRecord(String name, String description, String company, Double price, byte[] file, RecordDTO record) throws Exception{
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
        if (price!=0.0) {
            record.setPrice(price);
        } else {
            throw new Exception("Моля въведете цена!");
        }
        record.setDate(new Timestamp(System.currentTimeMillis()));
        record.setFile(file);          
    }

    /**
      * Method for serializing the data from the add menu
      * @param name
      * @throws CAException
     */
    @Override
    public void deleteRecord(String name) throws CAException {
        RecordDTO record = this.connector.getRecordByName(name);
       
        int flag;
        flag = this.connector.deleteRecord(record.getId());
        if(flag==1){
            throw new CAException("Успешно изтриване на запис!",0);
        }else {
            throw new CAException("Неуспешно изтриване на запис",2);
        }
    }
}
