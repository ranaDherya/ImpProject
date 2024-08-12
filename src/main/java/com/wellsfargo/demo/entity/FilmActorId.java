package com.example.demo.entity;

import java.io.Serializable;
import java.util.Objects;

public class FilmActorId implements Serializable {
private Integer actorId;
private Integer filmId;
public FilmActorId (){}

public FilmActorId (Integer actorId, Integer filmId) {
this.actorId = actorId;
this.filmId = filmId;
}

public Integer getActorId() {
return this.actorId;
}

public Integer getFilmId() {
return this.filmId;
}

public void setActorId (Integer actorId) {
this.actorId=actorId;
}

public void setFilmId (Integer filmId) {
this.filmId=filmId;
}

@Override
public boolean equals(Object o) {
   if (this == o) return true;
   if (o == null || getClass() != o.getClass()) return false;
  FilmActorId that = (FilmActorId) o;
  return Objects.equals(actorId, that.actorId) && Objects.equals(filmId, that.filmId);
}

@Override
public int hashCode() {
  return Objects.hash(actorId, filmId);
}

}