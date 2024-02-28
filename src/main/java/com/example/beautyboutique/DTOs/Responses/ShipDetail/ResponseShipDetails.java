package com.example.beautyboutique.DTOs.Responses.ShipDetail;

import com.example.beautyboutique.Models.ShipDetail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ResponseShipDetails {
    private List<ShipDetail> shipDetails;
}
