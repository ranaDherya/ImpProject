package com.example.demo.entity;

import jakarta.persistence.*;
import java.util.*;

import com.example.demo.entity.Inventory;
import com.example.demo.entity.Customer;
import com.example.demo.entity.Staff;
@Entity
@Table(name = "STORE", schema = "test_dbo.dbo")
public class Store {
@Id
@Column(name="STORE_ID")
private Integer storeId;

@Column(name="MANAGER_STAFF_ID")
private Integer managerStaffId;

@ManyToOne
@JoinColumn(name="ADDRESS_ID", nullable = true)
private Address addressId;

@Column(name="LAST_UPDATE")
private String lastUpdate;

@OneToMany(mappedBy = "storeId", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
private List<Inventory> inventory;

@OneToMany(mappedBy = "storeId", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
private List<Customer> customer;

@OneToMany(mappedBy = "storeId", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
private List<Staff> staff;

public Integer getStoreId() {
return this.storeId;
}

public void setStoreId (Integer storeId) {
this.storeId = storeId;
}

public Integer getManagerStaffId() {
      return this.managerStaffId;
}

public void setManagerStaffId(Integer managerStaffId) {
      this.managerStaffId = managerStaffId;
}

public Address getAddressId() {
      return this.addressId;
}

public void setAddressId(Address addressId) {
      this.addressId = addressId;
}

public String getLastUpdate() {
      return this.lastUpdate;
}

public void setLastUpdate(String lastUpdate) {
      this.lastUpdate = lastUpdate;
}

public List<Inventory> getInventory() {
      return this.inventory;
}

public void setInventory(List<Inventory> inventory) {
      this.inventory = inventory;
}

public List<Customer> getCustomer() {
      return this.customer;
}

public void setCustomer(List<Customer> customer) {
      this.customer = customer;
}

public List<Staff> getStaff() {
      return this.staff;
}

public void setStaff(List<Staff> staff) {
      this.staff = staff;
}

}
