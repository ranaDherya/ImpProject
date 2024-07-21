package com.wellsfargo.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
//@Table(name="actor")
public class Actor {
    @Id
    private Integer actorId;

    @ManyToOne
    @JoinColumn(name = "film_id", insertable = false, updatable = false)
    @JsonBackReference
    private Film film;

    public Actor() {
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public Integer getActorId() {
        return actorId;
    }

    public void setActorId(Integer actorId) {
        this.actorId = actorId;
    }
}
