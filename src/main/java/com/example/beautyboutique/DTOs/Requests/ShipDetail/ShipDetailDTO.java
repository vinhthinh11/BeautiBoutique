package com.example.beautyboutique.DTOs.Requests.ShipDetail;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ShipDetailDTO {
    private String fullName;
    private String address;
    private String phoneNumber;
}
