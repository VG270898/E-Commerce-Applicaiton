package com.customer.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CustomerDTO {
    private String customerName;
    private String customerGender;
    private int customerAge;
    private String customerAddress;


}
