package com.sopl.sopl.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Follow {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private User followUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private User followedUser;

}
