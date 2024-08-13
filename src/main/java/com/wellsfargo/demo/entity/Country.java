package com.example.demo.entity;

import jakarta.persistence.*;
import java.util.*;

import com.example.demo.entity.City;
@Entity
@Table(name = "COUNTRY", schema = "test_dbo.dbo")
public class Country {
@Id
@Column(name="COUNTRY_ID")
private Integer countryId;

@Column(name="COUNTRY")
private String country;

@Column(name="LAST_UPDATE")
private String lastUpdate;

@OneToMany(mappedBy = "countryId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
private List<City> city;

public Integer getCountryId() {
return this.countryId;
}

public void setCountryId (Integer countryId) {
this.countryId = countryId;
}

public String getCountry() {
      return this.country;
}

public void setCountry(String country) {
      this.country = country;
}

public String getLastUpdate() {
      return this.lastUpdate;
}

public void setLastUpdate(String lastUpdate) {
      this.lastUpdate = lastUpdate;
}

public List<City> getCity() {
      return this.city;
}

public void setCity(List<City> city) {
      this.city = city;
}

}
