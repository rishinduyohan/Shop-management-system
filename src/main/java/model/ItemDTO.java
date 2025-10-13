package model;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ItemDTO {
    private String itemCode;
    private String description;
    private String category;
    private int qty;
    private double unitPrice;
}
