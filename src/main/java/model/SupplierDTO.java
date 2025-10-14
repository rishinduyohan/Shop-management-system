package model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class SupplierDTO {
    private String supplierId;
    private String name;
    private String companyName;
    private String address;
    private String city;
    private String province;
    private String postalCode;
    private String phone;
    private String email;
}
