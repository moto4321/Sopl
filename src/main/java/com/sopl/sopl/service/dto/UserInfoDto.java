package com.sopl.sopl.service.dto;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class UserInfoDto {

    private Long userId;
    private String email;

    public UserInfoDto(Long userId, String email) {
        this.userId = userId;
        this.email = email;
    }



}
