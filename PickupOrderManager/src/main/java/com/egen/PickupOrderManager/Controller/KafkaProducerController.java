//package com.egen.PickupOrderManager.Controller;
//
//import com.egen.PickupOrderManager.Model.Pickup;
//import com.egen.PickupOrderManager.Service.KafkaService.KafkaProducerService;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("stream")
//public class KafkaProducerController {
//    private KafkaProducerService kafkaProducerService;
//
//    public KafkaProducerController(KafkaProducerService kafkaProducerService){
//        this.kafkaProducerService = kafkaProducerService;
//    }
//    @PostMapping("createPickup")
//    public String streamPickup(@RequestBody Pickup pickup) {
//        System.out.println(pickup.getEmployee().getPickEmpId());
//        kafkaProducerService.sendPickup(pickup);
//        return "Kafka pickup";
//    }
//
//
//
//}
