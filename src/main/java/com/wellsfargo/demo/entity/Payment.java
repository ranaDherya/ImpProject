package com.example.demo.entity;

import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name = "PAYMENT", schema = "test_dbo.dbo")
public class Payment {
@Id
@Column(name="PAYMENT_ID")
private Integer paymentId;

@ManyToOne
@JoinColumn(name="CUSTOMER_ID", nullable = true)
private Customer customerId;

@ManyToOne
@JoinColumn(name="STAFF_ID", nullable = true)
private Staff staffId;

@ManyToOne
@JoinColumn(name="RENTAL_ID", nullable = true)
private Rental rentalId;

@Column(name="AMOUNT")
private String amount;

@Column(name="PAYMENT_DATE")
private String paymentDate;

public Integer getPaymentId() {
return this.paymentId;
}

public void setPaymentId (Integer paymentId) {
this.paymentId = paymentId;
}

public Customer getCustomerId() {
      return this.customerId;
}

public void setCustomerId(Customer customerId) {
      this.customerId = customerId;
}

public Staff getStaffId() {
      return this.staffId;
}

public void setStaffId(Staff staffId) {
      this.staffId = staffId;
}

public Rental getRentalId() {
      return this.rentalId;
}

public void setRentalId(Rental rentalId) {
      this.rentalId = rentalId;
}

public String getAmount() {
      return this.amount;
}

public void setAmount(String amount) {
      this.amount = amount;
}

public String getPaymentDate() {
      return this.paymentDate;
}

public void setPaymentDate(String paymentDate) {
      this.paymentDate = paymentDate;
}

}
