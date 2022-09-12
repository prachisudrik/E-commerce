package com.egen.PickupOrderManager.Service;

import com.egen.PickupOrderManager.Enum.PickStatus;
import com.egen.PickupOrderManager.Model.*;
import com.egen.PickupOrderManager.Repository.PickupManagementRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import lombok.extern.slf4j.Slf4j;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import static org.junit.Assert.*;


@Slf4j
@RunWith(SpringRunner.class)
public class PickupManagementServiceTest {

    @MockBean
    private PickupManagementRepository pickupManagementRepository;

    @TestConfiguration
    static class PickupManagementServiceTestConfiguration{
        @Bean
        public PickupManagementService getService(){
            return new PickupManagementService();
        }
    }
    @Autowired
    IPickupManagementService ipickupManagementService1;

    List<Pickup> result = new ArrayList<>();

    @Before
    public void setup(){
        Pickup pickup = new Pickup();
        Employee employee = new Employee();
        Item item = new Item();
        Orders orders = new Orders();
        Warehouse warehouse = new Warehouse();

        employee.setPickEmpId(99);
        employee.setPickPerfId(99);

        warehouse.setPickWarehouseId(99);
        warehouse.setPickToteCartId(99);
        warehouse.setPickToteCartId(99);

        List<Item> itemList = new ArrayList<>();
        item.setPickItemId(99);
        item.setPickItemHeight(20.0);
        item.setPickItemLength(20.0);
        item.setPickItemWeight(20.0);
        item.setPickItemQty(9);
        item.setPickItemIsAllowed("yes");
        item.setPickSubstItem(3.0);
        itemList.add(item);

        item.setPickItemId(100);
        item.setPickItemHeight(20.0);
        item.setPickItemLength(20.0);
        item.setPickItemWeight(20.0);
        item.setPickItemQty(9);
        item.setPickItemIsAllowed("yes");
        item.setPickSubstItem(3.0);
        itemList.add(item);

        List<Orders> ordersList = new ArrayList<>();
        orders.setPickOrderId(99);
        orders.setPickOrderItemHeight(20.9);
        orders.setPickOrderItemWidth(20.9);
        orders.setPickOrderItemLength(20.9);
        orders.setPickOrderItemQty(8);
        ordersList.add(orders);

        orders.setPickOrderId(100);
        orders.setPickOrderItemHeight(20.9);
        orders.setPickOrderItemWidth(20.9);
        orders.setPickOrderItemLength(20.9);
        orders.setPickOrderItemQty(8);
        ordersList.add(orders);

        pickup.setPickStatus(PickStatus.PICKED);
        pickup.setEmployee(employee);
        pickup.setWarehouse(warehouse);
        pickup.setOrderList(ordersList);
        pickup.setItemList(itemList);
        //pickup.setPickId(99);
        pickup.setPickZone("Mumbai");

        orders.setPickup(pickup);
        warehouse.setPickup(pickup);
        item.setPickup(pickup);
        employee.setPickup(pickup);

        result = Collections.singletonList(pickup);

        log.info("************       "+result.get(0).getPickStatus().ordinal());

        Mockito.when(pickupManagementRepository.findAll())
                .thenReturn(result);

        Mockito.when(pickupManagementRepository.cancelPickup(0))
                .thenReturn(result.get(0).getPickStatus().ordinal());

    }

    @After
    public void cleanup(){ }

    @Test
    public void getAllEmpty() {
        List<Pickup> result = Collections.emptyList();
        assertEquals("return empty", Collections.emptyList(), result);
    }

    @Test
    public void getAll() {
        List<Pickup> resultList = ipickupManagementService1.getAll();
        assertEquals("pickup list should match", result, resultList);
    }

    @Test
    public void getPickStatus() {
        PickStatus pickStatus = ipickupManagementService1.getAll().get(0).getPickStatus();
        assertEquals("Pickup is picked", PickStatus.PICKED, pickStatus);
    }
    @Test
    public void cancelPickup() {
        ipickupManagementService1.cancelPickup(0);
        long resultPick = ipickupManagementService1.getPickup1(0);
        assertEquals("Pickup is cancelled", 0, resultPick);
    }
}