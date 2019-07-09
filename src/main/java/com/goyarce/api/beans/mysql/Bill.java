package com.goyarce.api.beans.mysql;

import javax.persistence.*;
import java.util.Date;


@Entity
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bill_id")
    private Integer id;

    @Column(name = "due_date")
    private Date due_date;

    @Column(name = "paid_date")
    private Date paid_date;

    @Column(name = "issue_date")
    private Date issue_date;

    @Column(name = "total_amount")
    private Double total_amount;

    @Column(name = "paid_amount")
    private Double paid_amount;

    @Column(name = "guardian_id")
    private String guardian_id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDue_date() {
        return due_date;
    }

    public void setDue_date(Date due_date) {
        this.due_date = due_date;
    }

    public Date getPaid_date() {
        return paid_date;
    }

    public void setPaid_date(Date paid_date) {
        this.paid_date = paid_date;
    }

    public Date getIssue_date() {
        return issue_date;
    }

    public void setIssue_date(Date issue_date) {
        this.issue_date = issue_date;
    }

    public Double getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(Double total_amount) {
        this.total_amount = total_amount;
    }

    public Double getPaid_amount() {
        return paid_amount;
    }

    public void setPaid_amount(Double paid_amount) {
        this.paid_amount = paid_amount;
    }

    public String getGuardian_id() {
        return guardian_id;
    }

    public void setGuardian_id(String guardian_id) {
        this.guardian_id = guardian_id;
    }

    @Override
    public String toString(){
        return "Bill: " + getId() + "|" + getDue_date() + "|" + getPaid_date() +
                "|" + getTotal_amount() + "|" + getPaid_amount();
    }
}
