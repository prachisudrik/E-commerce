package com.egen.PickupOrderManager.Service;

import com.egen.PickupOrderManager.Enum.PickStatus;
import com.egen.PickupOrderManager.Exception.InternalServerError;
import com.egen.PickupOrderManager.Model.*;
import com.egen.PickupOrderManager.Repository.PickupManagementRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.sql.Date;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PickupManagementService implements IPickupManagementService{

    @Autowired
    PickupManagementRepository pickupManagementRepository;

    @Transactional
    @Override
    public List<Pickup> getAll() {
        try{
        return pickupManagementRepository.findAll();
        }
        catch(Exception e){
            throw new InternalServerError("Pickup not found");
        }
    }

    @Transactional
    @Override
    public PickStatus getPickStatus(long id) {
        try{
            Optional<Pickup> pickup = pickupManagementRepository.findById(id);
            Pickup pickup1 = pickup.get();
            pickup1.getPickStatus();
            return pickup1.getPickStatus();
        }catch(Exception e){
            throw new InternalServerError("Pickup not found");
        }
    }

    @Transactional
    @Override
    public String createPick(Pickup pickup) {
        try {
            Pickup pickup1 = new Pickup();
            List<Orders> ordersList = new ArrayList<>();
            Warehouse warehouse = new Warehouse();
            Employee employee = new Employee();
            List<Item> itemList = new ArrayList<>();


            BeanUtils.copyProperties(pickup, pickup1);

            for (int i = 0; i < pickup.getItemList().size(); i++) {
                Item item = new Item();
                BeanUtils.copyProperties(pickup.getItemList().get(i), item);
                item.setPickup(pickup);
                itemList.add(item);
            }
            pickup1.setItemList(itemList);

            for (int i = 0; i < pickup.getOrderList().size(); i++) {
                Orders orders = new Orders();
                BeanUtils.copyProperties(pickup.getOrderList().get(i), orders);
                orders.setPickup(pickup);
                ordersList.add(orders);
            }
            pickup1.setOrderList(ordersList);

            BeanUtils.copyProperties(pickup.getWarehouse(), warehouse);
            BeanUtils.copyProperties(pickup.getEmployee(), employee);

            warehouse.setPickup(pickup);
            employee.setPickup(pickup);
            employee.setPickEmpPerfStartDtTm(Date.from(Instant.now()));
            pickup1.setWarehouse(warehouse);
            pickup1.setEmployee(employee);
            pickup1.setPickStatus(PickStatus.PICKED);
            BeanUtils.copyProperties(pickup1, pickup);
            pickupManagementRepository.save(pickup);
            return "Pick in service";
        }catch(Exception e){
            throw new InternalServerError("Failed to pickup");
        }
    }


    @Override
    @Transactional
    public int cancelPickup(long id) {
        try{
//            Pickup pickup = new Pickup();
//            pickup.getEmployee().setPickEmpPerfEndDtTm(Date.from(Instant.now()));
            return pickupManagementRepository.cancelPickup(id);


        }catch(Exception e){
            throw new InternalServerError("Failed to cancel");
        }
    }

    @Transactional
    @Override
    public int getPickup1(long id) {
        return pickupManagementRepository.getPickup1(id);
    }

    @Transactional
    @Override
    public String createPickups(List<Pickup> pickupList) {
        try {
            List<Pickup> pickupList1 = new ArrayList<>();
            for (int j = 0; j < pickupList.size(); j++) {
                System.out.println("22222222 " + j);
                Pickup pickup1 = new Pickup();
                Pickup pickup = pickupList.get(j);
                Warehouse warehouse = new Warehouse();
                Employee employee = new Employee();
                List<Item> itemList = new ArrayList<>();
                List<Orders> ordersList = new ArrayList<>();

                BeanUtils.copyProperties(pickup, pickup1);

                for (int i = 0; i < pickup.getItemList().size(); i++) {
                    Item item = new Item();
                    BeanUtils.copyProperties(pickup.getItemList().get(i), item);
                    item.setPickup(pickup);
                    itemList.add(item);
                }
                pickup1.setItemList(itemList);

                for (int i = 0; i < pickup.getOrderList().size(); i++) {
                    Orders orders = new Orders();
                    BeanUtils.copyProperties(pickup.getOrderList().get(i), orders);
                    orders.setPickup(pickup);
                    ordersList.add(orders);
                }
                pickup1.setOrderList(ordersList);

                BeanUtils.copyProperties(pickup.getWarehouse(), warehouse);
                BeanUtils.copyProperties(pickup.getEmployee(), employee);
                employee.setPickEmpPerfStartDtTm(Date.from(Instant.now()));
                warehouse.setPickup(pickup);
                employee.setPickup(pickup);

                pickup1.setWarehouse(warehouse);
                pickup1.setEmployee(employee);
                pickup1.setPickStatus(PickStatus.PICKED);
                BeanUtils.copyProperties(pickup1, pickup);
                pickupList1.add(pickup);
            }
            pickupManagementRepository.saveAll(pickupList1);
            return "Batch pick in service";
        }catch(Exception e){
            throw new InternalServerError("Failed to batch pickup");
        }
    }
}
