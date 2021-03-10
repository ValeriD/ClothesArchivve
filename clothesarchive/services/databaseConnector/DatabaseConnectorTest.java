package clothesarchive.services.databaseConnector;

import clothesarchive.config.ConfigReader;
import clothesarchive.exceptions.CAException;
import clothesarchive.models.RecordDTO;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseConnectorTest {
    RecordDTO record = new RecordDTO("TEST", "This is for test purposes","Adidas", 15,null, new Timestamp(System.currentTimeMillis()));

    DatabaseConnector connector;

    {
        try {
            connector = new DatabaseConnector();
        } catch (CAException e) {
            e.printStackTrace();
        }
    }


    @Test
    @Order(1)
    @DisplayName("Insert record in the database test")
    void insertRecord() {
        int index = this.connector.insertRecord(this.record);
        assertTrue(index>0);
    }

    @Test
    @Order(2)
    void updateRecord() {
        long id = this.connector.getRecordByName(this.record.getName()).getId();
        this.record.setName("NEW_NAME"+id);
        this.record.setId(id);
        long index = this.connector.updateRecord(this.record);
        assertEquals(id,index);
    }

    @Test
    @Order(3)
    void deleteRecord() {
        int exitStatus = this.connector.deleteRecord(4);
        int exitStatus2 = this.connector.deleteRecord(156);

        assertAll(()->assertEquals(1,exitStatus),
                  ()->assertEquals(0, exitStatus2)
        );
    }
}