package com.egen.PickupOrderManager.Model;

import com.egen.PickupOrderManager.Enum.PickStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "pickup")
public class Pickup {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "pick_id")
    private long pickId;

    @Column(name = "pick_date")
    private Date pickDate;

    @Column(name = "pick_store")
    private String pickStore;

    @Column(name = "pick_zone")
    private String pickZone;

    @Column(name = "pick_status")
    private PickStatus pickStatus;

    @Column(name = "pick_batch_id")
    private long pickBatchId;

    @OneToOne(mappedBy = "pickup", cascade = {CascadeType.ALL})
    @JoinColumn(name = "pick_id")
    @JsonIgnoreProperties("pickup")
    private Warehouse warehouse;

    public long getPickId() {
        return pickId;
    }

    public void setPickId(long pickId) {
        this.pickId = pickId;
    }

    @OneToMany(mappedBy = "pickup", cascade = {CascadeType.ALL})
    @JsonIgnoreProperties("pickup")
    private List<Orders> orderList;

    @OneToOne(mappedBy = "pickup", cascade = {CascadeType.ALL})
    @JsonIgnoreProperties("pickup")
    private Employee employee;

    @OneToMany(mappedBy = "pickup", cascade = {CascadeType.ALL})
    @JsonIgnoreProperties("pickup")
    private List<Item> itemList;

    public Date getPickDate() {
        return pickDate;
    }

    public void setPickDate(Date pickDate) {
        this.pickDate = pickDate;
    }

    public String getPickStore() {
        return pickStore;
    }

    public void setPickStore(String pickStore) {
        this.pickStore = pickStore;
    }

    public String getPickZone() {
        return pickZone;
    }

    public void setPickZone(String pickZone) {
        this.pickZone = pickZone;
    }

    public PickStatus getPickStatus() {
        return pickStatus;
    }

    public void setPickStatus(PickStatus pickStatus) {
        this.pickStatus = pickStatus;
    }

    public long getPickBatchId() {
        return pickBatchId;
    }

    public void setPickBatchId(long pickBatchId) {
        this.pickBatchId = pickBatchId;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public List<Orders> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Orders> orderList) {
        this.orderList = orderList;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;


    }

    public Pickup() {
    }
}
