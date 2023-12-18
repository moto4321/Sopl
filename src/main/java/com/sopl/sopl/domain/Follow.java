package com.sopl.sopl.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Follow {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "follow_id")
    private User followUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "followed_id")
    private User followedUser;

    /* 연관 관계 메서드 */
    public void setFollowUser(User followUser) {
        this.followUser = followUser;
        followUser.getFollowList().add(this);
    }

    public void setFollowedUser(User followedUser) {
        this.followedUser = followedUser;
        followedUser.getFollowedList().add(this);
    }
}
