package com.example.demo.entity;

import jakarta.persistence.*;
import java.util.*;

import com.example.demo.entity.FilmCategory;
@Entity
@Table(name = "CATEGORY", schema = "test_dbo.dbo")
public class Category {
@Id
@Column(name="CATEGORY_ID")
private Integer categoryId;

@Column(name="NAME")
private String name;

@Column(name="LAST_UPDATE")
private String lastUpdate;

@OneToMany(mappedBy = "categoryId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
private List<FilmCategory> filmCategory;

public Integer getCategoryId() {
return this.categoryId;
}

public void setCategoryId (Integer categoryId) {
this.categoryId = categoryId;
}

public String getName() {
      return this.name;
}

public void setName(String name) {
      this.name = name;
}

public String getLastUpdate() {
      return this.lastUpdate;
}

public void setLastUpdate(String lastUpdate) {
      this.lastUpdate = lastUpdate;
}

public List<FilmCategory> getFilmCategory() {
      return this.filmCategory;
}

public void setFilmCategory(List<FilmCategory> filmCategory) {
      this.filmCategory = filmCategory;
}

}
