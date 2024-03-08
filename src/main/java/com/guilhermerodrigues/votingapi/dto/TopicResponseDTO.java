package com.guilhermerodrigues.votingapi.dto;

import com.guilhermerodrigues.votingapi.entity.Topic;

public record TopicResponseDTO(Long id, String content) {
    public TopicResponseDTO(Topic topic) {
        this(
            topic.getId(),
            topic.getContent()
        );
    }
}
