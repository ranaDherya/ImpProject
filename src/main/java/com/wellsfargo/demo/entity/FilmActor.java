package com.example.demo.entity;

import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name = "FILM_ACTOR", schema = "test_dbo.dbo")
public class FilmActor {
   @EmbeddedId
  private FilmActorId filmActorId;
@ManyToOne
@MapsId("actorId")
@JoinColumn(name = "ACTOR_ID", nullable = true)
private Actor actorId;

@ManyToOne
@MapsId("filmId")
@JoinColumn(name = "FILM_ID", nullable = true)
private Film filmId;

@Column(name="LAST_UPDATE")
private String lastUpdate;

public FilmActorId getFilmActorId() {
return this.filmActorId;
}

public void setFilmActorId (FilmActorId filmActorId) {
this.filmActorId = filmActorId;
}

public String getLastUpdate() {
      return this.lastUpdate;
}

public void setLastUpdate(String lastUpdate) {
      this.lastUpdate = lastUpdate;
}

}
