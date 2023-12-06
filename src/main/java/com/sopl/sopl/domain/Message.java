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
    @JoinColumn(name = "sender_id")
//    @Column(name = "sender_id")
    private User sender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver_id")
//    @Column(name = "receiver_id")
    private User receiver;

    private String content;

    /* 연관 관계 메서드 */
    public void setSenderMessage(User sender) {
        this.sender = sender;
        sender.getSenderList().add(this);
    }

    public void setReceiverMessage(User receiver) {
        this.receiver = receiver;
        sender.getReceiverList().add(this);
    }
}
