package com.egen.PickupOrderManager.Service;

import com.egen.PickupOrderManager.Enum.PickStatus;
import com.egen.PickupOrderManager.Model.Pickup;

import java.util.Date;
import java.util.List;

public interface IPickupManagementService {
    List<Pickup> getAll();
    PickStatus getPickStatus(long id);

    String createPick(Pickup pickup);

    int cancelPickup(long id);
    int getPickup1(long id);

    String createPickups(List<Pickup> pickupList);
}
