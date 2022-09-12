package com.egen.PickupOrderManager.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.egen.PickupOrderManager.Model.Pickup;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Repository
public interface PickupManagementRepository extends JpaRepository<Pickup, Long> {
    @Query(value = "Select pick_status from pickup where pick_id = :id",nativeQuery = true)
    int getPickup1(long id);

    @Transactional
    @Modifying
    @Query(value = "Update Pickup pickup set pickup.pickStatus = 0 where pickup.pickId = :id")
    int cancelPickup(long id);
//    @Query(value = "Update Employee employee set employee.pickEmpPerfEndDtTm = :date where employee.pickEmpId = :id")
//    int cancelPickup(long id, Date date);


}
