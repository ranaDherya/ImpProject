package com.example.demo.dto;

import com.example.demo.entity.Actor;
import java.util.List;
import java.util.stream.Collectors;

public class ActorDTO {
      private Integer actorId;
      private String firstName;
      private String lastName;
      private String lastUpdate;
      private List<FilmActorDTO> filmActor;

      public ActorDTO() {       }

      public static ActorDTO convertToDTO(Actor actor) {
          ActorDTO actorDTO = new ActorDTO();
          actorDTO.setActorId(actor.getActorId());
          actorDTO.setFirstName(actor.getFirstName());
          actorDTO.setLastName(actor.getLastName());
          actorDTO.setLastUpdate(actor.getLastUpdate());

          List<FilmActorDTO> filmActorDTO = actor.getFilmActor().stream().map(FilmActorDTO::convertToDTO).collect(Collectors.toList());
          actorDTO.setFilmActor(filmActorDTO);
          return actorDTO;
       }

      public Actor convertToEntity() {
          Actor actor = new Actor();
          actor.setActorId(this.actorId);
          actor.setFirstName(this.firstName);
          actor.setLastName(this.lastName);
          actor.setLastUpdate(this.lastUpdate);
          actor.setFilmActor(this.filmActor.stream().map(FilmActorDTO::convertToEntity).collect(Collectors.toList()));
          return actor;
       }

public Integer getActorId() {
      return this.actorId;
}

public void setActorId(Integer actorId) {
      this.actorId = actorId;
}

public String getFirstName() {
      return this.firstName;
}

public void setFirstName(String firstName) {
      this.firstName = firstName;
}

public String getLastName() {
      return this.lastName;
}

public void setLastName(String lastName) {
      this.lastName = lastName;
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

}
