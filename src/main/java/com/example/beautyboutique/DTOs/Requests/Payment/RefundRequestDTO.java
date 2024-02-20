package com.example.beautyboutique.DTOs.Requests.Payment;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RefundRequestDTO {
    private String zpTransId;
    private Long amount;
    private String description;

}
