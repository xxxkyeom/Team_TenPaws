package com.example.tenpaws.domain.chat.chatmessage.service;

import com.example.tenpaws.domain.chat.chatmessage.dto.ChatMessageRequest;
import com.example.tenpaws.domain.chat.chatmessage.dto.ChatMessageResponse;
import com.example.tenpaws.domain.chat.chatmessage.repository.ChatMessageRepository;
import com.example.tenpaws.global.exception.BaseException;
import com.example.tenpaws.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatMessageServiceImpl implements ChatMessageService {
    private final ChatMessageRepository chatMessageRepository;

    @Override
    @Transactional
    public ChatMessageResponse createChatMessage(ChatMessageRequest chatMessageRequest) {
        try {
            return new ChatMessageResponse(chatMessageRepository.save(chatMessageRequest.toEntity()));
        } catch (Exception e) {
            throw new BaseException(ErrorCode.CHAT_MESSAGE_NOT_REGISTERED);
        }
    }

    @Override
    public List<ChatMessageResponse> getChatMessagesByChatRoomId(Long chatRoomId) {
        return chatMessageRepository.findByChatRoomId(chatRoomId).stream().map(ChatMessageResponse::new).toList();
    }
}
