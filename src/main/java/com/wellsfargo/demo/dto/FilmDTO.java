package com.example.demo.dto;

import com.example.demo.entity.Film;
import java.util.List;
import java.util.stream.Collectors;

public class FilmDTO {
      private Integer filmId;
      private String title;
      private String description;
      private String releaseYear;
      private Integer rentalDuration;
      private String rentalRate;
      private Integer length;
      private String replacementCost;
      private String rating;
      private String specialFeatures;
      private String lastUpdate;
      private List<FilmActorDTO> filmActor;
      private List<FilmCategoryDTO> filmCategory;
      private List<InventoryDTO> inventory;

      public FilmDTO() {       }

      public static FilmDTO convertToDTO(Film film) {
          FilmDTO filmDTO = new FilmDTO();
          filmDTO.setFilmId(film.getFilmId());
          filmDTO.setTitle(film.getTitle());
          filmDTO.setDescription(film.getDescription());
          filmDTO.setReleaseYear(film.getReleaseYear());
          filmDTO.setRentalDuration(film.getRentalDuration());
          filmDTO.setRentalRate(film.getRentalRate());
          filmDTO.setLength(film.getLength());
          filmDTO.setReplacementCost(film.getReplacementCost());
          filmDTO.setRating(film.getRating());
          filmDTO.setSpecialFeatures(film.getSpecialFeatures());
          filmDTO.setLastUpdate(film.getLastUpdate());

          List<FilmActorDTO> filmActorDTO = film.getFilmActor().stream().map(FilmActorDTO::convertToDTO).collect(Collectors.toList());
          filmDTO.setFilmActor(filmActorDTO);
          List<FilmCategoryDTO> filmCategoryDTO = film.getFilmCategory().stream().map(FilmCategoryDTO::convertToDTO).collect(Collectors.toList());
          filmDTO.setFilmCategory(filmCategoryDTO);
          List<InventoryDTO> inventoryDTO = film.getInventory().stream().map(InventoryDTO::convertToDTO).collect(Collectors.toList());
          filmDTO.setInventory(inventoryDTO);
          return filmDTO;
       }

      public Film convertToEntity() {
          Film film = new Film();
          film.setFilmId(this.filmId);
          film.setTitle(this.title);
          film.setDescription(this.description);
          film.setReleaseYear(this.releaseYear);
          film.setRentalDuration(this.rentalDuration);
          film.setRentalRate(this.rentalRate);
          film.setLength(this.length);
          film.setReplacementCost(this.replacementCost);
          film.setRating(this.rating);
          film.setSpecialFeatures(this.specialFeatures);
          film.setLastUpdate(this.lastUpdate);
          film.setFilmActor(this.filmActor.stream().map(FilmActorDTO::convertToEntity).collect(Collectors.toList()));
          film.setFilmCategory(this.filmCategory.stream().map(FilmCategoryDTO::convertToEntity).collect(Collectors.toList()));
          film.setInventory(this.inventory.stream().map(InventoryDTO::convertToEntity).collect(Collectors.toList()));
          return film;
       }

public Integer getFilmId() {
      return this.filmId;
}

public void setFilmId(Integer filmId) {
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

public List<FilmActorDTO> getFilmActor() {
      return this.filmActor;
}

public void setFilmActor(List<FilmActorDTO> filmActor) {
      this.filmActor = filmActor;
}

public List<FilmCategoryDTO> getFilmCategory() {
      return this.filmCategory;
}

public void setFilmCategory(List<FilmCategoryDTO> filmCategory) {
      this.filmCategory = filmCategory;
}

public List<InventoryDTO> getInventory() {
      return this.inventory;
}

public void setInventory(List<InventoryDTO> inventory) {
      this.inventory = inventory;
}

}
