package com.sopl.sopl.domain;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@RedisHash("token")
public class AuthToken {

    @Id
    Long userId; // USER id

    String token_JWT;

    @TimeToLive
    Integer expiration;

    @Builder
    public AuthToken (Long userId, String token_JWT, Integer expiration) {
        this.userId = userId;
        this.token_JWT = token_JWT;
        this.expiration = expiration;
    }
}
