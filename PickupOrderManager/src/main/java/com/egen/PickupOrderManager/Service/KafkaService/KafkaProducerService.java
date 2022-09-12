package com.egen.PickupOrderManager.Service.KafkaService;



import com.egen.PickupOrderManager.Model.Pickup;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class KafkaProducerService {
    private final KafkaTemplate<String, Pickup> pickupKafkaTemplate;

    @Value("${kafka.topic.name.pickup}")
    private String TOPIC;

    public KafkaProducerService(KafkaTemplate<String, Pickup> orderKafkaTemplate){
        this.pickupKafkaTemplate = orderKafkaTemplate;
    }

    public void sendPickup(Pickup pickup){
        log.info(String.format("$$$ => Producing Message: ",pickup));
        pickupKafkaTemplate.executeInTransaction(t -> {
            ListenableFuture<SendResult<String,Pickup>> future = t.send(TOPIC, String.valueOf(pickup.getEmployee().getPickEmpId()) + UUID.randomUUID(), pickup);
            future.addCallback(new ListenableFutureCallback<SendResult<String, Pickup>>() {
                @Override
                public void onFailure(Throwable e) {
                    log.info("Unable to produce message=[{}] due to {}",pickup, e.getMessage());
                }
                @Override
                public void onSuccess(SendResult<String, Pickup> result) {
                    log.info("Sent Message=[{}] with offset=[{}]",pickup,result.getRecordMetadata().offset());
                }
            });
            return true;
        });
    }


    public void sendBatchPickup(List<Pickup> pickupList){
        for(int i = 0; i < pickupList.size(); i++) {
            Pickup pickup = pickupList.get(i);
            log.info(String.format("$$$ => Producing Message: ", pickup));
            pickupKafkaTemplate.executeInTransaction(t -> {
                ListenableFuture<SendResult<String, Pickup>> future = t.send(TOPIC, String.valueOf(pickup.getEmployee().getPickEmpId()) + UUID.randomUUID(), pickup);
                future.addCallback(new ListenableFutureCallback<SendResult<String, Pickup>>() {
                    @Override
                    public void onFailure(Throwable e) {
                        log.info("Unable to produce message=[{}] due to {}", pickup, e.getMessage());
                    }

                    @Override
                    public void onSuccess(SendResult<String, Pickup> result) {
                        log.info("Sent Message=[{}] with offset=[{}]", pickup, result.getRecordMetadata().offset());
                    }
                });
                return true;
            });
        }
    }
}