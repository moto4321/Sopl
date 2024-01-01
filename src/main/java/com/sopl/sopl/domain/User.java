package com.sopl.sopl.domain;

import lombok.Builder;
import lombok.Getter;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class User {

    @Id
    private Long id;

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    private String nickname;

    @NotEmpty
    private String provider;

//    @NotEmpty
//    private String password;

    @OneToMany(mappedBy = "user")
    private List<Music> musics = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "followUser", cascade = CascadeType.ALL)
    private List<Follow> followList = new ArrayList<>();

    @OneToMany(mappedBy = "followedUser", cascade = CascadeType.ALL)
    private List<Follow> followedList = new ArrayList<>();

    @OneToMany(mappedBy = "sender")
    private List<Message> senderList = new ArrayList<>();

    @OneToMany(mappedBy = "receiver")
    private List<Message> receiverList = new ArrayList<>();

    @Builder
    public User(Long id, String email, String nickname, String provider) {
        Assert.hasText(email, "email은 필수값입니다.");
        Assert.hasText(nickname, "name은 필수값입니다.");
        Assert.hasText(provider, "provider값은 필수값입니다.");
        this.id = id;
        this.email = email;
        this.nickname= nickname;
    }

    public User() {
    }
}
