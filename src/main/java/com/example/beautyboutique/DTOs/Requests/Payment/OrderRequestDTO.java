package com.example.beautyboutique.DTOs.Requests.Payment;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderRequestDTO {
    private Integer userId;
    private Integer id;

}
