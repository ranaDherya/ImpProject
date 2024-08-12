package com.example.demo.dto;

import com.example.demo.entity.Language;
import java.util.List;
import java.util.stream.Collectors;

public class LanguageDTO {
      private Integer languageId;
      private String name;
      private String lastUpdate;
      private List<FilmDTO> film;

      public LanguageDTO() {       }

      public static LanguageDTO convertToDTO(Language language) {
          LanguageDTO languageDTO = new LanguageDTO();
          languageDTO.setLanguageId(language.getLanguageId());
          languageDTO.setName(language.getName());
          languageDTO.setLastUpdate(language.getLastUpdate());

          List<FilmDTO> filmDTO = language.getFilm().stream().map(FilmDTO::convertToDTO).collect(Collectors.toList());
          languageDTO.setFilm(filmDTO);
          return languageDTO;
       }

      public Language convertToEntity() {
          Language language = new Language();
          language.setLanguageId(this.languageId);
          language.setName(this.name);
          language.setLastUpdate(this.lastUpdate);
          language.setFilm(this.film.stream().map(FilmDTO::convertToEntity).collect(Collectors.toList()));
          return language;
       }

public Integer getLanguageId() {
      return this.languageId;
}

public void setLanguageId(Integer languageId) {
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

public List<FilmDTO> getFilm() {
      return this.film;
}

public void setFilm(List<FilmDTO> film) {
      this.film = film;
}

}
