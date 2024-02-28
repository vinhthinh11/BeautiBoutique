package com.example.beautyboutique.Controllers;


import com.example.beautyboutique.DTOs.Requests.ShipDetail.ShipDetailDTO;
import com.example.beautyboutique.DTOs.Responses.ResponseMessage;
import com.example.beautyboutique.DTOs.Responses.ShipDetail.ResponseShipDetails;
import com.example.beautyboutique.Models.ShipDetail;
import com.example.beautyboutique.Services.ShipDetail.ShipDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/ship-detail")
public class ShipDetailController {

    @Autowired
    ShipDetailServiceImpl shipDetailService;

    @GetMapping(value = "/get-all")
    public ResponseEntity<?> getShipDetails(@RequestParam(value = "userId", required = true) Integer userId) {

        try {
            List<ShipDetail> shipDetails = shipDetailService.getShipDetailList(userId);
            return new ResponseEntity<>(new ResponseShipDetails(shipDetails), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseMessage("Internal Server Error!"), HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @PostMapping(value = "/create-ship", consumes = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_FORM_URLENCODED_VALUE
    }, produces =
            MediaType.APPLICATION_JSON_VALUE
    )
    public @ResponseBody ResponseEntity<?> createShipDetail(@RequestParam(value = "userId", required = true) Integer userId,
                                                            ShipDetailDTO shipDetailDTO) {

        try {
            Boolean isCreated = shipDetailService.createShipDetail(userId, shipDetailDTO);
            if (isCreated)
                return new ResponseEntity<>(new ResponseMessage("Created ship details successfully!"), HttpStatus.CREATED);
            return new ResponseEntity<>(new ResponseMessage("Created ship details fail!"), HttpStatus.BAD_REQUEST);

        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseMessage("Internal Server Error!"), HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @PutMapping(value = "/update-ship", consumes = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_FORM_URLENCODED_VALUE
    }, produces =
            MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> updateShipDetail(@RequestParam(value = "userId", required = true) Integer userId,
                                              @RequestParam(value = "shipDetailId", required = true) Integer shipDetailId,
                                              ShipDetailDTO shipDetailDTO) {

        try {
            Boolean isUpdated = shipDetailService.updateShipDetail(userId, shipDetailId, shipDetailDTO);
            if (isUpdated)
                return new ResponseEntity<>(new ResponseMessage("Updated ship details successfully!"), HttpStatus.OK);
            return new ResponseEntity<>(new ResponseMessage("Updated ship details fail!"), HttpStatus.BAD_REQUEST);

        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseMessage("Internal Server Error!"), HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @DeleteMapping(value = "/delete-ship")
    public ResponseEntity<?> deleteShipDetail(@RequestParam(value = "userId", required = true) Integer userId,
                                              @RequestParam(value = "shipDetailId", required = true) Integer shipDetailId) {

        try {
            Boolean isDeleted = shipDetailService.deleteShipDetail(userId, shipDetailId);
            if (isDeleted)
                return new ResponseEntity<>(new ResponseMessage("Deleted ship details successfully!"), HttpStatus.OK);
            return new ResponseEntity<>(new ResponseMessage("Deleted ship details fail!"), HttpStatus.BAD_REQUEST);

        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseMessage("Internal Server Error!"), HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }


}
