package com.example.demo.entity;

import jakarta.persistence.*;
import java.util.*;

import com.example.demo.entity.FilmActor;
@Entity
@Table(name = "ACTOR", schema = "test_dbo.dbo")
public class Actor {
@Id
@Column(name="ACTOR_ID")
private Integer actorId;

@Column(name="FIRST_NAME")
private String firstName;

@Column(name="LAST_NAME")
private String lastName;

@Column(name="LAST_UPDATE")
private String lastUpdate;

@OneToMany(mappedBy = "actorId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
private List<FilmActor> filmActor;

public Integer getActorId() {
return this.actorId;
}

public void setActorId (Integer actorId) {
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

public List<FilmActor> getFilmActor() {
      return this.filmActor;
}

public void setFilmActor(List<FilmActor> filmActor) {
      this.filmActor = filmActor;
}

}
