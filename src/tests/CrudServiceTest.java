package src.tests;

import src.main.java.exceptions.CAException;
import src.main.java.models.RecordDTO;
import org.junit.jupiter.api.*;
import src.main.java.services.CRUD.CrudService;
import src.main.java.services.CRUD.CrudServiceImpl;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;


/**
 *
 * @author Valeri Dobrev
 */
class CrudServiceImplTest {

    CrudService service;
    RecordDTO recordDTO;
    @BeforeEach
    void setup() throws CAException {
        this.service = new CrudServiceImpl();
    }

    @Nested
    @DisplayName("Serialize data into RecordDTO")
    class SerializeRecord{
        @Test
        @DisplayName("All valid fields")
        void serializeAllValidFields() throws Exception {
            recordDTO = service.serializeRecord("Test","Description", "Adidas", 15.0, null);
            assertAll(
                    ()-> assertEquals(recordDTO.getName(), "Test"),
                    ()->assertEquals(recordDTO.getDescription(), "Description"),
                    ()->assertEquals(recordDTO.getCompany(), "Adidas"),
                    ()->assertEquals(recordDTO.getPrice(), 15),
                    ()->assertNull(recordDTO.getFile())
            );
        }
        @Test
        @DisplayName("Invalid name")
        void serializeInvalidName(){
            assertThrows(Exception.class,()->service.serializeRecord("","Description", "Adidas", 15.0, null));
        }
        @Test
        @DisplayName("Invalid company name")
        void serializeInvalidCompanyName(){
            assertThrows(Exception.class,()->service.serializeRecord("Test","Description", "", 15.0, null));
        }
        @Test
        @DisplayName("Invalid price")
        void serializeInvalidPrice(){
            assertAll(
                    ()->assertThrows(Exception.class,()->service.serializeRecord("Test","Description", "Adidas", 0.0, null)),
                    ()->assertThrows(Exception.class,()->service.serializeRecord("Test","Description", "Adidas", -15.0, null))
            );
        }
    }

    @Nested
    @DisplayName("Save tests")
    class SaveService{
        @Nested
        @DisplayName("Insert record")
        class InsertRecord {
            @Test
            @DisplayName("Insert valid record")
            void insertValidRecord() {
               assertDoesNotThrow(()-> service.saveRecord("Test","Description", "Adidas", 15.0, null,1));
            }
            @Test
            @DisplayName("Insert null name")
            void insertNullName(){
                assertThrows(CAException.class, ()->service.saveRecord(null,"Description", "Adidas", 15.0, null,1));
            }
            @Test
            @DisplayName("Insert already existing record")
            void insertAlreadyExisting() throws CAException {
                service.saveRecord("Test","Description", "Adidas", 15.0, null,1);
                assertThrows(CAException.class, ()->service.saveRecord("Test","Description", "Adidas", 15.0, null,1));
            }

            @AfterEach
            void clean() throws SQLException {
                service.deleteAllRecords();
            }
        }

        @Nested
        @DisplayName("Update tests")
        class UpdateRecord{
            long id;
            @BeforeEach
            void setup() throws CAException {
                service.saveRecord("Test","Description", "Adidas", 15.0, null,1);
                this.id = service.getRecordByName("Test").getId();
            }

            @Test
            @DisplayName("Update record change description/company/price")
            void updateRecord() throws CAException {
                service.saveRecord("Test","New description", "Adidas1", 16.0, null,0, "Test");
                assertAll(
                        ()->assertEquals("New description",service.getRecordByName("Test").getDescription()),
                        ()->assertEquals("Adidas1",service.getRecordByName("Test").getCompany()),
                        ()->assertEquals(16,service.getRecordByName("Test").getPrice()),
                        ()->assertEquals(this.id, service.getRecordByName("Test").getId())
                );
            }
            @Test
            @DisplayName("Update record changing name that doesn't exist")
            void updateWithNotExistingName() throws CAException {
                service.saveRecord("New name","Description", "Adidas", 15.0, null,0, "Test");
                assertAll(
                        ()->assertEquals(this.id, service.getRecordByName("New name").getId()),
                        ()->assertEquals("Description", service.getRecordByName("New name").getDescription()),
                        ()->assertEquals("Adidas", service.getRecordByName("New name").getCompany()),
                        ()->assertEquals(15, service.getRecordByName("New name").getPrice())
                );
            }

