package com.egen.PickupOrderManager.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Table(name = "warehouse")
@Entity
public class Warehouse {
    @Id
    @Column(name = "pick_warehouse_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long pickWarehouseId;

    @Column(name = "pick_tote_id")
    private long pickToteId;

    @Column(name = "pick_tote_cart_id")
    private long pickToteCartId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(columnDefinition = "pick_id")
    private Pickup pickup;

    public long getPickWarehouseId() {
        return pickWarehouseId;
    }

    public void setPickWarehouseId(long pickWarehouseId) {
        this.pickWarehouseId = pickWarehouseId;
    }

    public long getPickToteId() {
        return pickToteId;
    }

    public void setPickToteId(long pickToteId) {
        this.pickToteId = pickToteId;
    }

    public long getPickToteCartId() {
        return pickToteCartId;
    }

    public void setPickToteCartId(long pickToteCartId) {
        this.pickToteCartId = pickToteCartId;
    }

    public Pickup getPickup() {
        return pickup;
    }

    public void setPickup(Pickup pickup) {
        this.pickup = pickup;
    }
}
