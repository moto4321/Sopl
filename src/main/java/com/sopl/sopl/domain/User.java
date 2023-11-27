package com.sopl.sopl.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;
    private String email;
    private String name;

    @OneToMany(mappedBy = "user")
    private List<Music> musics = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Music> comments = new ArrayList<>();
}
