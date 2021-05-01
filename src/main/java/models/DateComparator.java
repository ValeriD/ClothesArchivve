package src.main.java.models;

import java.util.Comparator;

/**
 * Comparator that sorts the records by date descending
 */
public class DateComparator implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        RecordDTO record1 = (RecordDTO) o1;
        RecordDTO record2 = (RecordDTO) o2;

        return record2.getDate().compareTo(record1.getDate());
    }
}
