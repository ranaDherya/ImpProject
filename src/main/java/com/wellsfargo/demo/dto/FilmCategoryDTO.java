package com.example.demo.dto;

import com.example.demo.entity.FilmCategory;
import java.util.List;
import java.util.stream.Collectors;

public class FilmCategoryDTO {
      private String lastUpdate;

      public FilmCategoryDTO() {       }

      public static FilmCategoryDTO convertToDTO(FilmCategory filmCategory) {
          FilmCategoryDTO filmCategoryDTO = new FilmCategoryDTO();
          filmCategoryDTO.setLastUpdate(filmCategory.getLastUpdate());

          return filmCategoryDTO;
       }

      public FilmCategory convertToEntity() {
          FilmCategory filmCategory = new FilmCategory();
          filmCategory.setLastUpdate(this.lastUpdate);
          return filmCategory;
       }

public String getLastUpdate() {
      return this.lastUpdate;
}

public void setLastUpdate(String lastUpdate) {
      this.lastUpdate = lastUpdate;
}

}
