package com.sopl.sopl.service.dto;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class UserInfoDto {

    private Long userId;
    private String email;
    private String nickname;

    public UserInfoDto(Long userId, String email, String nickname) {
        this.userId = userId;
        this.email = email;
        this.nickname = nickname;
    }



}
