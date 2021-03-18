package clothesarchive.services.databaseConnector;

import clothesarchive.exceptions.CAException;
import clothesarchive.models.RecordDTO;
import org.junit.jupiter.api.*;

import java.sql.SQLException;
import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Valeri Dobrev
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DatabaseConnectorTest {
    RecordDTO record;
    DatabaseConnectorImpl connector;

    @BeforeAll
    public void init(){
        try {
            this.connector = new DatabaseConnectorImpl();

        } catch (CAException e) {
            e.printStackTrace();
        }
    }
    @BeforeEach
    public void initRecord(){
        this.record = new RecordDTO("TEST", "This is for test purposes","Adidas", 15,null, new Timestamp(System.currentTimeMillis()));
    }

    @Nested
    @DisplayName("Insert tests")
    class Insert {
        @BeforeEach
        void setup() throws SQLException {
            connector.deleteAll();
        }

        @Test
        @DisplayName("Insert record in the database test")
        void insertRecord() throws SQLException {
            connector.insertRecord(record);
            assertEquals(connector.getRecords().size(), 1);
        }

        @Test
        @DisplayName("Insert record that already exists")
        void insertRecordThatExists() throws SQLException {
            connector.insertRecord(record);
            assertEquals(1, connector.getRecords().size());
            assertThrows(SQLException.class, ()->connector.insertRecord(record));
        }

        @Test
        @DisplayName("Insert record that has no name")
        void insertEmptyNameRecord(){
            record.setName(null);
            assertThrows(SQLException.class, ()->connector.insertRecord(record));
        }
    }

    @Nested
    @DisplayName("Update tests")
    class Update {
        long id;
        @BeforeEach
        void setup() throws SQLException {
            connector.insertRecord(record);
            this.id = connector.getRecordByName("TEST").getId();
            record.setId(this.id);
        }

        @Test
        @DisplayName("Regular update")
        void updateRecord() throws SQLException {
            record.setName("NEW_NAME" + this.id);
            long index = connector.updateRecord(record);
            assertEquals(this.id, index);
        }
        @Test
        @DisplayName("Update when not found")
        void notFoundUpdate() throws SQLException {
            record.setId(0);
            int res = connector.updateRecord(record);
            assertEquals(0,res);
        }


        @Test
        @DisplayName("Update with the existing name")
        void updateWithExistingName() throws SQLException {
            record.setName("Test1");
            connector.insertRecord(record);
            record.setId(this.id);
            assertThrows(SQLException.class,()->connector.updateRecord(record));
        }
        @Test
        @DisplayName("Update with null for name")
        void updateNullName(){
            record.setName(null);
            assertThrows(SQLException.class,()-> connector.updateRecord(record));
        }

        @AfterEach
        void clean() throws SQLException {
            connector.deleteAll();
        }
    }

    @Nested
    @DisplayName("Delete tests")
    class Delete{

        @Nested
        @DisplayName("Delete record by Id")
        class DeleteRecord{
            int id;
            @BeforeEach
            void setup() throws SQLException {
                this.id = connector.insertRecord(record);
            }
            @Test
            @DisplayName("Delete existing record")
            void deleteRecord() throws SQLException {
                int res = connector.deleteRecord(this.id);
                assertEquals(1,res);
            }
            @Test
            @DisplayName("Delete not existing record")
            void deleteNotExistingRecord() throws SQLException {
                int res = connector.deleteRecord(this.id+1);
                assertEquals(0,res);
            }

            @Test
            @DisplayName("Delete record at invalid index")
            void deleteInvalidId(){
                assertThrows(SQLException.class, () -> connector.deleteRecord(-1));
            }

            @AfterEach
            void clean() throws SQLException {
                connector.deleteAll();
            }
        }
        @Nested
        @DisplayName("Delete record by name")
        class DeleteByName{
            @BeforeEach
            void setup() throws SQLException {
                connector.insertRecord(record);
            }
            @Test
            @DisplayName("Delete record by given name")
            void deleteRecordByName() throws SQLException {
                int res = connector.deleteRecordByName(record.getName());
                assertEquals(1,res);
            }

            @Test
            @DisplayName("Delete not existing record")
            void deleteNotExistingRecord() throws SQLException {
                int res = connector.deleteRecordByName("Test1");
                assertEquals(0,res);
            }
            @Test
            @DisplayName("Delete null name")
            void deleteNullName() {
                assertThrows(SQLException.class, ()->connector.deleteRecordByName(null));
            }

            @AfterEach
            void clean() throws SQLException {
                connector.deleteAll();
            }
        }
        @Nested
        @DisplayName("Delete all")
        class DeleteAll{
            @BeforeEach
            void setup() throws SQLException {
                connector.insertRecord(record);
                record.setName("New name");
                connector.insertRecord(record);
            }
            @Test
            @DisplayName("Delete all records")
            void deleteAllRecords() throws SQLException {
                int res = connector.deleteAll();
                assertAll(()->assertEquals(1,res),
                          () -> assertEquals(0, connector.getRecordsNames().size()));

            }
        }
    }

    @Nested
    @DisplayName("Retrieve record")
    class Retrieve{
        @Nested
        @DisplayName("Retrieve record by ID")
        class RetrieveById{
            int id;
            @BeforeEach
            void setup() throws SQLException {
                this.id = connector.insertRecord(record);
                record.setId(this.id);
            }
            @Test
            @DisplayName("Retrieve record by valid ID")
            void getByValidId() throws SQLException {
                RecordDTO recordDTO = connector.getRecord(this.id);
                assertEquals(record,recordDTO);
            }

            @Test
            @DisplayName("Retrieve record by invalid index")
            void getByNotValidId(){
                assertThrows(SQLException.class, ()->connector.getRecord(-1));
            }

            @Test
            @DisplayName("Retrieve not existing record")
            void getNotExistingRecord(){
                assertThrows(SQLException.class, ()->connector.getRecord(this.id+1));
            }

            @AfterEach
            void clean() throws SQLException {
                connector.deleteAll();
            }
        }

        @Nested
        @DisplayName("Retrieve record by name")
        class RetrieveByName{
            @BeforeEach
            void setup() throws SQLException {
                int id = connector.insertRecord(record);
                record.setId(id);
            }

            @Test
            @DisplayName("Retrieve record by existing name")
            void getByExistingName() throws SQLException {
                RecordDTO recordDTO = connector.getRecordByName(record.getName());
                assertEquals(record,recordDTO);
            }
            @Test
            @DisplayName("Retrieve record by not existing name")
            void getByNotExistingName() throws SQLException {
                assertNull(connector.getRecordByName("Test1"));
            }
            @Test
            @DisplayName("Retrieve record by null name")
            void getByNullName(){
                assertThrows(SQLException.class, ()->connector.getRecordByName(null));
            }
            @AfterEach
            void clean() throws SQLException {
                connector.deleteAll();
            }
        }
        @Nested
        @DisplayName("Retrieve all records")
        class RetrieveAll{
            @BeforeEach
            void setup() throws SQLException {
                connector.insertRecord(record);
                record.setName("New name");
                connector.insertRecord(record);
            }

            @Test
            @DisplayName("Retrieve all records")
            void getAll() throws SQLException {
                assertEquals(2,connector.getRecords().size());
            }

            @Test
            @DisplayName("Retrieve all records' names")
            void getAllNames() throws SQLException {
                assertEquals(2, connector.getRecordsNames().size());
            }
            @AfterEach
            void clean() throws SQLException {
                connector.deleteAll();
            }
        }
    }

    @AfterEach
    public void deleteAllRecords() throws SQLException {
        this.connector.deleteAll();
    }


}