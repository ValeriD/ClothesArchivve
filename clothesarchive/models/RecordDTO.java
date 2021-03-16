/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clothesarchive.models;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Objects;


/**
 *
 * @author Valeri Dobrev
 */

public class RecordDTO {
    private long id;
    private String name;
    private String description;
    private String company;
    private double price;
    private byte[] file;
    private Timestamp date;

    public RecordDTO(){
        
    }

    public RecordDTO(String name, String description, String company,double price, byte[] file, Timestamp date) {
        this.id=0;
        this.name = name;
        this.description = description;
        this.company = company;
        this.price = price;
        this.file = file;
        this.date = date;
    }

    /**
     * Getters and setters
     */
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "RecordDTO{" +"id=" + id + ", name=" + name + ", description=" + description + ", company=" + company + ", price=" + price + ", date=" + date + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecordDTO recordDTO = (RecordDTO) o;
        return id == recordDTO.id && Double.compare(recordDTO.price, price) == 0 && name.equals(recordDTO.name) && Objects.equals(description, recordDTO.description) && Objects.equals(company, recordDTO.company) && Arrays.equals(file, recordDTO.file);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, name, description, company, price, date);
        result = 31 * result + Arrays.hashCode(file);
        return result;
    }
}
