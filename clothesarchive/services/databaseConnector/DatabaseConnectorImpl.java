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
public class DatabaseConnectorImpl implements DatabaseConnector {

    private final Connection connection;
    private PreparedStatement prestatment;
    private ResultSet resultSet;

    //------------------------------------------------------------------------------------------------------------------
    public DatabaseConnectorImpl() throws CAException{
        try
        {
            ConfigReader reader = new ConfigReader();
            String url = "jdbc:mysql://"+reader.getDomain()+":"+reader.getPort()+"/"+reader.getDatabaseName()+"?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Europe/Athens";
            this.connection=DriverManager.getConnection(url,reader.getUser(), reader.getUserPassword());
        }
        catch(Exception e){
            throw new CAException(e.getMessage(),2);
        }
    }

    //------------------------------------------------------------------------------------------------------------------
    @Override
    public int insertRecord(RecordDTO record) throws SQLException {
        String sql = "INSERT INTO clothes(name, description, company, price, time, file) VALUES(?,?,?,?,?,?)";

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
        return 0;
    }

    //------------------------------------------------------------------------------------------------------------------
    @Override
    public int updateRecord(RecordDTO record) throws SQLException {
        String sql = "UPDATE clothes set name=?, description=?, company=?, price=?, time=?, file=? where id = ?";

        this.prestatment = this.connection.prepareStatement(sql);
        this.prestatment.setString(1, record.getName());
        this.prestatment.setString(2, record.getDescription());
        this.prestatment.setString(3, record.getCompany());
        this.prestatment.setDouble(4, record.getPrice());
        this.prestatment.setTimestamp(5, record.getDate());
        this.prestatment.setString(6, null);
        this.prestatment.setLong(7, record.getId());

        int affectedRows = this.prestatment.executeUpdate();

        if (affectedRows == 1) {
            return (int) record.getId();
        }

        return 0;
    }

    //------------------------------------------------------------------------------------------------------------------
    @Override
    public int deleteRecord(long index) throws SQLException {
        String sql = "DELETE FROM clothes WHERE id = ?";
        if(index<=0){
            throw new SQLException();
        }
        this.prestatment=this.connection.prepareStatement(sql);
        this.prestatment.setLong(1, index);

        int affectedRows = this.prestatment.executeUpdate();
        if(affectedRows==1){
            return 1;
        }

        return 0;
    }

    //------------------------------------------------------------------------------------------------------------------
    @Override
    public int deleteRecordByName(String name) throws SQLException {
        if(name==null){
            throw new SQLException();
        }
        String sql = "DELETE from clothes WHERE name = ?";
        int affectedRows;

        this.prestatment = this.connection.prepareStatement(sql);
        this.prestatment.setString(1, name);
        affectedRows = this.prestatment.executeUpdate();
        if(affectedRows==1){
            return 1;
        }

        return 0;
    }

    //------------------------------------------------------------------------------------------------------------------
    @Override
    public int deleteAll() throws SQLException {
        ArrayList<String> names = (ArrayList<String>) getRecordsNames();
        for (String name:names) {
            deleteRecordByName(name);
        }
        return 1;
    }

    //------------------------------------------------------------------------------------------------------------------
    @Override
    public RecordDTO getRecord(long index) throws SQLException {
        String sql = "Select * from clothes where id=?";
        RecordDTO record=null;
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
            throw new SQLException();
        }
    }

    //------------------------------------------------------------------------------------------------------------------
    @Override
    public RecordDTO getRecordByName(String name) throws SQLException {
        String sql = "Select * from clothes where name=?";
        RecordDTO record = null;
        int i=0;

        this.prestatment = this.connection.prepareStatement(sql);
        this.prestatment.setString(1, name);


        this.resultSet = this.prestatment.executeQuery();
        while(this.resultSet.next()) {
            if (i == 0) {
                record = this.serializeRecord(this.resultSet);
            }
            i++;
        }

        if(i>=1){
            return record;
        }else{
            throw new SQLException();
        }
    }

    //------------------------------------------------------------------------------------------------------------------
    @Override
    public List<RecordDTO> getRecords() throws SQLException {
        List<RecordDTO> records = new ArrayList();
        
        String sql = "Select * from clothes";

        this.prestatment = this.connection.prepareStatement(sql);

        this.resultSet = this.prestatment.executeQuery();
        while(this.resultSet.next()){
            records.add(this.serializeRecord(this.resultSet));
        }

        return records;
    }

    //------------------------------------------------------------------------------------------------------------------
    @Override
    public List<String> getRecordsNames() throws SQLException {
        String sql = "SELECT name from clothes";
        ArrayList<String> names;

        this.prestatment = this.connection.prepareStatement(sql);
        this.resultSet = this.prestatment.executeQuery();
        names = (ArrayList<String>) serializeNames(this.resultSet);

        return names;
    }

    //------------------------------------------------------------------------------------------------------------------
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

    //------------------------------------------------------------------------------------------------------------------
    @Override
    public List<String> serializeNames(ResultSet set) throws SQLException {
        ArrayList<String> names= new ArrayList<>();
        while(true){
            if (!set.next()) break;
            names.add(set.getString("name"));
        }
        return names;
    }

    //------------------------------------------------------------------------------------------------------------------
    @Override
    public void closeConnection() throws SQLException {
        this.connection.close();
    }
    
}
