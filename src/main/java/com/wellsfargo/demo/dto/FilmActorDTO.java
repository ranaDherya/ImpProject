package com.example.demo.dto;

import com.example.demo.entity.FilmActor;
import java.util.List;
import java.util.stream.Collectors;

public class FilmActorDTO {
      private String lastUpdate;

      public FilmActorDTO() {       }

      public static FilmActorDTO convertToDTO(FilmActor filmActor) {
          FilmActorDTO filmActorDTO = new FilmActorDTO();
          filmActorDTO.setLastUpdate(filmActor.getLastUpdate());

          return filmActorDTO;
       }

      public FilmActor convertToEntity() {
          FilmActor filmActor = new FilmActor();
          filmActor.setLastUpdate(this.lastUpdate);
          return filmActor;
       }

public String getLastUpdate() {
      return this.lastUpdate;
}

public void setLastUpdate(String lastUpdate) {
      this.lastUpdate = lastUpdate;
}

}
