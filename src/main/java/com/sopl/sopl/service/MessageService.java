package com.sopl.sopl.service;

import com.sopl.sopl.domain.Message;
import com.sopl.sopl.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MessageService {
    private final MessageRepository messageRepository;

    public List<Message> getDirectMessages(Long senderId, Long receiverId) {
        return messageRepository.findMessages(senderId, receiverId);
    }

    public void sendDirectMessage(Message message) {
        messageRepository.saveMessage(message);
    }

}
