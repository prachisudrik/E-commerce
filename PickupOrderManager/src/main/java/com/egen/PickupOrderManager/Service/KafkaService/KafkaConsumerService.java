package com.egen.PickupOrderManager.Service.KafkaService;

import com.egen.PickupOrderManager.Model.Pickup;
import com.egen.PickupOrderManager.Service.IPickupManagementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;
import org.springframework.kafka.support.*;

@Service
@Slf4j
public class KafkaConsumerService {
    public IPickupManagementService iPickupManagementService;

    public KafkaConsumerService(@Qualifier("pickupManagementService") IPickupManagementService iPickupManagementService) {
        this.iPickupManagementService = iPickupManagementService;
    }
    @KafkaListener(containerFactory = "jsonKafkaListenerContainerFactory",
            topics = "${kafka.topic.name.pickup}",
            groupId = "${kafka.consumer.groupId}")
    public void consumeOrderDetails(@Header(KafkaHeaders.OFFSET)Long offset,
                                    @Header(KafkaHeaders.RECEIVED_PARTITION_ID)Integer partition,
                                    @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY)String key,
                                    Pickup pickup){
        log.info("Consumed order: {} for customerID: {} from Partition: {} at Offset: {}",key
                ,pickup.getEmployee().getPickEmpId(),partition,offset);
        iPickupManagementService.createPick(pickup);
    }
}