            @Test
            @DisplayName("Update with not existing oldName")
            void updateWithNoExistingOldName(){
                try {
                    service.saveRecord("New name","Description", "Adidas", 15.0, null,0, "Test1");
                } catch (CAException e) {
                    assertEquals("Запис с такова име не съществува!", e.getMessage());
                }
            }

            @Test
            @DisplayName("Update when the changed name already exists")
            void updateWhenNewNameAlreadyExists() {
                try {
                    service.saveRecord("New name","Description", "Adidas", 15.0, null,1);
                    service.saveRecord("New name","Description", "Adidas", 15.0, null,0, "Test");
                } catch (CAException e) {
                    assertEquals("Запис с такова име вече съществува!", e.getMessage());
                }
            }

            @Test
            @DisplayName("Update")

            @AfterEach
            void clean() throws SQLException {
                service.deleteAllRecords();
            }
        }
    }

    @Nested
    @DisplayName("Deleting record")
    class DeleteRecord{
        @BeforeEach
        void setup() throws CAException {
            service.saveRecord("Test","Description", "Adidas", 15.0, null,1);
        }

        @Test
        @DisplayName("Deleting by existing name")
        void deleteByExistingName() throws CAException {
            assertEquals(1, service.deleteRecord("Test"));
            assertEquals(0, service.getRecords().size());
        }

        @Test
        @DisplayName("Deleting by not existing name")
        void deleteByNotExistingName() throws CAException {
            assertEquals(0, service.deleteRecord("Test1"));
            assertEquals(1, service.getRecords().size());
        }
        @Test
        @DisplayName("Deleting by null name")
        void deleteNullName(){
            assertThrows(CAException.class, ()->service.deleteRecord(null));
        }

        @Test
        @DisplayName("Delete all records")
        void deleteAllRecords() throws SQLException, CAException {
            service.deleteAllRecords();
            assertEquals(0, service.getRecords().size());
        }

        @AfterEach
        void clean() throws SQLException {
            service.deleteAllRecords();
        }
    }

    @Nested
    @DisplayName("Retrieve record")
    class RetrieveRecord{
        @BeforeEach
        void setup() throws CAException {
            service.saveRecord("Test","Description", "Adidas", 15.0, null,1);
        }

        @Test
        @DisplayName("Retrieve by existing name")
        void getByExistingName() {
            assertAll(
                    ()-> assertEquals(service.getRecordByName("Test").getName(), "Test"),
                    ()-> assertEquals(service.getRecordByName("Test").getDescription(), "Description"),
                    ()-> assertEquals(service.getRecordByName("Test").getCompany(), "Adidas"),
                    ()-> assertEquals(service.getRecordByName("Test").getPrice(), 15)
            );
        }
        @Test
        @DisplayName("Retrieve record by not existing name")
        void getByNotExistingName() throws CAException {
            assertNull(service.getRecordByName("Test1"));
        }
        @Test
        @DisplayName("Retrieve by null name")
        void getByNullName(){
            assertThrows(CAException.class, ()->service.getRecordByName(null));
        }

        @Test
        @DisplayName("Get all records")
        void getAllRecords() throws CAException {
            assertEquals(1, service.getRecords().size());
        }


        @AfterEach
        void clean() throws SQLException {
            service.deleteAllRecords();
        }
    }

    @Nested
    @DisplayName("Record exists")
    class RecordExists{
        @BeforeEach
        void setup() throws CAException {
            service.saveRecord("Test","Description", "Adidas", 15.0, null,1);
        }

        @Test
        @DisplayName("Existing record")
        void checkExisting() throws CAException {
            assertTrue(service.exists("Test"));
        }

        @Test
        @DisplayName("Not existing record")
        void checkNotExisting() throws CAException {
            assertFalse(service.exists("Test1"));
        }
        @Test
        @DisplayName("Null name record")
        void checkNullName() {
            assertThrows(CAException.class, ()->service.exists(null));
        }

        @AfterEach
        void clean() throws SQLException {
            service.deleteAllRecords();
        }
    }

}