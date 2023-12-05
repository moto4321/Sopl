package com.sopl.sopl.service;

import com.sopl.sopl.domain.Message;
import com.sopl.sopl.repository.MessageRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MessageService {
    private MessageRepository messageRepository;

    public List<Message> getDirectMessages(Long senderId, Long receiverId) {
        return messageRepository.findMessages(senderId, receiverId);
    }

}
