package com.egen.PickupOrderManager.Controller;

import com.egen.PickupOrderManager.Enum.PickStatus;
import com.egen.PickupOrderManager.Repository.PickupManagementRepository;
import com.egen.PickupOrderManager.Service.IPickupManagementService;
import com.egen.PickupOrderManager.Service.KafkaService.KafkaProducerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.egen.PickupOrderManager.Model.Pickup;

import java.time.Instant;
import java.util.Date;
import java.util.List;


@Slf4j
@RestController
@RequestMapping(method = RequestMethod.GET, value = "pickup")
public class PickupManagementController {

    @Qualifier("pickupManagementService")
    @Autowired
    IPickupManagementService iPickupManagementService;

    @Autowired
    private KafkaProducerService kafkaProducerService;

    @Autowired
    PickupManagementRepository pickupManagementRepository;

    public PickupManagementController() {
    }

    @GetMapping("/all")
    private ResponseEntity<List<Pickup>> getAllOrders(){
        return ResponseEntity.ok(iPickupManagementService.getAll());
    }

    @RequestMapping("/getPickStatus/{id}")
    private PickStatus getPickStatus(@PathVariable long id){
        return iPickupManagementService.getPickStatus(id);
    }



    @PostMapping("/createOne")
    private String createOne(@RequestBody Pickup pickup){
        iPickupManagementService.createPick(pickup);
        kafkaProducerService.sendPickup(pickup);
        return "Pickup order created in db and kafka";
    }

    @PutMapping("/cancelPickup/{id}")
    private String cancelPickup(@PathVariable long id){

        iPickupManagementService.cancelPickup(id);
        return "Order Cancelled";
    }

    @PostMapping("/createPickups")
    private String createPickups(@RequestBody List<Pickup> pickupList){
        iPickupManagementService.createPickups(pickupList);
        kafkaProducerService.sendBatchPickup(pickupList);
        return "Batch created";
    }
}
