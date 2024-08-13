package com.example.demo.entity;

import jakarta.persistence.*;
import java.util.*;

import com.example.demo.entity.Payment;
@Entity
@Table(name = "RENTAL", schema = "test_dbo.dbo")
public class Rental {
@Id
@Column(name="RENTAL_ID")
private Integer rentalId;

@Column(name="RENTAL_DATE")
private String rentalDate;

@ManyToOne
@JoinColumn(name="INVENTORY_ID", nullable = true)
private Inventory inventoryId;

@ManyToOne
@JoinColumn(name="CUSTOMER_ID", nullable = true)
private Customer customerId;

@Column(name="RETURN_DATE")
private String returnDate;

@ManyToOne
@JoinColumn(name="STAFF_ID", nullable = true)
private Staff staffId;

@Column(name="LAST_UPDATE")
private String lastUpdate;

@OneToMany(mappedBy = "rentalId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
private List<Payment> payment;

public Integer getRentalId() {
return this.rentalId;
}

public void setRentalId (Integer rentalId) {
this.rentalId = rentalId;
}

public String getRentalDate() {
      return this.rentalDate;
}

public void setRentalDate(String rentalDate) {
      this.rentalDate = rentalDate;
}

public Inventory getInventoryId() {
      return this.inventoryId;
}

public void setInventoryId(Inventory inventoryId) {
      this.inventoryId = inventoryId;
}

public Customer getCustomerId() {
      return this.customerId;
}

public void setCustomerId(Customer customerId) {
      this.customerId = customerId;
}

public String getReturnDate() {
      return this.returnDate;
}

public void setReturnDate(String returnDate) {
      this.returnDate = returnDate;
}

public Staff getStaffId() {
      return this.staffId;
}

public void setStaffId(Staff staffId) {
      this.staffId = staffId;
}

public String getLastUpdate() {
      return this.lastUpdate;
}

public void setLastUpdate(String lastUpdate) {
      this.lastUpdate = lastUpdate;
}

public List<Payment> getPayment() {
      return this.payment;
}

public void setPayment(List<Payment> payment) {
      this.payment = payment;
}

}
