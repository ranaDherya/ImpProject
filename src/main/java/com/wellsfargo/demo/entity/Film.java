package com.example.demo.entity;

import jakarta.persistence.*;
import java.util.*;

import com.example.demo.entity.FilmActor;
import com.example.demo.entity.FilmCategory;
import com.example.demo.entity.Inventory;
@Entity
@Table(name = "FILM", schema = "test_dbo.dbo")
public class Film {
@Id
@Column(name="FILM_ID")
private Integer filmId;

@Column(name="TITLE")
private String title;

@Column(name="DESCRIPTION")
private String description;

@Column(name="RELEASE_YEAR")
private String releaseYear;

@ManyToOne
@JoinColumn(name="LANGUAGE_ID", nullable = true)
private Language languageId;

@ManyToOne
@JoinColumn(name="ORIGINAL_LANGUAGE_ID", nullable = true)
private Language originalLanguageId;

@Column(name="RENTAL_DURATION")
private Integer rentalDuration;

@Column(name="RENTAL_RATE")
private String rentalRate;

@Column(name="LENGTH")
private Integer length;

@Column(name="REPLACEMENT_COST")
private String replacementCost;

@Column(name="RATING")
private String rating;

@Column(name="SPECIAL_FEATURES")
private String specialFeatures;

@Column(name="LAST_UPDATE")
private String lastUpdate;

@OneToMany(mappedBy = "filmId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
private List<FilmActor> filmActor;

@OneToMany(mappedBy = "filmId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
private List<FilmCategory> filmCategory;

@OneToMany(mappedBy = "filmId", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
private List<Inventory> inventory;

public Integer getFilmId() {
return this.filmId;
}

public void setFilmId (Integer filmId) {
this.filmId = filmId;
}

public String getTitle() {
      return this.title;
}

public void setTitle(String title) {
      this.title = title;
}

public String getDescription() {
      return this.description;
}

public void setDescription(String description) {
      this.description = description;
}

public String getReleaseYear() {
      return this.releaseYear;
}

public void setReleaseYear(String releaseYear) {
      this.releaseYear = releaseYear;
}

public Language getLanguageId() {
      return this.languageId;
}

public void setLanguageId(Language languageId) {
      this.languageId = languageId;
}

public Language getOriginalLanguageId() {
      return this.originalLanguageId;
}

public void setOriginalLanguageId(Language originalLanguageId) {
      this.originalLanguageId = originalLanguageId;
}

public Integer getRentalDuration() {
      return this.rentalDuration;
}

public void setRentalDuration(Integer rentalDuration) {
      this.rentalDuration = rentalDuration;
}

public String getRentalRate() {
      return this.rentalRate;
}

public void setRentalRate(String rentalRate) {
      this.rentalRate = rentalRate;
}

public Integer getLength() {
      return this.length;
}

public void setLength(Integer length) {
      this.length = length;
}

public String getReplacementCost() {
      return this.replacementCost;
}

public void setReplacementCost(String replacementCost) {
      this.replacementCost = replacementCost;
}

public String getRating() {
      return this.rating;
}

public void setRating(String rating) {
      this.rating = rating;
}

public String getSpecialFeatures() {
      return this.specialFeatures;
}

public void setSpecialFeatures(String specialFeatures) {
      this.specialFeatures = specialFeatures;
}

public String getLastUpdate() {
      return this.lastUpdate;
}

public void setLastUpdate(String lastUpdate) {
      this.lastUpdate = lastUpdate;
}

public List<FilmActor> getFilmActor() {
      return this.filmActor;
}

public void setFilmActor(List<FilmActor> filmActor) {
      this.filmActor = filmActor;
}

public List<FilmCategory> getFilmCategory() {
      return this.filmCategory;
}

public void setFilmCategory(List<FilmCategory> filmCategory) {
      this.filmCategory = filmCategory;
}

public List<Inventory> getInventory() {
      return this.inventory;
}

public void setInventory(List<Inventory> inventory) {
      this.inventory = inventory;
}

}
