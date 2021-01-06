package clothesarchive.services.CRUD;

import clothesarchive.exceptions.CAException;
import clothesarchive.models.RecordDTO;
import org.junit.jupiter.api.BeforeAll;

import java.sql.SQLException;


class CrudServiceImplTest {

    private RecordDTO record = new RecordDTO("Test","alabala", "test", 15,null,null);
    private CrudService service = new CrudServiceImpl();

    CrudServiceImplTest() throws CAException {
    }

    @org.junit.jupiter.api.Test
    void saveRecord() throws CAException {
        this.service.saveRecord(record.getName(), record.getDescription(), record.getCompany(), record.getPrice(),record.getFile());

    }

    @org.junit.jupiter.api.Test
    void deleteRecord(){
    }
}