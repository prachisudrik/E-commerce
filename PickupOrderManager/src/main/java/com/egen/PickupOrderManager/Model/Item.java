package com.egen.PickupOrderManager.Model;

import javax.persistence.*;


@Entity
@Table(name = "item")
public class Item {
    @Id
    @Column(name = "pick_item_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long pickItemId;

    @Column(name = "pick_item_qty")
    private int pickItemQty;

    @Column(name = "pick_item_weight")
    private double pickItemWeight;

    @Column(name = "pick_item_height")
    private double pickItemHeight;

    @Column(name = "pick_item_length")
    private double pickItemLength;

    @Column(name = "pick_subst_item")
    private double pickSubstItem;

    @Column(name = "pick_item_is_allowed")
    private String pickItemIsAllowed;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(columnDefinition = "pick_id")
    private Pickup pickup;

    public long getPickItemId() {
        return pickItemId;
    }

    public void setPickItemId(long pickItemId) {
        this.pickItemId = pickItemId;
    }

    public int getPickItemQty() {
        return pickItemQty;
    }

    public void setPickItemQty(int pickItemQty) {
        this.pickItemQty = pickItemQty;
    }

    public double getPickItemWeight() {
        return pickItemWeight;
    }

    public void setPickItemWeight(double pickItemWeight) {
        this.pickItemWeight = pickItemWeight;
    }

    public double getPickItemHeight() {
        return pickItemHeight;
    }

    public void setPickItemHeight(double pickItemHeight) {
        this.pickItemHeight = pickItemHeight;
    }

    public double getPickItemLength() {
        return pickItemLength;
    }

    public void setPickItemLength(double pickItemLength) {
        this.pickItemLength = pickItemLength;
    }

    public double getPickSubstItem() {
        return pickSubstItem;
    }

    public void setPickSubstItem(double pickSubstItem) {
        this.pickSubstItem = pickSubstItem;
    }

    public String getPickItemIsAllowed() {
        return pickItemIsAllowed;
    }

    public void setPickItemIsAllowed(String pickItemIsAllowed) {
        this.pickItemIsAllowed = pickItemIsAllowed;
    }

    public Pickup getPickup() {
        return pickup;
    }

    public void setPickup(Pickup pickup) {
        this.pickup = pickup;
    }
}
