package app.batch.batch.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Employee implements Serializable {
    private String firstName;
    private String lastName;
    private String companyName;
    private String address;
    private String city;
    private String county;
    private String state;
    private String zip;
}
