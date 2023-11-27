package com.sopl.sopl.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Message {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    @Column(name = "sender_id")
    private User sender;

    @JoinColumn(name = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    @Column(name = "receiver_id")
    private User receiver;
}
