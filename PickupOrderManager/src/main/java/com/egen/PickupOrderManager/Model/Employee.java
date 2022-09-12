package com.egen.PickupOrderManager.Model;

import javax.persistence.*;
import java.util.Date;

@Table(name = "employee")
@Entity
public class Employee {
    @Id
    @Column(name = "pick_emp_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long pickEmpId;

    @Column(name = "pick_emp_perf_start_dt_tm")
    private Date pickEmpPerfStartDtTm;

    @Column(name = "pick_emp_perf_end_dt_tm")
    private Date pickEmpPerfEndDtTm;

    @Column(name = "pick_perf_id")
    private long pickPerfId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(columnDefinition = "pick_id")
    private Pickup pickup;

    public long getPickEmpId() {
        return pickEmpId;
    }

    public void setPickEmpId(long pickEmpId) {
        this.pickEmpId = pickEmpId;
    }

    public Date getPickEmpPerfStartDtTm() {
        return pickEmpPerfStartDtTm;
    }

    public void setPickEmpPerfStartDtTm(Date pickEmpPerfStartDtTm) {
        this.pickEmpPerfStartDtTm = pickEmpPerfStartDtTm;
    }

    public Date getPickEmpPerfEndDtTm() {
        return pickEmpPerfEndDtTm;
    }

    public void setPickEmpPerfEndDtTm(Date pickEmpPerfEndDtTm) {
        this.pickEmpPerfEndDtTm = pickEmpPerfEndDtTm;
    }

    public long getPickPerfId() {
        return pickPerfId;
    }

    public void setPickPerfId(long pickPerfId) {
        this.pickPerfId = pickPerfId;
    }

    public Pickup getPickup() {
        return pickup;
    }

    public void setPickup(Pickup pickup) {
        this.pickup = pickup;
    }
}
