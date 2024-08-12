package com.example.demo.entity;

import jakarta.persistence.*;
import java.util.*;

import com.example.demo.entity.Rental;
import com.example.demo.entity.Payment;
@Entity
@Table(name = "CUSTOMER", schema = "test_dbo.dbo")
public class Customer {
@Id
@Column(name="CUSTOMER_ID")
private Integer customerId;

@ManyToOne
@JoinColumn(name="STORE_ID", nullable = true)
private Store storeId;

@Column(name="FIRST_NAME")
private String firstName;

@Column(name="LAST_NAME")
private String lastName;

@Column(name="EMAIL")
private String email;

@ManyToOne
@JoinColumn(name="ADDRESS_ID", nullable = true)
private Address addressId;

@Column(name="ACTIVE")
private Boolean active;

@Column(name="CREATE_DATE")
private String createDate;

@Column(name="LAST_UPDATE")
private String lastUpdate;

@OneToMany(mappedBy = "customerId", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
private List<Rental> rental;

@OneToMany(mappedBy = "customerId", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
private List<Payment> payment;

public Integer getCustomerId() {
return this.customerId;
}

public void setCustomerId (Integer customerId) {
this.customerId = customerId;
}

public Store getStoreId() {
      return this.storeId;
}

public void setStoreId(Store storeId) {
      this.storeId = storeId;
}

public String getFirstName() {
      return this.firstName;
}

public void setFirstName(String firstName) {
      this.firstName = firstName;
}

public String getLastName() {
      return this.lastName;
}

public void setLastName(String lastName) {
      this.lastName = lastName;
}

public String getEmail() {
      return this.email;
}

public void setEmail(String email) {
      this.email = email;
}

public Address getAddressId() {
      return this.addressId;
}

public void setAddressId(Address addressId) {
      this.addressId = addressId;
}

public Boolean getActive() {
      return this.active;
}

public void setActive(Boolean active) {
      this.active = active;
}

public String getCreateDate() {
      return this.createDate;
}

public void setCreateDate(String createDate) {
      this.createDate = createDate;
}

public String getLastUpdate() {
      return this.lastUpdate;
}

public void setLastUpdate(String lastUpdate) {
      this.lastUpdate = lastUpdate;
}

public List<Rental> getRental() {
      return this.rental;
}

public void setRental(List<Rental> rental) {
      this.rental = rental;
}

public List<Payment> getPayment() {
      return this.payment;
}

public void setPayment(List<Payment> payment) {
      this.payment = payment;
}

}
