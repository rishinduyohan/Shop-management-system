package model;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
@AllArgsConstructor
public class EmployeeDTO {
    private String employeeId;
    private String name;
    private String nic;
    private String dob;
    private String position;
    private double salary;
    private String contactNumber;
    private String address;
    private String joinedDate;
    private String status;
}
