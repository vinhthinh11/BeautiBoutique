package com.example.beautyboutique.Services.ShipDetail;

import com.example.beautyboutique.DTOs.Requests.ShipDetail.ShipDetailDTO;
import com.example.beautyboutique.Models.ShipDetail;

import java.util.List;

public interface ShipDetailService {

    List<ShipDetail> getShipDetailList(Integer userId);

    Boolean createShipDetail(Integer userId, ShipDetailDTO shipDetail);

    Boolean updateShipDetail(Integer userId, Integer shipDetailId, ShipDetailDTO shipDetail);

    Boolean deleteShipDetail(Integer userId, Integer shipDetailId);
}
