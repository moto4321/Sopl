package com.sopl.sopl.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class Music {

    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String s3_address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "music")
    private List<Comment> comments = new ArrayList<>();

    /* 연관 관계 메서드 */
    public void setUserMusic(User user) {
        this.user = user;
        user.getMusics().add(this);
    }
}
