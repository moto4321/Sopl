package com.sopl.sopl.domain;

import jakarta.persistence.*;
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

    @OneToMany(mappedBy = "followUser", cascade = CascadeType.ALL)
    private List<Follow> followList = new ArrayList<>();

    @OneToMany(mappedBy = "followedUser", cascade = CascadeType.ALL)
    private List<Follow> followedList = new ArrayList<>();

    @OneToMany(mappedBy = "sender")
    private List<Message> senderList = new ArrayList<>();

    @OneToMany(mappedBy = "receiver")
    private List<Message> receiverList = new ArrayList<>();
}
