/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clothesarchive.services.databaseConnector;

import clothesarchive.config.ConfigReader;
import clothesarchive.exceptions.CAException;
import clothesarchive.models.RecordDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Valeri Dobrev
 */
public class DatabaseConnector implements DatabaseConnectorInt{
   
    private Connection connection;
    private PreparedStatement prestatment;
    private ResultSet resultSet;
    private Statement statement;
    
    public DatabaseConnector() throws CAException{
        try
        {
            ConfigReader reader = new ConfigReader();
            String url = "jdbc:mysql://"+reader.getDomain()+":"+reader.getPort()+"/"+reader.getDatabaseName()+"?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            this.connection=DriverManager.getConnection(url,reader.getUser(), reader.getUserPassword());
        }
        catch(Exception e){
            throw new CAException(e.getMessage(),2);
        }
       
    }
    
    
    @Override
    public int addRecord(RecordDTO record) {
       if(!this.exists(record.getId())){
           return this.insertRecord(record);
       }else{
           return this.updateRecord(record);
       }
    }
    /**
     * Method for adding a Record into the DB
     * @param record
     * @return index of the newly added record
     */
    @Override
    public int insertRecord(RecordDTO record) {
        String sql = "INSERT INTO clothes(name, description, company, price, time, file) VALUES(?,?,?,?,?,?)";
        
        if(!this.exists(record.getName())){
            try{
                this.prestatment = this.connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);

                this.prestatment.setString(1,record.getName());
                this.prestatment.setString(2,record.getDescription());
                this.prestatment.setString(3,record.getCompany());
                this.prestatment.setDouble(4,record.getPrice());
                this.prestatment.setTimestamp(5, record.getDate());
                if(record.getFile()==null){
                    this.prestatment.setString(6, null);
                }else{
                    this.prestatment.setBlob(6,new javax.sql.rowset.serial.SerialBlob(record.getFile()));
                }
                int rowAffected = this.prestatment.executeUpdate();
                if(rowAffected == 1){
                    this.resultSet=this.prestatment.getGeneratedKeys();
                    if(this.resultSet.next()){
                        return this.resultSet.getInt(1);
                        
                    }
                }

            }catch(SQLException e){
                System.out.println(e);
                return -1;
            }catch(Exception e){
                return -1;
            }
        }
        return 0; 
    }

    /**
     * Updates a record in the DB
     * @param record
     * @return index of the updated record
     */
    @Override
    public int updateRecord(RecordDTO record) {
        String sql = "UPDATE clothes set name=?, description=?, company=?, price=?, time=?, file=? where id = ?";
        //Implement equals for RecordDTO(the time and the id cannot be changed)and check if the current record is equals to the returned of getByName
        //if yes then update, else show the message for changing the name of the record
        if(this.getRecordByName(record.getName()).getId() == record.getId()){
            try{
                this.prestatment = this.connection.prepareStatement(sql);
                this.prestatment.setString(1,record.getName());
                this.prestatment.setString(2,record.getDescription());
                this.prestatment.setString(3,record.getCompany());
                this.prestatment.setDouble(4,record.getPrice());
                this.prestatment.setTimestamp(5, record.getDate());
                this.prestatment.setString(6, null);
                this.prestatment.setLong(7,record.getId());

                int affectedRows = this.prestatment.executeUpdate();

                if(affectedRows==1){
                    return (int) record.getId();
                }
            }catch(SQLException e){
                System.out.println(e);
                return -1;
            }
        }
        
        return 0;
    }

    /**
     * Deletes record from the DB
     * @param index
     * @return 1 if successfully deleted and 0 if not
     */
    @Override
    public int deleteRecord(long index) {
        String sql = "DELETE FROM clothes WHERE id = ?";
        try{
            this.prestatment=this.connection.prepareStatement(sql);
            this.prestatment.setLong(1, index);
            
            int affectedRows = this.prestatment.executeUpdate();
            if(affectedRows==1){
                return 1;
            }
        }catch(SQLException e){
            System.out.println(e);
            return -1;
        }
        return 0;
    }

    /**
     *  Method for retrieving record from the DB
     * @param index
     * @return the record from the DB
     */
    @Override
    public RecordDTO getRecord(long index) {
        String sql = "Select * from clothes where id=?";
        RecordDTO record = null;
        try{
            this.prestatment = this.connection.prepareStatement(sql);
            this.prestatment.setLong(1, index);
            
            this.resultSet = this.prestatment.executeQuery();
            int i=0;
            while(this.resultSet.next()){
                if(i==0){
                    record = this.serializeRecord(this.resultSet);
                }
                i++;
                
            }
            if(i==1){
                return record;
            }else{
                return null;
            }
        }catch(SQLException e){
            System.out.println(e);
        }
        return record;
    }
    
    /**
     *  Method for retrieving record from the DB by name
     * @param name
     * @return the record from the DB
     */
    @Override
    public RecordDTO getRecordByName(String name) {
        String sql = "Select * from clothes where name=?";
        RecordDTO record = null;
        int i=0;
        try{
            this.prestatment = this.connection.prepareStatement(sql);
            this.prestatment.setString(1, name);
            
            
            this.resultSet = this.prestatment.executeQuery();
            while(this.resultSet.next()){
                if(i==0){
                    record=this.serializeRecord(this.resultSet);
                }
                i++;
                
            }
            
            if(i>=1){
                return record;
            }else{
                return null;
            }
            
        }catch(SQLException e){
            System.out.println(e);
        }
        
        return record;
    }

    /**
     * Method for retrieving all records form the DB
     * @return List of Records
     */
    @Override
    public List<RecordDTO> getRecords() {
        List<RecordDTO> records = new ArrayList();
        
        String sql = "Select * from clothes";
        
        try{
            this.prestatment = this.connection.prepareStatement(sql);
            
            this.resultSet = this.prestatment.executeQuery();
            while(this.resultSet.next()){
                records.add(this.serializeRecord(this.resultSet));
            }
        }catch(SQLException e){
            System.out.println(e);

        }
        
        return records;
    }

    /**
     * Used for creating RecordDTO object from ResultSet
     * @param resultSet
     * @return Record
     * @throws SQLException
     */
    @Override
    public RecordDTO serializeRecord(ResultSet resultSet) throws SQLException{
        RecordDTO record = new RecordDTO();
        record.setId(resultSet.getLong("id"));
        record.setName(resultSet.getString("name"));
        record.setDescription(resultSet.getString("description"));
        record.setCompany(resultSet.getString("company"));
        record.setPrice(resultSet.getLong("price"));
        record.setDate(resultSet.getTimestamp("time"));
        record.setFile(resultSet.getBytes("file"));
        return record;
    }

     /**
     * Method for checking if the record with given name already exists
     * @param name
     * @return true if yes, false - if not
     */
    @Override
    public boolean exists(String name) {
        if(this.getRecordByName(name)!= null){    
            return true;
        }
        return false;
    }

    /**
     * Method for checking if the record with given index already exists
     * @param index
     * @return true if yes, false - if not
     */
    @Override
    public boolean exists(long index){
        if(this.getRecord(index)!=null){
            return true;
        }
        return false;
    }

    @Override
    public void setup() throws CAException{
        String sql = "CREATE DATABASE IF NOT EXISTS clothesarchive charset 'utf-8';" +
                    
                    "create table if not exists clothes"+
                    "	(id int primary key auto_increment," +
                    "    name varchar(100) not null unique," +
                    "    description varchar(100)," +
                    "    company varchar(50) not null," +
                    "    price double not null," +
                    "    time timestamp," +
                    "    file longblob);";
        try{
            this.statement = this.connection.createStatement();
            this.statement.executeUpdate(sql);
        }catch(SQLException e){
            throw new CAException(e.getMessage(),2);
        }
    }


   

    
    
    
    
}
