package com.example.demo.entity;

import jakarta.persistence.*;
import java.util.*;

import com.example.demo.entity.Address;
@Entity
@Table(name = "CITY", schema = "test_dbo.dbo")
public class City {
@Id
@Column(name="CITY_ID")
private Integer cityId;

@Column(name="CITY")
private String city;

@ManyToOne
@JoinColumn(name="COUNTRY_ID", nullable = true)
private Country countryId;

@Column(name="LAST_UPDATE")
private String lastUpdate;

@OneToMany(mappedBy = "cityId", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
private List<Address> address;

public Integer getCityId() {
return this.cityId;
}

public void setCityId (Integer cityId) {
this.cityId = cityId;
}

public String getCity() {
      return this.city;
}

public void setCity(String city) {
      this.city = city;
}

public Country getCountryId() {
      return this.countryId;
}

public void setCountryId(Country countryId) {
      this.countryId = countryId;
}

public String getLastUpdate() {
      return this.lastUpdate;
}

public void setLastUpdate(String lastUpdate) {
      this.lastUpdate = lastUpdate;
}

public List<Address> getAddress() {
      return this.address;
}

public void setAddress(List<Address> address) {
      this.address = address;
}

}
