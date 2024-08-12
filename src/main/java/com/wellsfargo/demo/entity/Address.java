package com.example.demo.entity;

import jakarta.persistence.*;
import java.util.*;

import com.example.demo.entity.Customer;
import com.example.demo.entity.Staff;
import com.example.demo.entity.Store;
@Entity
@Table(name = "ADDRESS", schema = "test_dbo.dbo")
public class Address {
@Id
@Column(name="ADDRESS_ID")
private Integer addressId;

@Column(name="ADDRESS")
private String address;

@Column(name="ADDRESS2")
private String address2;

@Column(name="DISTRICT")
private String district;

@ManyToOne
@JoinColumn(name="CITY_ID", nullable = true)
private City cityId;

@Column(name="POSTAL_CODE")
private String postalCode;

@Column(name="PHONE")
private String phone;

@Column(name="LAST_UPDATE")
private String lastUpdate;

@OneToMany(mappedBy = "addressId", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
private List<Customer> customer;

@OneToMany(mappedBy = "addressId", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
private List<Staff> staff;

@OneToMany(mappedBy = "addressId", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
private List<Store> store;

public Integer getAddressId() {
return this.addressId;
}

public void setAddressId (Integer addressId) {
this.addressId = addressId;
}

public String getAddress() {
      return this.address;
}

public void setAddress(String address) {
      this.address = address;
}

public String getAddress2() {
      return this.address2;
}

public void setAddress2(String address2) {
      this.address2 = address2;
}

public String getDistrict() {
      return this.district;
}

public void setDistrict(String district) {
      this.district = district;
}

public City getCityId() {
      return this.cityId;
}

public void setCityId(City cityId) {
      this.cityId = cityId;
}

public String getPostalCode() {
      return this.postalCode;
}

public void setPostalCode(String postalCode) {
      this.postalCode = postalCode;
}

public String getPhone() {
      return this.phone;
}

public void setPhone(String phone) {
      this.phone = phone;
}

public String getLastUpdate() {
      return this.lastUpdate;
}

public void setLastUpdate(String lastUpdate) {
      this.lastUpdate = lastUpdate;
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

public List<Store> getStore() {
      return this.store;
}

public void setStore(List<Store> store) {
      this.store = store;
}

}
