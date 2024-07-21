package com.wellsfargo.demo.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.List;

import com.wellsfargo.demo.entity.Actor;

@Entity
@Table(name = "film")
//@NamedEntityGraph(name = "Film.actors", attributeNodes = @NamedAttributeNode("actors"))
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer filmId;
    @Column
    private String filmName;

    @OneToMany(mappedBy = "film", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Actor> actors;

    // getters and setters
    public List<Actor> getActorList() {
        return actors;
    }

    public void setActorList(List<Actor> actorList) {
        this.actors = actorList;
    }

    public Film() {
    }

    public Integer getFilmId() {
        return filmId;
    }

    public void setFilmId(Integer filmId) {
        this.filmId = filmId;
    }

    public String getFilmName() {
        return filmName;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }
}
