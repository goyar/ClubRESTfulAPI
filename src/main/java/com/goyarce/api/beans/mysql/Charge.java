package com.goyarce.api.beans.mysql;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Charge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="charge_id")
    private Integer id;

    @Column(name="player_id")
    private String player_id;

    @Column(name="guardian_id")
    private String guardian_id;

    @Column(name="description")
    private String description;

    @Column(name="amount")
    private Float amount;

    @Column(name="date")
    private Date date;

    @Column(name="state")
    private String state;

    @Column(name="bill_id")
    private Integer bill_id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlayer_id() {
        return player_id;
    }

    public void setPlayer_id(String player_id) {
        this.player_id = player_id;
    }

    public String getGuardian_id() {
        return guardian_id;
    }

    public void setGuardian_id(String guardian_id) {
        this.guardian_id = guardian_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getBill_id() {
        return bill_id;
    }

    public void setBill_id(Integer bill_id) {
        this.bill_id = bill_id;
    }

    @Override
    public String toString(){
        return "Charge: " + getId() + "|" + getPlayer_id() + "|" + getGuardian_id() +
                "|" + getDescription() + "|" + getAmount() + "|" + getDate() +
                "|" + getState() + "|" + getBill_id();
    }
}
