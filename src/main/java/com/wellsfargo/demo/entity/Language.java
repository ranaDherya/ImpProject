package com.example.demo.entity;

import jakarta.persistence.*;
import java.util.*;

import com.example.demo.entity.Film;
@Entity
@Table(name = "LANGUAGE", schema = "test_dbo.dbo")
public class Language {
@Id
@Column(name="LANGUAGE_ID")
private Integer languageId;

@Column(name="NAME")
private String name;

@Column(name="LAST_UPDATE")
private String lastUpdate;

@OneToMany(mappedBy = "languageId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
private List<Film> film;

public Integer getLanguageId() {
return this.languageId;
}

public void setLanguageId (Integer languageId) {
this.languageId = languageId;
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

public List<Film> getFilm() {
      return this.film;
}

public void setFilm(List<Film> film) {
      this.film = film;
}

}
