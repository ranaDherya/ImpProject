package com.example.demo.entity;

import jakarta.persistence.*;
import java.util.*;

import com.example.demo.entity.Rental;
@Entity
@Table(name = "INVENTORY", schema = "test_dbo.dbo")
public class Inventory {
@Id
@Column(name="INVENTORY_ID")
private Integer inventoryId;

@ManyToOne
@JoinColumn(name="FILM_ID", nullable = true)
private Film filmId;

@ManyToOne
@JoinColumn(name="STORE_ID", nullable = true)
private Store storeId;

@Column(name="LAST_UPDATE")
private String lastUpdate;

@OneToMany(mappedBy = "inventoryId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
private List<Rental> rental;

public Integer getInventoryId() {
return this.inventoryId;
}

public void setInventoryId (Integer inventoryId) {
this.inventoryId = inventoryId;
}

public Film getFilmId() {
      return this.filmId;
}

public void setFilmId(Film filmId) {
      this.filmId = filmId;
}

public Store getStoreId() {
      return this.storeId;
}

public void setStoreId(Store storeId) {
      this.storeId = storeId;
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

}
