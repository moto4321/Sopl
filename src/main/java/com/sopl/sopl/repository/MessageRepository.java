package com.sopl.sopl.repository;

import com.sopl.sopl.domain.Message;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MessageRepository {

    private final EntityManager em;

    public List<Message> findMessages(Long senderId, Long receiverId) {
        return em.createQuery(
                "select m" +
                        " from Message m" +
                        " where m.sender_id = :senderId" +
                        " and m.receiver_id = :receiverId", Message.class)
                .setParameter("senderId", senderId)
                .setParameter("receiver_id", receiverId)
                .getResultList();
    }

    public void saveMessage(Message message) {
        if (message.getId() == null) {
            em.persist(message);
        } else {
            em.merge(message);
        }
    }
}
