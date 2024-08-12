package com.example.demo.entity;

import jakarta.persistence.*;
import java.util.*;

import com.example.demo.entity.Rental;
import com.example.demo.entity.Payment;
@Entity
@Table(name = "STAFF", schema = "test_dbo.dbo")
public class Staff {
@Id
@Column(name="STAFF_ID")
private Integer staffId;

@Column(name="FIRST_NAME")
private String firstName;

@Column(name="LAST_NAME")
private String lastName;

@ManyToOne
@JoinColumn(name="ADDRESS_ID", nullable = true)
private Address addressId;

@Column(name="EMAIL")
private String email;

@ManyToOne
@JoinColumn(name="STORE_ID", nullable = true)
private Store storeId;

@Column(name="ACTIVE")
private Boolean active;

@Column(name="USERNAME")
private String username;

@Column(name="PASSWORD")
private String password;

@Column(name="LAST_UPDATE")
private String lastUpdate;

@OneToMany(mappedBy = "staffId", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
private List<Rental> rental;

@OneToMany(mappedBy = "staffId", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
private List<Payment> payment;

public Integer getStaffId() {
return this.staffId;
}

public void setStaffId (Integer staffId) {
this.staffId = staffId;
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

public Address getAddressId() {
      return this.addressId;
}

public void setAddressId(Address addressId) {
      this.addressId = addressId;
}

public String getEmail() {
      return this.email;
}

public void setEmail(String email) {
      this.email = email;
}

public Store getStoreId() {
      return this.storeId;
}

public void setStoreId(Store storeId) {
      this.storeId = storeId;
}

public Boolean getActive() {
      return this.active;
}

public void setActive(Boolean active) {
      this.active = active;
}

public String getUsername() {
      return this.username;
}

public void setUsername(String username) {
      this.username = username;
}

public String getPassword() {
      return this.password;
}

public void setPassword(String password) {
      this.password = password;
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
