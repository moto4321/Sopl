package com.sopl.sopl.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Entity
public class Music {

    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String s3_address;

    @JoinColumn(name = "id")
    private User user;

}
