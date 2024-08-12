package com.example.demo.entity;

import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name = "FILM_CATEGORY", schema = "test_dbo.dbo")
public class FilmCategory {
   @EmbeddedId
  private FilmCategoryId filmCategoryId;
@ManyToOne
@MapsId("filmId")
@JoinColumn(name = "FILM_ID", nullable = true)
private Film filmId;

@ManyToOne
@MapsId("categoryId")
@JoinColumn(name = "CATEGORY_ID", nullable = true)
private Category categoryId;

@Column(name="LAST_UPDATE")
private String lastUpdate;

public FilmCategoryId getFilmCategoryId() {
return this.filmCategoryId;
}

public void setFilmCategoryId (FilmCategoryId filmCategoryId) {
this.filmCategoryId = filmCategoryId;
}

public String getLastUpdate() {
      return this.lastUpdate;
}

public void setLastUpdate(String lastUpdate) {
      this.lastUpdate = lastUpdate;
}

}
