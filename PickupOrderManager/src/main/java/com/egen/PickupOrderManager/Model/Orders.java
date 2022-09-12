package com.egen.PickupOrderManager.Model;

import javax.persistence.*;


@Entity
@Table(name = "orders")
public class Orders {

    @Id
    @Column(name = "pick_order_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long pickOrderId;

    @Column(name = "pick_order_item_qty")
    private int pickOrderItemQty;

    @Column(name = "pick_order_item_height")
    private double pickOrderItemHeight;

    @Column(name = "pick_order_item_width")
    private double pickOrderItemWidth;

    @Column(name = "pick_order_item_length")
    private double pickOrderItemLength;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(columnDefinition = "pick_id")
//    @JsonIgnoreProperties("orderList")
    private Pickup pickup;

    public Orders() {
    }

    public long getPickOrderId() {
        return pickOrderId;
    }

    public void setPickOrderId(long pickOrderId) {
        this.pickOrderId = pickOrderId;
    }

    public int getPickOrderItemQty() {
        return pickOrderItemQty;
    }

    public void setPickOrderItemQty(int pickOrderItemQty) {
        this.pickOrderItemQty = pickOrderItemQty;
    }

    public double getPickOrderItemHeight() {
        return pickOrderItemHeight;
    }

    public void setPickOrderItemHeight(double pickOrderItemHeight) {
        this.pickOrderItemHeight = pickOrderItemHeight;
    }

    public double getPickOrderItemWidth() {
        return pickOrderItemWidth;
    }

    public void setPickOrderItemWidth(double pickOrderItemWidth) {
        this.pickOrderItemWidth = pickOrderItemWidth;
    }

    public double getPickOrderItemLength() {
        return pickOrderItemLength;
    }

    public void setPickOrderItemLength(double pickOrderItemLength) {
        this.pickOrderItemLength = pickOrderItemLength;
    }

    public Pickup getPickup() {
        return pickup;
    }

    public void setPickup(Pickup pickup) {
        this.pickup = pickup;
    }
}
